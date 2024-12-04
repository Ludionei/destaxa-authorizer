package authorizer.application;

import authorizer.infrastructure.protocol.ISO8583Request0200;
import authorizer.infrastructure.protocol.ISO8583Response0210;

public interface PaymentProcessor {
    public ISO8583Response0210 processPayment(ISO8583Request0200 request);
}