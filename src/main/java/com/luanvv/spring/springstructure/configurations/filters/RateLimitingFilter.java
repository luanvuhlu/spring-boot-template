package com.luanvv.spring.springstructure.configurations.filters;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import org.springframework.web.filter.OncePerRequestFilter;

public class RateLimitingFilter extends OncePerRequestFilter {

  private final Bucket bucket;

  public RateLimitingFilter() {
    var limit = Bandwidth.builder()
        .capacity(100)
        .refillGreedy(100, Duration.ofSeconds(1))
        .build();
    this.bucket = Bucket.builder().addLimit(limit).build();
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (bucket.tryConsume(1)) {
      filterChain.doFilter(request, response);
    } else {
      response.setStatus(429);
      response.getWriter().write("Too many requests");
    }
  }
}
