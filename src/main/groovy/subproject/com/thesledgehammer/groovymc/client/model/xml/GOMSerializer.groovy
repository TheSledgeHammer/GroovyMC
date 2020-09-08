package subproject.com.thesledgehammer.groovymc.client.model.xml

interface GOMSerializer<T> {

    void Serialize(String path, String version, String type);
}