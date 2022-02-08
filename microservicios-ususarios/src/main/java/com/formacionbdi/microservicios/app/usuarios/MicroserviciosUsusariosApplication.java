package com.formacionbdi.microservicios.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.formacionbdi.microservicios.commons.alumnos.entity"})
public class MicroserviciosUsusariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosUsusariosApplication.class, args);
	}

}
