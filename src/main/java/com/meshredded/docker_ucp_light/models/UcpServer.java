package com.meshredded.docker_ucp_light.models;

import lombok.Data;

@Data
public class UcpServer {

	String endpoint;
	String username;
	String password;
	String label;
	String accessToken;
	
	public UcpServer(String endpoint, String username, String password, String label) {
		super();
		this.endpoint = endpoint;
		this.username = username;
		this.password = password;
		this.label = label;
	}
	
	
}
