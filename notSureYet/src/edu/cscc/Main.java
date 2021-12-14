package edu.cscc;

import java.util.Scanner;


/* @author Charles Farmer
This program willlllll........ be a calculator.
 */
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Boolean active = true;
        String[] items;

        System.out.print("Input some shit (quit to stop): ");
        String stuff = input.nextLine();
        items = stuff.split(" ");
        System.out.print(items);
    }
}
