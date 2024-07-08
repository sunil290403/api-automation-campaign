package api.validators;

import api.pojos.recipients.Datum;
import api.pojos.recipients.RecipientResponse;
import api.pojos.recipients.RecipientsResponse;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class RecipientValidator extends CampaignValidator{
    public void validateAllRecipients(Response response){
        SoftAssert softAssert = new SoftAssert();
        RecipientsResponse recipientsResponse = response.as(RecipientsResponse.class);
        List<Datum> recipients = recipientsResponse.getData();
        softAssert.assertNotNull(recipients, "emailTemplateResponse is null");
        Datum recipient = recipients.get(0);
        softAssert.assertNotNull(recipient.getId(),"id is null");
        softAssert.assertNotNull(recipient.getName(),"name is null");
        softAssert.assertNotNull(recipient.getRecipients(),"recipients is null");
        validateMeta(recipientsResponse.getMeta());
    }

    public void validateRecipient(Response response ,String recipientId){
        SoftAssert softAssert = new SoftAssert();
        RecipientResponse recipientResponse = response.as(RecipientResponse.class);
        Datum recipient = recipientResponse.getData();
        softAssert.assertEquals(recipient.getId(),recipientId,"recipientId mismatch");
        softAssert.assertNotNull(recipient.getName(),"name is null");
        softAssert.assertNotNull(recipient.getRecipients(),"recipients is null");
        validateMeta(recipientResponse.getMeta());
    }
}
