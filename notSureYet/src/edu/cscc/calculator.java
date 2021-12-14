package edu.cscc;

public class calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mult(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) throws Exception {
        if (b != 0) { return a / b; }
        else { throw new Exception("Denominator cannot be 0"); }
    }

    // a to the b power
    public int exp(int a, int b) {
        int result = a;
        if (b == 0) { return 1; }
        for (int i=0; i < b; i++) {
            result *= a;
        }
        return result;
    }

    // a to the bth root
    public int root(int a, int b) {
        return exp(a, 1/b);
    }


}
