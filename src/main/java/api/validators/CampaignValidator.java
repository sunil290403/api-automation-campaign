package api.validators;

import api.enums.ApiStatus;
import api.enums.StatusCode;
import api.pojos.ErrorResponse;
import api.pojos.IErrorResponse;
import api.pojos.campaigns.CampaignResponse;
import api.pojos.campaigns.Meta;
import api.pojos.campaigns.CreateCampaignRequest;
import api.pojos.campaigns.Data;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static api.enums.StatusCode.CREATED;

public class CampaignValidator {

    public void validateCampaignCreation(CreateCampaignRequest request, Response response, StatusCode statusCode , IErrorResponse expectedError){
        SoftAssert softAssert= new SoftAssert();
        if(statusCode==CREATED){
            CampaignResponse campaignResponse = response.as(CampaignResponse.class);
            Data campaign = campaignResponse.getData();
            softAssert.assertNotNull(campaign.getId(),"campaign id is null");
            softAssert.assertEquals(campaign.getCampaignName(), request.getCampaignName(),"campaign name mismatch");
            softAssert.assertEquals(campaign.getEmailTemplateId(), request.getCampaignName(),"email template id mismatch");
            softAssert.assertEquals(campaign.getRecipientListId(), request.getCampaignName(),"recipient list id   mismatch");
            softAssert.assertEquals(campaign.getScheduledTime(), request.getScheduledTime(),"schedule time mismatch");
            validateMeta(campaignResponse.getMeta());
        }else{
            //To-Do : validations for negative scenarios
             validateNegativeCases(response,expectedError);
        }
    }
    public void validateNegativeCases(Response response , IErrorResponse expectedError){
        SoftAssert softAssert = new SoftAssert();
        ErrorResponse.Error error = response.as(ErrorResponse.class).getErrors().get(0);
        softAssert.assertEquals(error.getErrorCode() , expectedError.getErrorCode(),"error code mismatch");
        softAssert.assertEquals(error.getMessage() , expectedError.getMessage(),"error message mismatch");
        if(expectedError.getDetail()!=null && error.getDetail()!=null) {
            softAssert.assertEquals(error.getDetail(), expectedError.getDetail(), "error detail mismatch");
        }
        softAssert.assertEquals(error.getStatus() , expectedError.getStatus(),"error status mismatch");
    }

    public void validateMeta(Meta meta){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(meta.getStatus(), ApiStatus.SUCCESS,"api status mismatch");
        softAssert.assertTrue(isValidTimeStamp(meta.getTimestamp()),"api status mismatch");
        softAssert.assertAll();
    }
    public boolean isValidTimeStamp(String timestamp) {
        String TIMESTAMP_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$";
        // Validate the timestamp format using a regular expression
        Pattern pattern = Pattern.compile(TIMESTAMP_REGEX);
        Matcher matcher = pattern.matcher(timestamp);
        return matcher.matches();
    }
}
