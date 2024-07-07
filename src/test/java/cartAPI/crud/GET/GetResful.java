package cartAPI.crud.GET;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetResful {

    static int sd;
    static int id=1;

//    RequestSpecification r= RestAssured.given();
    @BeforeTest
    public void baseUri(){
        given().baseUri("https://restful-booker.herokuapp.com");
    }

    @Test 
    public void GetRequestP(){

        given().basePath("/booking").pathParam("id", 2).
        when().get("/{id}").
        then().log().all().statusCode(200);
    }
    @Test (enabled = true)
    public void GetRequestN(){

       given().basePath("/booking/1").
        when().get().
        then().log().all().statusCode(200);
    }


}
