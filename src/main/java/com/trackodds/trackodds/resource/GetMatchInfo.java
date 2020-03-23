package com.trackodds.trackodds.resource;

import java.io.IOException;
import java.util.List;

import com.trackodds.trackodds.models.jsonobject.marketcatalogue.Market;
import com.trackodds.trackodds.models.jsonobjects.getevents.Event;
import com.trackodds.trackodds.models.jsonojects.getcompetitions.Competitions;

public interface GetMatchInfo {
	
	public List<Competitions> getCompetitions() throws IOException, InterruptedException;
	
	
	  public List<Event> getEventsForCompetition(String eventId) throws IOException,
	  InterruptedException;
	  
	
	  public List<Market> getMarketsForMatch(String eventId) throws IOException,
	  InterruptedException;
	  

	 public List<Market> getPricesForMarkets(String marketId) throws IOException,
	 InterruptedException;


	 

}
