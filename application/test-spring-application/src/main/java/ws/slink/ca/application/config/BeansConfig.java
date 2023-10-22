package ws.slink.ca.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.slink.ca.api.presentator.AccountListPresentator;
import ws.slink.ca.api.presentator.AccountPresentator;
import ws.slink.ca.domain.presentator.AccountPresentatorFactory;
import ws.slink.ca.domain.presentator.PresentatorType;

import java.util.Map;

@Configuration
@SuppressWarnings("unchecked")
public class BeansConfig {

    @Bean
    AccountPresentatorFactory accountPresentatorFactory() {
        return new AccountPresentatorFactory();
    }

    @Bean
    AccountPresentator<String> stringAccountPresentator() {
        return accountPresentatorFactory().createItemPresentator(PresentatorType.STRING);
    }
    @Bean
    AccountPresentator<Map<String, String>> simpleAccountPresentator() {
        return accountPresentatorFactory().createItemPresentator(PresentatorType.SIMPLE);
    }
    @Bean
    AccountListPresentator<String> stringListAccountPresentator() {
        return accountPresentatorFactory().createCollectionPresentator(PresentatorType.STRING_LIST);
    }

}
