import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> requestBody = Map.of(
                "name", "Ilya",
                "job", "Java developer"
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody);

        String uri = "https://reqres.in/api/users";
        String responseBody = restTemplate.postForObject(uri, request, String.class);
        System.out.println(responseBody);
    }
}
