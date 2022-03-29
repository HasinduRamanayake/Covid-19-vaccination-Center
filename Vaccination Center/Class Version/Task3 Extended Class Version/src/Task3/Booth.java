package Task3;

import java.util.Scanner;

public class Booth {

    private String VaccineName;
    public static int stock = 150;
    public Patient patient;
    public static Booth[] booth = new Booth[6];
    public static int AZcount, SPcount , PFcount = 0;


    public Booth(Patient patient, String VaccineName) {

        this.VaccineName = VaccineName;  //Implementing Booth array with Vaccine Type
        this.patient = patient;                     //making Patient class object in Booth constructor to access Patient Attributes via booth array
    }

    public static void addPatients(){
        Scanner input = new Scanner(System.in);
        System.out.println("""
                Select Vaccination to Proceed :
                Booth 0 & 1: AstraZeneca
                Booth 2 & 3: Sinopharm
                Booth 4 & 5: Pfizer""");

        String Vaccine = input.next();
        switch (Vaccine) {                        //making vaccine count for count patient vaccine requests
            case "AstraZeneca" -> AZcount++;
            case "Sinopharm" -> SPcount++;
            case "Pfizer" -> PFcount++;
            default -> {
                System.out.println("Enter Valid Vaccine Name!!!");
                addPatients();
            }
        }
        for (int j=0; j<6;j++) {
            if((AZcount<=2) && (SPcount<=2) && (PFcount<=2)){
                if (booth[j].getVaccineName().equals(Vaccine) && booth[j].patient.getFirstName().equals("E")) {

                    System.out.println("Enter Customer First Name: ");
                    booth[j].patient.setFirstName(input.next());
                    System.out.println("Enter Customer Surname: ");
                    booth[j].patient.setSurName(input.next());
                    System.out.println("Enter Customer Age: ");
                    booth[j].patient.setAge(input.nextInt());
                    System.out.println("Enter Customer City: ");
                    booth[j].patient.setCity(input.next());
                    System.out.println("Enter Customer NIC or Passport number: ");
                    booth[j].patient.setNIC_PassPortNumber(input.next());

                    System.out.println("Patient successfully added to booth number: "+j+"/ Vaccination :"+booth[j].getVaccineName());
                    break;
                }

            }else {

                System.out.println("Booth Center Currently Full");
                switch (Vaccine) {
                    case "AstraZeneca" -> AZcount--;
                    case "Sinopharm" -> SPcount--;
                    case "Pfizer" -> PFcount--;
                }
                break;
            }
        }
        stock--;
        if (stock <= 20) {
            System.out.println("WARNING!!! Vaccine stock at :" + stock);
        }
    }

    public static void removePatients(){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Booth number to Remove Patient :");
        int BNUM = input.nextInt();
        booth[BNUM].patient.setFirstName("E");
        System.out.println("Successfully removed the patient");

        switch (booth[BNUM].getVaccineName()) {
            case "AstraZeneca" -> AZcount--;
            case "Sinopharm" -> SPcount--;
            case "Pfizer" -> PFcount--;
        }
    }


    public String getVaccineName() {
        return VaccineName;
    }

    public void setVaccineName(String vaccineName) {
        VaccineName = vaccineName;
    }
}
