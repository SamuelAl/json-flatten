import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Samuel Alarco Cantos
 * @version 0.0.1
 * @since 01/01/2021
 *
 */
public class JsonFlatten {

	public static void main(String[] args) {

		String content;

		// Input file specified through argument
		if (args.length == 1) {
			Path fileName = Path.of(args[0]);
		
			try {
				content = Files.readString(fileName);
				String json = JsonFlattener.flattenJson(content);

				Path output = Paths.get("output.json");
				Files.write(output, json.getBytes());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Input and output specified through redirection
		else if (args.length == 0) {
			Scanner stdin = new Scanner(System.in);
			content = readIn(stdin);

			String json = JsonFlattener.flattenJson(content);
			System.out.print(json);
			stdin.close();
		}

	}

	/*
	 * Utility function to read string content from Scanner.
	 * 
	 * @param scanner : Scanner object from which to read string
	 * @return result : String read from scanner, or "" if no content
	 */
	private static String readIn(Scanner scanner) {
		if (!scanner.hasNextLine()) {
			return "";
		}
			
		String result = scanner.useDelimiter(Pattern.compile("\\A")).next();
		return result;
	}

}
