package com.zachtrong.elgamaljava;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.zachtrong.elgamaljava.ElGamalController.random;

public class PrimitiveRootSearch {

    public static BigInteger primitiveRootSearch(BigInteger p) throws Exception {
        if (p == null || !MillerRabin.testStrongPrime(p))
            throw new Exception("Invalid p for primitive root search");

        if (p.compareTo(BigInteger.TWO) == 0) {
            return BigInteger.ONE;
        }

        // Find prime factors of p-1 once
        BigInteger p1 = BigInteger.TWO;
        BigInteger p2 = p.subtract(BigInteger.ONE).divide(p1);

        while (true) {
            BigInteger g;
            do {
                g = new BigInteger(p2.bitLength(), random);
            } while (g.compareTo(BigInteger.TWO) < 0 || g.compareTo(p.subtract(BigInteger.ONE)) > 0);

            if (g.modPow(p.subtract(BigInteger.ONE).divide(p1), p).compareTo(BigInteger.ONE) != 0) {
                if (g.modPow(p.subtract(BigInteger.ONE).divide(p2), p).compareTo(BigInteger.ONE) != 0) {
                    return g;
                }
            }
        }
    }

    private static boolean checkPrimitiveRoot(BigInteger g, BigInteger p,
                                              BigInteger n, Set<BigInteger> factors) {
        // Run g^(n / "each factor) mod p
        // If the is 1 mod p then g is not a primitive root
        Iterator<BigInteger> i = factors.iterator();
        while (i.hasNext()) {
            if (g.modPow(n.divide(i.next()), p)
                    .equals(BigInteger.ONE)) {
                return false;
            }
        }
        return true;
    }

    private static Set<BigInteger> findPrimeFactors(BigInteger n) {
        // Set is unique
        Set<BigInteger> factors = new HashSet<BigInteger>();
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) <= 0; i = i
                .add(BigInteger.ONE)) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                // Add y to factors and decrease n
                factors.add(i);
                n = n.divide(i);
                // This should speed things up a bit for very large numbers!
                if (MillerRabin.testStrongPrime(n))
                    return factors;
            }
        }
        return factors;
    }
}
