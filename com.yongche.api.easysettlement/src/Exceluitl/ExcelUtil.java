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
	
	//��ȡExcel������
	@SuppressWarnings("deprecation")
	public static List<S_Parameter> read(String filepath) throws Exception{
		 HSSFWorkbook wb = new HSSFWorkbook();
		 HSSFSheet s = wb.createSheet();
		 HSSFRow row = s.createRow(0);
		 HSSFCell cell = row.createCell((int)0,0);

		 //��������������������������xls��������  "D:\\alpha.xls"
		 wb = new HSSFWorkbook(new FileInputStream(filepath));
		 s = wb.getSheetAt(0);
		 
		 //���EXCEL����
		 int rowNums=s.getLastRowNum();
		 //System.out.print("total rows: "+rowNums);
		 //���Excell����
		 //int columnNum=r.getPhysicalNumberOfCells();
		 
		 List<S_Parameter> params=new ArrayList<S_Parameter>();
		 for(int i=1;i<=rowNums;i++){
			 HSSFRow r = s.getRow(i);
//			 cell=r.getCell(0);
			 S_Parameter param= new S_Parameter();
			 r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			 param.set_driver_id(r.getCell(0).getStringCellValue());
			 Log.e(r.getCell(0).getStringCellValue()+"\n");
			 r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			 param.set_settlement_id(r.getCell(1).getStringCellValue());
			 Log.e(r.getCell(1).getStringCellValue()+"\n");
			 params.add(param);
		 }
		 return params;

	}

	/**
	  * д��Excel�����������괦д�����ݡ�
	  * String value����Ҫ���������
	  * int x �������꣬Excel�� 0 ����
	  * int y   �������꣬Excel�� 0 ����
	  */
		public static void writeCell(String filePath,int x,int y,String value) {
			try {
				// ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�
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