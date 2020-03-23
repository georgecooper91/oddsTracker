package com.trackodds.trackodds.controller;


import java.io.IOException;

import java.security.Principal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.trackodds.trackodds.models.User;
import com.trackodds.trackodds.models.jsonojects.getcompetitions.Competitions;
import com.trackodds.trackodds.resource.AddUserService;
import com.trackodds.trackodds.resource.AddUserValidator;
import com.trackodds.trackodds.resource.GetMatchInfoImp;

@Controller
public class HomeResource {
		
	 @Autowired 
	 AddUserService addUserService;
	 	 
	/*
	 * GetMatchInfoImp getMatchinfo;
	 * 
	 * public HomeResource(GetMatchInfoImp getCompetitions) { this.getMatchinfo =
	 * getCompetitions; }
	 */

	@RequestMapping("/")
    public ModelAndView home(){
    	String model = "Welcome!!";
        return new ModelAndView("index", "model", model);
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
    
    @RequestMapping("/signup")
    public ModelAndView signUp() {
    	return new ModelAndView("signup", "user", new User());
    }
    
    @RequestMapping(value="/newuser", method = RequestMethod.POST)
    public ModelAndView newUser(@ModelAttribute @Valid User newUser, BindingResult bindingResult) {
    	new AddUserValidator().validate(newUser, bindingResult);
    	if(bindingResult.hasErrors()) {
    		return new ModelAndView("signup");
    	} else if(addUserService.isUserAlreadyPresent(newUser.getUserName())) {
    		return new ModelAndView("signup");
    	} else {
    		addUserService.saveUser(newUser);
    		return new ModelAndView("profile", "user", newUser);
    	}
    }
    //Principal principal principal.getName()
    @RequestMapping("/profile")
	public ModelAndView getProfile() {
    	String user = "";
		return new ModelAndView("profile", "user", user);
	}
    
	/*
	 * @RequestMapping("/trackodds") public ModelAndView getTracking() throws
	 * IOException, InterruptedException { Map<String, Object> model = new
	 * HashMap<>(); List<Competitions> comps = getMatchinfo.getCompetitions();
	 * model.put("competitions", comps);
	 * 
	 * return new ModelAndView("tracker", "model", model); }
	 */
    
    @RequestMapping(value="/logout")
    public String getLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/login";
    }
}
