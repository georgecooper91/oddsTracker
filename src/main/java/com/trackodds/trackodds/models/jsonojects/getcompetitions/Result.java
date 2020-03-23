package com.trackodds.trackodds.models.jsonojects.getcompetitions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	
	@JsonProperty("result")
	List<Competitions> result;

	
	 public List<Competitions> getResult() { return result; }
	 
	 public void setResult(List<Competitions> result) { this.result = result; }
	
	
	

}
