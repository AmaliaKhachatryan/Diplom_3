package databaseuri;

import datauser.DataUser;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UserClient extends BaseURL {
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
