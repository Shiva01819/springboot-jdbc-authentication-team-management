package TeamManagement.TeamManagement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//This will ensure that all the authentication is done using the database
//not using the default username and password 
@Configuration
@EnableWebSecurity
public class Authenticate extends WebSecurityConfigurerAdapter{
	//All these variables must be private only
	//We are giving instructions to the Springboot security to read the info in application.properties
	//file and store it in respective fields. 
	//These fields are used by springsecurity to connect to the database and verify the users.
	@Value("${spring.datasource.driverClassName}")
	private String  databaseDriverClassName;
	
	@Value("${spring.datasource.url}")
	private  String  datasourceUrl;
	
	@Value("${spring.datasource.username}")
	private  String databaseUserName;
	
	@Value("${spring.datasource.password}")
	private  String databasePassword;
	
	
	@Autowired
	DataSource   dbds;
	
	//This class reads the messages from messages.properties file and
	//displays the message in appropriate situations.
	@Autowired
	ResourceBundleMessageSource rbms;
	
	/*Performs the authentication by retrieving username and password from the login page
	and compares it with existing usernames an password in the database.*/
	@Autowired
	public  void configAuthentication(AuthenticationManagerBuilder  ambd) throws Exception
	{
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder>  
		jdbcudmc=ambd.jdbcAuthentication().dataSource(dbds);

		System.out.println("User authenticated......");
	}
	
	//Ask for encoding and encrypting the password. 
	//On the login page actual password must be masked with .....
	@Bean
	public PasswordEncoder  passwordEncoder() {
		return  NoOpPasswordEncoder.getInstance();
	}
	
	//Connects to the database
	@Autowired
	public  DataSource  datasource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(databaseDriverClassName);
		ds.setUrl(datasourceUrl);
		ds.setUsername(databaseUserName);
		ds.setPassword(databasePassword);
		System.out.println("Connected to the database....");
		return ds;
	}
	
	protected void configure(HttpSecurity  http) throws Exception
	{
		/*Normal users and Administrators can call /IPL2022/list
		 * provided they must be authenticated. How authentication is
		 * done ? Using a form. Who all can login ? Everyone who is 
		 * having an account can login. Who all can logout ?
		 * Everyone who has logged in can logout. 
		 * 
		 * anyRequest() PUT or POST or GET or DELETE or TRACE or OPTION
		 */
		
		/*http.authorizeRequests().antMatchers("/home").
		hasAnyAuthority("Administrator", "Normal").
		antMatchers("/IPL2022/list").hasAnyAuthority
		("Administrator", "Normal").antMatchers
		("/IPL2022/addNewTeam").hasAnyAuthority("Administrator").
		antMatchers("/IPL2022/delete").hasAnyAuthority("Administrator").
		antMatchers("/IPL2022/update").hasAnyAuthority("Administrator").
		anyRequest().authenticated().and().
		formLogin().permitAll().and().logout().permitAll();*/
		
		//Shorter code below

		http.authorizeRequests().antMatchers("/").
		hasAnyAuthority("ADMIN", "Normal").
		antMatchers("/IPL2022/list").hasAnyAuthority
		("ADMIN", "Normal").antMatchers
		("/IPL2022/addNewTeam", "/IPL2022/delete", "/IPL2022/update").
		hasAnyAuthority("ADMIN").anyRequest().authenticated().and().
		formLogin().permitAll().and().logout().permitAll();
		System.out.println("Role checked...");
		
		
	}
}
