package SchematHornera_Roda;

import java.util.ArrayList;
import java.util.Scanner;

public class schematHorneraDzielenieWielomianu {
    static final Scanner scan;

    static {
        scan = new Scanner(System.in);
    }

    public schematHorneraDzielenieWielomianu() {
    }

    public static void main(String[] args) {
        System.out.println("Witaj w programie dzielenie wielomianów schematem Hornera");
        int polynomialDegree = getPolynomialDegree();
        ArrayList<Double> polynomial = readPolynomial(polynomialDegree);
        writePolynomial(polynomial);
        ArrayList<Double> polynomialDivided = polynomialDividing(polynomial, polynomialDegree);
    }

    public static void writePolynomial(ArrayList<Double> polynomial) {
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

    public static ArrayList<Double> polynomialDividing(ArrayList<Double> polynomial, int polynomialDegree) {

        ArrayList<Double> resultPolynomial = new ArrayList<>();

        String userData;
        ArrayList<Double> coefficients = new ArrayList<>();


        System.out.println();
        System.out.println("Podaj wartość dwumianu P(x),np.: P(x) = 2x-3");
        userData = scan.nextLine();

        if (userData.charAt(userData.length() - 1) == 'x' || userData.charAt(userData.length() - 1) == 'X') {
            coefficients = numberAtFirstPlace(userData,polynomial,polynomialDegree);
        } else {
            coefficients = xAtFirstPlace(userData, polynomial, polynomialDegree);
        }

        double polynomialDivisor = (coefficients.get(coefficients.size() - 1) / coefficients.get(0)) * -1;

        if (polynomialDivisor == 0) {
            System.out.println("Wprowadzono błędne wartości dla dwumianu!");
            return polynomialDividing(polynomial, polynomialDegree);
        }

        resultPolynomial.add(polynomial.get(0));

        for (int i = 1; i < polynomial.size(); i++) {
            double a = polynomialDivisor * resultPolynomial.get(i - 1);
            double b = a + polynomial.get(i);
            resultPolynomial.add(b);
        }

        int j = 0;
        int k = resultPolynomial.size() - 2;
        System.out.print("W(x):P(x) = ");
        for (int i = resultPolynomial.size() - 1; i > 0; i--) {
            if (k != 0) {
                if (resultPolynomial.get(j) != 0) {
                    System.out.print(resultPolynomial.get(j) + "x^" + k + " + ");
                }
                j++;
                k--;
            } else {
                System.out.print(resultPolynomial.get(j));
            }
        }
        System.out.print(" Reszta wynosi: " + resultPolynomial.get(resultPolynomial.size() - 1));
        return resultPolynomial;
    }

    public static ArrayList<Double> xAtFirstPlace(String userData, ArrayList<Double> polynomial, int polynomialDegree) {

        String xCoefficient = "";
        Double xCoefficientDouble = 0.0;
        String numberValue = "";
        Double numberValueDouble;
        int placeOfX = 0;

        for (int i = 0; i < userData.length(); i++) {
            if (userData.charAt(i) == 'x' || userData.charAt(i) == 'X') {
                if (i == 0) {
                    xCoefficient = "1";
                }
                if (i == 1 && userData.charAt(0) == '-') {
                    xCoefficient = "-1";
                }
                placeOfX = i;
                break;
            } else {
                xCoefficient += userData.charAt(i) + "";
            }
        }

        try {
            xCoefficientDouble = Double.parseDouble(xCoefficient);
        } catch (Exception ex) {
            System.out.println("Wprowadzono błędne wartości dla dwumianu!");
            return polynomialDividing(polynomial, polynomialDegree);
        }


        for (int i = ++placeOfX; i < userData.length(); i++) {
            numberValue += userData.charAt(i) + "";
        }

        try {
            numberValueDouble = Double.parseDouble(numberValue);
        } catch (Exception ex) {
            System.out.println("Wprowadzono błędne wartości dla dwumianu!");
            return polynomialDividing(polynomial, polynomialDegree);
        }

        ArrayList<Double> coefficients = new ArrayList<>();

        coefficients.add(xCoefficientDouble);
        coefficients.add(numberValueDouble);


        return coefficients;
    }

    public static ArrayList<Double> numberAtFirstPlace(String userData, ArrayList<Double> polynomial, int polynomialDegree) {

        String xCoefficient = "";
        Double xCoefficientDouble = 0.0;
        String numberValue = "";
        Double numberValueDouble;
        int whereSign = 0;
        int placeOfX = 0;

        if(userData.charAt(0) == '-') {
            whereSign = 1;
            numberValue = "-";
        }


        for (int i = whereSign; i < userData.length(); i++) {
            if ((userData.charAt(i) == '-' || userData.charAt(i) == '+')) {
                placeOfX = i;
                break;
            } else {
                numberValue += userData.charAt(i) + "";
            }
        }

        try {
            numberValueDouble = Double.parseDouble(numberValue);
        } catch (Exception ex) {
            System.out.println("Wprowadzono błędne wartości dla dwumianu!");
            return polynomialDividing(polynomial, polynomialDegree);
        }

        for (int i = placeOfX; i < userData.length()-1; i++) {
            xCoefficient += userData.charAt(i) + "";
        }

        if(xCoefficient.equals("+")) {
            xCoefficient = "1";
        }
        if(xCoefficient.equals("-")) {
            xCoefficient = "-";
        }

        try {
            xCoefficientDouble = Double.parseDouble(xCoefficient);
        } catch (Exception ex) {
            System.out.println("Wprowadzono błędne wartości dla dwumianu!");
            return polynomialDividing(polynomial, polynomialDegree);
        }


        ArrayList<Double> coefficients = new ArrayList<>();

        coefficients.add(xCoefficientDouble);
        coefficients.add(numberValueDouble);


        return coefficients;
    }
}
