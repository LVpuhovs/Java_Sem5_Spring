package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IFilterProductService {
	
	public abstract ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception;
	
	public abstract ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception;
	
	public abstract ArrayList<Product> searchByTitleOrDescription(String title, String description) throws Exception;
}
