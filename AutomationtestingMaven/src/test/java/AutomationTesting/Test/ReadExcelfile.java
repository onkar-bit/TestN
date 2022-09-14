package AutomationTesting.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcelfile{
	
	@Test
	public void readexcel() throws IOException {
		FileInputStream fs = new FileInputStream("C:\\Users\\285971\\Desktop\\stud.xlsx");
		Workbook workbook = new XSSFWorkbook(fs);
		Sheet sheet = workbook.getSheet("data");
		int rowscount = sheet.getPhysicalNumberOfRows();
		
		String data = sheet.getRow(0).getCell(0).getStringCellValue();
		System.out.println(data);
	}
}