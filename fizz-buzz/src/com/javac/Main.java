package com.javac;


import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      float firstNumber=3;
      float secondNumber=5;


      float divisor;

        System.out.print("enter divisor:");
        divisor=scanner.nextFloat();

        if  (divisor % firstNumber == 0 && (divisor%secondNumber==0)){

            System.out.println("fizzbuzz");
        } else if (divisor%secondNumber==0) {

            System.out.println("fizz");
        } else if(firstNumber % divisor == 0){
            System.out.println("buzz");

        }
        else  if(divisor % firstNumber!= 0 && (divisor % secondNumber!= 0))
            System.out.println(divisor);
    }}









