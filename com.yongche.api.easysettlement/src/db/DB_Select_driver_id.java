package db;

import global.GlobalDefine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import log.Log;

public class DB_Select_driver_id 
{
	public static long Select_Driver_ID(String Service_Order_ID)
	{
		String select_driver_id = "SELECT driver_id FROM yc_order.service_order WHERE service_order_id =" + Service_Order_ID;
		String driver_id = null;
		DB db1 = new DB(select_driver_id);
		
		 try {  
	            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集  
	            while (ret.next()) {   
	            driver_id = ret.getString(1);  
	            }
	            ret.close();  
	            db1.close();//关闭连接  
	        } catch (SQLException e) 
	            {  
		            if(GlobalDefine.debug_open)
		            {
		            	e.printStackTrace();
		            }  
	        }  
		
			return Long.parseLong(driver_id);
	}

}
