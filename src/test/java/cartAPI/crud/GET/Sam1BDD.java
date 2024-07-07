package cartAPI.crud.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Sam1BDD {
    @Test
    public void getRequest(){
//        Given();
        RequestSpecification rs=RestAssured.given();
        rs.baseUri("https://api.zippopotam.us");
        rs.basePath("/IN/413516");
//        When();
        rs.when().log().all().get();
//        Then();
        rs.then().log().all().statusCode(200);
        String response=rs.response().toString();
        System.out.println(response.isEmpty());

    }
}
