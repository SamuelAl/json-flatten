import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonFlattener {
	
	/*
	 * Method to flatten JSON object represented
	 * by a String.
	 * 
	 * @param json : String representing JSON object
	 * @return String
	 */
	
	@SuppressWarnings("unchecked")
	public static String flattenJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		String flatJSON = "";
		
		try {
			map = (Map<String, Object>) mapper.readValue(json, map.getClass());
			flatJSON = mapToFlatJson(map);
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return flatJSON;
	}
	
	/*
	 * Processes map into flattened JSON string 
	 * representation.
	 * Uses recursive helper method.
	 * 
	 * @param map : Map object representation of JSON object
	 * @return String
	 */
	private static String mapToFlatJson(Map<String, Object> map) {
		StringBuilder json = new StringBuilder();
		// Append opening bracket
		json.append("{\n");
		mapToFlatJsonHelper(map, new ArrayDeque<String>(), json);
		// Delete trailing comma from process
		json.delete(json.length() - 2, json.length());
		// Append closing bracket
		json.append("\n}");
		
		return json.toString();
	}
	
	/*
	 * Recursive helper method to flatten map into JSON.
	 * 
	 * @param map : Map representation of JSON object.
	 * @param path : Path buffer to accumulate traversed path.
	 * @param sb : StringBuilder object use to build JSON string representation.
	 */
	@SuppressWarnings("unchecked")
	private static void mapToFlatJsonHelper(Map<String, Object> map, Deque<String> path, StringBuilder sb) {
		for (String key : map.keySet()) {
			path.push(key);
			Object value = map.get(key);
			if (value instanceof Map) {
				mapToFlatJsonHelper((Map<String, Object>) value, path, sb);
			}
			else {
				sb.append(String.format("\t\"%s\": ", String.join(".", path))); 
				if (value instanceof String) {
					sb.append(String.format("\"%s\",\n", value));
				}
				else {
					sb.append(String.format("%s,\n", value));
				}
			}
			path.pop();
		}
	}
	
	

}
