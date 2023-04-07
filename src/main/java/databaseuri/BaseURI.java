package databaseuri;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseURI {
    protected static final String STELLAR_BURGERS_SITE = "https://stellarburgers.nomoreparties.site/";
    protected static final String USER_ACTIONS = "api/auth/user/";

    protected RequestSpecification getBaseReqSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(STELLAR_BURGERS_SITE)
                .build();
    }
}

