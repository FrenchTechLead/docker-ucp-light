package com.meshredded.docker_ucp_light.services;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.meshredded.docker_ucp_light.models.AuthToken;
import com.meshredded.docker_ucp_light.models.UcpServer;
import com.meshredded.docker_ucp_light.models.UsernamePassword;

public class AuthService extends RestService {
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public AuthToken getAuthToken(UcpServer s) {
		Response r = client.target(s.getEndpoint() + "/auth/login")
        		.request(MediaType.APPLICATION_JSON)
        		.post(Entity.entity(new UsernamePassword(s.getUsername(), s.getPassword()), MediaType.APPLICATION_JSON));
		
		return r.readEntity(AuthToken.class);
    }

}
