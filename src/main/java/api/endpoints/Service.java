package api.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Service {
    CAMPAIGN("http://localhost:7070"),
    EMAIL_TEMPLATE("http://localhost:7071"),
    RECIPIENTS("http://localhost:7072");

    private final String uri;
}
