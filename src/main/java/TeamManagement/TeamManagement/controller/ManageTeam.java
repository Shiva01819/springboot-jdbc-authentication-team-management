package TeamManagement.TeamManagement.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import TeamManagement.TeamManagement.model.Team;
import TeamManagement.TeamManagement.service.TeamService;

//Manageteam is user-defined. It is the back-controller.
@RestController
//Define the context or logical-boundary of the application.
//All the url-endpoints has to be accessed through this context only.
@RequestMapping("/IPL2022") //Application Context
public class ManageTeam {
	@Autowired
	TeamService   teamserv;

	ArrayList  <Team>alist=new ArrayList();
	@RequestMapping("/")
	public  ModelAndView  home(HttpServletRequest hsreq, HttpServletResponse hsres) {
		ModelAndView  mv=new ModelAndView();
		mv.setViewName("home");
		System.out.println(hsreq);
		return mv;
	}

	@RequestMapping(value="/list" ) //Receives GET request by default
	public  ModelAndView  teamsList(Model m) //Model  m)
	{
		//Dynamic view
		alist.clear();
		Iterable  <Team> itr= teamserv.getAllTeams();
		Iterator  <Team>teamitr=itr.iterator();
		while (teamitr.hasNext())
			alist.add(teamitr.next());
		System.out.println("*********************");
		//ModelAndView  mav=new ModelAndView();
		//mav.("showTeams", alist);
		m.addAttribute("teams", alist);
//		return new ModelAndView("showTeams", "teams", alist);
		
		ModelAndView  mv=new ModelAndView();
		mv.setViewName("showTeams");
		return mv;
	}
	@GetMapping("/get")
	public ModelAndView getById() {
		ModelAndView  mv=new ModelAndView();
		mv.setViewName("teamInfoBy");
		return mv;
	}
	
	
	
	@GetMapping("/teamById")
	public ModelAndView   getTeamById(@RequestParam("tId") String  teamId, Model m)
	{
		ModelAndView mv=new ModelAndView();
		//mv.setViewName("teamInfo");
		java.util.Optional team=teamserv.findById(teamId);
		Team  t=(Team)team.get();
		//m.addAttribute("team", t);
		mv.addObject("team", t);
		mv.setViewName("teamInfo");
		return mv;
		//return mv;
	}

	@GetMapping("/teamByName")
	public ModelAndView   getTeamByName(@RequestParam("tName") String  teamName, Model m)
	{
		ModelAndView mv=new ModelAndView();
		//mv.setViewName("teamInfo");
		java.util.Optional team=teamserv.findByName(teamName);
		Team  t=(Team)team.get();
		//m.addAttribute("team", t);
		mv.addObject("team", t);
		mv.setViewName("teamInfo");
		System.out.println("*******");
		return mv;
		//return mv;
	}
	
	
	//Only Admin users can call this
	@GetMapping("/addNewTeam")
	public ModelAndView  addNewTeam(Model  m) {
		Team  newteam=new Team("...", ".....", "....");
		m.addAttribute("team", newteam);
		ModelAndView  mv=new ModelAndView();
		mv.setViewName("addTeams");
		return mv;
	}

	@PostMapping(value="/add", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addTeam(Team t)
	{
		teamserv.addTeam(t);
		return  "<b>Added Team</b>";
	}
	//Only admin users can call this.
	@GetMapping(value="/delete")
	public String deleteTeam( @RequestParam("tId")  String teamId)
	{
		teamserv.deleteTeam(teamId);
		return  "<b>Deleted Team</b>";
	}
	//Only admin users can call this.
	@PutMapping(value="/update", consumes="application/json")
	public String updateTeam( @RequestBody Team  t)
	{
		teamserv.updateTeam(t);
		return  "<b>Updated Team</b>";
	}
}
