package com.thesledgehammer.groovymc.experimental

import groovy.json.JsonSlurper
import subproject.com.thesledgehammer.groovymc.client.model.xml.SuperGOM

interface GroovysonDeserializer<T>  {

    T deserializeJson(SuperGOM slurpy);

    T deserializeXml(groovy.xml.XmlSlurper slurpy);
}
