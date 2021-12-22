package crafterqademo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PatchAGroupTest {

    HeaderConfig header = new HeaderConfig();
    RequestBody requestBody;

    int createResponse;

    Response updateResponse;

    @BeforeClass
    public void setup () {
        RestAssured.baseURI = header.baseURI;
        RestAssured.basePath = header.apiGroups;
    }

    @Test
    public void Update_A_Group_VALIDREQUEST_200 () {

        requestBody =  new RequestBody(null,"Test_3", "test");

        //Create a new group and extract id:
        createResponse = RestAssured.given()
                .headers(header.defaultHeader())
                .body(requestBody)
                .post()
                .then()
                .extract().path("group.id");

        System.out.println("The new group ID is: " + createResponse);

        //Set new ID and new description
        requestBody.setId(createResponse);
        requestBody.setDesc("testing...");

        updateResponse = RestAssured.given()
                .headers(header.defaultHeader())
                .when()
                .body(requestBody)
                .patch()
                .then()
                .extract().response();

        Assert.assertEquals(updateResponse.statusCode(), 200);
        Assert.assertEquals(updateResponse.jsonPath().getString("group.desc"), requestBody.getDesc());
        updateResponse.prettyPrint();
    }
}