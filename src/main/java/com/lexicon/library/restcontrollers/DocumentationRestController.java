package com.lexicon.library.restcontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexicon.library.documentation.DocumentationMap;

/**
 * A REST controller providing functionality for documentation regarding all the library REST functionality.
 * 
 * @author Linus Karlbom
 */
@RestController
public class DocumentationRestController {
	
	@Autowired
	DocumentationMap documentation;
	
	/**
	 * @return a Map between Strings describing the paths for accessing REST functionalities and Strings describing how to use these functions.
	 */
	@GetMapping("/rest")
	public Map<String, String> getDocumentation(){
		return documentation.getMap();
	}

}
