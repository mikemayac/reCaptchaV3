package com.joel.maya.recaptcha_demo.controller;

import com.joel.maya.recaptcha_demo.service.RecaptchaService;
import com.joel.maya.recaptcha_demo.config.RecaptchaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para manejar las solicitudes de registro.
 *
 * Esta clase es responsable de manejar las solicitudes de registro en la aplicación.
 * La clase se encargará de procesar las solicitudes HTTP, interactuar con otros componentes de la aplicación, como
 * RecaptchaService, y devolver las vistas y respuestas apropiadas.
 */
@Controller
public class RegistrationController {

    private final RecaptchaService recaptchaService;
    private final RecaptchaConfig recaptchaConfig;

    /**
     * Constructor de la clase RegistrationController.
     * Inyectamos la clase RecaptchaService para realizar la validación de reCaptcha.
     * @param recaptchaService el servicio de reCaptcha.
     */
    @Autowired
    public RegistrationController(RecaptchaService recaptchaService, RecaptchaConfig recaptchaConfig) {
        this.recaptchaService = recaptchaService;
        this.recaptchaConfig = recaptchaConfig;
    }

    /**
     * Maneja las solicitudes GET para la página de registro. (al endpint de registro)
     *
     * @param model el modelo de la vista.
     * @return la vista de registro.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Realiza cualquier acción necesaria antes de mostrar el formulario de registro
        // Por ejemplo, añadir atributos al modelo de la vista.
        model.addAttribute("recaptchaSiteKey", recaptchaConfig.getRecaptchaSiteKey());
        return "register";
    }

    /**
     * Maneja las solicitudes POST para la página de registro. (al endpint de registro)
     * valida el token de reCaptcha utilizando el servicio RecaptchaService y, si la validación es exitosa, procesa el
     * registro y redirige a la página de éxito. En caso contrario, muestra un mensaje de error en la página de registro.
     *
     * @param recaptchaResponse la respuesta de reCaptcha generada en el lado del cliente.
     * @param model el modelo de la vista.
     * @return la vista de éxito si el registro es exitoso, de lo contrario la vista de error
     */
    @PostMapping("/register")
    public String processRegistration(@RequestParam(name = "g-recaptcha-response") String recaptchaResponse, Model model) {
        // Valida la respuesta de reCaptcha
        RecaptchaService.RecaptchaValidationResult validationResult = recaptchaService.validateResponse(recaptchaResponse);
        if (!validationResult.isSuccess()) {
            // Maejar el caso cuando la validacion de reCaptcha falla
            model.addAttribute("error", "La validación de reCaptcha falló con una puntuación de" +
                    +validationResult.getScore()+ " Por favor, inténtelo de nuevo.");
            return "register";
        }
        // Procesa el registro (guarda el usuario, envía correo electrónico de confirmación, etc.)
        // ...

        // Redirige a la página de éxito

        return "registration-success";
    }
}
