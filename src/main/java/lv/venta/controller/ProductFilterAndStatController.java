package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.service.IFilterProductService;


@Controller
@RequestMapping("/product/info")
public class ProductFilterAndStatController {
	
	@Autowired
	private IFilterProductService filteredService;
	
	@GetMapping("/filter/price") // localhost:8080/product/info/filter/price?threshold=0.99
	public String getFilterPrice(@RequestParam("threshold") float threshold, Model model) {
		

		try {
			ArrayList<Product> result = filteredService.filterProductByPriceThreshold(threshold);
			model.addAttribute("title", "Products filtered by price");
			model.addAttribute("mylist", result);
			return "show-product-all-page";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}	
	}
	
	@GetMapping("/filter/quantity") //localhost:8080/product/info/filter/quantity?threshold=5
	public String getFilterQuantity(@RequestParam("threshold") int threshold, Model model) {
		try {
			ArrayList<Product> result = filteredService.filterProductByQuantityThreshold(threshold);
			model.addAttribute("title", "Products filtered by quantity");
			model.addAttribute("mylist", result);
			return "show-product-all-page";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}	
	}
	
	
	@GetMapping("/filter/text") //localhost:8080/product/info/filter/text?keyword=abols
	public String getProductByTitleOrDescription(@RequestParam("keyword") String keyword, Model model) {
		try {
			ArrayList<Product> result = filteredService.searchByTitleOrDescription(keyword);
			model.addAttribute("title", "Products filtered by Title or description");
			model.addAttribute("mylist", result);
			return "show-product-all-page";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}	
	}
	
	@GetMapping("/total") //localhost:8080/product/info/total
	public String getProductInfoFilterByTitleOrDescriptionText(Model model)
	{
		try {
			float result = filteredService.calculateTotalProductValue();
			model.addAttribute("mymsg", "Total: " + result + " eur");//TODO noformatē tikai uz 2 cipariem aiz komata
			return "msg-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	
	}
}
