import java.util.ArrayList;
import java.util.List;

public class YandexTranslateResponse {
    private List<Translation> translations = new ArrayList<>();

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
}
