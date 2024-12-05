package authorizer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ApplicationServerSocket {

    private static final int PORT = 12345;

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        
        try {
        	
        	ServerSocket serverSocketObj = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT);
            
            while (true) {
              
            	Socket socket = serverSocketObj.accept();           	
            	ThreadSockets thread = new ThreadSockets(socket);
            	thread.start();

            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}