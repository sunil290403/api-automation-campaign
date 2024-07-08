package api.pojos.emailtemplates;

import api.pojos.campaigns.Meta;
import api.pojos.campaigns.Pagination;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailTemplatesResponse {
     List<Datum> data;
     Pagination pagination;
     ArrayList<Error> errors;
     Meta meta;
}
