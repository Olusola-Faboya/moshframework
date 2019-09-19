package moshproject.fuzzycollections;

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



public class DecisionComponentsExcelReader {
	public List<DecisionComponent> readPropertiesFromExcelFile(String excelFilePath) throws IOException {
     
		List<DecisionComponent> newList = new ArrayList<DecisionComponent>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        
        
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
     
      //  Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
     
        while (iterator.hasNext()) {
           Row nextRow = iterator.next();           
            Iterator<Cell> cellIterator = nextRow.cellIterator();
           
        
            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                
                int columnIndex = nextCell.getColumnIndex();   
                
               // for (Row row: firstSheet){
                	CellReference physicalRef = new CellReference(nextRow.getRowNum(), columnIndex);
                	CellReference cognitiveRef = new CellReference(nextRow.getRowNum(), columnIndex+1);
                	CellReference affectiveRef = new CellReference(nextRow.getRowNum(), columnIndex+2);
                	Cell cellPhysical = firstSheet.getRow(physicalRef.getRow()).getCell(physicalRef.getCol());
                	Cell cellCognitive = firstSheet.getRow(cognitiveRef.getRow()).getCell(cognitiveRef.getCol());
                	Cell cellAffective = firstSheet.getRow(affectiveRef.getRow()).getCell(affectiveRef.getCol());
                	
                	try{
                		if (cellPhysical!=null && cellCognitive!= null && cellPhysical.getCellType()==Cell.CELL_TYPE_NUMERIC
                				&& cellCognitive.getCellType()==Cell.CELL_TYPE_NUMERIC){
                			newList.add(new DecisionComponent(cellPhysical.getNumericCellValue(), cellCognitive.getNumericCellValue(),
                					cellAffective.getNumericCellValue()));
                		}
                	}catch (Exception ex){
                		ex.toString();
                	}
                	
               // }
                	
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
		    String excelFilePath = "src/DecisionComponent.xlsx";
		    DecisionComponentsExcelReader reader = new DecisionComponentsExcelReader();
		    List<DecisionComponent> newList = reader.readPropertiesFromExcelFile(excelFilePath);
		  //  List<AffectiveComponent> listMagnitude = reader.readPropertiesFromExcelFile(excelFilePath);
		    System.out.println(newList);
		    //System.out.println(listMagnitude);
		}
	 
	
}