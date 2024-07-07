package cartAPI.cartAPI;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.*;
public class CartAPI {
String baseUri=Constant.bUri;

    String cartId;
    @Test (priority = 1)
    void createNewcart(){
        cartId =given().contentType(ContentType.JSON).
                when().post(baseUri+"/carts").
                then().log().all().extract().jsonPath().get("cartId").toString();
        System.out.println(cartId);
    }
    int itemid;
    @Test (enabled=true,priority = 2)
    public void Additems() throws FileNotFoundException {

        Object job=Fileutil.Json("src/test/resources/Newcartbody.json");

       itemid=given().contentType(ContentType.JSON).body(job.toString()).
                when().post(baseUri+"/carts/"+cartId+"/items").
                then().log().all().extract().jsonPath().get("itemId");
    }

    @Test (priority = 3,enabled=true)
    public void getCartitems(){
        String items=given().contentType(ContentType.JSON).
                when().get(baseUri+"/carts/"+cartId).
                then().log().body().toString();
        
    }

//    /carts/:cartId/items/:itemId
    @Test (priority = 4,enabled=true)
    public void modifyCart() throws FileNotFoundException {

        Object job=Fileutil.Json("src/test/resources/modifycartitemquntity.json");

        String modItms=given().contentType(ContentType.JSON).body(job.toString()).
                when().patch(baseUri+"/carts/"+cartId+"/items/"+itemid).
                then().log().body().toString();
        System.out.println(modItms);
    }

    @Test (priority = 5,enabled=true)
    public void getCartItemsmodifiy(){
        String item=given().contentType(ContentType.JSON).
                when().get(baseUri+"/carts/"+cartId+"/items").
                then().log().body().toString();
        System.out.println(item);
    }

    @Test (priority = 6,enabled=true)
    public void replaceitems() throws FileNotFoundException {
//
        Object job1=Fileutil.Json("src/test/resources/Replace.json");
        given().contentType(ContentType.JSON).body(job1.toString()).
                when().patch(baseUri + "/carts/" + cartId + "/items/" + itemid).
                then().statusCode(204);

         given().contentType(ContentType.JSON).
                when().get(baseUri + "/carts/" + cartId + "/items").
                then().log().body().toString().equalsIgnoreCase(job1.toString());
    }
    @Test(priority = 7,enabled=true)
    void deleteCart(){
        given().contentType(ContentType.JSON).
                when().delete(baseUri+"/carts/"+cartId+"/items/"+itemid).
                then().statusCode(204);
    }

}

