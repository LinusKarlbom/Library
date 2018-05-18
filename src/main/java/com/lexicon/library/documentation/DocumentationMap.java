package com.lexicon.library.documentation;

import java.util.Map;

/**
 * An interface for classes allowing the obtaining of a documentation map.
 * 
 * @author Linus Karlbom
 */
public interface DocumentationMap {
	/**
	 * @return a Map between Strings describing the paths for accessing REST functionalities and Strings describing how to use these functions.
	 */
	public Map<String, String> getMap();
}
