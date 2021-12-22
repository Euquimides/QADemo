package crafterqademo;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class CreateAGroupTest {

    @Test
    public void create_new_group_EXPECTEDRESPONSE_201 () {
        HeaderConfig header = new HeaderConfig();
        RequestBody postContent = new RequestBody(null,"Test_1", "test");

        RestAssured.given()
                .when().headers(header.defaultHeader())
                .body(postContent)
                .post(header.baseURI + "/groups")
                .then()
                .statusCode(201)
                .log().body();
    }
}
