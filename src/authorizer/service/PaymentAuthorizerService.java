package authorizer.service;

import authorizer.infrastructure.protocol.ISO8583Request0200;
import authorizer.infrastructure.protocol.ISO8583Response0210;
import authorizer.usecase.PaymentAuthorizer;

public class PaymentAuthorizerService {

    private final PaymentAuthorizer paymentAuthorizer;

    public PaymentAuthorizerService(PaymentAuthorizer paymentAuthorizer) {
        this.paymentAuthorizer = paymentAuthorizer;
    }

    public ISO8583Response0210 authorizePayment(ISO8583Request0200 isoRequest) {
        return paymentAuthorizer.processPayment(isoRequest);
    }
    
}