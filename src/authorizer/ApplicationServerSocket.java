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
    private final PaymentAuthorizerService paymentAuthorizerService;

    public ApplicationServerSocket(PaymentAuthorizerService paymentAuthorizerService) {
        this.paymentAuthorizerService = paymentAuthorizerService;
    }

    public static void main(String[] args) {
    	
        PaymentAuthorizerService paymentAuthorizerService = new PaymentAuthorizerService(new PaymentAuthorizer());
        ApplicationServerSocket serverSocket = new ApplicationServerSocket(paymentAuthorizerService);
        
        try (ServerSocket serverSocketObj = new ServerSocket(PORT)) {
        	
            System.out.println("Server listening on port " + PORT);
            
            while (true) {
            	
                try (Socket socket = serverSocketObj.accept();
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                    // Recebendo a requisição de pagamento
                    ISO8583Request0200 isoRequest = (ISO8583Request0200) in.readObject();
                    
                    
                    System.out.println("---PaymentServerSocket-ISO8583Request0200---------------" + isoRequest.getValorTransacao() + "----------PaymentServerSocket-ISO8583Request0200------------------");

                    // Processando a requisição através do PaymentAuthorizerService
                    ISO8583Response0210 isoResponse = serverSocket.paymentAuthorizerService.authorizePayment(isoRequest);
                    
                    
                    System.out.println("---PaymentServerSocket-ISO8583Response0210---------------" + isoResponse.getCodigoResposta() + "----------PaymentServerSocket-ISO8583Response0210------------------");

                    // Enviando a resposta de volta
                    out.writeObject(isoResponse);

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}