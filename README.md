# estuary-web - Java WebSocket end point with Apache Ignite

## Table of Contents 
* [Description] (#description)
* [Architecture and Component Design] (#design)
* [Technologies and APIs] (#technologies-apis)
* [Deployment and Usage] (#deployment-usage

## Description

This project provides WebSocket Server implemention based on JEE spec. webpush.war is a single deployable file into tomcat. Apache Ignite 2.8 is used for message distribuiton across muliple instances of tomcat servers.  

Technologies 

## Component Design

![Alt text](src/doc/design.png "Web Push Design")

## Message Formats

@[Alt text](/webpush/src/docs/messgeformats.txt)

<div w3-include-html="/webpush/src/docs/messgeformats.txt"></div>
