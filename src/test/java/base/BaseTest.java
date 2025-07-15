package base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;
import utils.ConfigReader;


public class BaseTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.get("ENDPOINT_API"); // Example API

        // Attach Allure logger to every request
        RestAssured.filters(new AllureRestAssured());
    }


}
