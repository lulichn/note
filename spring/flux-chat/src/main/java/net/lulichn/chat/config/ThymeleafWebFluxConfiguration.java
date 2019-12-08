package net.lulichn.chat.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnProperty(name = "spring.thymeleaf.enabled", matchIfMissing = true)
public class ThymeleafWebFluxConfiguration {
  ////// 省略 //////
  @Bean
  @ConditionalOnMissingBean(name = "thymeleafReactiveViewResolver")
  public ThymeleafReactiveViewResolver thymeleafViewResolver(
      ISpringWebFluxTemplateEngine templateEngine) {
    ThymeleafReactiveViewResolver resolver = new ThymeleafReactiveViewResolver();
    resolver.setTemplateEngine(templateEngine);
    ////// 省略 //////
    return resolver;
  }
  ////// 省略 //////
}
