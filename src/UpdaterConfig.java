import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class UpdaterConfig {
    public class Repository {
        String owner, name;
        Map<String, String> files;
    }

    String encryptedToken;
    List<Repository> repositories;

    public static UpdaterConfig fromFile(String filename) throws IOException {
        return fromFile(new File(filename));
    }

    public static UpdaterConfig fromFile(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(stream);

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return gson.fromJson(reader, UpdaterConfig.class);
    }

    public char[] decryptToken() {
        return new UpdaterEncryption().decrypt(this.encryptedToken);
    }
}
