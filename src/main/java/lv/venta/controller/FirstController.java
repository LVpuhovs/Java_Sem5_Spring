package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;




@Controller
public class FirstController  {
	
	@Autowired
	private ICRUDProductService crudService;
	
	@Autowired
	private IFilterProductService filteredService;
	
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
		
		try {
			model.addAttribute("myobj", crudService.retrieveAll().get(0));
			return "show-product-page"; //tiek paradita show-product-page lapa
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/product/all")//localhost:8080/product/all
	public String getProductAll(Model model) {
		try {
			model.addAttribute("mylist" ,crudService.retrieveAll());
			return "show-product-all-page";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/productone")//localhost:8080/productone?id=3
	public String getProductOne(@RequestParam("id") int id, Model model) {
		
		try {
				model.addAttribute("myobj", crudService.retrieveById(id));
				return "show-product-page";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/product/all/{id}")//localhost:8080/product/all/2
	public String getProductAllId(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("myobj", crudService.retrieveById(id));
			return "show-product-page";
		}catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
	
	//TODO
	//1. Izveido getMapping funkciju kas atver HTML lapu
	
	@GetMapping("/product/insert")//localhost:8080/product/insert
	public String getProductInsert(Model model) {
		model.addAttribute("product", new Product());
		return "insert-product-page";
	}
	
	@PostMapping("/product/insert")
	public String postProductInsert(@Valid Product product, BindingResult result) { //iegust aizpilditu produktu
		//TODO: process POST request
		
		if(result.hasErrors()) {
			return "insert-product-page";
		}
		else {
			try {
				Product newProduct = crudService.create(product);
				return "redirect:/product/all";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "redirect:/error"; //parlec uz citu endpoint
			}
		}
	}
	
	@GetMapping("/error") // localhost:8080/error
	public String getError() {
		return "error-page";
	}
	//2. Izveido html lapu kurai bus 4 ievades lauki un submit poga
	//3. izveido postmapping funkciju, kura nolasa Product, kas nak no html
	// ar servisa palidzibu saglaba
}
