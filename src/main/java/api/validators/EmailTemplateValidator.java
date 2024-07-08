package api.validators;

import api.pojos.emailtemplates.Datum;
import api.pojos.emailtemplates.EmailTemplateResponse;
import api.pojos.emailtemplates.EmailTemplatesResponse;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

@SuppressWarnings("unchecked")
public class EmailTemplateValidator extends CampaignValidator{

    public void validateGetAllEmailTemplates(Response response){
        SoftAssert softAssert = new SoftAssert();
        EmailTemplatesResponse emailTemplatesResponse = response.as(EmailTemplatesResponse.class);
        List<Datum> emailTemplates = response.as(EmailTemplatesResponse.class).getData();
        softAssert.assertNotNull(emailTemplates, "emailTemplateResponse is null");
        Datum emailTemplate = emailTemplates.get(0);
        softAssert.assertNotNull(emailTemplate.getId(),"id is null");
        softAssert.assertNotNull(emailTemplate.getName(),"name is null");
        softAssert.assertNotNull(emailTemplate.getData(),"data is null");
        validateMeta(emailTemplatesResponse.getMeta());
    }

    public void validateEmailTemplate(Response response ,String templateId){
        SoftAssert softAssert = new SoftAssert();
        EmailTemplateResponse emailTemplateResponse = response.as(EmailTemplateResponse.class);
        Datum emailTemplate = emailTemplateResponse.getData();
        softAssert.assertEquals(emailTemplate.getId(),templateId,"id mismatch");
        softAssert.assertNotNull(emailTemplate.getName(),"name is null");
        softAssert.assertNotNull(emailTemplate.getData(),"data is null");
        validateMeta(emailTemplateResponse.getMeta());
    }

}
