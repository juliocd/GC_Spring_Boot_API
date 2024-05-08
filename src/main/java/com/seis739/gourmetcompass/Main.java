package com.seis739.gourmetcompass;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class Main {
    @GetMapping(path = "/")
    public @ResponseBody String health() {
        return "Server listening on port 8080";
    }

    @GetMapping(path = "/app")
    public @ResponseBody String appHealth() {
        return "Gourmet Compass API is listening on port 8080";
    }
}
