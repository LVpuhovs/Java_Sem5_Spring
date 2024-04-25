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
	
	@GetMapping("/filter/price") // localhost8080:/product/info/filter/price?threshold=0.99
	public String getFilterPrice(@RequestParam("threshold") float threshold, Model model) {
		

		try {
			ArrayList<Product> result = filteredService.filterProductByPriceThreshold(threshold);
			model.addAttribute("result", result);
			return "show-product-page";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
		
		
		
	}
	
}
