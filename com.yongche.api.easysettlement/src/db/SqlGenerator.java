package db;

import log.Log;

public class SqlGenerator 
{
	public static final String host = "select *from yc_driver_settlement";
	public static final String db_settlement_table = ".driver_settlement";
	public static final String db_distribute_commssion_table = ".distribute_commission_log";
	public static final String settlement_filter = " WHERE service_order_id = ";
	public static final String distribute_filter = " WHERE distribute_commission_log_id = ";
	
	public static final String rent_company_filter = "SELECT * FROM yc_crm_common.company WHERE company_id = ";
	public String Settlement_Sql_Generator(long driver_id, String settlement_id)
	{
		int index = (int) (driver_id % 64);
		String table_index = String.valueOf(index);
		String sql_string = host+table_index+db_settlement_table+ settlement_filter+settlement_id;
		Log.e("数据库查询语句： "+ sql_string+"\n");
		return sql_string;
	}
	
	public String distribute_commssion_Sql_Generator(long driver_id, String distribute_id)
	{
		int index = (int) (driver_id % 64);
		String table_index = String.valueOf(index);
		String sql_string = host+table_index+db_distribute_commssion_table + distribute_filter + distribute_id ;
		Log.e("数据库查询语句： "+ sql_string + "\n");
		return sql_string;
	}


	public String rent_company_ratio_Sql_Generator(String company_id)
	{
		String sql_string = this.rent_company_filter + company_id;
		Log.e("租赁公司查询语句：  " + sql_string + "\n");
		return sql_string;
	}
	
}