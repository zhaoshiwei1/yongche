package log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import global.GlobalDefine;

public class Log 
{
	public static void e(String msg)
	{
		if (GlobalDefine.debug_open)
		{
			System.out.print(msg);		
		}
	}
	
	public static void o(String msg)
	{
		if (GlobalDefine.out_put)
		{
			System.out.print(msg);		
		}
	}
	
	public static void out_to_file(String File_Path, StringBuffer buffer) {
		if(GlobalDefine.out_to_file)
		{
			try {  
	            File newFile = new File(File_Path);  
	            if (newFile.exists())// 存在，则删除  
	                if (!newFile.delete())// 删除成功则创建  
	                {  
	                    Log.e("删除文件" + newFile + "失败");  
	                }  
	            if (newFile.createNewFile()) {// 创建成功，则写入文件内容  
	                PrintWriter p = new PrintWriter(new FileOutputStream(newFile  
	                        .getAbsolutePath()));  
	                p.write(buffer.toString());  
	                p.close();  
	            } else {  
	                Log.e("创建文件：" + newFile + "失败");  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
    }  

}
