package TeamManagement.TeamManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teams")
public class Team {
	@Id
	@Column(name="teamid")
	private String teamId;
	@Column(name="teamname")
	private String  teamName;
	@Column(name="baselocation")
	private String  homeCity;
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public Team() {
		super();
	}
	public Team(String teamId, String teamName, String homeCity) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.homeCity = homeCity;
	}
	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", homeCity=" + homeCity + "]";
	}
}
