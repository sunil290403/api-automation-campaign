package api.pojos;

import api.enums.ApiStatus;
import api.pojos.campaigns.Meta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    List<Error> errors;
    Meta meta;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Error{
        String errorCode;
        String message;
        String detail;
        ApiStatus status;
    }
}
