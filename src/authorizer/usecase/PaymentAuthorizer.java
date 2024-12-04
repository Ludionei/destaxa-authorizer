package authorizer.usecase;

import java.util.UUID;

import authorizer.application.PaymentProcessor;
import authorizer.infrastructure.protocol.ISO8583Request0200;
import authorizer.infrastructure.protocol.ISO8583Response0210;

public class PaymentAuthorizer implements PaymentProcessor {

    private static final String APPROVED = "00";
    private static final String DENIED = "05";
    private static final String TIMEOUT = "99";
    private static final double MAX_TRANSACTION_AMOUNT = 1000.00;

    @Override
    public ISO8583Response0210 processPayment(ISO8583Request0200 request) {
        
        ISO8583Response0210 response = new ISO8583Response0210();
        
        response.setNsuHost(UUID.randomUUID().toString());
        response.setCodigoAutorizacao("123456");

        double valorTransacao = Double.parseDouble(request.getValorTransacao());

        if (valorTransacao > MAX_TRANSACTION_AMOUNT) {
        	response.setCodigoResposta(TIMEOUT);
        } else if (valorTransacao < 0) {
            response.setCodigoResposta(DENIED);
        } else if (valorTransacao > 0) {
            response.setCodigoResposta(APPROVED);
        } else {
            response.setCodigoResposta("01");
        }

        return response;
    }
    
}