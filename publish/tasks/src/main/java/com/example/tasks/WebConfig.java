package com.example.tasks;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    
    
    public void addCorsMappings(CorsRegistry registry) {
        // Mapeia todas as rotas e permite requisições do localhost:5173
    	 registry.addMapping("/**")
         .allowedOrigins("http://localhost:5173")  // Permite requisições da origem localhost:5173
         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
         .allowedHeaders("*")  // Cabeçalhos permitidos
         .allowCredentials(true);  // Permite o uso de credenciais como cookies ou autenticação básica
}

          
    }

