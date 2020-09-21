package subproject.com.thesledgehammer.groovymc.client.model.gom

interface GOMDeserializer<T> {

    def Deserialize(String path, String version, String type);
}