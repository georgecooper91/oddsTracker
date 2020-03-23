package com.trackodds.trackodds.models.makerequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class ReqComps {
	
	@JsonProperty("jsonrpc")
	private String jsonrpc = "2.0";
	
	@JsonProperty("method")
	private String method;
	
	@JsonProperty("id")
	private int id = 1;
	
	@JsonProperty("params")
	private Params params; 

	public String getJsonrpc() {
		return jsonrpc;
	}

	/*
	 * public void setJsonrpc(String jsonrpc) { this.jsonrpc = jsonrpc; }
	 */

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getId() {
		return id;
	}

	/*
	 * public void setId(int id) { this.id = id; }
	 */

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}
	
	
	
}
