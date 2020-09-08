package subproject.com.thesledgehammer.groovymc.client.model.xml

interface GOMDeserializer<T> {

    def Deserialize(String path, String version, String type);
}