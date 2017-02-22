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
	{//��õ�ǰ����id ��Ӧ�ķ�Ӷ����id �б�
		
		ArrayList<String> id_list = new ArrayList<String>();
		SqlGenerator sql_gen = new SqlGenerator();
		String sql = sql_gen.Settlement_Sql_Generator(driver_id, settlement_id);
		DButil  db1 = new DButil(sql);//����DBHelper����  
        try {  
            ResultSet ret = db1.pst.executeQuery();//ִ����䣬�õ������  
            while (ret.next()) {   
                String distribute_commission_log_id = ret.getString(2);  
                Log.e(driver_id + "\t" + distribute_commission_log_id + "\t" + settlement_id + "\n");
                //id_list.add(distribute_commission_log_id);
                if (distribute_commission_log_id.equals("0"))
                {
                	Log.e("��Ӷ������־idΪ0"+"\n");
                }else
                {
                	id_list.add(distribute_commission_log_id);
                }
            }//��ʾ����  
            ret.close();  
            db1.close();//�ر�����  
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

		DButil  db1 = new DButil(sql);//����DBHelper����  
        try {  
            ResultSet ret = db1.pst.executeQuery();//ִ����䣬�õ������     
            while(ret.next())
            {
	            int amount = Integer.parseInt(ret.getString(17));//��Ӷ�ܽ� ��17��
	            int other_amount = Integer.parseInt(ret.getString(25));//˾������� ��������
	            int Yidao_Ratio = Integer.parseInt(ret.getString(11));//�׵���Ӷ������ ��11��
	            int Rent_Ratio = Integer.parseInt(ret.getString(12));//���޹�˾������ ��12��
	            int Labor_Ratio = Integer.parseInt(ret.getString(13));//����˾������ ��13��
	            
	            //********************************************************************//
	            //˾���ı����� ��Ҫ����ȷ�ϣ� �ܹ��ҵ����޹�˾�����//
	            int Driver_Ratio = get_driver_ratio(ret.getString(5));
	            //********************************************************************//
	            
	            
	            int Yidao_amount = Integer.parseInt(ret.getString(18));//�׵���Ӷʵ��ֵ����18��
	            int Rent_amount = Integer.parseInt(ret.getString(19));//���޹�˾��Ӷʵ��ֵ�� ��19��
	            int Labor_amount = Integer.parseInt(ret.getString(20));//����˾��Ӷʵ��ֵ�� ��20��
	            int Driver_amount = Integer.parseInt(ret.getString(21));//˾����Ӷʵ��ֵ����21��
	            int Found_amount = Integer.parseInt(ret.getString(22));//�ɳ������Ӷʵ��ֵ�� ��22��
	            	
	            dp = new Distribute_Parameters(amount, other_amount, Yidao_Ratio,Rent_Ratio, Labor_Ratio, Driver_Ratio, Yidao_amount, Rent_amount, Labor_amount, Driver_amount, Found_amount);
            }
            ret.close();  
            db1.close();//�ر�����  
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
            ResultSet ret = db2.pst.executeQuery();//ִ����䣬�õ������     
            while(ret.next())
            {
            	ratio = Integer.parseInt(ret.getString(26));
            }
            ret.close();  
            db2.close();//�ر�����  
            
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


