package org.notabarista.service.util.impl;

import java.net.URI;
import java.util.List;

import org.notabarista.entity.response.Response;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.exception.MicroserviceNotFoundException;
import org.notabarista.service.util.IBackendRequestService;
import org.notabarista.service.util.enums.MicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BackendRequestService implements IBackendRequestService {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public URI getServiceUri(String microServiceName) throws AbstractNotabaristaException {

		if (log.isInfoEnabled()) {
			log.info("Getting uRI for micro service name: " + microServiceName);
		}
		if (discoveryClient == null) {
			throw new MicroserviceNotFoundException();
		}

//		getting the URI of the first instance of the found microservices with the same name
		List<ServiceInstance> instances = discoveryClient.getInstances(microServiceName);
		if (!CollectionUtils.isEmpty(instances)) {
			return instances.iterator().next().getUri();
		} else {
			throw new MicroserviceNotFoundException();
		}
	}

	@Override
	public <T> Response<T> executePost(MicroService microService, String uri, Object object,
			ParameterizedTypeReference<Response<T>> parameterizedTypeReference) throws AbstractNotabaristaException {

		URI serviceUri = this.getServiceUri(microService.getMicroserviceName());
		String resourcePath = serviceUri.toString() + uri;
		return executeRequest(resourcePath, object, HttpMethod.POST, parameterizedTypeReference);
	}

	@Override
	public <T> Response<T> executeGet(MicroService microService, String uri, Object object,
			ParameterizedTypeReference<Response<T>> parameterizedTypeReference) throws AbstractNotabaristaException {

		URI serviceUri = this.getServiceUri(microService.getMicroserviceName());
		String resourcePath = serviceUri.toString() + uri;
		return executeRequest(resourcePath, object, HttpMethod.GET, parameterizedTypeReference);
	}
	
	@Override
	public <T> Response<T> executePut(MicroService microService, String uri, Object object,
			ParameterizedTypeReference<Response<T>> parameterizedTypeReference) throws AbstractNotabaristaException {

		URI serviceUri = this.getServiceUri(microService.getMicroserviceName());
		String resourcePath = serviceUri.toString() + uri;
		return executeRequest(resourcePath, object, HttpMethod.PUT, parameterizedTypeReference);
	}
	
	@Override
	public <T> Response<T> executePatch(MicroService microService, String uri, Object object,
			ParameterizedTypeReference<Response<T>> parameterizedTypeReference) throws AbstractNotabaristaException {

		URI serviceUri = this.getServiceUri(microService.getMicroserviceName());
		String resourcePath = serviceUri.toString() + uri;
		return executeRequest(resourcePath, object, HttpMethod.PATCH, parameterizedTypeReference);
	}

	@Override
	public <T> Response<T> executeRequest(String uri, HttpMethod httpMethod, Object object,
			ParameterizedTypeReference<Response<T>> parameterizedTypeReference) {
		return executeRequest(uri, object, httpMethod, parameterizedTypeReference);
	}

	private <T extends Object> Response<T> executeRequest(String uri, Object object, HttpMethod httpMethod,
			ParameterizedTypeReference<Response<T>> parameterizedTypeReference) {

		if (log.isDebugEnabled()) {
			log.debug("Resource path: " + uri);
		}
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			ResponseEntity<Response<T>> response = null;

			if (null != object) {
				HttpEntity<Object> httpEntity = new HttpEntity<Object>(object, headers);
				response = restTemplate.exchange(uri, httpMethod, httpEntity, parameterizedTypeReference);
			} else {
				response = restTemplate.exchange(uri, httpMethod, null, parameterizedTypeReference);
			}

			if (log.isDebugEnabled()) {
				log.debug("Response status: " + response.getStatusCodeValue());
			}
			if (null != response && response.getStatusCodeValue() >= 200 && response.getStatusCodeValue() <= 299 && response.getBody() != null) {
				return response.getBody();
			}
		} catch (Exception e) {
			log.error("Http request error: ", e);
		}
		return null;
	}

}
