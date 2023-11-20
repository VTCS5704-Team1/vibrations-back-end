package com.vibrations.vibrationsapi;

import com.vibrations.vibrationsapi.security.JwtDecoderConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JwtDecoderConfiguration.class)
public class VibrationsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VibrationsApiApplication.class, args);
	}

}
