package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Ajoutez cette importation

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Je définit la sécurité des requêtes HTTP avec la classe HttpSecurity

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                // Je désactive la protection CSRF
                .csrf(csrf -> csrf.disable())

                // Je définit les requêtes autorisées
                .authorizeRequests(authorize -> {

                    authorize
                        // J'autorise les requêtes correspondant à "/public/**" sans authentification
                        .antMatchers("/public/**").permitAll()

                        // J'exige une authentification pour toutes les autres requêtes
                        .anyRequest().authenticated();
                })

                // J'ajoute le filtre JwtTokenFilter avant le filtre UsernamePasswordAuthenticationFilter
                .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
