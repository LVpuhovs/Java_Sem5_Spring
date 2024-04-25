package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class Seminar_5_spring {

public static void main(String[] args) {
SpringApplication.run(Seminar_5_spring.class, args);
}
	@Bean
	public CommandLineRunner testDatabaseLayer(IProductRepo productRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				Product p1 = new Product("Abols", "Zals", 0.69f, 5);
				Product p2 = new Product("Burvis", "Zils", 1.5f, 1);
				Product p3 = new Product("Zigis", "Brivais", 6f, 2);
				productRepo.save(p1);
				productRepo.save(p2);
				productRepo.save(p3);
			}
		};
		
	}
}
