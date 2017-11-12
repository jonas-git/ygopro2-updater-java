import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class Program {
    private static final String LOCAL_CONFIG_FILE = "./updaterconfig.json";

    public static void main(String[] args) {
        UpdaterConfig config = null;

        try {
            config = UpdaterConfig.fromFile(LOCAL_CONFIG_FILE);
        } catch (IOException e) {
            throw new UpdaterException(e, "Failed to open configuration file %s",
                    Paths.get(LOCAL_CONFIG_FILE).normalize().toAbsolutePath());
        }

        System.out.printf("encrypted_token: %s,\n", config.encryptedToken);
        System.out.printf("repositories: [\n");
        for (UpdaterConfig.Repository repo : config.repositories) {
            System.out.println("\t{");
            System.out.printf("\t\towner: %s\n", repo.owner);
            System.out.printf("\t\tname: %s\n", repo.name);
            System.out.printf("\t\tfiles: {\n");
            for (Map.Entry<String, String> file : repo.files.entrySet())
                System.out.printf("\t\t\t\"%s\": \"%s\",\n", file.getKey(), file.getValue());
            System.out.printf("\t\t]\n");
            System.out.println("\t},");
        }
        System.out.printf("]\n");

        System.out.printf("Decrypted token: %s\n", new String(config.decryptToken()));
    }
}
