package distribute;

import global.GlobalDefine;

import java.util.List;

import log.Log;
import Exceluitl.ExcelUtil;
import SettlementParameters.Distribute_Parameters;
import db.DB_Select_driver_id;
import db.DBselector;

public class CheckDistribute 
{//输入当前订单id 对应的分佣任务id 列表，检查分佣， 返回string buffer
	public static StringBuffer c_distribute(String settlement_id)
	{
		long driver_id = DB_Select_driver_id.Select_Driver_ID(settlement_id);
		
		StringBuffer bf = new StringBuffer("");
		int flag = 1;
		List<String> id_list = DBselector.get_distribute_commission_id_list(driver_id,settlement_id);
		for (String id : id_list)
		{
			Distribute_Parameters dp = DBselector.get_distribute_details(driver_id, id);
			if(dp == null)
			{
				Log.e("未能找到分佣任务记录: " + id + "\n");
			}else
			{
				if((dp.Yidao_amount == dp.Yidao_amount_E)&&(dp.Rent_amount == dp.Rent_amount_E)&&(dp.Labor_amount==dp.Labor_amount_E)
				&&(dp.Driver_amount == dp.Driver_amount_E)&&(dp.Found_amount==dp.Found_amount_E))
				{
					flag *=1;
				}else
				{
					flag *=0;
				}
			}
		
			bf.append("        分佣任务日志ID:  "+id+"\r\n");
			
			bf.append("          易到分佣比例： " + dp.Yidao_ratio + "\r\n");
			bf.append("      租赁公司分佣比例： " + dp.Rent_ratio + "\r\n");
			bf.append("      劳务公司分佣比例： " + dp.Labor_ratio + "\r\n");
			bf.append("          司机分佣比例： " + dp.Driver_ratio + "\r\n");
			bf.append("      成长基金分佣比例： " + dp.Found_ratio + "\r\n");
			
			bf.append("         易到分佣_期待： " + dp.Yidao_amount_E+"\r\n");
			bf.append("         易到分佣_实际： " + dp.Yidao_amount + "\r\n");
			bf.append("         租赁分佣_期待： " + dp.Rent_amount_E + "\r\n");
			bf.append("         租赁分佣_实际： " + dp.Rent_amount + "\r\n");
			bf.append("         劳务分佣_期待： " + dp.Labor_amount_E + "\r\n");
			bf.append("         劳务分佣_实际： " + dp.Labor_amount + "\r\n");
			bf.append("         成长分佣_期待： " + dp.Found_amount_E + "\r\n");
			bf.append("         成长分佣_实际： " + dp.Found_amount + "\r\n");
			bf.append("         司机分佣_期待： " + dp.Driver_amount_E + "\r\n");
			bf.append("         司机分佣_实际： " + dp.Driver_amount + "\r\n" + "\r\n");
		}
		if(flag == 1)
		{
			bf.append("分佣检查正确"+ "\n");
		}else
		{
			bf.append("分佣检查失败"+ "\n");
		}
		return bf;
	}

}
