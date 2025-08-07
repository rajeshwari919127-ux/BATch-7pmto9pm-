package DDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataFromExcelFile {

	public static void main(String[] args) throws IOException {

		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Testscriptdat.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Campaign");
		Row r = sh.getRow(1);
		String campname = r.getCell(2).toString();
		System.out.println(campname);
		String targetsize = r.getCell(3).toString();
		System.out.println(targetsize);
wb.close();
	}

}
