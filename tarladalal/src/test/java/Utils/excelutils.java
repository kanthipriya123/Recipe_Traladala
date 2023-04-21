package  Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class excelutils {
	public  static FileInputStream fi;
	public  static FileOutputStream fo;
	public  static XSSFWorkbook wb;
	public static  XSSFSheet ws;
	public  static XSSFRow row;
	public  static XSSFCell cell;
	public  static CellStyle style;
	public  static String path;

	public excelutils(String path)
	{
		excelutils.path=path;
	}

	public static int getRowCount(String xlfile,String xlsheet) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws =wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public  static int getcellcount(String xlfile, String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws =wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;

	}

	public  static String getcellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{

		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws =wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			String cellData= formatter.formatCellValue(cell);
			return cellData;
		}
		catch(Exception e)
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;

	}

	public static  void setcellData(String sheetname,int rownum,int colnum, String data) throws IOException
	{
		File xlfile =new File(path);
		if(!xlfile.exists())
		{

			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);

		}

		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		if(wb.getSheetIndex(sheetname)==-1)
			wb.createSheet(sheetname);
		ws = wb.getSheet(sheetname);

		if(ws.getRow(rownum)==null)
			ws.createRow(rownum);
		row=ws.getRow(rownum);

		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	
	public static void DeleteRow(String sheetname, int rownum)
	{
		
		ws.removeRow(row);
	}
	
	

}


