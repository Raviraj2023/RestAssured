package cartAPI.crud.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class SamNonBDD {
    @Test
    public void getRequest(){
        RequestSpecification sc= RestAssured.given();
        sc.get();

    }
}
