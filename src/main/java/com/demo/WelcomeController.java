package com.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcome1() {
		return "<html><head><style>body { display: flex; justify-content: center; align-items: center; height: 100vh;}</style></head><body><h1 style='color:red; text-align:center;'>WELCOME TO MY <b>WORKPLACE FAMILY❤️<b><br></h1></body></html>";
	}
}
