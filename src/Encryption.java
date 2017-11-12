public interface Encryption {
    String encrypt(char[] data) throws UpdaterException;
    char[] decrypt(String data) throws UpdaterException;
}
