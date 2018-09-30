package com.linkedin;

import info.debatty.java.lsh.LSHSuperBit;
import info.debatty.java.lsh.SuperBit;

import java.util.Random;

import static info.debatty.java.lsh.SuperBit.cosineSimilarity;

public class LinkedInApplicationTestSimilarity {

    public static void main(String[] args) {

        int n = 10;

        // Initialize Super-Bit
        SuperBit sb = new SuperBit(n);

        Random rand = new Random();
        double[] v1 = new double[n];
        double[] v2 = new double[n];
        for (int i = 0; i < n; i++) {
            v1[i] = 1;
            v2[i] = 0.111111;
        }

        boolean[] sig1 = sb.signature(v1);
        boolean[] sig2 = sb.signature(v2);

        System.out.println("Signature (estimated) similarity: " + sb.similarity(sig1, sig2));
        System.out.println("Signature (estimated) similarity: " + sb.similarity(sig1, sig2));
    }

    public double[] createUserVector(Long userId) {
        return null;
    }
}