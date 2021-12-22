package crafterqademo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasSize;

public class GetAllGroupsTest {

    HeaderConfig header = new HeaderConfig();
    Response response;

    @BeforeClass
    public void setupURL () {
        RestAssured.baseURI = header.baseURI;
        RestAssured.basePath = header.apiGroups;
    }

    /*Scenario: Check if Crater CMS GET response for /group is correct
     *   Given: That authorization exists
     *   When: GET query for /groups API is executed
     *   Then: Assert that status code is 200*/
    @Test
    public void get_all_groups_EXPECTEDRESPONSE_200 () {

        response = RestAssured.given()
                .when().headers(header.defaultHeader())
                .queryParam("limit", 100)
                .get()
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200, "OK");
        Assert.assertEquals(response.jsonPath().getString("groups[0].id"), "1");
        Assert.assertEquals(response.jsonPath().getString("groups[0].name"), "system_admin");
        response.prettyPrint();
    }

    /*Scenario: Check that CrafterCMS GET /groups.json, specifically groups[] size, is correct
     *   Given: That authorization exists
     *   When: GET query for /groups.json API is executed
     *   Then: Assert that groups[] size is equal to 10*/
    @Test
    public void count_items_for_GETAllGroups () {
        RestAssured.given()
                .when().headers(header.defaultHeader())
                .get(header.baseURI + "/groups.json")
                .then()
                .body("groups", hasSize(10));
    }

    /*Scenario: Check if Crater CMS GET response for /group is correct
     *   Given: That authorization exists
     *   When: GET query for /groups API is executed
     *   And: An incorrect "offset" query parameter is made
     *   Then: Assert that status code is 400*/
    @Test
    public void get_all_groups_EXPECTEDRESPONSE_400 () {
        response = given()
                .headers(header.defaultHeader())
                .queryParam("offset", "value")
                .when()
                .get()
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400, "Incorrect query parameters.");
        response.prettyPrint();
    }
}