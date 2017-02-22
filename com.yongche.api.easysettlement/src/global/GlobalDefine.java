package global;

public class GlobalDefine 
{
	//debug 消息开关
	public static final boolean debug_open = true;
	public static final boolean out_put = false;
	public static final boolean out_to_file = true;
	//结算数据库配置
	public static final String db_url = "jdbc:mysql://10.0.11.141/"; 
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "yongche";  
    public static final String password = ""; 
    
	public static final String db_order = "jdbc:mysql://10.0.11.159/"; 
    public static final String db_name = "com.mysql.jdbc.Driver";  
    public static final String db_user = "order";  
    public static final String db_password = "order"; 
    
    //yc_crm_common 数据库配置
    public static final String crm_db_url = "jdbc:mysql://10.0.11.229/";

    //xls 地址配置
    public static final String FILE_PATH = "F:\\aaa.xls";
    //文件输出 地址配置
    public static final String LOG_PATH = "F:\\log.txt";
}
