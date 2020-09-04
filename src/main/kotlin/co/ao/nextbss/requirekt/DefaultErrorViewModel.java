package co.ao.nextbss.requirekt;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class DefaultErrorViewModel implements ErrorResponse {
    private final int status;
    private final String code;
    private final String message;

    public DefaultErrorViewModel(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @NotNull
    @Override
    public String toJSON() {
        DefaultErrorViewModel error = new DefaultErrorViewModel(status, code, message);
        ArrayList<ErrorResponse> list = new ArrayList<>();
        list.add(error);
        return new ErrorWrapper(list).toJsonString();
    }
}