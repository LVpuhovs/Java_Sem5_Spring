package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;

@Controller
@RequestMapping("/product")
public class ProductCRUDController {
	@Autowired
	private ICRUDProductService crudService;
	
	@GetMapping("/test")//localhost:8080/product/test
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
	
	@GetMapping("/all")//localhost:8080/product/all
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
	
	@GetMapping("/one")//localhost:8080/product/one?id=3
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
	
	@GetMapping("/all/{id}")//localhost:8080/product/all/2
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
	
	
	@GetMapping("/insert")//localhost:8080/product/insert
	public String getProductInsert(Model model) {
		
		model.addAttribute("product", new Product());
		return "insert-product-page";
	}
	
	@PostMapping("/insert")
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
	
	
	@GetMapping("/update") //localhost:8080/product/update?id=2
	public String getProductUpateById(@RequestParam("id") int id, Model model ) {
		
		try {
			Product product = crudService.retrieveById(id);
			model.addAttribute("product", product);
			model.addAttribute("id", id);
			return "update-product-page";//tiek parādīta update-product-page.html lapa
				
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
		
		
	}
	
	@PostMapping("/update")
	public String postProductInsert(@RequestParam("id") int id,  @Valid Product product, BindingResult result, Model model) {// iegūst redigēto produktu

		if (result.hasErrors()) {
			model.addAttribute("id", id);
			return "update-product-page";
		} else {

			try {
				crudService.updateById(id, product);
				return "redirect:/product/all/" + id;
			} catch (Exception e) {
				return "redirect:/error";// pārlec uz citu endpointu
			}
		}

	}
	
	@GetMapping("/delete") //localhost:8080/product/delete?id=2
	public String getProductDeleteById(@RequestParam("id") int id, Model model) {
		
		try {
			crudService.deleteById(id);
			model.addAttribute("myobjs", crudService.retrieveAll());
			return "show-product-all-page"; // tiek parādīta show-product-all-page.html lapa	
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	}
}
