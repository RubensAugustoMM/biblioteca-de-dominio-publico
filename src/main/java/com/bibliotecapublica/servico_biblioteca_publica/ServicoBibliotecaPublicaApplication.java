package com.bibliotecapublica.servico_biblioteca_publica;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Autor;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioAutor;

@SpringBootApplication
public class ServicoBibliotecaPublicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoBibliotecaPublicaApplication.class, args);
	}

}
