package crafterqademo.configuration;
import org.aeonbits.owner.ConfigCache;

/**
 * Configuration manager using Owner library
 */
public class ConfigurationManager {

    private ConfigurationManager() {
    }

    public static APIConfig APIConfiguration() {
        return ConfigCache.getOrCreate(APIConfig.class);
    }
}