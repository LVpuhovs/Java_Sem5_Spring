package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FirstController {
	@GetMapping("/hello")
	public String getHome() {
		System.out.println("Hello from Spring boot");
		return "hello-page";
	}
	
}
