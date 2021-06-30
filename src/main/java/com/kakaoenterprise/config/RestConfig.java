package com.kakaoenterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig{
   @Bean
   RestTemplate restTemplate(){
       SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
       factory.setConnectTimeout(5000);    //5초
       factory.setReadTimeout(5000);   //5초
       return new RestTemplate(factory);
   }
}