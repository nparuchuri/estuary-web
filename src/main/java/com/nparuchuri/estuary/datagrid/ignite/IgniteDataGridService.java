package com.nparuchuri.estuary.datagrid.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.IgniteMessaging;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.events.EventType;
import org.apache.ignite.logger.log4j2.Log4J2Logger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.kubernetes.TcpDiscoveryKubernetesIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.nparuchuri.estuary.web.datagrid.DataGridMessage;
import com.nparuchuri.estuary.web.datagrid.DataGridService;
import com.nparuchuri.estuary.web.msg.WebMessage;
import com.nparuchuri.estuary.web.resource.WebResourceConfig;
import com.nparuchuri.estuary.web.server.ServerInstanceInfo;

/**
 * 
 * @author Narendra
 *
 */

public class IgniteDataGridService implements DataGridService {

	private static Logger logger = LogManager.getLogger(IgniteDataGridService.class);

	private Ignite ignite;

	private IgniteMessaging igniteMessaging;

	private static IgniteDataGridService itsInstance;

	private Gson gson;

	/**
	 * 
	 */
	private IgniteDataGridService() {
		this.gson = new Gson();
	}

	/**
	 * 
	 * @return
	 */
	public static IgniteDataGridService get() {
		if (itsInstance == null) {
			itsInstance = new IgniteDataGridService();
		}

		return itsInstance;
	}

	@Override
	public void init() {
		
		IgniteConfiguration icfg = new IgniteConfiguration();
		setDiscoverySPI(icfg);

		CacheConfiguration<String, String> ccfg = new CacheConfiguration<String, String>("MESSAGES");
		ccfg.setCacheMode(CacheMode.REPLICATED);

		icfg.setIncludeEventTypes(EventType.EVT_CACHE_OBJECT_PUT);
		icfg.setCacheConfiguration(ccfg);
		icfg.setMetricsLogFrequency(5 * 60 * 1000);

		IgniteLogger log = null;
		try {
			String file = getClass().getClassLoader().getResource("log4j2.xml").getFile();
			log = new Log4J2Logger(file);
		} catch (IgniteCheckedException e) {
			System.out.println("init ignite log config failed, Error loading log4j2.xml file ");
			e.printStackTrace(System.out);

		}
		icfg.setGridLogger(log);

		System.setProperty("IGNITE_QUIET", "false");
		System.setProperty("java.net.preferIPv4Stack", "true");

		// Start Ignite node.
		this.ignite = Ignition.start(icfg);

		this.igniteMessaging = ignite.message();
		this.igniteMessaging.localListen("EASYPUSH", new IgniteMessageListener(this.ignite.cluster().localNode().id()));
	}

	private void setDiscoverySPI(IgniteConfiguration icfg) {
		if ( isMultiCastKookup() ) {
			TcpDiscoverySpi spi = new TcpDiscoverySpi();
			TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
			ipFinder.setMulticastGroup("239.255.255.255");
			spi.setIpFinder(ipFinder);
			// Override default discovery SPI.
			icfg.setDiscoverySpi(spi);
		}
		else if ( isK8Kookup() ) {
			TcpDiscoverySpi spi = new TcpDiscoverySpi();
			TcpDiscoveryKubernetesIpFinder ipFinder = new TcpDiscoveryKubernetesIpFinder();
			ipFinder.setNamespace("estuary");
			ipFinder.setServiceName("estuary-discovery-service");
			spi.setIpFinder(ipFinder);
			icfg.setDiscoverySpi(spi);
		}
	}

	@Override
	public void destory() {
		Ignition.stop(false);

	}

	@Override
	public void put(WebMessage webPushMessage) {
		DataGridMessage dgMessage = new DataGridMessage(ServerInstanceInfo.get(), webPushMessage);
		dgMessage.setDataGridNodeId(this.ignite.cluster().localNode().id());
		String message = this.gson.toJson(dgMessage);
		logger.info("Inserting message into data grid " + message);
		this.igniteMessaging.send("EASYPUSH", message);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isK8Kookup() {
		String value = WebResourceConfig.get().getProperty("datagrid.ignite.iplookup");
		if ( value != null && value.equalsIgnoreCase("K8")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isMultiCastKookup() {
		String value = WebResourceConfig.get().getProperty("datagrid.ignite.iplookup");
		if ( value != null && value.equalsIgnoreCase("MC")) {
			return true;
		}
		return false;
	}

}
