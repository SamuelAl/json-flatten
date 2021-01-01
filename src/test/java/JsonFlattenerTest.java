import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author Samuel Alarco Cantos
 *
 */
class JsonFlattenerTest {

	
	
	@Test
	void flattenJsonTest() {
		
		String jsonFile, jsonOutput;
		try {
			Path inputFile = Paths.get(ClassLoader.getSystemResource("test_input.txt").toURI());
			Path expextedResultsFile = Paths.get(ClassLoader.getSystemResource("test_output.txt").toURI());
			jsonFile = Files.readString(inputFile);
			jsonOutput = Files.readString(expextedResultsFile);
			String[] jsonStrings = jsonFile.split("===");
			String[] jsonOutputs = jsonOutput.split("===");
			
			for (int i = 0; i < jsonStrings.length && i < jsonOutputs.length; i++) {
				String expected = normalizeString(jsonOutputs[i]);
				String output = JsonFlattener.flattenJson(jsonStrings[i]).strip();
				
				assertEquals(expected, output);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private String normalizeString(String str) {
		return str.strip().replace("\r\n", "\n").replace('\r', '\n');
	}
	
	

}
