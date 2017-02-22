package db;

import global.GlobalDefine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import SettlementParameters.Distribute_Parameters;
import log.Log;

public class DBselector 
{ 
	public static ArrayList<String> get_distribute_commission_id_list(long driver_id, String settlement_id)
	{//获得当前订单id 对应的分佣任务id 列表
		
		ArrayList<String> id_list = new ArrayList<String>();
		SqlGenerator sql_gen = new SqlGenerator();
		String sql = sql_gen.Settlement_Sql_Generator(driver_id, settlement_id);
		DButil  db1 = new DButil(sql);//创建DBHelper对象  
        try {  
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {   
                String distribute_commission_log_id = ret.getString(2);  
                Log.e(driver_id + "\t" + distribute_commission_log_id + "\t" + settlement_id + "\n");
                //id_list.add(distribute_commission_log_id);
                if (distribute_commission_log_id.equals("0"))
                {
                	Log.e("分佣任务日志id为0"+"\n");
                }else
                {
                	id_list.add(distribute_commission_log_id);
                }
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            if(GlobalDefine.debug_open)
            {
            	e.printStackTrace();
            }  
        }  
        ArrayList<String> id_list_withoutdup = new ArrayList<>(new HashSet<>(id_list));
		return id_list_withoutdup;
	}
	
	public static Distribute_Parameters get_distribute_details(long driver_id, String distribute_id)
	{
    	Distribute_Parameters dp = null;
		SqlGenerator sql_gen = new SqlGenerator();
		String sql = sql_gen.distribute_commssion_Sql_Generator(driver_id, distribute_id);

		DButil  db1 = new DButil(sql);//创建DBHelper对象  
        try {  
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集     
            while(ret.next())
            {
	            int amount = Integer.parseInt(ret.getString(17));//分佣总金额， 第17列
	            int other_amount = Integer.parseInt(ret.getString(25));//司机劳务金额， 其他费用
	            int Yidao_Ratio = Integer.parseInt(ret.getString(11));//易到分佣比例， 第11列
	            int Rent_Ratio = Integer.parseInt(ret.getString(12));//租赁公司比例， 第12列
	            int Labor_Ratio = Integer.parseInt(ret.getString(13));//劳务公司比例， 第13列
	            
	            //********************************************************************//
	            //司机的比例， 需要重新确认， 能够找到租赁公司表最好//
	            int Driver_Ratio = get_driver_ratio(ret.getString(5));
	            //********************************************************************//
	            
	            
	            int Yidao_amount = Integer.parseInt(ret.getString(18));//易到分佣实际值，第18列
	            int Rent_amount = Integer.parseInt(ret.getString(19));//租赁公司分佣实际值， 第19列
	            int Labor_amount = Integer.parseInt(ret.getString(20));//劳务公司分佣实际值， 第20列
	            int Driver_amount = Integer.parseInt(ret.getString(21));//司机分佣实际值，第21列
	            int Found_amount = Integer.parseInt(ret.getString(22));//成长基金分佣实际值， 第22列
	            	
	            dp = new Distribute_Parameters(amount, other_amount, Yidao_Ratio,Rent_Ratio, Labor_Ratio, Driver_Ratio, Yidao_amount, Rent_amount, Labor_amount, Driver_amount, Found_amount);
            }
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            if(GlobalDefine.debug_open)
            {
            	e.printStackTrace();
            }  
        }  
		return dp;
	}
	
	public static int get_driver_ratio(String company_id)
	{
		int ratio = 0; 
		SqlGenerator sql_gen = new SqlGenerator();
		String sql_str = sql_gen.rent_company_ratio_Sql_Generator(company_id);
		crm_dbutil db2 = new crm_dbutil(sql_str);
		try {  
            ResultSet ret = db2.pst.executeQuery();//执行语句，得到结果集     
            while(ret.next())
            {
            	ratio = Integer.parseInt(ret.getString(26));
            }
            ret.close();  
            db2.close();//关闭连接  
            
		}catch(SQLException e)
		{
            if(GlobalDefine.debug_open)
            {
            	e.printStackTrace();
            }  
		}
		return ratio;
	}

}


