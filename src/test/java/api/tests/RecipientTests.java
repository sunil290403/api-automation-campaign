package api.tests;

import api.clients.reciepients.RecipientClient;
import api.endpoints.Service;
import api.pojos.recipients.RecipientsResponse;
import api.validators.RecipientValidator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class RecipientTests {
    private final RecipientClient recipientClient= new RecipientClient();
    private final RecipientValidator recipientValidator = new RecipientValidator();

    @Test(description = "Recipient info Retrieval", groups = {"E2E"})
    public void RecipientInfoRetrieval(){

        //execute
        Response response = recipientClient.getAllRecipients();
        // validate
        String recipientId = response.as(RecipientsResponse.class).getData().get(0).getId();
        recipientValidator.validateAllRecipients(response);

        //fetch Recipient info
        response = recipientClient.getRecipientId(recipientId);
        recipientValidator.validateRecipient(response,recipientId);
    }
}
