package com.bootdo.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.common.utils.R;

@RestController
@RequestMapping("/m/")
public class GiveMeFiveController {

	
	@GetMapping("getcode")
	public R getcode() {
		
		return R.ok().put("code", "888888");
	}
}
