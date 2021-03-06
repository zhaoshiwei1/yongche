package db;

import global.GlobalDefine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB 
{  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DB(String sql) 
    {  
        try {  
            Class.forName(GlobalDefine.name);//指定连接类型  
            conn = DriverManager.getConnection(GlobalDefine.db_order, GlobalDefine.db_user, GlobalDefine.db_password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {
        	if (GlobalDefine.debug_open)
        	{
                e.printStackTrace();  
        	}
        }  
    }  
  
    public void close() 
    {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
        	if (GlobalDefine.debug_open)
        	{
                e.printStackTrace();  
        	}
        }  
    }  
}  
