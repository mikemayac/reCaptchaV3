package com.joel.maya.recaptcha_demo.service;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que represente la estructura de la respuesta de la API de reCaptcha.
 *
 * Se pueden agregar campos adicionales según la documentación de la API de reCaptcha si se requiere información adicional
 * sobre la respuesta.
 *
 * Se usa la biblioteca Jackson para deserializar automáticamente la respuesta JSON en un objeto de esta clase.
 */

public class RecaptchaValidationResponse {

    @JsonProperty("success")
    private boolean success;

    // ... podemos añadir otros campos de la respuesta de reCapctha si son necesarios.

    /**
     * Devuelve true si la respuesta de reCaptcha es válida, false en caso contrario.
     * @return true si la respuesta de reCaptcha es válida, false en caso contrario.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Establece si la respuesta de reCaptcha es válida.
     * @param success true si la respuesta de reCaptcha es válida, false en caso contrario.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    // ... getters y setters para otros campos de la respuesta de reCapctha si son necesarios.
}
