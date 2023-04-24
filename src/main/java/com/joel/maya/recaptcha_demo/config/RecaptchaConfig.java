package com.joel.maya.recaptcha_demo.config;

/**
 * La clase RecaptchaConfig se utilizará para almacenar las propiedades de reCaptcha,
 * como la clave del sitio y la clave secreta, que se utilizarán para interactuar con el servicio de reCaptcha.
 * Utilizaremos la característica de inyección de propiedades de Spring para cargar estas propiedades desde el archivo de
 * configuración application.properties
 *
 * @author Joel Maya
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para almacenar las propiedades de reCaptcha.
 *
 * Esta clase se encarga de cargar las propiedades de reCaptcha desde el archivo
 * de configuración y ponerlas a disposición de los componentes de la aplicación
 * que necesitan interactuar con el servicio de reCaptcha.
 */

@Configuration
public class RecaptchaConfig {

    /**
     * La clave del sitio para reCaptcha, utilizada en el lado del cliente
     * para generar el token de reCaptcha.
     */
    @Value("${recaptcha.site-key}")
    private String recaptchaSiteKey;

    /**
     * La clave secreta para reCaptcha, utilizada en el lado del servidor
     * para validar el token de reCaptcha.
     */
    @Value("${recaptcha.secret-key}")
    private String recaptchaSecretKey;

    /**
     * Obtiene la clave del sitio para reCaptcha.
     *
     * @return la clave del sitio
     */
    public String getRecaptchaSiteKey(){
        return recaptchaSiteKey;
    }

    /**
     * Obtiene la clave secreta para reCaptcha.
     *
     * @return la clave secreta
     */
    public String getRecaptchaSecretKey(){
        return recaptchaSecretKey;
    }
}
