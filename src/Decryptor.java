import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class Decryptor {

    public void decpt() {

	System.out.println("Welcome to Server side");
	Reader in = null;

	try {

	    ServerSocket server = null;
	    Socket client;
	    server = new ServerSocket(4567);// слушаем порт 4567
	    client = server.accept();

	    System.out.println("Got client");

	    // in = new InputStreamReader(client.getInputStream());

	    InputStream is = client.getInputStream();
	    // is = null;

	    while (is != null) {

		FileInputStream fis = null;
		try {
		    String keyfile = "c:\\AutoCrypt\\key.key";
		    String algorithm = "DESede";

		    fis = new FileInputStream(keyfile);
		    byte[] keyspecbytes = new byte[fis.available()];
		    fis.read(keyspecbytes);
		    SecretKeyFactory skf = SecretKeyFactory
			    .getInstance(algorithm);
		    DESedeKeySpec keyspec = new DESedeKeySpec(keyspecbytes);
		    SecretKey key = skf.generateSecret(keyspec);
		    Cipher cipher = Cipher.getInstance(algorithm);
		    cipher.init(Cipher.DECRYPT_MODE, key);
		    ObjectInputStream ois = new ObjectInputStream(
			    new CipherInputStream(is, cipher));
		    // String secret = (String) ois.readObject();

		    // расшифровываем лист
		    List<String> secret1 = (List<String>) ois.readObject();
		    // запускаем с параметрами с листа
		    // RunDisk run = new RunDisk();
		    // run.mountDskCpt(secret1);

		    StringBuilder sb = new StringBuilder();
		    String[] array = (String[]) secret1.toArray();
		    for (int i = 0; i < array.length; i++) {
			sb.append(array[i] + " ");
		    }
		    String secret2 = sb.toString();

		    int n = secret1.size();

		    // System.out.println(secret2 + "list size = " + n);
		    fis.close();
		    ois.close();
		    is.close();

		} catch (Exception e) {
		    e.printStackTrace();
		}

	    }
	    in.close();
	    client.close();
	    server.close();

	    System.out.println("Server closed");
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

}
