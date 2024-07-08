package api.pojos;

import api.enums.ApiStatus;

public interface IErrorResponse {
    String getErrorCode();
    String getMessage();
    String getDetail();
    ApiStatus getStatus();
}
