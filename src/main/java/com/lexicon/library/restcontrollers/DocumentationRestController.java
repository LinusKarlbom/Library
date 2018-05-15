package com.lexicon.library.restcontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexicon.library.documentation.DocumentationMap;

@RestController
public class DocumentationRestController {
	
	@Autowired
	DocumentationMap documentation;
	
	@GetMapping("/rest")
	public Map<String, String> getDocumentation(){
		return documentation.getMap();
	}

}
