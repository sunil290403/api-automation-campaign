package api.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTempEndpoints {
    GET_ALL_EMAIL_TEMPLATES("email/templates"),
    GET_EMAIL_TEMPLATE("email/templates/{id}");

    private final String path;
}
