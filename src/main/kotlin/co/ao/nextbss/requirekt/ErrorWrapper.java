package co.ao.nextbss.requirekt;

import co.ao.nextbss.Yoru;
import java.util.List;

public class ErrorWrapper {
    private final List<CustomErrorResponse> errors;

    public ErrorWrapper(List<CustomErrorResponse> errors) {
        this.errors = errors;
    }

    public List<CustomErrorResponse> getErrors() {
        return errors;
    }

    public String toJsonString() {
        return new Yoru<ErrorWrapper>().toJson(this);
    }
}
