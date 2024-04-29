package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{
	//publiskas un abstrakt
		Product findByTitleAndDescriptionAndPrice(String title, String description, float price);

		ArrayList<Product> findByPriceLessThanEqual(float priceThreshold);
		
		ArrayList<Product> findByQuantityLessThanEqual(int quantityThreshold);
		
		ArrayList<Product> findByTitleLikeIgnoreCaseOrDescriptionLikeIgnoreCase(String searchText1, String searchText2);
}
