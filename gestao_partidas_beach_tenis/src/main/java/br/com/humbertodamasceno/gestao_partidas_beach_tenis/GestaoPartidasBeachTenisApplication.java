package br.com.humbertodamasceno.gestao_partidas_beach_tenis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.humbertodamasceno.gestao_partidas_beach_tenis")
public class GestaoPartidasBeachTenisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoPartidasBeachTenisApplication.class, args);
	}

}
