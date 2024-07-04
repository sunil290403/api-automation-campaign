package api.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecipientsEndpoints {
    GET_RECIPIENTS("recipients/lists"),
    GET_RECIPIENT_INFO("recipients/lists/{id}");

    private final String path;
}
