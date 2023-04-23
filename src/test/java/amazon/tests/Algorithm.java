package amazon.tests;

import java.util.Scanner;

public class Algorithm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Giriniz b1, b2 ve i : ");
        boolean b1 = input.nextBoolean();
        boolean b2 = input.nextBoolean();
        int i = input.nextInt();

        int result = mainStep(b1, b2, i);
        System.out.println(result);
    }

    public static int mainStep(boolean b1, boolean b2, int i) {
        int result = i;


        if (b1) //b1 = true
        {
            if (i == 0) {
                result = -1;
            } else if (i >= 2 && i <= 9) // else li yazarsan result = -1 girmez
            {
                // ??
                if (b2) {
                    result = 10 - result;
                } else {
                    result = 10 + result;
                }

            } else if (i <= -2 && i >= -9) {

                if (b2) {
                    result = 5 + result;
                } else {
                    result = 5 - result;
                }
            }
            return result;
        }
        // to 1
        else { // b1 == false
            if (i == 0) { // i==0
                if (b2) {
                    result = 1;
                } else {
                    result = -1;
                }
            } else // i!=0
            {
                if (b2 && i <= -10 || i >= 10) {
                    result = result + 1;
                } else if (!b2 && i <= -10 || i >= 10) {
                    result = result - 1;
                } else if (i > -5 && i < 5) {
                    if (b2) {
                        result = result + 10;
                    } else {
                        result = result - 10;
                    }
                }

            }
            return result;
        }


    }
}
