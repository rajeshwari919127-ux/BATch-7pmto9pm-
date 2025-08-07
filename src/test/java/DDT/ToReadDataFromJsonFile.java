package DDT;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ToReadDataFromJsonFile {

	public static void main(String[] args) throws IOException, ParseException {

		FileReader fr=new FileReader(".\\src\\test\\resources\\CommonData1.json");
		JSONParser parser=new JSONParser();
		//convert physical file into java object
		Object javaobj=parser.parse(fr);
		//Typecasting
		JSONObject obj=(JSONObject) javaobj;
		//Reading data
		Object BROWSER = obj.get("Browser").toString();
		System.out.println(BROWSER);
		
		
		
	}

}
