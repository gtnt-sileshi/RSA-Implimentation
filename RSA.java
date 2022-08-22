package rsa;

import java.math.BigDecimal;
import java.math.BigInteger;

class RSA {

    static int gcd(int e, int phi) {
        if (e == 0) {
            return phi;
        } else {
            return gcd(phi % e, e);
        }
    }

    static String[] encode(int Q, int P, String msgen) {
        int n, phi, d = 0, i, e;
        int msg = Integer.parseInt(msgen);
        n = P * Q;
        phi = (P - 1) * (Q - 1);
        System.out.println("the value of phi = " + phi);

        for (e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1) // e is for public key exponent
            {
                break;
            }
        }
        System.out.println("the value of e = " + e);
        double c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message is : -");
        System.out.println(c);
        int text = (int) c;
        for (i = 0; i <= e; i++) {
            int x = 1 + (i * phi);
            if (x % e == 0) //d is for private key exponent
            {
                d = x / e;
                break;
            }
        }
        String mess, pb, pv;
        mess = Integer.toString(text);
        pb = Integer.toString(n) + "," + Integer.toString(e);
        pv = Integer.toString(n) + "," + Integer.toString(d);
        String cipher[] = {mess, pb, pv};
        return cipher;
    }

    static String[] decode(int Q, int P, String cipher) {

        System.out.println("cipher " + cipher);
        int n, phi, d = 0, e, i;
        int msg = Integer.parseInt(cipher);
        n = P * Q;
        phi = (P - 1) * (Q - 1);
        System.out.println("the value of phi = " + phi);

        for (e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1) // e is for public key exponent
            {
                break;
            }
        }
        System.out.println("the value of e = " + e);
        for (i = 0; i <= e; i++) {
            int x = 1 + (i * phi);
            if (x % e == 0) //d is for private key exponent
            {
                d = x / e;
                break;
            }
        }

        System.out.println("The value of d   " + d);

        BigInteger N = BigInteger.valueOf(n);

        //converting int value of n to BigInteger
        N = BigInteger.valueOf(n);
        //converting float value of c to BigInteger
        BigInteger C = BigDecimal.valueOf(msg).toBigInteger();
        BigInteger msgback = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is : -");
        System.out.println(msgback);
        String mess, pb, pv;
        mess = msgback.toString();
        pb = Integer.toString(n) + "," + Integer.toString(e);
        pv = Integer.toString(n) + "," + Integer.toString(d);
        String message[] = {mess, pb, pv};
        return message;
    }

}
