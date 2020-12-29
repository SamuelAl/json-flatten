import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;




public class JSONFlattener {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Path fileName = Path.of("example.json");
		String content = Files.readString(fileName);
		System.out.println(content);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			map = (Map<String, Object>) mapper.readValue(content, map.getClass());
			String json = generateJSON(map);
			System.out.println(json);
			
			Path output = Paths.get("output.json");
			Files.write(output, json.getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void printKeys(Map<String, Object> map, Deque<String> path) {
		for (String key : map.keySet()) {
			path.push(key);
			Object value = map.get(key);
			if (value instanceof Map) {
				printKeys((Map<String, Object>) value, path);
			}
			else {
				System.out.printf("%s: %s\n", String.join(".", path), value);
			}
			path.pop();
		}
	}
	
	private static String generateJSON(Map<String, Object> map) {
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
