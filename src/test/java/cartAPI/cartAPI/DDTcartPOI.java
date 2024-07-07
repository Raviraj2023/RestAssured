package cartAPI.cartAPI;

import org.testng.annotations.Test;

public class DDTcartPOI {
    @Test(dataProvider = "getData",dataProviderClass = ExcelUtil.class)
    public void testWorkbook(String r, String c){
        System.out.println("test");
        System.out.println(r);
        System.out.println(c);
    }
}
