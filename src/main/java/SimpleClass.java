import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
** Vinodhbabu Dharani
 */
public class SimpleClass {

    /**
    ** main method
     */
    public static void main(String[] args) {

        String url = getUrl();

        if (url.isEmpty()) {
            System.out.println("Please enter a valid url");
            throw new InputMismatchException("Please enter a valid url");
        }

        try {
            JSONArray jsonArray = readJsonFromUrl(url);
            processJSONArray(jsonArray);
        } catch (JSONException e) {
            System.out.println("JSONException :: An exception occurred while processing JSON Array");
        } catch (IOException e) {
            System.out.println("IOException :: An IOException has occurred while parsing the JSON response from the URL");
        }
    }

    /**
    ** method to get url from the user
     */
    private static String getUrl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the url: ");

        return scanner.nextLine();
    }

    /**
     * method to get JSON after making a REST call to the URL
     * @param url
     * @return JSONArray
     */
    private static JSONArray readJsonFromUrl(String url) throws IOException {

        /**
            InputStream inputStream = new URL(url).openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return new JSONArray(stringBuilder.toString());
        **/

        // Technically we would make a REST call to the url and the response would be a JSONArray
        // The code for the rest call and parsing the JSON response from the call is above and it is commented

        // But, I wrote a method that mocks the JSON response from the URL
        return mockJSONArray();
    }

    /**
     * method to mock the JSONArray
     * @return JSONArray
     */
    private static JSONArray mockJSONArray() {

        // But, I'm mocking the json response from the url here
        int[] num = {1, 2, 3, 4, 5};
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("numbers", num);
        jsonObject1.put("key", "value");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("numbers", num);
        jsonObject2.put("key", "value");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject1);
        jsonArray.put(jsonObject2);

        System.out.println("The mocked JSON Array is : " + jsonArray);
        return jsonArray;
    }

    /**
     * Process JSON Atray to find the total of arrays and also the running total
     * @param jsonArray
     * @throws JSONException
     */
    private static void processJSONArray(JSONArray jsonArray) throws JSONException {
        int runningTotal = 0;
        int counter = 0;

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int[] arrayOfNumbers = (int[])jsonObject.get("numbers"); // jsonObject.getJSONArray("numbers");

            int arrayTotal = 0;

            for (int j=0; j < arrayOfNumbers.length; j++) {
                arrayTotal += arrayOfNumbers[j];
                counter++;
            }

            System.out.println("Sum of object #" + i + " is " + arrayTotal);
            runningTotal += arrayTotal;
        }

        System.out.println("Running total of all the values in the JSON Array is " + runningTotal);
        System.out.println("Total number of integers that were used: " + counter);
    }
}
