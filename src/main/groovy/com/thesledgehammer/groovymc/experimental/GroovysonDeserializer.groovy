package com.thesledgehammer.groovymc.experimental

import groovy.json.JsonSlurper

interface GroovysonDeserializer<T>  {

    T deserializeJson(JsonSlurper slurpy);

    T deserializeXml(groovy.xml.XmlSlurper slurpy);
}
