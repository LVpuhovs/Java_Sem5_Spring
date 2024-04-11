package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImple implements ICRUDProductService, IFilterProductService{

	private ArrayList<Product> allProduct = new ArrayList<>();
	@Override
	public ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception {
		// TODO Auto-generated method stub
		if(priceThreshold <= 0 || priceThreshold > 10000) throw new Exception("Wrong price treshold");
		
		ArrayList<Product> filteredProducts = new ArrayList<>();
		
		for(Product tempP: allProduct) {
			if(tempP.getPrice() <= priceThreshold) {
				filteredProducts.add(tempP);
			}
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception {
		// TODO Auto-generated method stub
		if(quantityThreshold < 0 || quantityThreshold > 100) throw new Exception("Wrong quantity theshold");
		
		ArrayList<Product> filteredProducts =  new ArrayList<>();
		for(Product tempP: allProduct) {
			if(tempP.getPrice() <= quantityThreshold) {
				filteredProducts.add(tempP);
			}
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> searchByTitleOrDescription(String searchText) throws Exception {
		// TODO Auto-generated method stub
		if (searchText == null) throw new Exception("Wrong");
		ArrayList<Product> filteredProducts = new ArrayList<>();
		
		for(Product tempP: allProduct) {
			if(tempP.getTitle().contains(searchText) || tempP.getDescription().contains(searchText)) {
				filteredProducts.add(tempP);
			}
		}
		return filteredProducts;
		
	}

	@Override
	public Product create(Product product) throws Exception {
		// TODO Auto-generated method stub
		if(product == null) throw new Exception("wrong product input");
		
		
		
		for(Product tempP: allProduct) {
			if(tempP.getTitle().equals(product.getTitle()) && tempP.getDescription().equals(product.getDescription()) && tempP.getPrice() == product.getPrice()) {
				tempP.setQuantity(tempP.getQuantity() + product.getQuantity());
				return tempP;
			}
		}
		Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
		allProduct.add(newProduct);
		return newProduct;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		// TODO Auto-generated method stub
		if(allProduct.isEmpty()) throw new Exception("List is empty");
		
		return allProduct;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		// TODO Auto-generated method stub
		if( id < 0) throw new Exception("id should be positive");
		for(Product tempP: allProduct) {
			if(tempP.getId() == id) {
				return tempP;
			}
		}
		throw new Exception("Product with " + id + "not found");
	}

	@Override
	public void updateById(int id, Product product) throws Exception {
		// TODO Auto-generated method stub
		Product updateProduct = retrieveById(id);
		
		updateProduct.setTitle(product.getTitle());
		updateProduct.setDescription(product.getDescription());
		updateProduct.setQuantity(product.getQuantity());
		updateProduct.setPrice(product.getPrice());
	}

	@Override
	public Product deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		Product deleteProduct = retrieveById(id);
		allProduct.remove(deleteProduct);
		return deleteProduct;
	}
	
	
}
