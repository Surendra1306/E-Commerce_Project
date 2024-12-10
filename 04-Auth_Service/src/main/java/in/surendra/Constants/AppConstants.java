package in.surendra.Constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth-service-messages")
@Configuration
public class AppConstants{
	
	public final static String reg_Succ = "regSucc";
	
	public final static String reg_Err = "regErr";
	
	public final static String login_Succ = "loginSucc";
	
	public final static String login_Err = "loginErr";
	
}