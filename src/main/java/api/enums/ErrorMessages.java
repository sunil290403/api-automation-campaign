package api.enums;

import api.pojos.IErrorResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorMessages implements IErrorResponse {
    CAMPAIGN_NOT_FOUND("CAM-E-001","Campaign not found",null,ApiStatus.FAILURE),
    CAMPAIGN_NAME_NOT_BLANK("BAS-E-002","Input Validation Error","campaignName: must not be blank",ApiStatus.FAILURE),
    EMAIL_TEMPLATE_NOT_BLANK("BAS-E-002","Input Validation Error","emailTemplateId: must not be blank",ApiStatus.FAILURE),
    RECIPIENT_ID_NOT_BLANK("BAS-E-002","Input Validation Error","campaignName: must not be blank",ApiStatus.FAILURE),
    RECIPIENT_ID_NOT_FOUND("CAM-E-003","Recipient List Not Found","campaignName: must not be blank",ApiStatus.FAILURE),
    EMAIL_TEMPLATE_NOT_FOUND("CAM-E-002","Email Template Not Found",null,ApiStatus.FAILURE);
    final String errorCode;
    final String message;
    final String detail;
    final ApiStatus status;
}
