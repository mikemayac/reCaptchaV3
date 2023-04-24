package com.joel.maya.recaptcha_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad web para la aplicación.
 *
 * Esta clase define las reglas de seguridad para la aplicación, como qué rutas
 * están protegidas, qué rutas son accesibles sin autenticación y cómo se maneja
 * la autenticación en sí.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Crea y configura un filtro de seguridad para la aplicación.
     *
     *  Definimos un bean SecurityFilterChain utilizando el método securityFilterChain().
     *  Este método toma un objeto HttpSecurity como argumento, que utilizamos para configurar las reglas de seguridad
     *  de la misma manera que lo hicimos con WebSecurityConfigurerAdapter. Al final del método, llamamos a http.build()
     *  para construir el SecurityFilterChain y devolverlo.
     *
     * @return el objeto SecurityFilterChain que define las reglas de seguridad para la aplicación.
     * @throws Exception si ocurre un error al crear el filtro de seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        return http.build();
    }

}
