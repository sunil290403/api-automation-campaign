package api.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTempEndpoints {
    GET_RECIPIENTS("email/templates"),
    GET_RECIPIENT_INFO("email/templates/{id}");

    private final String path;
}
