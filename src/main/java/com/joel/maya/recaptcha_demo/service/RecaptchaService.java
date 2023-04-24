package com.joel.maya.recaptcha_demo.service;

/**
 * La clase RecaptchaService es responsable de validar las respuestas de reCaptcha en el lado del servidor.
 * Esta clase se comunicará con la API de reCaptcha de Google utilizando las claves del sitio y las claves secretas
 * almacenadas en la clase RecaptchaConfig.
 * @author Joel Maya
 * @version 1.0
 */

import com.joel.maya.recaptcha_demo.config.RecaptchaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio para validar las respuestas de reCaptcha en el lado del servidor.
 * Esta clase se encarga de comunicarse con la API de reCaptcha de Google para validar los tokens de reCaptcha en el
 * lado del servidor.
 */
@Service
public class RecaptchaService {

    private final RecaptchaConfig recaptchaConfig;

    /**
     * Constructor de la clase RecaptchaService.
     * Inyectamos la clase RecaptchaConfig para acceder a las propiedades de reCaptcha.
     * @param recaptchaConfig la configuración de reCaptcha.
     */
    @Autowired
    public RecaptchaService(RecaptchaConfig recaptchaConfig) {
        this.recaptchaConfig = recaptchaConfig;
    }

    /**
     * Verifica si una respuesta de reCaptcha es válida.
     *
     * El método isResponseValid() toma la respuesta de reCaptcha generada en el lado del cliente como argumento y se
     * comunica con la API de reCaptcha de Google para verificar si la respuesta es válida.
     *
     * @param recaptchaResponse la respuesta de reCaptcha generada en el lado del cliente.
     * @return true si la respuesta es válida, false en caso contrario.
     */
    public boolean isResponseValid(String recaptchaResponse) { //verify()
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://www.google.com/recaptcha/api/siteverify";
        String secretKey = recaptchaConfig.getRecaptchaSecretKey();

        Map<String, String> params = new HashMap<>();
        params.put("secret", secretKey);
        params.put("response", recaptchaResponse);

        ResponseEntity<RecaptchaValidationResponse> response = restTemplate.postForEntity(apiUrl, params, RecaptchaValidationResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().isSuccess();
        } else {
            // Manejar errores de comunicación con la API de reCaptcha
            return false;
        }
    }
}
