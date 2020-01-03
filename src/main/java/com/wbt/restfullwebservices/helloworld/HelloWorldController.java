package com.wbt.restfullwebservices.helloworld;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	@GetMapping("/hello-world")
	public String helloword()
	{
		return "Hello World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWordBean helloWordBean()
	{
		return new HelloWordBean("Hello world Bean");
	}
	
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWordBean helloWordBeanpath(@PathVariable String name)
	{
		return new HelloWordBean(String.format("Hello world Bean   %s",name));
	}
	
	@RequestMapping("user")
	@ResponseBody
	public Principal user(Principal principal)
	{
		return principal;
		
	}

}
