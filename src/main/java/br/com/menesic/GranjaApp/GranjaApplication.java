package br.com.menesic.GranjaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class GranjaApplication
{
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        Locale.setDefault(new Locale("pt", "BR"));
    }
    public static void main(String[] args) {
        SpringApplication.run(GranjaApplication.class, args);
    }
}
