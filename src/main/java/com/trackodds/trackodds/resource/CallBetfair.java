package com.trackodds.trackodds.resource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Service;

@Service
public class CallBetfair {


	 public String makeRequest(String json) throws IOException, InterruptedException {
		 HttpClient client = HttpClient.newHttpClient();
		 HttpRequest request = HttpRequest.newBuilder()
				 .uri(URI.create("https://api.betfair.com/exchange/betting/json-rpc/v1/"))
				 .header("content-type", "application/json")
				 .header("X-Application", System.getenv("BET-X-Application"))
				 .header("X-Authentication", System.getenv("BET-X-Auth"))
				 .POST(BodyPublishers.ofString(json))
				 .build();
	  
		  HttpResponse<String> response = 
				  client.send(request, BodyHandlers.ofString()); 
		  System.out.println(response.statusCode());
		  System.out.println(response.body()); 
		  return response.body(); 
	  }

}
