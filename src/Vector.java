
import java.util.List;


public class Vector {

    List<Double> numbers;
    String language;

    public List<Double> getNumbers() {
        return numbers;
    }

    public String getLanguage() {
        return language;
    }

    public Vector(List<Double> numbers, String language) {
        this.numbers = numbers;
        this.language = language;
    }

    @Override
    public String toString() {
        return numbers + "";
    }

}
