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
     * @param recaptchaResponse la respuesta de reCaptcha generada en el lado del cliente.
     * @return RecaptchaValidationResult con información sobre la validación y la puntuación obtenida.
     */
    public RecaptchaValidationResult validateResponse(String recaptchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://www.google.com/recaptcha/api/siteverify";
        String secretKey = recaptchaConfig.getRecaptchaSecretKey();

        Map<String, String> params = new HashMap<>();
        params.put("secret", secretKey);
        params.put("response", recaptchaResponse);

        ResponseEntity<RecaptchaValidationResponse> response = restTemplate.postForEntity(apiUrl, params, RecaptchaValidationResponse.class);
        RecaptchaValidationResult validationResult = new RecaptchaValidationResult();

        if (response.getStatusCode().is2xxSuccessful()) {
            validationResult.setSuccess(response.getBody().isSuccess());
            validationResult.setScore(response.getBody().getScore());
        } else {
            // Manejar errores de comunicación con la API de reCaptcha
            validationResult.setSuccess(false);
            validationResult.setScore(0);
        }

        return validationResult;
    }

    // Clase para almacenar el resultado de la validación de reCAPTCHA
    public static class RecaptchaValidationResult {
        private boolean success;
        private float score;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }
    }
}
