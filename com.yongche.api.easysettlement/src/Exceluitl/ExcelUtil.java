package Exceluitl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import log.Log;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import SettlementParameters.S_Parameter;

public class ExcelUtil {
	
	//读取Excel中数据
	@SuppressWarnings("deprecation")
	public static List<S_Parameter> read(String filepath) throws Exception{
		 HSSFWorkbook wb = new HSSFWorkbook();
		 HSSFSheet s = wb.createSheet();
		 HSSFRow row = s.createRow(0);
		 HSSFCell cell = row.createCell((int)0,0);

		 //－－－－－－－－－－－－从xls读出数据  "D:\\alpha.xls"
		 wb = new HSSFWorkbook(new FileInputStream(filepath));
		 s = wb.getSheetAt(0);
		 
		 //获得EXCEL行数
		 int rowNums=s.getLastRowNum();
		 //System.out.print("total rows: "+rowNums);
		 //获得Excell列数
		 //int columnNum=r.getPhysicalNumberOfCells();
		 
		 List<S_Parameter> params=new ArrayList<S_Parameter>();
		 for(int i=1;i<=rowNums;i++){
			 HSSFRow r = s.getRow(i);
//			 cell=r.getCell(0);
			 S_Parameter param= new S_Parameter();
			 r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			 param.set_settlement_id(r.getCell(0).getStringCellValue());
			 Log.e(r.getCell(0).getStringCellValue()+"\n");
			 params.add(param);
		 }
		 return params;

	}

	/**
	  * 写入Excel，在任意坐标处写入数据。
	  * String value：你要输入的内容
	  * int x ：行坐标，Excel从 0 算起
	  * int y   ：列坐标，Excel从 0 算起
	  */
		public static void writeCell(String filePath,int x,int y,String value) {
			try {
				// 创建Excel的工作书册 Workbook,对应到一个excel文档
				HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
				HSSFSheet sheet=wb.getSheetAt(0);
				HSSFRow row=sheet.getRow(x);
				HSSFCell cell=row.getCell((short) y);
				cell.setCellValue(value);
				FileOutputStream os;
				os = new FileOutputStream(filePath);
				wb.write(os);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}