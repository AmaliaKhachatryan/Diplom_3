package databaseuri;

import datauser.DataUser;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class BaseURI {
    protected static final String STELLAR_BURGERS_SITE = "https://stellarburgers.nomoreparties.site/";
    protected static final String USER_ACTIONS = "api/auth/user/";
    protected static final String CREATE_USER= "api/auth/register/";

    protected RequestSpecification getBaseReqSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(STELLAR_BURGERS_SITE)
                .build();
    }
    @Step("Удалить клиента.")
    public boolean checkRemoveUser(String token) {
              return given()
                .spec(getBaseReqSpec())
                .header("authorization", token)
                .when()
                .delete(USER_ACTIONS)
                .then()
                .assertThat()
                .extract()
                .body()
                .path("message").equals("User successfully removed");
    }
    @Step("Cоздать клиента.")
    public ValidatableResponse createUser(DataUser user) {
              return given()
                .spec(getBaseReqSpec())
                .body(user)
                .when()
                .post(CREATE_USER)
                .then()
                .assertThat()
                .statusCode(200)
                .body("success", is(true));
    }
}

