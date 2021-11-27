package pl.pawit.NYT.Articles;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("article")
@Getter
@Setter
public class ArticleConfigurationProperties {

    private Template template;

    @Getter
    @Setter
    public static class Template{
        private boolean allowArticlesFromTemplate;
    }
}
