package com.meshredded.docker_ucp_light.services;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RestService {

	ClientConfig clientConfig;
	Client client;

	RestService(){
		SSLContext sc = null;
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManagerImpl()};
		try {
		    sc = SSLContext.getInstance("TLS");
		    sc.init(null, trustAllCerts, new SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		    log.error(e.getMessage());
		    e.printStackTrace();
		}
		
		
		client =  ClientBuilder.newBuilder()
                .sslContext(sc)
                .hostnameVerifier((s1, s2) -> true)
                .build();
		
	}

}

class X509TrustManagerImpl implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
