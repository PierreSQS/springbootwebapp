package guru.springframework.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
           // ignore CSRF for H2-Console
           httpSecurity
                   .csrf(csrfConfigurer -> csrfConfigurer.ignoringRequestMatchers(PathRequest.toH2Console()))
                   .authorizeHttpRequests(authorize ->
                           authorize.requestMatchers(PathRequest.toH2Console()).permitAll()
                                    .requestMatchers("/","/products","/product/show/*").permitAll()
                                   .anyRequest().authenticated()
                   )
                   .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login").permitAll())
                   .logout(LogoutConfigurer::permitAll);

           httpSecurity.headers(httpHeadersConfigurer ->
                httpHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return httpSecurity.build();
    }


}