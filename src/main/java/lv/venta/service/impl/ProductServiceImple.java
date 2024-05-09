package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImple implements ICRUDProductService, IFilterProductService{
	
	@Autowired
	private IProductRepo productRepo;
	
	
	
	@Override
	public ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception {
		// TODO Auto-generated method stub
		if(priceThreshold <= 0 || priceThreshold > 10000) throw new Exception("Wrong price treshold");
		
		ArrayList<Product> filteredProduct = productRepo.findByPriceLessThanEqual(priceThreshold);
		
		
		return filteredProduct;
	}

	@Override
	public ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception {
		// TODO Auto-generated method stub
		if(quantityThreshold < 0 || quantityThreshold > 100) throw new Exception("Wrong quantity theshold");
		
		ArrayList<Product> filteredProducts =  productRepo.findByQuantityLessThanEqual(quantityThreshold);
		
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> searchByTitleOrDescription(String searchText) throws Exception {
		// TODO Auto-generated method stub
		if (searchText == null) throw new Exception("Wrong");
		ArrayList<Product> filteredProducts = productRepo.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(searchText, searchText);
		
		return filteredProducts;
		
	}

	@Override
	public Product create(Product product) throws Exception {
		// TODO Auto-generated method stub
		if(product == null) throw new Exception("wrong product input");
		
		
		Product productFromDB = productRepo.findByTitleAndDescriptionAndPrice(product.getTitle(),product.getDescription(),product.getPrice());
		
		if(productFromDB != null) {
				
			productFromDB.setQuantity(productFromDB.getQuantity() + product.getQuantity());
			productRepo.save(productFromDB);
			
			return productFromDB;
		}
	
		
		Product storedProduct = productRepo.save(product);
		return storedProduct;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		// TODO Auto-generated method stub
		if(productRepo.count() == 0) throw new Exception("List is empty");
		
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		// TODO Auto-generated method stub
		if( id < 0) throw new Exception("id should be positive");
		
		if(productRepo.existsById(id)) {
			return productRepo.findById(id).get();
		}
		throw new Exception("Product with " + id + " not found");
	}

	@Override
	public void updateById(int id, Product product) throws Exception {
		// TODO Auto-generated method stub
		Product updateProduct = retrieveById(id);
		
		updateProduct.setTitle(product.getTitle());
		updateProduct.setDescription(product.getDescription());
		updateProduct.setQuantity(product.getQuantity());
		updateProduct.setPrice(product.getPrice());
		productRepo.save(updateProduct);
	}

	@Override
	public Product deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		Product deleteProduct = retrieveById(id);
		productRepo.delete(deleteProduct);
		return deleteProduct;
	}

	@Override
	public float calculateTotalProductValue() throws Exception {
		// TODO Auto-generated method stub
		if(productRepo.count() == 0)throw new Exception("No Products");
		float result = productRepo.calculateTotalValueOfDBProducts();
		return result;
	}
	
	
}
