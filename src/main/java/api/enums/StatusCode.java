package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    UNSUPPORTED_MEDIA_TYPE(415),
    UNPROCESSABLE_ENTITY(422),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503),
    PRECONDITION_FAILED(412),
    EXPECTATION_FAILED(417),
    PRECONDITION_REQUIRED(428),
    NOT_ACCEPTABLE(406),
    NOT_IMPLEMENTED(501),
    NO_DEFINITION(420);

    private final Integer code;
}