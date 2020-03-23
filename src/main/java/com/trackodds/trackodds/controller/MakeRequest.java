package com.trackodds.trackodds.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trackodds.trackodds.resource.SetupTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.trackodds.trackodds.models.jsonobject.marketcatalogue.Market;
import com.trackodds.trackodds.models.jsonobjects.getevents.Event;
import com.trackodds.trackodds.models.jsonojects.getcompetitions.Competitions;
import com.trackodds.trackodds.resource.GetMatchInfoImp;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MakeRequest {

	GetMatchInfoImp getMatchInfo;
	SetupTracker setupTracker;

	@Autowired
	public MakeRequest(GetMatchInfoImp getMatchInfoImp) {
		 this.getMatchInfo = getMatchInfoImp;
	 }
	 
	@RequestMapping("/trackodds")
	public ModelAndView getTracking() throws IOException, InterruptedException {
	  	Map<String, Object> model = new HashMap<>();
	   	List<Competitions> comps = getMatchInfo.getCompetitions();
	   	model.put("competitions", comps);

	   	return new ModelAndView("tracker", "model", model);
	 }
	
	
	@RequestMapping(path="/trackodds/{id}")
	public ModelAndView getMatchesPage(@PathVariable("id")String eventId) throws IOException, InterruptedException {
		Map<String, Object> modal = new HashMap<>();
		modal.put("url", eventId);
		List<Event> events = getMatchInfo.getEventsForCompetition(eventId);
		modal.put("events", events);
		
		return new ModelAndView("matches", "modal", modal);
	}
	
	
	@RequestMapping(path="/trackodds/{id}/{matchId}")
	public ModelAndView getMatchMarkets(@PathVariable("matchId") String matchId, @PathVariable("id") String id) throws IOException, InterruptedException {
		  Map<String, Object> modal = new HashMap<>(); 
		  modal.put("url", id + "/" + matchId);
		  List<Market> markets = getMatchInfo.getMarketsForMatch(matchId);
		  modal.put("markets", markets);
		  
		  return new ModelAndView("matchMarkets", "modal", modal); 
	  }

	@RequestMapping(value = "/trackodds/{id}/{matchId}/{marketId}",
			method = RequestMethod.POST
	)
	public ModelAndView getMarketPrices(@PathVariable Map<String, String> varsMap,
										@ModelAttribute Market chosenMarket, BindingResult bindingResult) throws IOException, InterruptedException {
		Map<String, Object> modal = new HashMap<>();
		modal.put("url", varsMap);
		List<Market> prices = getMatchInfo.getPricesForMarkets(varsMap.get("marketId"));
		prices.get(0).setMarketName(chosenMarket.getMarketName());
		modal.put("prices", prices);
		prices.get(0).setMarketStartTime(chosenMarket.getMarketStartTime());
		//prices.get(0).setMarketStartTime(chosenMarket.getTotalMatched());

		return new ModelAndView("showPrices", "modal", modal);
	}

	@RequestMapping(value="/trackodds/{id}/{matchId}/{marketId}/{price}",
		method = RequestMethod.POST
	)
	public String trackPrice(@PathVariable Map<String, String> varsMap, RedirectAttributes redirAttrs, long selectionId){
//		Map<String, Object> modal = new HashMap<>();
//		modal.put("selectionId", selectionId);
//		modal.put("url", varsMap);
		String marketIdd = varsMap.get("marketId");
		String pricett = varsMap.get("price");
		setupTracker.trackOddsServ(marketIdd, pricett, selectionId);
		redirAttrs.addAttribute("message", "Now tracking this price");
		return "redirect:/trackodds";
				//"redirect:/trackodds/{id}/{matchId}/{marketId}";
	}


}
