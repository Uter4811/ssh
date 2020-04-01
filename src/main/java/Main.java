import org.yaml.snakeyaml.Yaml;

public class Main {

    public static void main(String[] args) {
        ConfigService configService = new ConfigService();
        ConfigService.Config config = configService.readConfig();
        for (Server server : config.servers) {
            
        }

    }
}