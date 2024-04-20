package br.com.menesic.GranjaApp.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class MessageProvider {

    public static final String MESSAGE_FILE = "messages.properties";

    private Properties messageProperties;

    public String getMessage(final ExceptionMessageEnum errorMessage) {
        return Optional.ofNullable(errorMessage)
                .map(errMsg -> messageProperties.getProperty(errorMessage.getMessage(), StringUtils.EMPTY))
                .orElse(StringUtils.EMPTY);
    }

    @PostConstruct
    public void onLoad() throws IOException {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(MESSAGE_FILE)) {
            this.messageProperties = new Properties();
            if (is != null) {
                this.messageProperties.load(is);
            }
        }
    }
}
