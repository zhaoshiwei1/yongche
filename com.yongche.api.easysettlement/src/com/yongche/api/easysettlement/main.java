package com.yongche.api.easysettlement;

import global.GlobalDefine;

import java.util.List;

import log.Log;
import Exceluitl.ExcelUtil;
import SettlementParameters.S_Parameter;
import db.DBselector;
import distribute.CheckDistribute;

public class main 
{
	
	//test message for git
	//test message for git
	public static void main(String[] args) throws Exception
	{
		Log.e("Start!"+"\n");
		StringBuffer buf = new StringBuffer("");
		List<S_Parameter> params = ExcelUtil.read(GlobalDefine.FILE_PATH);
		int row = 1;
		for(S_Parameter pa : params)
		{	
			buf.append("�����ţ� " + pa.settlement_id + "\r\n" + "\r\n");
			buf.append(CheckDistribute.c_distribute(pa.settlement_id));
			buf.append("\r\n"+"\r\n"+"****************************************************************************"+"\r\n");
			row +=1;
		}
	
		Log.o(buf.toString());
		Log.out_to_file(GlobalDefine.LOG_PATH, buf);
	}
	
}
