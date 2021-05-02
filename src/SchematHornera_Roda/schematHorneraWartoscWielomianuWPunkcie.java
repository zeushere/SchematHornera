package SchematHornera_Roda;

import java.util.ArrayList;
import java.util.Scanner;

public class schematHorneraWartoscWielomianuWPunkcie {

    static final Scanner scan;

    static {
        scan = new Scanner(System.in);
    }

    public schematHorneraWartoscWielomianuWPunkcie() {
    }

    public static void main(String[] args) {
        System.out.println("Witaj w programie wyliczającym wartość wielomianu w punkcie.");
        int polynomialDegree = getPolynomialDegree();
        ArrayList<Double> polynomial = readPolynomial(polynomialDegree);
        double result = computingTheValueAtPoint(polynomial);
    }

    public static double computingTheValueAtPoint(ArrayList<Double> polynomial) {
        System.out.print("Podaj argument dla którego będziemy liczyć wartość: ");
        double x;

        String userData = scan.nextLine();
        try {
            x = Double.parseDouble(userData);
        } catch (Exception ex) {
            System.out.println("Argument musi być liczbą!");
            return computingTheValueAtPoint(polynomial);
        }

        double result = polynomial.get(0);

        for (int i = 0; i <= polynomial.size() - 2; ++i) {
            result = result * x + polynomial.get(i + 1);
        }
        int j = 0;
        int k = polynomial.size() - 1;
        System.out.print("W(x) = ");
        for (int i = polynomial.size(); i > 0; i--) {
            if (k != 0) {
                if (polynomial.get(j) != 0) {
                    System.out.print(polynomial.get(j) + "x^" + k + " + ");
                }
                j++;
                k--;
            } else {
                System.out.print(polynomial.get(j));
            }
        }
        System.out.println();
        System.out.println("W(" + x + ")=" + result);

        return result;

    }

    public static int getPolynomialDegree() {
        double polynomialCoefficient;

        System.out.print("Podaj stopień wielomianu: ");
        String userData;

        userData = scan.nextLine();

        int degreeOfPolynomial = 0;

        try {
            degreeOfPolynomial = Integer.parseInt(userData);
        } catch (Exception ex) {
            System.out.println("Stopień wielomianu musi być liczbą!");
            return getPolynomialDegree();
        }
        return degreeOfPolynomial;
    }

    public static ArrayList<Double> readPolynomial(int degreeOfPolynomial) {
        ArrayList<Double> wielomian = new ArrayList();
        double polynomialCoefficient;

        String userData;

        for (int i = degreeOfPolynomial; i >= 1; --i) {

            System.out.print("Podaj współczynnik dla stopnia " + i + ": ");
            userData = scan.nextLine();
            try {
                polynomialCoefficient = Double.parseDouble(userData);
            } catch (Exception ex) {
                System.out.println("Współczynnik wielomianu musi być liczbą!");
                return readPolynomial(degreeOfPolynomial);
            }
            wielomian.add(polynomialCoefficient);
        }

        System.out.print("Podaj wyraz wolny dla wielomianu: ");
        userData = scan.nextLine();
        try {
            polynomialCoefficient = Double.parseDouble(userData);
        } catch (Exception ex) {
            System.out.println("Wyraz wolny wielomianu musi być liczbą!");
            return readPolynomial(degreeOfPolynomial);
        }
        wielomian.add(polynomialCoefficient);

        return wielomian;
    }
}
