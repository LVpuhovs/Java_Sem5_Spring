package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;




@Controller
public class FirstController {
	Product myProduct1 = new Product("Abols", "Zals", 0.69f, 5);
	Product myProduct2 = new Product("Zemene", "dzeltena", 0.25f, 3);
	Product myProduct3 = new Product("Zars", "Bruns", 1.29f, 2);
	ArrayList<Product> allProduct = new ArrayList<>(Arrays.asList(myProduct1,myProduct2, myProduct3 ));
	
	@GetMapping("/hello")//localhost:8080/hello
	public String getHello() {
		System.out.println("Hello from Spring boot");
		return "hello-page";//tiek paradita hello-page lapa
	}
	
	@GetMapping("/hello/msg")//localhost:8080/hello/msg
	public String getHelloMsg(Model model) {
						//nosaukums, vertiba
		model.addAttribute("mymsg","Zina no backend!! Hei!");
		return "msg-page"; //tiek paradita msg-page lapa
	}
	
	@GetMapping("/product/test")//localhost:8080/product/test
	public String getProductTest(Model model) {
		Product myProduct = new Product("Abols", "Sarkans", 0.99f, 5);
		model.addAttribute("myobj", myProduct);
		return "show-product-page"; //tiek paradita show-product-page lapa
	}
	
	@GetMapping("/product/all")//localhost:8080/product/all
	public String getProductAll(Model model) {
		
		allProduct.add(myProduct1);
		allProduct.add(myProduct2);
		allProduct.add(myProduct3);
		model.addAttribute("mylist" ,allProduct);
		return "show-product-all-page";
	}
	
	
	@GetMapping("/productone")//localhost:8080/productone?id=3
	public String getProductOne(@RequestParam("id") int id, Model model) {
		for (Product tempP: allProduct) {
			if(tempP.getId() == id) {
				model.addAttribute("myobj", tempP);
				return "show-product-page";
			}
		}
		
		model.addAttribute("msg", "Wrong id");
		return "error-page";
	}
	
	@GetMapping("/product/all/{id}")//localhost:8080/product/all/2
	public String getProductAllId(@PathVariable("id") int id, Model model) {
		for (Product tempP: allProduct) {
			if(tempP.getId() == id) {
				model.addAttribute("myobj", tempP);
				return "show-product-page";
			}
		}
		
		model.addAttribute("msg", "Wrong id");
		return "error-page";
	}
}
