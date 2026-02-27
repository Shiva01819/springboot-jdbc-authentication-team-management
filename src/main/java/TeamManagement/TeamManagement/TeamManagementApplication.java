package TeamManagement.TeamManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;

@SpringBootApplication
public class TeamManagementApplication {
	public static void main(String[] args) {
		System.out.println(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		SpringApplication.run(TeamManagementApplication.class, args);
	}
}