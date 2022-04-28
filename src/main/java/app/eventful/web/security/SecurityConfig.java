package app.eventful.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    @Autowired
//    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/user/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .cors()
//                .configurationSource(corsConfigurationSource());
//    }
//
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedMethods(List.of(
//                HttpMethod.GET.name(),
//                HttpMethod.POST.name(),
//                HttpMethod.PATCH.name(),
//                HttpMethod.DELETE.name()
//        ));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
//        return source;
//    }
public void configure(WebSecurity web) throws Exception {
    // TODO: If you are implementing the security requirements, remove this following line
    web.ignoring().antMatchers("/**");
}
}
