package db;

import global.GlobalDefine;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
public class crm_dbutil 
{  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public crm_dbutil(String sql) 
    {  
        try {  
            Class.forName(GlobalDefine.name);//ָ����������  
            conn = DriverManager.getConnection(GlobalDefine.crm_db_url, GlobalDefine.user, GlobalDefine.password);//��ȡ����  
            pst = conn.prepareStatement(sql);//׼��ִ�����  
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
