package com.meshredded.docker_ucp_light.services;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.meshredded.docker_ucp_light.models.UcpContainer;
import com.meshredded.docker_ucp_light.models.UcpServer;

public class ContainersService extends RestService {
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<UcpContainer> getContainers(UcpServer s) {
		
		Response r = client.target(s.getEndpoint() + "/containers/json")
        		.request(MediaType.APPLICATION_JSON)
        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + s.getAccessToken())
        		.get();
		
		return r.readEntity(new GenericType<List<UcpContainer>>( ) {});
    }

}
