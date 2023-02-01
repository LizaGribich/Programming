import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        //задание 1
        int[] a = new int[16];
        for (int i = 0; i < 16; i++) {
            a[i] = i + 1;
        }
        //задание 2
        double[] x = new double[10];
        double max = 8.0;
        double min = -6.0;
        for (int i = 0; i < 10; i++) {
            x[i] = (float) (min + Math.random() * (max - min));
        }
        // задание 3
        float[][] A = new float[16][10];
        int[] list_check = {1, 5, 6, 9, 11, 13, 14, 16};
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 15) {
                    float stepen = (float) (pow(2 * (pow(2 * x[j], x[j])), 4 * x[j]));
                    float osnova = (float) (pow(atan((x[j] + 1) / 14), (1 / 3)) * (1 - pow(sin(x[j]), (1 / 3))));
                    A[i][j] = (float) (pow(osnova, stepen));
                } else if (i == 1 || i == 5 || i == 6 || i == 9 || i == 11 || i == 13 || i == 14 || i == 16) {
                    A[i][j] = (float) (atan(pow(pow(((x[j] + 1) / 14), 2), 2)));
                } else {
                    A[i][j] = (float) (asin(pow(sin(pow(0.25 * sin(x[j]), 3)), 2)));
                }
            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%.5f ", A[i][j]);
            }
            System.out.println();
        }

    }

}