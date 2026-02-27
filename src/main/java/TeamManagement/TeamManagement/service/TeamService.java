package TeamManagement.TeamManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TeamManagement.TeamManagement.model.Team;
import TeamManagement.TeamManagement.repository.TeamRepository;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;

@Service
public class TeamService {
	@Autowired
	TeamRepository  teamrep;
	public Team  findTeam(String  teamId)
	{
		java.util.Optional<Team>  opt= teamrep.findById(teamId);
		System.out.println("Retrieved a team...");
		return  opt.get();
	}
	public void  addTeam(Team  t) {
		teamrep.save(t);
		System.out.println("Inserted a team...");
	}
	public  Iterable<Team>  getAllTeams() {
		Iterable<Team> itr=teamrep.findAll();
		System.out.println("Retrieved all teams....");
		return itr;
	}
	public java.util.Optional<Team>  findById(String  teamId){
		java.util.Optional<Team>  teamInfo = teamrep.findById(teamId);
		return  teamInfo;
	}
	public void deleteTeam(String teamId) {
		// TODO Auto-generated method stub
		teamrep.deleteById(teamId);
	}
	public void updateTeam(Team t) {
		// TODO Auto-generated method stub
		teamrep.save(t);
	}
	public java.util.Optional findByName(String teamName) {
		java.util.Optional<Team>  teamInfo = teamrep.findByName(teamName);
		System.out.println("---------");
		return  teamInfo;
	}
}
