package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.ConfigReader;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic("GitHub Public API")
@Feature("Authenticated User API")
@Owner("TestAutomation")
@DisplayName("Validate GitHub /user endpoint")
public class UserApiTest extends BaseTest {
    private String token;

    @BeforeAll
    public void loadToken() {
        token = ConfigReader.get("GITHUB_TOKEN");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify authenticated user details with personal access token")
    public void getUserShouldReturn200() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("login", notNullValue())
                .body("id", equalTo(36905911))
                .body("login", containsString("FrankSantillan"))
                .body("url",equalTo("https://api.github.com/users/FrankSantillan"))
                .body("user_view_type", equalTo("private"))
                .body("location",startsWithIgnoringCase("COLIMA"))
                .body("name",nullValue())
                .body("created_at",notNullValue())
                .body("public_repos",greaterThan(0))
                .body("two_factor_authentication",is(false));
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify authenticated user details with personal access token")
    public void getUserShouldReturnError() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("login", notNullValue())
                .body("id", equalTo(36905911))
                .body("login", containsString("FrankSantillan"))
                .body("user_view_type", equalTo("public"));
    }
}