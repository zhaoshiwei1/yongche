package SettlementParameters;

public class Distribute_Parameters 
{
	public int amount = 0;
	public int other_amount = 0;
	public int Yidao_ratio = 0;
	public int Rent_ratio = 0;
	public int Labor_ratio = 0;
	public int Driver_ratio = 0;
	
	public int Found_ratio = 0;
	
	public int Yidao_amount_E = 0;
	public int Rent_amount_E = 0;
	public int Labor_amount_E = 0;
	public int Driver_amount_E = 0;
	public int Found_amount_E = 0;
	
	public int Yidao_amount = 0;
	public int Rent_amount = 0;
	public int Labor_amount = 0;
	public int Driver_amount = 0;
	public int Found_amount = 0;
	
	public Distribute_Parameters(int amount, int other_amount, int Yidao_ratio, int Rent_ratio, int Labor_ratio, int Driver_ratio
			, int Yidao_amount, int Rent_amount, int Labor_amount, int Driver_amount, int Found_amount)
	{
		this.amount = amount;
		this.other_amount = other_amount;
		this.Yidao_ratio = Yidao_ratio;
		this.Rent_ratio = Rent_ratio;
		this.Labor_ratio = Labor_ratio;
		this.Driver_ratio = Driver_ratio;//司机分佣比例应该是从租赁公司获得， 当前没有找到方法
		
		this.Yidao_amount = Yidao_amount;
		this.Rent_amount = Rent_amount;
		this.Labor_amount = Labor_amount;
		this.Driver_amount = Driver_amount;
		this.Found_amount = Found_amount;
		
		this.Found_ratio = (this.Rent_ratio + this.Labor_ratio)>(10000-this.Driver_ratio)?(this.Rent_ratio+this.Labor_ratio):(10000-this.Driver_ratio);
		this.Found_ratio = this.Found_ratio - this.Rent_ratio - this.Labor_ratio;
		
		this.Yidao_amount_E = this.int_division(this.amount * this.Yidao_ratio, 10000);
		this.Rent_amount_E = this.int_division((this.amount - this.Yidao_amount_E) * this.Rent_ratio, 10000);
		this.Labor_amount_E = this.int_division((this.amount - this.Yidao_amount_E) * this.Labor_ratio, 10000);
//		this.Driver_amount_E = this.int_division((this.amount - this.Yidao_amount_E) * this.Driver_ratio, 10000)+ this.other_amount;
//		this.Found_amount_E = this.amount + this.other_amount - this.Yidao_amount_E - this.Rent_amount_E - this.Labor_amount_E - this.Driver_amount_E;
		this.Found_amount_E = this.int_division((this.amount - this.Yidao_amount_E) * this.Found_ratio, 10000);
		this.Driver_amount_E = this.amount - this.Yidao_amount_E - this.Rent_amount_E - this.Labor_amount_E - this.Found_amount_E + this.other_amount;	
	}
	
	public int int_division(int a, int b)
	{
		int c = a / b; 
		int d = a % b; 
		if(2 * d > b)
		{
			c = c +1; 
		}else
		{
		}
		return c;
	}

}
