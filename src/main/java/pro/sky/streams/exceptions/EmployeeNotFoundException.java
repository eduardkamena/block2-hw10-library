package pro.sky.streams.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends UnsupportedOperationException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

