package pro.sky.streams.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends UnsupportedOperationException {
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}

