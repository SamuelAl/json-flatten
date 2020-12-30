import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonFlattener {

	@SuppressWarnings("unchecked")
	public static String flattenJSON(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		String flatJSON = "";
		
		try {
			map = (Map<String, Object>) mapper.readValue(json, map.getClass());
			flatJSON = generateJSON(map);
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return flatJSON;
	}
	
	public static String generateJSON(Map<String, Object> map) {
		StringBuilder json = new StringBuilder();
		// Append opening bracket
		json.append("{\n");
		generateJSONHelper(map, new ArrayDeque<String>(), json);
		// Delete trailing comma from process
		json.delete(json.length() - 2, json.length());
		// Append closing bracket
		json.append("\n}");
		
		return json.toString();
	}
	
	// Recursive method to flatten JSON Object and serialize into string
	@SuppressWarnings("unchecked")
	private static void generateJSONHelper(Map<String, Object> map, Deque<String> path, StringBuilder sb) {
		for (String key : map.keySet()) {
			path.push(key);
			Object value = map.get(key);
			if (value instanceof Map) {
				generateJSONHelper((Map<String, Object>) value, path, sb);
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
