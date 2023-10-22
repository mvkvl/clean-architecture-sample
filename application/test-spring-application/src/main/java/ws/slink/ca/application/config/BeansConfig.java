package ws.slink.ca.application.config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ws.slink.ca.api.datastore.AccountDataStore;
import ws.slink.ca.api.presentator.AccountListPresentator;
import ws.slink.ca.api.presentator.AccountPresentator;
import ws.slink.ca.domain.presentator.AccountPresentatorFactory;
import ws.slink.ca.domain.presentator.PresentatorType;
import ws.slink.ca.framework.java.ds.InMemAccountDataStore;

import java.lang.annotation.Annotation;
import java.util.Map;

@Slf4j
@Configuration
@SuppressWarnings("unchecked")
public class BeansConfig {

    @Component
    public static class AutowireCandidateResolverConfigurer implements BeanFactoryPostProcessor {
        private static class EnvironmentAwareQualifierAnnotationAutowireCandidateResolver extends QualifierAnnotationAutowireCandidateResolver {

            private static class ResolvedQualifier implements Qualifier {
                private final String value;

                ResolvedQualifier(String value) { this.value = value; }

                @NonNull
                @Override
                public String value() { return this.value; }
                @Override
                public Class<? extends Annotation> annotationType() { return Qualifier.class; }

            }

            @Override
            protected boolean checkQualifier(@NonNull BeanDefinitionHolder bdHolder,
                                             @NonNull Annotation annotation,
                                             @NonNull TypeConverter typeConverter) {
                if (annotation instanceof Qualifier qualifier && (qualifier.value().startsWith("${") && qualifier.value().endsWith("}"))) {
                        DefaultListableBeanFactory bf = (DefaultListableBeanFactory) this.getBeanFactory();
                        ResolvedQualifier resolvedQualifier = new ResolvedQualifier(bf.resolveEmbeddedValue(qualifier.value()));
                        return super.checkQualifier(bdHolder, resolvedQualifier, typeConverter);

                }
                return super.checkQualifier(bdHolder, annotation, typeConverter);
            }
        }
        @Override
        public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
            DefaultListableBeanFactory bf = (DefaultListableBeanFactory) beanFactory;
            bf.setAutowireCandidateResolver(new EnvironmentAwareQualifierAnnotationAutowireCandidateResolver());
        }
    }

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

    @Bean
    @Qualifier("inmem")
    @ConditionalOnProperty(value = "ca.account.datastore.type", havingValue = "inmem")
    AccountDataStore inMemAccountDataStore() {
        log.info("Instantiating in-mem account data store");
        return new InMemAccountDataStore();
    }

}
