package vttp2022.paf.day15;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vttp2022.paf.day15.filters.AuthenticationFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registerFilters() {
        // Create an instance of authentication filter
        AuthenticationFilter authFilter = new AuthenticationFilter();

        // Create an instance of registration filter
        FilterRegistrationBean<AuthenticationFilter> regFilter = new FilterRegistrationBean<>();
        regFilter.setFilter(authFilter);
        regFilter.addUrlPatterns("/protected/*");
        
        return regFilter;
    }
    
}
