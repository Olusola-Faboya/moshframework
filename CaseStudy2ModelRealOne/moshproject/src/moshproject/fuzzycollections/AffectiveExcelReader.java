package moshproject.fuzzycollections;

import gov.nasa.worldwind.formats.vpf.VPFTableReader.Column;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class AffectiveExcelReader {
	public List<AffectiveComponent> readPropertiesFromExcelFile(String excelFilePath) throws IOException {
     
		List<AffectiveComponent> newList = new ArrayList<AffectiveComponent>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        
        
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
     
      for (int i=0; i< workbook.getNumberOfSheets(); i++){
        Sheet firstSheet = workbook.getSheetAt(i);
        Iterator<Row> iterator = firstSheet.iterator();
     
        while (iterator.hasNext()) {
           Row nextRow = iterator.next();           
            Iterator<Cell> cellIterator = nextRow.cellIterator();
           
        
            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                
                int columnIndex = nextCell.getColumnIndex();   
                   	CellReference pleasantRef = new CellReference(nextRow.getRowNum(), columnIndex);
                	CellReference magnitudeRef = new CellReference(nextRow.getRowNum(), columnIndex+1);
                	Cell cellPlesant = firstSheet.getRow(pleasantRef.getRow()).getCell(pleasantRef.getCol());
                	Cell cellMagnitude = firstSheet.getRow(magnitudeRef.getRow()).getCell(magnitudeRef.getCol());
                	
                	try{
                		if (cellPlesant!=null && cellMagnitude!= null && cellPlesant.getCellType()==Cell.CELL_TYPE_NUMERIC
                				&& cellMagnitude.getCellType()==Cell.CELL_TYPE_NUMERIC){
                			newList.add(new AffectiveComponent(cellPlesant.getNumericCellValue(), cellMagnitude.getNumericCellValue()));
                		}
                	}catch (Exception ex){
                		ex.toString();
                	}
                	
                
                	
            }
            
        }
      }
		return newList;     
                
        }
	
	 private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) 
		 throws IOException {
			    Workbook workbook = null;
			 
			    if (excelFilePath.endsWith("xlsx")) {
			        workbook = new XSSFWorkbook(inputStream);
			    } else if (excelFilePath.endsWith("xls")) {
			        workbook = new HSSFWorkbook(inputStream);
			    } else {
			        throw new IllegalArgumentException("The specified file is not Excel file");
			    }
		return workbook;
	}
		 
	private Object getCellValue(Cell cell) {
    	 switch (cell.getCellType()) {
    	    case Cell.CELL_TYPE_STRING:
    	        return cell.getStringCellValue();
    	 
    	    case Cell.CELL_TYPE_BOOLEAN:
    	        return cell.getBooleanCellValue();
    	 
    	    case Cell.CELL_TYPE_NUMERIC:
    	        return cell.getNumericCellValue();
    	    }
    	
		return null;
    	
    }
	
	
//	 public List<AffectiveComponent> getPleasantness(){
//		 
//		 
//		return getPleasantness();
//		 	 
//	 }
	 public static void main(String[] args) throws IOException {
		    String excelFilePath = "src/AffectiveProperties.xlsx";
		    AffectiveExcelReader reader = new AffectiveExcelReader();
		    List<AffectiveComponent> newList = reader.readPropertiesFromExcelFile(excelFilePath);
		  //  List<AffectiveComponent> listMagnitude = reader.readPropertiesFromExcelFile(excelFilePath);
		    System.out.println(newList);
		    //System.out.println(listMagnitude);
		}
	 
	
}
