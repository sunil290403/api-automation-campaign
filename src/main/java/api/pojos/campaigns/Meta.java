package api.pojos.campaigns;

import api.enums.ApiStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meta{
     ApiStatus status;
     String timestamp;
}