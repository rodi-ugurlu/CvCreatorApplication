package com.rodiugurlu.cvcreator;

import java.util.Scanner;

public class AlgorithmTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("bir sayi gir yarram ");
        int sayi = scanner.nextInt();
        int toplam = 0;
while (sayi!=0){
    toplam+=sayi%10;
    sayi/=10;
}
        System.out.println(toplam);

    }
}

