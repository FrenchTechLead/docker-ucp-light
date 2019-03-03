package com.meshredded.docker_ucp_light.services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import lombok.Getter;

@Getter
public abstract class RestService {
	
	ClientConfig clientConfig;
	Client client;
	
	RestService(){
		clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		client = Client.create(clientConfig);
		client.addFilter(new LoggingFilter(System.out));
		
	}

}
