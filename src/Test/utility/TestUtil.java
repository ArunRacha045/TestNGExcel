package Test.utility;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*import com.first.Cell;
import com.first.XSSFRow;
import com.first.XSSFSheet;
import com.first.XSSFWorkbook;
*/

public class TestUtil {
	
	public String[][] getExcelData(String Doc_name, String Sheet_name){
    	
    	String[][] data = null;   	
	  	try
	  	{
	   	FileInputStream fis = new FileInputStream(Doc_name);
	   	XSSFWorkbook wb = new XSSFWorkbook(fis); 
	   	XSSFSheet sh = wb.getSheet(Sheet_name);
	   	XSSFRow row = sh.getRow(0);
	   	int noOfRows = sh.getPhysicalNumberOfRows();
	   	int noOfCols = row.getLastCellNum();
	   	System.out.println("noOfRows : "+noOfRows+" noOfCols :"+noOfCols);
	   	
	   	Cell cell;
	   	data = new String[noOfRows-1][noOfCols];
	   	
	   	for(int i =1; i<noOfRows;i++){
		     for(int j=0;j<noOfCols;j++){
		    	   row = sh.getRow(i);
		    	   cell= row.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);// System.out.println(cell);
		    	   switch (cell.getCellType()) {
		    	   
		    	   case STRING:                   
                   data[i-1][j] = cell.getStringCellValue();
		    	   System.out.println(data[i-1][j]);
		    	   break;
		    	   case NUMERIC:
		    		   if(DateUtil.isCellDateFormatted(cell)) {
		    			   
		    			  Date dateCellValue=cell.getDateCellValue();
		    			  SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		    			  data[i-1][j]=dateFormat.format(dateCellValue);
		    			   System.out.println(data[i-1][j]);
		    			   
		  	    		   }
		    		   else {
		    			   NumberFormat numberFormat = new DecimalFormat("#");
		    			   data[i-1][j]=String.valueOf(numberFormat.format(cell.getNumericCellValue()));
		    		     // data[i-1][j]=String.valueOf(cell.getNumericCellValue());
		    			   System.out.println(data[i-1][j]);
		    		   }
		    		   break;	    		   
                   default:
                	   data[i-1][j] = ""; 
		    	  		    	  
	   	 	   }
	   	}
	  	}
	  	}
	  	catch (Exception e) {
	     	   System.out.println("The exception is: " +e.getMessage());
       	}
    	return data;
	}

}
