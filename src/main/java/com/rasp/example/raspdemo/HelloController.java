package com.rasp.example.raspdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/security")
public class HelloController {

    // 1. SQL Injection
    @GetMapping("/sql-injection")
    public String sqlInjection(@RequestParam String user) {
        // Potencjalne zapytanie SQL z wykorzystaniem parametru
        String sql = "SELECT * FROM users WHERE username = '" + user + "'"; // SQL Injection
        return "Executing: " + sql; // Nie należy stosować w produkcji
    }

    // 2. Cross-Site Scripting (XSS)
    @GetMapping("/xss")
    public String crossSiteScripting(@RequestParam String script) {
        return "<div>" + script + "</div>"; // Potencjalne XSS
    }

    // 3. Cross-Site Request Forgery (CSRF)
    @PostMapping("/csrf")
    public String crossSiteRequestForgery(@RequestBody Map<String, String> data) {
        return "Received data: " + data; // Może być wrażliwy na CSRF
    }

    // 4. Security Misconfiguration
    @GetMapping("/misconfiguration")
    public String securityMisconfiguration() {
        // Potencjalna luka przez ujawnienie informacji o systemie
        return "This is example response for security misconfiguration.";
    }

    // 5. Sensitive Data Exposure
    @GetMapping("/sensitive-data")
    public Map<String, String> sensitiveDataExposure() {
        Map<String, String> sensitiveData = new HashMap<>();
        sensitiveData.put("username", "user123");
        sensitiveData.put("password", "pass123"); // Ujawnić poufne dane - NIE UŻYWAĆ W PRODUKCJI
        return sensitiveData;
    }

    // 6. Broken Authentication
    @PostMapping("/broken-auth")
    public String brokenAuthentication(@RequestParam String username, @RequestParam String password) {
        return "Logged in as " + username; // Prosty mechanizm autoryzacji
    }

    // 7. Insecure Deserialization
    @PostMapping("/insecure-deserialization")
    public String insecureDeserialization(@RequestBody String serializedObject) {
        return "Deserialized object: " + serializedObject; // Potencjalnie niebezpieczne
    }

    // 8. Insufficient Logging & Monitoring
    @GetMapping("/insufficient-logging")
    public String insufficientLogging() {
        return "This endpoint lacks logging!";
    }

    // 9. Using Components with Known Vulnerabilities
    @GetMapping("/vulnerable-components")
    public String vulnerableComponents() {
        return "This endpoint demonstrates using components with known vulnerabilities. Check your dependencies for vulnerabilities."; // Testowanie zależności
    }

    // 10. Unvalidated Redirects and Forwards
    @GetMapping("/unvalidated-redirect")
    public String unvalidatedRedirect(@RequestParam String url) {
        // Potencjalny błąd: Umożliwienie przekierowania do zewnętrznych URL-i bez walidacji
        return "Redirecting to: " + url; // Może prowadzić do otwarcia niebezpiecznego URL
    }
}
