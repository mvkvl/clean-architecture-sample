package ws.slink.ca.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.slink.ca.domain.presentator.SimpleAccountPresentator;
import ws.slink.ca.domain.presentator.StringAccountListPresentator;
import ws.slink.ca.domain.presentator.StringAccountPresentator;

@Configuration
public class BeansConfig {

    @Bean
    SimpleAccountPresentator simpleAccountPresentator() {
        return new SimpleAccountPresentator();
    }
    @Bean
    StringAccountPresentator stringAccountPresentator() {
        return new StringAccountPresentator();
    }

    @Bean
    StringAccountListPresentator stringAccountListPresentator() {
        return new StringAccountListPresentator();
    }

}
