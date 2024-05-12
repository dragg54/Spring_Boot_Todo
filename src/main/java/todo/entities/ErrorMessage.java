package todo.entities;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    public HttpStatus status;
    public String message;
}
