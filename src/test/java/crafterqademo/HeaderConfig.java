package crafterqademo;

import java.util.HashMap;
import java.util.Map;

import static crafterqademo.configuration.ConfigurationManager.APIConfiguration;

public class HeaderConfig {

    public String baseURI = APIConfiguration().studioHostname();
    public String apiToken = APIConfiguration().apiKey();
    public String apiGroups = APIConfiguration().apiGroups();

    /*
     * Method for header configuration for CrafterCMS APIs testing*/
    public Map<String, String> defaultHeader (){
        Map<String, String> defaultHeaders = new HashMap<String, String>();
        defaultHeaders.put("Authorization", apiToken);
        defaultHeaders.put("Content-Type", "application/json");
        return defaultHeaders;
    }
}
