package com.joel.maya.recaptcha_demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para almacenar las propiedades de reCaptcha.
 *
 * Esta clase se encarga de cargar las propiedades de reCaptcha desde el archivo
 * de configuración (application.properties) y ponerlas a disposición de los componentes de la aplicación
 * que necesitan interactuar con el servicio de reCaptcha.
 */

@Configuration
public class RecaptchaConfig {

    /**
     * La clave del sitio para reCaptcha, utilizada en el lado del cliente
     * para generar el token de reCaptcha.
     */
    @Value("${recaptcha.site-key}")
    private String siteKey;

    /**
     * La clave secreta para reCaptcha, utilizada en el lado del servidor
     * para validar el token de reCaptcha.
     */
    @Value("${recaptcha.secret-key}")
    private String secretKey;

    /**
     * Obtiene la clave del sitio para reCaptcha.
     *
     * @return la clave del sitio
     */
    public String getSiteKey(){
        return siteKey;
    }

    /**
     * Obtiene la clave secreta para reCaptcha.
     *
     * @return la clave secreta
     */
    public String getSecretKey(){
        return secretKey;
    }
}
