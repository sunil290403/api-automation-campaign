package api.helpers;

import api.pojos.campaigns.CreateCampaignRequest;
import api.utils.Builder;

public class CampaignHelper {

    public CreateCampaignRequest getCampaignRequest(String campaignName, String emailTemplateId, String recId){
        return Builder.build(CreateCampaignRequest.class).with(r->{
         r.setCampaignName(campaignName);
         r.setEmailTemplateId(emailTemplateId);
         r.setRecipientListId(recId);
         r.setScheduledTime(0);
        }).get();
    }
}
