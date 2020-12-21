package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    }
}

class Palindrome{
    public static void main(String[] args){//определяет, является ли сторка палиндромом
        System.out.println("Введите текст ");
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();
        String[] text = in.split(" ");
        for (int i=0;i<text.length;i++){
            String s = text[i];
            if (isPalindrome(s)){
                System.out.println("строка "+s+" является палиндромом");
            }
        }
    }

    public static String reverseString(String s){//Переворот строки
        String rev = "";
        for (int i=s.length()-1;i>=0;i--){
            rev+=s.charAt(i);
        }
        return rev;
    }
    public static boolean isPalindrome(String s){//определяет, является ли сторка палиндромом
        String rev = reverseString(s);
        if (s.equals(rev)){
            return true;
        }
        else return false;
    }
}

