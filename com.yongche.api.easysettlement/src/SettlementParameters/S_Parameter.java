package SettlementParameters;

public class S_Parameter 
{
	public long driver_id;
	public String settlement_id; 
	
	public void set_driver_id(String id)
	{
		this.driver_id = Long.parseLong(id);
	}
	
	public void set_settlement_id(String id)
	{
		this.settlement_id = id;
	}
}
