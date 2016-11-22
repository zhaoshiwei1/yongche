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
	            if (newFile.exists())// ���ڣ���ɾ��  
	                if (!newFile.delete())// ɾ���ɹ��򴴽�  
	                {  
	                    Log.e("ɾ���ļ�" + newFile + "ʧ��");  
	                }  
	            if (newFile.createNewFile()) {// �����ɹ�����д���ļ�����  
	                PrintWriter p = new PrintWriter(new FileOutputStream(newFile  
	                        .getAbsolutePath()));  
	                p.write(buffer.toString());  
	                p.close();  
	            } else {  
	                Log.e("�����ļ���" + newFile + "ʧ��");  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
    }  

}
