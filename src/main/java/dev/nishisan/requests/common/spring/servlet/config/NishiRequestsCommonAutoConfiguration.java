package dev.nishisan.requests.common.spring.servlet.config;

import dev.nishisan.requests.common.spring.servlet.ResponseStatusAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(
        name = "nishi.requests.common.enabled",  // O nome da propriedade
        havingValue = "true",                    // Só ative se o valor for "true"
        matchIfMissing = true                    // ❗ ESSENCIAL: Se a propriedade não existir, considere como "true" (ativado por padrão)
)
public class NishiRequestsCommonAutoConfiguration {
    @Bean
    public ResponseStatusAdvice responseStatusAdvice() {
        return new ResponseStatusAdvice();
    }
}
