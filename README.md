# Web Site Monitoring Tool

[![Build Status](https://travis-ci.org/web-site-monitoring-tool/web-site-monitoring-tool.svg?branch=master)](https://travis-ci.org/web-site-monitoring-tool/web-site-monitoring-tool)
[![Coverage Status](https://coveralls.io/repos/github/web-site-monitoring-tool/web-site-monitoring-tool/badge.svg?branch=master)](https://coveralls.io/github/web-site-monitoring-tool/web-site-monitoring-tool?branch=master)

## Goal

The goal is to build a tool for monitoring audience activity of website. This service should provide website statistics to website administrator.

## Function requirements

### Main

1. Website statistics should contain statistics by browsers, statistics by countries, statistics by day of week, statistics by time of day, statistics by website pages
2. Website statistics should be updated automatically every hour
3. Every user have opportunity to read statistics that are shared with him
4. Every authenticated user have opportunity to add his website
5. Every authenticated user have opportunity to share statistics of his website with anyone user
6. Every authenticated user have opportunity to share statistics of his website with each authenticated user
7. Every authenticated user have opportunity to share statistics of his website with everyone
8. Service should provide simple static web-console

## Technologies

### Server

* Language: Java
* Database: any sql database, Apache HBase
* Build system: Apache Maven
* Frameworks: Spring Boot, Spring WebFlux, Spring Data, Apache Spark
* Environment: Docker

### Web-console

* Language: JS
* Frameworks: Bootstrap
