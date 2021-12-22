package crafterqademo.configuration;

import org.aeonbits.owner.Config;

/**
 * API configuration keys/methods based on Owner library
 */
@Config.Sources({"file:src/test/resources/test-variables.properties"})
public interface APIConfig extends Config {
    @Key("hostname")
    String studioHostname();
    @Key("api-path-groups")
    String apiGroups();
    @Key("api-key")
    String apiKey();
}