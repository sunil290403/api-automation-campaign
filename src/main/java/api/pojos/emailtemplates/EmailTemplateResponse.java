package api.pojos.emailtemplates;

import api.pojos.campaigns.Meta;
import api.pojos.campaigns.Pagination;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailTemplateResponse {
    Datum data;
    Pagination pagination;
    List<Error> errors;
    Meta meta;
}
