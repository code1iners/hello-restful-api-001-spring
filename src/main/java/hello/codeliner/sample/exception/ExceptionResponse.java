package hello.codeliner.sample.exception;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String details;

    public static ExceptionResponse create(String message, String details) {
        ExceptionResponse ex = new ExceptionResponse();
        ex.setMessage(message);
        ex.setDetails(details);
        return ex;
    }
}
