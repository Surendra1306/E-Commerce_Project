package in.surendra.Constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "product-service")
@Configuration
public class AppConstants {
	
	public final static String Catg_Succ = "catSucc";
	
	public final static String Catg_Err = "catErr";
	
	public final static String Catg_Fetch ="catgFetch";
	
	public final static String Catg_Fetch_All = "allCatgFetch";
	
	public final static String Catg_Update = "catgUpdate";
	
	public final static String Catg_Delete = "catgDelete";
	
	
	public final static String Prod_Succ = "prodSucc";
	
	public final static String Prod_Err = "ProdErr";
	
	public final static String Prod_Fetch = "prodFetch";
	
	public final static String Prod_Fetch_All = "allProdFetch";
	
	public final static String Prod_Update = "prodUpdate";
	
	public final static String Prod_Delete = "prodDelete";
	
	public final static String Stock_Update = "stockUpdate";

}
