package com.webtoiec.serverwebtoiec.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    authorities.forEach(authority -> {
      // nếu quyền có vai trò user, chuyển đến trang "/" nếu login thành công
      if (authority.getAuthority().equals("ROLE_MEMBER")) {
        try {
          redirectStrategy.sendRedirect(request, response, "/");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
        try {
          redirectStrategy.sendRedirect(request, response, "/admin");
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        throw new IllegalStateException();

      }
    });
  }
}
