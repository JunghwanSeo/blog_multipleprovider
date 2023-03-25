package net.plorus.demo.multipleprovider.platform.amanda.controller;

import net.plorus.demo.multipleprovider.config.security.principal.AmandaPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amanda/")
public class AmandaAccountController {
    @GetMapping("/principal")
    public AmandaPrincipal getPrincipal(@AuthenticationPrincipal AmandaPrincipal amandaPrincipal) {
        return amandaPrincipal;
    }
}
