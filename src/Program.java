import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Program {
    private static String readManifestValue(String name) {
        String jarPath = Program.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        try {
            JarFile jarFile = new JarFile(jarPath);
            Manifest manifest = jarFile.getManifest();
            return manifest.getMainAttributes().getValue(name);
        }
        catch (IOException e) {
            throw new UpdaterException(e, "Failed to access the manifest of %s", jarPath);
        }
    }

    public static void main(String[] args) {
        System.out.println(readManifestValue("GitHub-Token"));
    }
}
