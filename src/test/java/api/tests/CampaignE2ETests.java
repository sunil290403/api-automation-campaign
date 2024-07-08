package api.tests;

import api.clients.campaigns.CampaignClient;
import api.helpers.CampaignHelper;
import api.pojos.campaigns.CampaignResponse;
import api.pojos.campaigns.CreateCampaignRequest;
import api.validators.CampaignValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static api.constants.CampaignConstants.*;
import static api.enums.StatusCode.CREATED;
import static api.enums.StatusCode.OK;
import static org.testng.Assert.assertEquals;

public class CampaignE2ETests {
    private final CampaignHelper campaignHelper = new CampaignHelper();
    private final CampaignClient campaignClient = new CampaignClient();
    private final CampaignValidator campaignValidator = new CampaignValidator();

    @Test(description = "Campaign Flow E2E test", groups = {"E2E"})
    public void verifyCampaignFlow() {

        //setup
        CreateCampaignRequest request = campaignHelper.getCampaignRequest(CAMPAIGN_NAME, EMAIL_TEMPLATE_ID, RECIPIENT_LIST_ID);
        //execute
        Response response = campaignClient.createCampaign(request, CREATED);
        //validate
        String campaignId = response.as(CampaignResponse.class).getData().getId();
        campaignValidator.validateCampaignCreation(request, response, CREATED, null);

        //update the campaign
        //execute
        CampaignResponse campaignResponse = campaignClient.updateCampaign(campaignId, CAMPAIGN_NAME_2, OK).as(CampaignResponse.class);
        //validate
        assertEquals(campaignResponse.getData().getCampaignName(), CAMPAIGN_NAME_2, "modified campaign name mismatch");
        //Restore to original campaign
        campaignResponse = campaignClient.updateCampaign(campaignId, CAMPAIGN_NAME, OK).as(CampaignResponse.class);
        //validate
        assertEquals(campaignResponse.getData().getCampaignName(), CAMPAIGN_NAME, "modified campaign name mismatch");

        //fetch the campaign
        //execute
        campaignResponse = campaignClient.getCampaign(campaignId, OK).as(CampaignResponse.class);
        //validate
        assertEquals(campaignResponse.getData().getId(), campaignId, "campaignId mismatch");

    }
}
