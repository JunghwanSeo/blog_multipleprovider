package net.plorus.demo.multipleprovider.platform.drake.controller.client;

import net.plorus.demo.multipleprovider.config.security.credential.DrakeCredential;
import net.plorus.demo.multipleprovider.platform.drake.controller.client.dto.DrakeInternalResAuthentication;
import org.springframework.stereotype.Component;

@Component
public class DrakeClient {
    public DrakeInternalResAuthentication getAuthenticationInformation(DrakeCredential drakeCredential) {
        return new DrakeInternalResAuthentication(
                "Drake",
                1194L,
                "Drake.inc",
                "20231212000000");
    }
}
