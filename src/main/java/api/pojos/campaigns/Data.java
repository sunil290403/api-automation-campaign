package api.pojos.campaigns;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Data{
     String id;
     String campaignName;
     String emailTemplateId;
     String recipientListId;
     int scheduledTime;
}
