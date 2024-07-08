package api.pojos.campaigns;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignResponse {
     Data data;
     Pagination pagination;
     List<Error> errors;
     Meta meta;
}
