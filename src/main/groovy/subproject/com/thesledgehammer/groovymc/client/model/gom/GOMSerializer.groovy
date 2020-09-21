package subproject.com.thesledgehammer.groovymc.client.model.gom

interface GOMSerializer<T> {

    void Serialize(String path, String version, String type);
}