package co.ao.nextbss.requirekt;

import co.ao.nextbss.Yoru;
import java.util.List;

class ErrorWrapper {
    private final List<ErrorViewModel> errors;

    ErrorWrapper(List<ErrorViewModel> errors) {
        this.errors = errors;
    }

    public List<ErrorViewModel> getErrors() {
        return errors;
    }

    public String toJsonString() {
        return new Yoru<ErrorWrapper>().toJson(this);
    }
}
