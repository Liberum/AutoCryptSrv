import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void StartServer() throws IOException {

	System.out.println("Welcome to Server side");
	BufferedReader in = null;

	try {

	    ServerSocket server = null;
	    Socket client;
	    server = new ServerSocket(4567);// слушаем порт 4567
	    client = server.accept();

	    System.out.println("Got client");
	    // PrintWriter out = null;
	    in = new BufferedReader(new InputStreamReader(
		    client.getInputStream()));
	    // out = new PrintWriter(client.getOutputStream(), true);
	    String input;

	    while ((input = in.readLine()) != null) {
		if (input.equalsIgnoreCase("exit")) {
		    break;
		}

		// Ответ клиенту будет в виде Server: сообщение_клиента
		// out.println("Server:" + input);
		System.out.println(input);
	    }

	    // out.close();
	    in.close();
	    client.close();
	    server.close();
	    System.out.println("Server closed");
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

}
