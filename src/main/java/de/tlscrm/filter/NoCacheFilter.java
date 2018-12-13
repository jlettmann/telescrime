package de.tlscrm.filter;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ViewExpiredException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ensures no caching for JSF-Resources to avoid {@link ViewExpiredException}. Credit to the
 * wonderful BalusC <a href="https://stackoverflow.com/a/3642969">from here</a>.
 *
 * @author ltegethoff
 *
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class NoCacheFilter implements Filter {

   @Override
   public void doFilter(final ServletRequest request, final ServletResponse response,
         final FilterChain chain) throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest) request;
      HttpServletResponse res = (HttpServletResponse) response;

      if (!req.getRequestURI()
            .startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF
                                                                                       // resources
                                                                                       // (CSS/JS/Images/etc)
         res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
         res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
         res.setDateHeader("Expires", 0); // Proxies.
      }

      chain.doFilter(request, response);
   }

}
