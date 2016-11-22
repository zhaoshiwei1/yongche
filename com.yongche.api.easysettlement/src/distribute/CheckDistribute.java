package distribute;

import global.GlobalDefine;

import java.util.List;

import log.Log;
import Exceluitl.ExcelUtil;
import SettlementParameters.Distribute_Parameters;
import db.DBselector;

public class CheckDistribute 
{//���뵱ǰ����id ��Ӧ�ķ�Ӷ����id �б�����Ӷ�� ����string buffer
	public static StringBuffer c_distribute(long driver_id, String settlement_id, int row)
	{
		StringBuffer bf = new StringBuffer("");
		int flag = 1;
		List<String> id_list = DBselector.get_distribute_commission_id_list(driver_id, settlement_id);
		for (String id : id_list)
		{
			Distribute_Parameters dp = DBselector.get_distribute_details(driver_id, id);
			if(dp == null)
			{
				Log.e("δ���ҵ���Ӷ�����¼: " + id + "\n");
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
		
			bf.append("        ��Ӷ������־ID:  "+id+"\r\n");
			
			bf.append("          �׵���Ӷ������ " + dp.Yidao_ratio + "\r\n");
			bf.append("      ���޹�˾��Ӷ������ " + dp.Rent_ratio + "\r\n");
			bf.append("      ����˾��Ӷ������ " + dp.Labor_ratio + "\r\n");
			bf.append("          ˾����Ӷ������ " + dp.Driver_ratio + "\r\n");
			bf.append("      �ɳ������Ӷ������ " + dp.Found_ratio + "\r\n");
			
			bf.append("         �׵���Ӷ_�ڴ��� " + dp.Yidao_amount_E+"\r\n");
			bf.append("         �׵���Ӷ_ʵ�ʣ� " + dp.Yidao_amount + "\r\n");
			bf.append("         ���޷�Ӷ_�ڴ��� " + dp.Rent_amount_E + "\r\n");
			bf.append("         ���޷�Ӷ_ʵ�ʣ� " + dp.Rent_amount + "\r\n");
			bf.append("         �����Ӷ_�ڴ��� " + dp.Labor_amount_E + "\r\n");
			bf.append("         �����Ӷ_ʵ�ʣ� " + dp.Labor_amount + "\r\n");
			bf.append("         �ɳ���Ӷ_�ڴ��� " + dp.Found_amount_E + "\r\n");
			bf.append("         �ɳ���Ӷ_ʵ�ʣ� " + dp.Found_amount + "\r\n");
			bf.append("         ˾����Ӷ_�ڴ��� " + dp.Driver_amount_E + "\r\n");
			bf.append("         ˾����Ӷ_ʵ�ʣ� " + dp.Driver_amount + "\r\n" + "\r\n");
		}
		if(flag == 1)
		{
			bf.append("��Ӷ�����ȷ"+ "\n");
		}else
		{
			bf.append("��Ӷ���ʧ��"+ "\n");
		}
		return bf;
	}

}
