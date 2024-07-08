package api.tests;

import api.clients.emailtemplates.EmailTemplateClient;
import api.pojos.emailtemplates.EmailTemplatesResponse;
import api.validators.EmailTemplateValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class EmailTemplateTests {
    private final EmailTemplateClient emailTemplateClient = new EmailTemplateClient();
    private final EmailTemplateValidator emailTemplateValidator = new EmailTemplateValidator();

    @Test(description = "Email Template retrieval", groups = {"E2E"})
    public void emailTemplateRetrieval(){
        //execute
        Response response = emailTemplateClient.getAllEmailTemplates();
        // validate
        String templateId = response.as(EmailTemplatesResponse.class).getData().get(0).getId();
        emailTemplateValidator.validateGetAllEmailTemplates(response);

        //fetch individual template info
        response = emailTemplateClient.getEmailTemplate(templateId);
        emailTemplateValidator.validateEmailTemplate(response,templateId);
    }
}
