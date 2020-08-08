package com.spring.sboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerDemo {
	
	@RequestMapping("/home")
//	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
}
