package Task3;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class VaccinationCenter {

    static String gross = "";

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        initialiseVaccines(Booth.booth);

        boolean runProgram = true;                                                     // make while loop infinite util user wants to Exit

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

    private static void viewAllBooths(){

        System.out.println("Available booths :");
        for (int i = 0; i <= 5; i++) {
            System.out.println("Booth num :" + i);
        }
    }
    private static void viewEmptyBooths (){
        for (int i = 0; i < 6; i++) {
            if (Booth.booth[i].patient.getFirstName().equals("E")) {
                System.out.println("Booth num " + i + " is empty");

            }
        }

    }

    private static void addPatient() {
        Booth.addPatients();
    }

    private static void removePatient(){
        Booth.removePatients();
    }
    private static void sortNames(){
        String T ;
        int i = 0;
        String[] boothName = new String[Booth.booth.length];
        for (int y=0;y< Booth.booth.length;y++){
            boothName[y] = Booth.booth[y].patient.getFirstName();
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
            if (boothName[k].equals("E")){
                System.out.println(" ");
            }else{
                System.out.println(boothName[k]);

            }

        }
        System.out.println();
    }

    private static void storeFile()  {


        try {
            BufferedWriter PntFile = new BufferedWriter(new FileWriter("Patient Data.txt"));
            for (int l=0; l<6;l++){  //Storing Patient Details to file

                if(!Booth.booth[l].patient.getFirstName().equals("E")){
                    String info = Booth.booth[l].patient.getFirstName()+" "+ Booth.booth[l].patient.getSurName()+" "+l+" "+ Booth.booth[l].patient.getAge()+" "+ Booth.booth[l].patient.getCity()+
                            " "+ Booth.booth[l].patient.getNIC_PassPortNumber()+" "+Booth.booth[l].getVaccineName()+"\n";
                    gross += info;

                }

            }
            PntFile.write(gross);
            PntFile.close();
            gross="";
            System.out.println("File Updated Successfully");


        }catch(Exception A){

            System.out.println("Error in writing to file");
            A.printStackTrace();

        }

    }

    private static void loadFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\hasin\\IdeaProjects\\Task3 Extended Class Version\\Patient Data.txt");
        Scanner Reader = new Scanner(file);
        try {
            //loading data from File
            while (Reader.hasNext()) {

                String Fname = Reader.next();
                String Surname =Reader.next();
                String Boothnum = Reader.next();
                String Age = Reader.next();
                String City = Reader.next();
                String NIC = Reader.next();
                String VaccineName = Reader.next();

                System.out.println("Full Name : "+Fname+" "+Surname+"\nBooth Number :"+Boothnum+"\nAge :"+ Age+"\nCity :"+ City+
                        "\nNIC or PassPort number :"+ NIC+"\nVaccine Type"+VaccineName+"\n\n");
                int b = Integer.parseInt(Boothnum);
                int Ageint = Integer.parseInt(Age);

                //assigning patient to booth by setting patient attributes
                Booth.booth[b].patient.setFirstName(Fname);
                Booth.booth[b].patient.setSurName(Surname);
                Booth.booth[b].patient.setAge(Ageint);
                Booth.booth[b].patient.setCity(City);
                Booth.booth[b].patient.setNIC_PassPortNumber(NIC);

                Booth.stock--;

                switch (Booth.booth[b].getVaccineName()) {
                    case "AstraZeneca" -> Booth.AZcount++;
                    case "Sinopharm" -> Booth.SPcount++;
                    case "Pfizer" -> Booth.PFcount++;
                }
            }

        }catch (Exception a){
            System.out.println("Error Occurred");
        }

    }
    private static void vaccineStock() {
        System.out.println("Remaining Vaccine Stock :" + Booth.stock);

    }
    private static void addVaccines() {

        int L_stock_size = 150-Booth.stock;
        Booth.stock = Booth.stock+L_stock_size;
        System.out.println(L_stock_size+" Vaccinations successfully added to the Stock ");
        System.out.println("New Stock :"+Booth.stock);

    }

    private static void initialiseVaccines(Booth[] allocate ){
        for (int x=0;x<6;x++){
            allocate[x] = new Booth(new Patient() , "");
            allocate[x].patient.setFirstName("E");

        }
        allocate[0].setVaccineName("AstraZeneca");
        allocate[1].setVaccineName("AstraZeneca");
        allocate[2].setVaccineName("Sinopharm");
        allocate[3].setVaccineName("Sinopharm");
        allocate[4].setVaccineName("Pfizer");
        allocate[5].setVaccineName("Pfizer");

        System.out.println("Initialized Successfully");
    }

}
