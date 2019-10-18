package com.bootdo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class ErrorsController {
	@GetMapping(value = { "/404" })
	public String index404(Model model) {
		return "error/404";
	}

	@GetMapping(value = { "/403" })
	public String index403(Model model) {
		return "error/403";
	}

	@GetMapping(value = { "/500" })
	public String index500(Model model) {
		return "error/500";
	}
}
