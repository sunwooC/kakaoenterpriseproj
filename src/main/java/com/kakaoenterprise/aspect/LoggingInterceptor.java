package com.kakaoenterprise.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {

	private final ObjectMapper objectMapper;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
			return;
		}
		final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
		final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
		log.info("Request Body : {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
		log.info("Response Body : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));

	}
}