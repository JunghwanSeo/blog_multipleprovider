package net.plorus.demo.multipleprovider.platform.amanda.controller.client;

import net.plorus.demo.multipleprovider.config.security.credential.AmandaCredential;
import net.plorus.demo.multipleprovider.platform.amanda.controller.client.dto.AmandaInternalResAuthentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AmandaClient {
    public AmandaInternalResAuthentication getAuthenticationInformation(AmandaCredential amandaCredential) {
        return new AmandaInternalResAuthentication(
                "Amanda",
                UUID.randomUUID().toString(),
                true,
                "20231212180000",
                UUID.randomUUID().toString());
    }
}
