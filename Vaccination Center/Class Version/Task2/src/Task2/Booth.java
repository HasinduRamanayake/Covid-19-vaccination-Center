package Task2;

import java.util.Scanner;

public class Booth {

    private String pntName;
    public static Booth[] booth = new Booth[6];
    public static int stock = 150;
    public static String CustomerName = "";

    public Booth(String pntName){
        this.pntName  = pntName;
    }

    public static void addPatients(){

        //integer Validation
        Scanner input = new Scanner(System.in);
        System.out.println("Enter booth number (0-5)");
        try {

            int BNUM = input.nextInt();
            System.out.println("Enter Customer Name To Add booth number " + BNUM + ":");
            CustomerName = input.next();
            booth[BNUM].setPntName(CustomerName); //assigning patient name to specified booth index
            System.out.println("Patient successfully added to Booth");
            stock--;

        }catch (Exception e){
            System.out.println("Enter valid Booth number");
        }


        if (stock <= 20) {
            System.out.println("WARNING!!! Vaccine stock at :" + stock);
        }
    }
    public static void removePatients(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Booth number to Remove Patient :");
        int BNUM = input.nextInt();
        booth[BNUM].setPntName("E");                        //removing patient by setting patient Name as "E"
        System.out.println("Successfully removed the patient");
    }


    //getting and setting Patient name

    public void setPntName(String pntName) {
        this.pntName = pntName;
    }

    public String getPntName() {
        return pntName;
    }
}
