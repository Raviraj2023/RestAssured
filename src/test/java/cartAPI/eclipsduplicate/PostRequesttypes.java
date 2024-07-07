package cartAPI.eclipsduplicate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class PostRequesttypes {

    int id;
    @Test(priority = 1, enabled = false)
    void hashmap() throws Exception {

//		{
//	    "firstname" : "Jim",
//	    "lastname" : "Brown",
//	    "totalprice" : 111,
//	    "depositpaid" : true,
//	    "bookingdates" : {
//	        "checkin" : "2018-01-01",
//	        "checkout" : "2019-01-01"
//	    },
//	    "additionalneeds" : "Breakfast"
//	}
//		1. Using Java collection Hashmap create POST request
        try {
            HashMap hdata = new HashMap();
            hdata.put("firstname", "Arvind");
            hdata.put("lastname", "Singh");
            hdata.put("totalprice", 123);
            hdata.put("depositpaid", true);

            HashMap data1 = new HashMap();
            data1.put("checkin", "2008-01-01");
            data1.put("checkout", "2008-02-01");

            hdata.put("bookingdates", data1);
            hdata.put("additionalneeds", "PunjabiFast");

            System.out.println(hdata);

            ValidatableResponse res = given().baseUri("https://restful-booker.herokuapp.com")
                    .contentType(ContentType.JSON).body(hdata).when().post("/booking").then().assertThat()
                    .statusCode(200).body("booking.firstname", equalTo("Arvind"));
//			.log().body();
            System.out.println(res.extract().asPrettyString());

        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }

    }

    //	2. Using JAVAObject create POST request
    @Test(priority = 2, enabled = false)
    void JSONObject() {

        JSONObject data = new JSONObject();
        data.put("firstname", "Arvind");
        data.put("lastname", "Singh");
        data.put("totalprice", 123);
        data.put("depositpaid", true);

        JSONObject data1 = new JSONObject();
        data1.put("checkin", "2008-01-01");
        data1.put("checkout", "2008-02-01");

        data.put("bookingdates", data1);
        data.put("additionalneeds", "PunjabiFast");

        System.out.println(data.toString());

        given().contentType(ContentType.JSON).body(data.toString()).when()
                .post("https://restful-booker.herokuapp.com/booking").then().log().body();
    }


//	3. Using POJO class create POST request

    @Test(priority = 3, enabled = false)
    void pojoClass() throws JsonProcessingException {
        Pojodemo po = new Pojodemo();
        po.setFirstname("Ramesh");
        po.setLastname("dube");
        po.setTotalprice(124);
        po.setDepositpaid(true);
        Pojodemo.Bookingdate frs=new Pojodemo.Bookingdate();
        frs.setCheckin("2008-02-01");
        frs.setCheckout("2008-02-03");
        po.setBookingdates(frs);
        po.setAdditionalneeds("chineesdish");

        ObjectMapper obje=new ObjectMapper();
        String aw=obje.writerWithDefaultPrettyPrinter().writeValueAsString(po);

        System.out.println(aw);

        given().contentType(ContentType.JSON).body(aw).when()
                .post("https://restful-booker.herokuapp.com/booking").then().log().body();

    }
//	4. Using External json file create POST request

    @Test (priority = 4, enabled = true)
    void jsonfile() throws FileNotFoundException {

        File nn=new File("src/test/resources/body.json");

        FileReader fr=new FileReader(nn);

        JSONTokener jt=new JSONTokener(fr);

        JSONObject job=new JSONObject(jt);

        Object obf=job;
        System.out.println( "this is job:"+obf);

        given().contentType(ContentType.JSON).body(job.toString()).when()
                .post("https://restful-booker.herokuapp.com/booking").then().log().body();
    }
}
