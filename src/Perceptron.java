
import java.util.*;


public class Perceptron {

    String language;

    List<Vector> learningVectors = new ArrayList<>();

    double lerningRate = 0.01;
    double[] libras = new double[26];
    double bias = Math.random();

    public void learn() {
        for (int i = 0; i < libras.length; i++) {
            libras[i] = Math.random();
        }
        int error = 10;
        while (error > 0) {
            error = 0;

            for (int i = 0; i < learningVectors.size(); i++) {

                double ratio = calcRatio(learningVectors.get(i).getNumbers(), libras);
                int output = ratio >= bias ? 1 : 0;
                if (output != (learningVectors.get(i).getLanguage().equals(language) ? 1 : 0)) {

                    libras = newLibras(libras, learningVectors.get(i), output);
                    bias = newBias(bias, learningVectors.get(i), output);
                    error++;
                }
            }
        }
    }

    public int test(Vector vector) {
        return calcRatio(vector.getNumbers(), libras) >= bias ? 1 : 0;
    }


    private double[] newLibras(double[] libras, Vector vector, int expectedReceived) {
        int exptected = vector.getLanguage().equals(this.language) ? 1 : 0;

        for (int i = 0; i < libras.length; i++) {
            libras[i] = libras[i] + lerningRate * (exptected - expectedReceived) * vector.getNumbers().get(i);
        }
        return libras;
    }

    private double newBias(double bias, Vector vector, int expectedReceived) {
        int exptected = vector.getLanguage().equals(this.language) ? 1 : 0;
        bias = bias - lerningRate * (exptected - expectedReceived);
        return bias;
    }

    private static double calcRatio(List<Double> vector, double[] libras) {
        double tmp = 0;

        for (int i = 0; i < vector.size(); i++) {
            tmp += vector.get(i) * libras[i];
        }
        return tmp;
    }

    public void addLearning(List<Vector> vectors) {
        learningVectors = vectors;
    }

    public Perceptron(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
