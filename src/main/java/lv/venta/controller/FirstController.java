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
	
	@GetMapping("/error") // localhost:8080/error
	public String getError() {
		return "error-page";
	}
	
	
	
	
	
	//TODO
	//1. Izveido getMapping funkciju kas atver HTML lapu
	
	
	
	
	
	
	
	
	//2. Izveido html lapu kurai bus 4 ievades lauki un submit poga
	//3. izveido postmapping funkciju, kura nolasa Product, kas nak no html
	// ar servisa palidzibu saglaba
}
