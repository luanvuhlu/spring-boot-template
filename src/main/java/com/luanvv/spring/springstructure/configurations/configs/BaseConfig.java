package com.luanvv.spring.springstructure.configurations.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import tools.jackson.databind.ObjectMapper;

/**
 * Base configuration class for common beans.
 */
@EnableRetry
@EnableAsync
@EnableJpaAuditing
@Configuration
public class BaseConfig {

  /**
   * Bean for logging HTTP requests.
   *
   * @return a configured CommonsRequestLoggingFilter bean
   */
  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
    loggingFilter.setIncludeClientInfo(true);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setMaxPayloadLength(10000);
    loggingFilter.setIncludeHeaders(false);
    return loggingFilter;
  }

  /**
   * Bean for ModelMapper.
   *
   * @return a configured ModelMapper bean
   */
  @Bean(name = "modelMapper")
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }

  /**
   * Bean for ObjectMapper.
   *
   * @return a configured ObjectMapper bean
   */
  @Bean(name = "objectMapper")
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  /**
   * Bean for RetryTemplate.
   *
   * @return a configured RetryTemplate bean
   */
  @Bean
  public RetryTemplate retryTemplate() {
    RetryTemplate retryTemplate = new RetryTemplate();

    FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(2000L);
    retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
    retryPolicy.setMaxAttempts(2);
    retryTemplate.setRetryPolicy(retryPolicy);

    return retryTemplate;
  }
}
