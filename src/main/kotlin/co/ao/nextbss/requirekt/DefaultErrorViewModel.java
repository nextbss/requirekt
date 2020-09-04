package co.ao.nextbss.requirekt;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class CustomErrorViewModel implements CustomErrorResponse {
    private final int status;
    private final String code;
    private final String message;

    public CustomErrorViewModel(int status, String code, String message) {
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
        CustomErrorViewModel errorViewModel = new CustomErrorViewModel(status, code, message);
        ArrayList<CustomErrorResponse> viewModels = new ArrayList<>();
        viewModels.add(errorViewModel);
        return new ErrorWrapper(viewModels).toJsonString();
    }
}