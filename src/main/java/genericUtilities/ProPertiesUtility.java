package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.devtools.v133.indexeddb.model.Key;

public class ProPertiesUtility {
	
	public String ToReadDataFromProprtiesFile(String Key) throws IOException
	{
	FileInputStream fis=new FileInputStream("C:\\Users\\Raji\\eclipse-workspace\\Ninza_CRM\\src\\test\\resources\\Commondata.properties");
	Properties prop=new Properties();
	prop.load(fis);
	String value=prop.getProperty(Key);
     return value;
	}
	
}
