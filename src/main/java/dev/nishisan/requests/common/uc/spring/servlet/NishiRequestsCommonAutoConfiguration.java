package dev.nishisan.requests.common.uc.spring.servlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class NishiRequestsCommonAutoConfiguration {
    @Bean
    public ResponseStatusAdvice responseStatusAdvice() {
        return new ResponseStatusAdvice();
    }
}
