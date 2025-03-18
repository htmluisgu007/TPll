package br.edu.fatecpg.tp.api.Api;

import br.edu.fatecpg.tp.api.Api.service.ConsomeApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		ConsomeApi consomeApi = new ConsomeApi();

		Scanner scan = new Scanner(System.in);

		String json = consomeApi.lerjson("https://parallelum.com.br/fipe/api/v1/carros/marcas");
		System.out.println(json);

		System.out.println("Digite o código da marca do carro desejado: ");
		String marca = scan.nextLine();
		json = consomeApi.lerjson( "https://parallelum.com.br/fipe/api/v1/carros/marcas/"+marca+"/modelos");
		System.out.println(json);

		System.out.println("Digite o código do modelo do carro desejado: ");
		String modelo = scan.nextLine();
		json =  consomeApi.lerjson("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+marca+"/modelos/"+modelo+"/anos");
		System.out.println(json);

		System.out.println("Digite o ano do carro desejado (YYYY):  ");
		String ano = scan.nextLine();
		json = consomeApi.lerjson("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+marca+"/modelos/"+modelo+"/anos/"+ano+"");
		System.out.println(json);
	}

}