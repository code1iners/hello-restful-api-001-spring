package hello.codeliner.sample.hello;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final MessageSource messageSource;
    

    @GetMapping(path = "/hello-international")
    public String helloInternational(
        @RequestHeader(name = "Accept-Language", required=false) Locale locale) {
            String message = messageSource.getMessage("greeting.message", null, locale);
        return message;
    }
}
