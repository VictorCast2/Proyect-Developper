package com.App.Proyect_Developper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyectDevelopperApplication implements CommandLineRunner {

	/**
	 *	Integrantes: Víctor José Castillo Castro Y Sebastian Andrés Montero Beltran
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProyectDevelopperApplication.class, args);
	}

	/**
	 * Método que se ejecuta al iniciar la aplicación
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "admin";
		String encodedPassword = encoder.encode(rawPassword);
		System.out.println("Contraseña encriptada: " + encodedPassword);
	}

}