package mk.ukim.finki.emtlab1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFound extends RuntimeException{

    public AuthorNotFound(Long id) {
        super(String.format("Author with id: %s was not found", id));
    }

}
