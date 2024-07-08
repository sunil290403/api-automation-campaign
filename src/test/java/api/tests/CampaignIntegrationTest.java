package api.tests;

import api.clients.campaigns.CampaignClient;
import api.dataprovider.CampaignDp;
import api.enums.StatusCode;
import api.helpers.CampaignHelper;
import api.pojos.IErrorResponse;
import api.pojos.campaigns.CreateCampaignRequest;
import api.validators.CampaignValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static api.constants.CampaignConstants.*;
import static api.enums.StatusCode.CREATED;

public class CampaignIntegrationTest {
    private final CampaignHelper campaignHelper = new CampaignHelper();
    private final CampaignClient campaignClient = new CampaignClient();
    private final CampaignValidator campaignValidator = new CampaignValidator();

    @Test(description = "Integration Test for campaign , email template and recipients endpoints", groups = {"sanity" , "regression"}
                 ,dataProviderClass = CampaignDp.class , dataProvider = "campaignDp")
    public void campaignIntegrationTests(String campaignName , String emailTemplateId , String recipientId, StatusCode statusCode, IErrorResponse expectedError){
        //setup
        CreateCampaignRequest request = campaignHelper.getCampaignRequest(campaignName,emailTemplateId,recipientId);
        //execute
        Response response =  campaignClient.createCampaign(request, statusCode);
        //validate
        campaignValidator.validateCampaignCreation(request,response,statusCode,expectedError);
    }
}
