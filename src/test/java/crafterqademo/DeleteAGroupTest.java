package crafterqademo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteAGroupTest {

    HeaderConfig header = new HeaderConfig();

    int createResponse;

    Response deleteResponse;
    Response verifyResponse;

    @BeforeClass
    public void setupURL () {
        RestAssured.baseURI = header.baseURI;
        RestAssured.basePath = header.apiGroups;
    }

    @Test
    public void Delete_A_Group_VALIDREQUEST_200 () {

        RequestBody requestBody = new RequestBody(null,"Test_2", "test");

        //Create a new group and extract ID:
        createResponse = RestAssured.given()
                .headers(header.defaultHeader())
                .body(requestBody)
                .post()
                .then()
                .extract().path("group.id");

        System.out.println("The new group ID is: " + createResponse);

        //Delete new group based on recently extracted ID
        deleteResponse = RestAssured.given()
                .headers(header.defaultHeader())
                .and()
                .queryParam("id", createResponse)
                .when()
                .delete()
                .then()
                .extract().response();
        //Assert status code is OK
        Assert.assertEquals(deleteResponse.statusCode(), 200, "Group was deleted correctly.");
        //Print response as Pretty String
        deleteResponse.prettyPrint();

        //Verify that group has been deleted
        verifyResponse = RestAssured.given()
                .headers(header.defaultHeader())
                .when()
                .get("/" + String.valueOf(createResponse))
                .then()
                .extract().response();
        //Assert status code is 404 NOT FOUND
        Assert.assertEquals(verifyResponse.statusCode(), 404, "Group not found.");
        verifyResponse.prettyPrint();
    }

    @Test
    public void Delete_A_Group_INVALIDVALIDREQUEST_400 () {

        //New delete request with ID as String
        deleteResponse = RestAssured.given()
                .when().headers(header.defaultHeader())
                .queryParam("id", "yeah")
                .delete()
                .then()
                .extract().response();
        //Assert that status code is 400 INVALID REQUEST
        Assert.assertEquals(deleteResponse.statusCode(), 400, "Invalid request.");
        //Print response as Pretty String
        deleteResponse.prettyPrint();
    }
}
