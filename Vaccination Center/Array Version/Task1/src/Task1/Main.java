package Task1;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static int stock = 150;
    static String[] booth = new String[6];
    static String CustomerName = "";
    static String gross = "";


    public static void main(String[] args) throws IOException {


        Scanner input = new Scanner(System.in);
        initialise(booth);                                                //initialising booth with "E"

        boolean runProgram = true;                                        // make while loop infinite until user wants to Exit

        while (runProgram) {
            System.out.println();
            System.out.println("""
                        COVID-19 VACCINATION CENTER
                        100 or VVB: View all Vaccination Booths
                        101 or VEB: View all Empty Booths
                        102 or APB: Add Patient to a Booth
                        103 or RPB: Remove Patient from a Booth
                        104 or VPS: View Patients Sorted in alphabetical order
                        105 or SPD: Store Program Data into file
                        106 or LPD: Load Program Data from file
                        107 or VRV: View Remaining Vaccinations
                        108 or AVS: Add Vaccinations to the Stock
                        999 or EXT: Exit the Program""");

            System.out.println();

            System.out.println("ENTER THE FUNCTION TO ACCESS: ");
            String Enum = input.next().toUpperCase(Locale.ROOT);

            switch (Enum) {
                case "100", "VVB" -> viewAllBooths();
                case "101", "VEB" -> viewEmptyBooths();
                case "102", "APB" -> addPatient();
                case "103", "RPB" -> removePatient();
                case "104", "VPS" -> sortNames();
                case "105", "SPD" -> storeFile();
                case "106", "LPD" -> loadFile();
                case "107", "VRV" -> vaccineStock();
                case "108", "AVS" -> addVaccines();
                case "999", "EXT" -> runProgram= false;

                default -> System.out.println("Enter Valid Code ");
            }
        }
    }

    private static void viewAllBooths (){

        System.out.println("Available booths :");
        for (int i = 0; i <= 5; i++) {
            System.out.println("Booth num :" + i);
        }
    }

    private static void viewEmptyBooths (){
        for (int i = 0; i < 6; i++) {
            if (booth[i].equals("E")) {
                System.out.println("Booth num " + i + " is empty");

            }
        }
    }

    private static void addPatient(){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter booth number (0-5)");

        //integer Validation
        try {

            int BNUM = input.nextInt();
            System.out.println("Enter Customer Name To Add booth number " + BNUM + ":");
            CustomerName = input.next();
            booth[BNUM] = CustomerName ; //assigning patient name to specified booth index
            System.out.println("Patient successfully added to Booth");
            stock--;

        }catch (Exception e){
            System.out.println("Enter valid Booth number");
        }

        if (stock <= 20) {
            System.out.println("WARNING!!! Vaccine stock at :" + stock);
        }

    }
    private static void removePatient(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Booth number to Remove :");
        int BNUM = input.nextInt();
        booth[BNUM] = "E";
        System.out.println("Successfully removed the patient");

    }
    private static void sortNames(){
        String T ;
        int i = 0;
        String[] boothName = new String[booth.length];
        for (int y=0;y< booth.length;y++){                 //making another patients Name array to sort
            boothName[y] = booth[y];
        }
        while (i <= 5) {
            for (int j = i + 1; j <= 5; j++) {
                if (boothName[i].compareToIgnoreCase(boothName[j]) > 0) {

                    T = boothName[i];                       //swapping names
                    boothName[i] = boothName[j];
                    boothName[j] = T;

                }
            }
            i++;
        }
        System.out.println("The names in alphabetical order are: ");

        for (int k = 0; k <= 5; k++) {                       //printing sorted array
            if (boothName[k].equals("E")){
                System.out.println(" ");
            }else{
                System.out.println(boothName[k]);

            }

        }
        System.out.println();
    }

    private static void storeFile() {


        try {
            BufferedWriter PntFile = new BufferedWriter(new FileWriter("Patient Data.txt"));        //Storing current Patients data into file
            for (int l=0; l<6;l++) {
                if (!booth[l].equals("E")) {

                    String info =  booth[l] +" "+ l+"\n";
                    gross += info;      //updating gross via loop to get all patients data
                }
            }
            PntFile.write(gross);
            PntFile.close();
            gross = "";             //assigning gross to "" after write into file

            System.out.println("File Updated Successfully");

        }catch(Exception A){
            System.out.println("Error in writing to file");
            A.printStackTrace();
        }

    }

    private static void loadFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\hasin\\IdeaProjects\\Task1\\Patient Data.txt");
        Scanner Reader = new Scanner(file);          //Read from Patient Data.txt
        try {

            while (Reader.hasNext()) {

                String name = Reader.next();
                String Boothnum = Reader.next();
                System.out.println("Name :"+name+" /Booth number :"+Boothnum);
                int b = Integer.parseInt(Boothnum);
                booth[b] = name;                    //assign patient to specified booth
                stock--;                            //reducing current Stock
            }

        }catch (Exception a){
            System.out.println("Error Occurred");
        }
    }

    private static void vaccineStock() {

        System.out.println("Remaining Vaccine Stock :" + stock);
    }

    private static void addVaccines() {

        int L_stock_size = 150-stock;
        stock = stock+L_stock_size;
        System.out.println(L_stock_size+" Vaccinations successfully added to the Stock ");
        System.out.println("New Stock :"+stock);
    }
    private static void initialise(String[] allocate ){
        for (int x=0;x<6;x++){                             //Initializing booth array using reference
            allocate[x] = "E";
        }
        System.out.println("Initialized Successfully");
    }

}



