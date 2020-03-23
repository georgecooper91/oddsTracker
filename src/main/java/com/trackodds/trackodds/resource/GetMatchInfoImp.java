package com.trackodds.trackodds.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

import com.trackodds.trackodds.models.makerequest.PriceProjection;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackodds.trackodds.models.jsonobject.marketcatalogue.Catalogue;
import com.trackodds.trackodds.models.jsonobject.marketcatalogue.Market;
import com.trackodds.trackodds.models.jsonobjects.getevents.Event;
import com.trackodds.trackodds.models.jsonobjects.getevents.Events;
import com.trackodds.trackodds.models.jsonojects.getcompetitions.Competitions;
import com.trackodds.trackodds.models.jsonojects.getcompetitions.Result;
import com.trackodds.trackodds.models.makerequest.Filter;
import com.trackodds.trackodds.models.makerequest.Params;
import com.trackodds.trackodds.models.makerequest.ReqComps;


@Service
public class GetMatchInfoImp implements GetMatchInfo {

	Catalogue myCatalogue;
	//MarketRepo marketRepo;
	PriceProjection priceProjection;
	ReqComps reqComps;
	Params params;
	Filter filter;	
	CallBetfair callBetfair;	
	ObjectMapper mapper;
	private Map<String, Object> filters;
	private final String uri = "SportsAPING/v1.0/";
	private String request;
	private String response;
	List<Market> markets;


	List<Market> meakets3;

//	public GetMatchInfoImp(PriceProjection priceProjection, ReqComps reqComps, Params params, Filter filter, CallBetfair callBetfair) {
//		this.priceProjection = priceProjection;
//		this.reqComps = reqComps;
//		this.params = params;
//		this.filter = filter;
//		this.callBetfair = callBetfair;
//	}

//	public GetMatchInfoImp(MarketRepo marketRepo, PriceProjection priceProjection, ReqComps reqComps, Params params, Filter filter, CallBetfair callBetfair) {
//		this.marketRepo = marketRepo;
//		this.priceProjection = priceProjection;
//		this.reqComps = reqComps;
//		this.params = params;
//		this.filter = filter;
//		this.callBetfair = callBetfair;
//	}

		public GetMatchInfoImp(Catalogue catalogue, PriceProjection priceProjection, ReqComps reqComps, Params params, Filter filter, CallBetfair callBetfair) {
		this.myCatalogue = catalogue;
		this.priceProjection = priceProjection;
		this.reqComps = reqComps;
		this.params = params;
		this.filter = filter;
		this.callBetfair = callBetfair;
	}

	@Override
	public List<Competitions> getCompetitions() throws IOException, InterruptedException {
		String method = uri + "listCompetitions";

		filters = filter.getFilters();
		filters.clear();
		filters.put("eventTypeIds", Arrays.asList("1"));
		filters.put("marketCountries", Arrays.asList("IT", "GB", "ES", "DE"));
		filters.put("locale", "en");

		request = createRequest(method);
    	
    	response = callBetfair.makeRequest(request);
		List<Result> result = mapper.readValue(response, new TypeReference<List<Result>>(){});
						
		List<Competitions> comps = result.get(0).getResult();
			
		Comparator<Competitions> byMarketCount = (Competitions comp1, Competitions comp2) 
			-> comp2.getMarketCount() - comp1.getMarketCount();

		Collections.sort(comps, byMarketCount);
		return comps;
	}
	
	private String createRequest(String url) {
		String json = null;
		mapper = new ObjectMapper();
		
		params.setFilter(filter);
		reqComps.setMethod(url);
		reqComps.setParams(params);
		List<ReqComps> reqCompsList = new ArrayList<ReqComps>();
		reqCompsList.add(reqComps);
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqCompsList);
			System.out.println("json to send = " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}	 

	@Override
	public List<Event> getEventsForCompetition(String eventId) throws IOException, InterruptedException {
		String method = uri + "listEvents";
		
		filters = filter.getFilters();
		filters.clear();
		filters.put("eventTypeIds", Arrays.asList("1"));
		filters.put("competitionIds", Arrays.asList(eventId));
		filters.put("locale", "en");
		//params.setFilter(filter);
		request = createRequest(method);
		//response = makeRequest.makeRequest(request);
		response = callBetfair.makeRequest(request);
		List<Events> events = mapper.readValue(response, new TypeReference<List<Events>>(){});
		List<Event> event = events.get(0).getEventList();
		
		Comparator<Event> orderedEvents = (Event event1, Event event2)
				-> event2.getMarketCount() - event1.getMarketCount();		
		Collections.sort(event, orderedEvents);		
		return event;
	}

	@Override
	public List<Market> getMarketsForMatch(String eventId) throws IOException, InterruptedException {
		String method = uri + "listMarketCatalogue";
		
		filters = filter.getFilters();
		filters.clear();
		filters.put("eventIds", Arrays.asList(eventId));
		filters.put("locale", "en");
		params.setMaxResults("200");
		params.setMarketProjection(Arrays.asList("COMPETITION", "EVENT", "EVENT_TYPE", "RUNNER_DESCRIPTION",
				"MARKET_START_TIME"));
		request = createRequest(method);

		
		response = callBetfair.makeRequest(request);
		List<Catalogue> catalogue = mapper.readValue(response, new TypeReference<List<Catalogue>>() {});
		List<Market> markets = catalogue.get(0).getMarket();
		myCatalogue.setMarket(markets);
		Comparator<Market> orderedMarkets = (Market market1, Market market2) ->
		 		(int)market2.getTotalMatched() - (int)market1.getTotalMatched();
		  		
		params.setMaxResults(null);
		params.setMarketProjection(Arrays.asList(""));
		 
		return markets;
		
	}

	@Override
	public List<Market> getPricesForMarkets(String marketId) throws IOException, InterruptedException {
		String method = uri + "listMarketBook";

		filters = filter.getFilters();
		filters.clear();
		//filters.put("eventIds", Arrays.asList(eventId));
		priceProjection.setPriceData(Arrays.asList("SP_AVAILABLE", "EX_BEST_OFFERS", "EX_TRADED"));
		params.setMarketIds(Arrays.asList(marketId));
		params.setPriceProjection(priceProjection);

		request = createRequest(method);

		response = callBetfair.makeRequest(request);
		List<Catalogue> catalogue = mapper.readValue(response, new TypeReference<List<Catalogue>>(){});
		List<Market> markets2 = catalogue.get(0).getMarket();


		mergeMarkest(markets2);

		params.setMaxResults(null);
		params.setMarketProjection(Arrays.asList(""));
		return markets2;
	}

	private List<Market> mergeMarkest(List<Market> prices) {
		List<Market> chosenMarket =  myCatalogue.getMarket().stream()
				.filter(catalogueMarkets -> catalogueMarkets.getMarketId().equals(prices.get(0).getMarketId()))
				.collect(Collectors.toList());

		chosenMarket.get(0).getRunners().forEach(cRunner -> prices.get(0).getRunners().stream()
			.filter(bRunner -> bRunner.getSelectionId() == cRunner.getSelectionId())
			.forEach(match -> match.setRunnerName(cRunner.getRunnerName())));

		prices.get(0).setMarketName(chosenMarket.get(0).getMarketName());
		return prices;
	}
}
