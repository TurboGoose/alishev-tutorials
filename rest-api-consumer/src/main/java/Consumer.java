import org.springframework.web.client.RestTemplate;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://reqres.in/api/users/1";
        String json = restTemplate.getForObject(uri, String.class);
        System.out.println(json);
    }
}
