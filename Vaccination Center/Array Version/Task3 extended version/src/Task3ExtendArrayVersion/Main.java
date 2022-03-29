package Task3ExtendArrayVersion;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static int stock = 150;
    static String[] booth = new String[6];
    static String[] vaccineBooth = new String[6];
    static String CustomerFirstName = "";
    static String CustomerSurName = "";
    static String VaccineName = "";
    static String gross = "";
    static int AZcount, SPcount , PFcount = 0;


    public static void main(String[] args) throws IOException {


        Scanner input = new Scanner(System.in);
        initialise(booth);
        initialiseVaccines(vaccineBooth);

        boolean runProgram = true;      // make while loop infinite until user wants to Exit

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
                case "999", "EXT" -> runProgram = false;

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
        System.out.println("""
                Select Vaccination to Proceed :
                Booth 0 & 1: AstraZeneca
                Booth 2 & 3: Sinopharm
                Booth 4 & 5: Pfizer""");

        String Vaccine = input.next();
        VaccineName = Vaccine;
        switch (Vaccine) {
            case "AstraZeneca" -> AZcount++;                                                      //making vaccine count for count patient vaccine requests
            case "Sinopharm" -> SPcount++;
            case "Pfizer" -> PFcount++;
            default -> {
                System.out.println("Enter Valid Vaccine Name!!!");
                addPatient();
            }
        }
        for (int j=0; j<6;j++) {
            if((AZcount<=2) && (SPcount<=2) && (PFcount<=2)){
                if ( vaccineBooth[j].equalsIgnoreCase(Vaccine) && booth[j].equals("E")) {           //adding patients to booth using vaccineBooth array

                    System.out.println("Enter Customer First Name: ");
                    CustomerFirstName = input.next();
                    System.out.println("Enter Customer Surname: ");
                    CustomerSurName = input.next();
                    booth[j] = CustomerFirstName+" "+ CustomerSurName;
                    System.out.println("Patient successfully added to booth number: "+j+"/ Vaccine :"+vaccineBooth[j]);
                    break;
                }

            }else{
                System.out.println("Booth Center Currently Full");
                switch (Vaccine) {
                    case "AstraZeneca" -> AZcount--;
                    case "Sinopharm" -> SPcount--;
                    case "Pfizer" -> PFcount--;
                }
            }
        }
        stock--;
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
        for (int y=0;y< booth.length;y++){
            boothName[y] = booth[y];
        }
        while (i <= 5) {
            for (int j = i + 1; j <= 5; j++) {
                if (boothName[i].compareToIgnoreCase(boothName[j]) > 0) {

                    //swapping names
                    T = boothName[i];
                    boothName[i] = boothName[j];
                    boothName[j] = T;

                }
            }
            i++;
        }
        System.out.println("The names in alphabetical order are: ");

        for (int k = 0; k <= 5; k++) {
            if (boothName[k].equals("E")){                  //printing sorted array
                System.out.println(" ");
            }else{
                System.out.println(boothName[k]);

            }

        }
        System.out.println();
    }

    private static void storeFile(){


        try {
            BufferedWriter PntFile = new BufferedWriter(new FileWriter("Patient Data.txt"));        //Storing current Patients data into file
            for (int l=0; l<6;l++) {
                if (!booth[l].equals("E")) {

                    String info =  booth[l] +" "+ l+" "+vaccineBooth[l]+"\n";
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
        File file = new File("C:\\Users\\hasin\\IdeaProjects\\Task3 extended version\\Patient Data.txt");

        Scanner Reader = new Scanner(file);          //Read from Patient Data.txt
        try {

            while (Reader.hasNext()) {

                String Fname = Reader.next();
                String Surname = Reader.next();
                String Boothnum = Reader.next();
                String VaccineName = Reader.next();
                System.out.println("Name :"+Fname+" "+Surname+" /Booth number :"+Boothnum+" /Vaccine name"+VaccineName);
                int b = Integer.parseInt(Boothnum);
                booth[b] = Fname +" "+ Surname;      //assign patient to specified booth
                stock--;                            //reducing current Stock
                switch (VaccineName) {                      //counting Vaccination Request Type
                    case "AstraZeneca" -> AZcount++;
                    case "Sinopharm" -> SPcount++;
                    case "Pfizer" -> PFcount++;
                }
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
        System.out.println("new Stock :"+ stock);
    }
    private static void initialise(String[] allocate1 ){
        for (int x=0;x<6;x++){

            allocate1[x] = "E";

        }
        System.out.println("Initialized Successfully");
    }

    private static void initialiseVaccines(String[] allocate2 ){        //assigning Vaccines to boothVaccines array

        allocate2[0] = "AstraZeneca";
        allocate2[1] = "AstraZeneca";
        allocate2[2] = "Sinopharm";
        allocate2[3] = "Sinopharm";
        allocate2[4] = "Pfizer";
        allocate2[5] = "Pfizer";

    }

}


