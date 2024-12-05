package authorizer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import authorizer.infrastructure.protocol.ISO8583Request0200;
import authorizer.infrastructure.protocol.ISO8583Response0210;
import authorizer.service.PaymentAuthorizerService;
import authorizer.usecase.PaymentAuthorizer;

public class ApplicationServerSocket {

    private static final int PORT = 12345;

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