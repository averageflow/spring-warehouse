package nl.averageflow.springwarehouse;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@TestConfiguration
public class TestConfigForMail {
    @Bean
    public JavaMailSender mailSender() {
        return new MockMailSender();
    }

    private class MockMailSender extends JavaMailSenderImpl {
    }
}
