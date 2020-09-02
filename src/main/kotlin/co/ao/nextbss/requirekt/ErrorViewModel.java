package co.ao.nextbss.requirekt;

import co.ao.nextbss.Yoru;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorViewModel {
    private final int status;
    private final String code;
    private final String message;

    public static String singleJSON(HttpStatus status, String errorCode, String message) {
        ErrorViewModel errorViewModel = new ErrorViewModel(status, errorCode, message);
        ArrayList<ErrorViewModel> viewModels = new ArrayList<>();
        viewModels.add(errorViewModel);
        return new ErrorWrapper(viewModels).toJsonString();
    }

    public static String singleJSON(HttpStatus status, String message) {
        ErrorViewModel errorViewModel = new ErrorViewModel(status, "", message);
        ArrayList<ErrorViewModel> viewModels = new ArrayList<>();
        viewModels.add(errorViewModel);
        return new ErrorWrapper(viewModels).toJsonString();
    }

    public static List<ErrorViewModel> single(HttpStatus status, String errorCode, String message) {
        return Collections.singletonList(new ErrorViewModel(status, errorCode, message));
    }

    private ErrorViewModel(HttpStatus status, String code, String message) {
        this.status = status.value();
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

    public String toJsonString() {
        return new Yoru<ErrorViewModel>().toJson(this);
    }

    public static String listToJsonString(List<ErrorViewModel> errors) {
        return new Yoru<List<ErrorViewModel>>().toJson(errors);
    }
}