package api.dataprovider;

import org.testng.annotations.DataProvider;

import java.util.UUID;

import static api.constants.CampaignConstants.*;
import static api.enums.ErrorMessages.*;
import static api.enums.StatusCode.BAD_REQUEST;
import static api.enums.StatusCode.NOT_FOUND;

public class CampaignDp {

    @DataProvider(name = "campaignDp")
    public Object[][] campaignDp() {
        // campaign name , email template id , recipient id , status code, expected error
        return new Object[][]
                {
                        {"", EMAIL_TEMPLATE_ID, RECIPIENT_LIST_ID, BAD_REQUEST, CAMPAIGN_NAME_NOT_BLANK},
                        {null, EMAIL_TEMPLATE_ID, RECIPIENT_LIST_ID, BAD_REQUEST, CAMPAIGN_NAME_NOT_BLANK},
                        {CAMPAIGN_NAME, "", RECIPIENT_LIST_ID, BAD_REQUEST, EMAIL_TEMPLATE_NOT_BLANK},
                        {CAMPAIGN_NAME, null, RECIPIENT_LIST_ID, BAD_REQUEST, EMAIL_TEMPLATE_NOT_BLANK},
                        {CAMPAIGN_NAME, UUID.randomUUID().toString(), RECIPIENT_LIST_ID, NOT_FOUND, EMAIL_TEMPLATE_NOT_FOUND},
                        {CAMPAIGN_NAME, EMAIL_TEMPLATE_ID, "", BAD_REQUEST, RECIPIENT_ID_NOT_BLANK},
                        {CAMPAIGN_NAME, EMAIL_TEMPLATE_ID, null, BAD_REQUEST, RECIPIENT_ID_NOT_BLANK},
                        {CAMPAIGN_NAME, EMAIL_TEMPLATE_ID, UUID.randomUUID().toString(), NOT_FOUND, RECIPIENT_ID_NOT_FOUND}
                };
    }
}