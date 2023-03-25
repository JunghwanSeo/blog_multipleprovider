package net.plorus.demo.multipleprovider.platform.drake.controller;

import net.plorus.demo.multipleprovider.config.security.principal.DrakePrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drake/")
public class DrakeAccountController {
    @GetMapping("/principal")
    public DrakePrincipal getPrincipal(@AuthenticationPrincipal DrakePrincipal drakePrincipal) {
        return drakePrincipal;
    }
}
