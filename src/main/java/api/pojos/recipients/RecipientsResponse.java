package api.pojos.recipients;

import api.pojos.campaigns.Meta;
import api.pojos.campaigns.Pagination;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipientsResponse {
    List<Datum> data;
    Pagination pagination;
    ArrayList<Error> errors;
    Meta meta;
}
