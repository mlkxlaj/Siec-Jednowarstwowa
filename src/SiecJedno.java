import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.lang.Character.*;

public class SiecJedno {

    static List<Vector> vectors = new ArrayList<>();
    static List<Perceptron> perceptrons = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        learning("Data/lang.train.csv");
        testing("Data/lang.test.csv");
    }

    private static void learning(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));

        while (scanner.hasNextLine()) {
            String[] scan = scanner.nextLine().split(",", 2);
            if (!containsLanguage(scan[0])) {
                perceptrons.add(new Perceptron(scan[0]));
            }
            vectors.add(new Vector(countAlphabet(scan[1]), scan[0]));
        }

        for (Perceptron perceptron : perceptrons) {
            perceptron.addLearning(vectors);
            perceptron.learn();
        }
    }

    private static void testing(String path) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(path));
        double right = 0;
        double count = 0;
        while (scanner.hasNextLine()) {
            count++;
            String[] scan = scanner.nextLine().split(",", 2);
            for (int i = 0; i < perceptrons.size(); i++) {

                if (1 == perceptrons.get(i).test(new Vector(countAlphabet(scan[1]), scan[0]))) {
                    right++;
                    break;
                }
            }
        }
        System.out.println(right / count * 100 + " %");

    }

    public static List<Double> countAlphabet(String str) {
        double[] alphabet = new double[26];

        for (int i = 0; i < str.length(); i++) {
            char ch = toLowerCase(str.charAt(i));
            if (((int) ch > 96) && ((int) ch < 123)) alphabet[toLowerCase(ch) - 'a'] += 1;
        }

        return DoubleStream.of(alphabet)
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean containsLanguage(String string) {
        boolean test = false;
        for (Perceptron perceptron : perceptrons) {
            if (perceptron.getLanguage().equals(string)) {
                test = true;
                break;
            }
        }
        return test;
    }
}
