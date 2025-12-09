package com.luanvv.spring.springstructure.configurations.configs.web;

import com.luanvv.spring.springstructure.configurations.formatters.DateFormatter;
import com.luanvv.spring.springstructure.configurations.formatters.LocalDateTimeFormatAnnotationFormatterFactory;
import com.luanvv.spring.springstructure.configurations.formatters.StringTrimmerConverter;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport implements ApplicationContextAware {

  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
      "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable("default");
  }

  @Bean
  public DateFormatter dateFormatter() {
    return new DateFormatter();
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatterForFieldAnnotation(new LocalDateTimeFormatAnnotationFormatterFactory());
    registry.addConverter(new StringTrimmerConverter(true));
    super.addFormatters(registry);
  }
}
