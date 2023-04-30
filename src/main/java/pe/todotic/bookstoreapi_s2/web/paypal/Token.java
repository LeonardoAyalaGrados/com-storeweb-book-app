package pe.todotic.bookstoreapi_s2.web.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Token {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("access_type")
    private String accessType;
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("expires_in")
    private Long expiresIn;
}
