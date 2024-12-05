package authorizer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import authorizer.infrastructure.protocol.ISO8583Request0200;
import authorizer.infrastructure.protocol.ISO8583Response0210;
import authorizer.service.PaymentAuthorizerService;
import authorizer.usecase.PaymentAuthorizer;

public class ThreadSockets  extends Thread {
	
	private Socket socket;
	
	public ThreadSockets(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			
	        PaymentAuthorizerService paymentAuthorizerService = new PaymentAuthorizerService(new PaymentAuthorizer());
			
            // Recebendo a requisição de pagamento
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ISO8583Request0200 isoRequest = (ISO8583Request0200) in.readObject();
            
            // Processando a requisição através do PaymentAuthorizerService
            ISO8583Response0210 isoResponse = paymentAuthorizerService.authorizePayment(isoRequest);
            
            // Enviando a resposta de volta
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(isoResponse);
            
            //fechar streams
            in.close();
            out.close();
            socket.close();
            
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
