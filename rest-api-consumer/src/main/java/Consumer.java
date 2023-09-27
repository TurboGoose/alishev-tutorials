import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String text;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter text on russian:");
            text = sc.nextLine().strip();
        }

        Map<String, String> requestBody = Map.of(
                "folderId", System.getenv("FOLDER_ID"),
                "texts", "[" + text + "]",
                "targetLanguageCode", "en"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(System.getenv("IAM_TOKEN"));

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        String uri = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        YandexTranslateResponse responseBody = restTemplate.postForObject(uri, request, YandexTranslateResponse.class);
        System.out.println("Translation:");
        System.out.println(responseBody.getTranslations().get(0).getText());
    }
}
