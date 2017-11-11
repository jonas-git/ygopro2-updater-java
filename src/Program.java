import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Program {
    private static final String PROPERTIES_FILENAME = "resources/updater.properties";

    private static String readManifestValue(String name) {
        String jarName = Program.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        try {
            JarFile jarFile = new JarFile(jarName);
            Manifest manifest = jarFile.getManifest();
            return manifest.getMainAttributes().getValue(name);
        }
        catch (IOException e) {
            throw new UpdaterException(e, "Failed to access the manifest of %s", jarName);
        }
    }

    public static void main(String[] args) {
        System.out.println(readManifestValue("GitHub-Token"));
    }
}
