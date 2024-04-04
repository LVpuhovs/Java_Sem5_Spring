package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class FirstController {
	
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
	
}
