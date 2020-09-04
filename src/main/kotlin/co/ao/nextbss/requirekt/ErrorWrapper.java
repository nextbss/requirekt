package co.ao.nextbss.requirekt;

import co.ao.nextbss.Yoru;
import java.util.List;

public class ErrorWrapper {
    private final List<ErrorResponse> errors;

    public ErrorWrapper(List<ErrorResponse> errors) {
        this.errors = errors;
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public String toJsonString() {
        return new Yoru<ErrorWrapper>().toJson(this);
    }
}
