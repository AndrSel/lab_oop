package com.company;

public class Main {

    public static void main(String[] args) {

    }
}

class Primes {

    public static void main(String[] args){//вывод простых чисел от 1 до 100
        for (int i=2;i<=100;i++){
            if (isPrimes(i)){
                System.out.println(i);
            }
        }
    }

    public static boolean isPrimes(int n){//Проверка, является ли число простым
        for (int i=2;i<n;i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}