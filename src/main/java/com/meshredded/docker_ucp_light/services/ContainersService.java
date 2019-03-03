package com.meshredded.docker_ucp_light.services;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.meshredded.docker_ucp_light.models.UcpContainer;
import com.meshredded.docker_ucp_light.models.UcpServer;
import com.sun.jersey.api.client.GenericType;

public class ContainersService extends RestService {
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<UcpContainer> getContainers(UcpServer s) {
		
        return client.resource(s.getEndpoint() + "/containers/json")
        		.type(MediaType.APPLICATION_JSON)
        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + s.getAccessToken())
        		.get(new GenericType<List<UcpContainer>>() {});
    }

}
