package amazon.utils;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonUtils {

    // this method is used for json file to List<HashMap>
    public static List<HashMap<String, String>> getJsonData(String jsonFileName) { // library

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\"+ jsonFileName;

        String jsonContent = null;
        List<HashMap<String, String>> data = null;
        try {
            // convert json file to convert to json string -- commons-io
            jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

            // jackson-databind
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(jsonContent,
                    new TypeReference<List<HashMap<String, String>>>() {
                    });
        }
        catch(Exception ex)
        {
            System.out.println("Error : "+ ex);
        }
        // [{country=Argentina, gender=female, name=Esma}, {country=Belarus, gender=male, name=Fatih}]
        System.out.println(data);
        return data;

    }

    // @DataProvider
    public Object[][] getData() { // Java side

        List<HashMap<String, String>> data = getJsonData("data.json");

        return new Object[][] { { data.get(0)}, { data.get(1)} };

    } // [{username=Esma, password=rahull}, {username=Fatih, password=shetty}]

    //@Test
    public void fillForm(HashMap<String,String> input) {

        /*

        FormPage2 formpage = new FormPage2(driver);

        formpage.getTxtNameField().sendKeys(input.get("name")); // name textbox
        formpage.selectRadioBtn(input.get("gender"));// female radio button

        // select country from combobox
        formpage.selectCountryFrCbb(input.get("country"));
        formpage.getBtnLetsShop().click(); // lets shop button click

         */

    }


}
