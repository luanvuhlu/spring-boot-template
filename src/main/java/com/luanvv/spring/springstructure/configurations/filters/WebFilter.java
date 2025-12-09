package com.luanvv.spring.springstructure.configurations.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class WebFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // Do not need implement
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    chain.doFilter(req, response);
  }

  public void destroy() {
    // Do not need implement
  }
}
