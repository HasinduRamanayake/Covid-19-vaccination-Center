package Task4;

import java.util.LinkedList;
import java.util.Scanner;

public class Booth {

    static int stock = 150;
    private String VaccineName;
    public Patient patient;
    public static Booth[] booth = new Booth[6];
    public static LinkedList<Booth> waitingList = new LinkedList<>();
    public static int AZcount, SPcount , PFcount = 0;


    public Booth(Patient patient, String VaccineName) {

        this.VaccineName = VaccineName;  //Implementing Booth array with Vaccine Type
        this.patient = patient;                     //making Patient class object in Booth constructor to access Patient Attributes via booth array
    }

    public static void addPatient(){
        Scanner input = new Scanner(System.in);

        System.out.println("""
                Select Vaccination to Proceed :
                Booth 0 & 1: AstraZeneca
                Booth 2 & 3: Sinopharm
                Booth 4 & 5: Pfizer""");


        String Vaccine = input.next();


        switch (Vaccine) {
            case "AstraZeneca" -> AZcount++;
            case "Sinopharm" -> SPcount++;
            case "Pfizer" -> PFcount++;
            default -> {
                System.out.println("Enter Valid Vaccine Name!!!");
                addPatient();
            }
        }


        for (int j=0; j<6;j++) {

            if((AZcount<=2) && (SPcount<=2) && (PFcount<=2)){

                if (booth[j].getVaccineName().equals(Vaccine) && booth[j].patient.getFirstName().equals("E")) {

                    System.out.println("Enter Customer First Name: ");              //Assigning Patient attributes by setters
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

            }else {     //adding Patients to Waiting list if Booth already filled with Vaccination request

                if (waitingList.get(j).getVaccineName().equals(Vaccine) && waitingList.get(j).patient.getFirstName().equals("E")) {

                    System.out.println("Booth Center Currently Full. You are adding to Waiting list");
                    System.out.println("Enter Customer First Name: ");
                    waitingList.get(j).patient.setFirstName(input.next());
                    System.out.println("Enter Customer Surname: ");
                    waitingList.get(j).patient.setSurName(input.next());
                    System.out.println("Enter Customer Age: ");
                    waitingList.get(j).patient.setAge(input.nextInt());
                    System.out.println("Enter Customer City: ");
                    waitingList.get(j).patient.setCity(input.next());
                    System.out.println("Enter Customer NIC or Passport number: ");
                    waitingList.get(j).patient.setNIC_PassPortNumber(input.next());

                    System.out.println("Patient successfully added to waitingList number: "+j+"/ Vaccination :"+waitingList.get(j).getVaccineName());

                    switch (Vaccine) {
                        case "AstraZeneca" -> AZcount--;
                        case "Sinopharm" -> SPcount--;
                        case "Pfizer" -> PFcount--;
                    }
                    break;

                }
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

        switch (booth[BNUM].getVaccineName()) {
            case "AstraZeneca" -> AZcount--;
            case "Sinopharm" -> SPcount--;
            case "Pfizer" -> PFcount--;
        }

        System.out.println("Successfully removed the patient");
        //adding Waiting list Patient automatically when booth patient removed.
        for(int k=0; k<6; k++){
            if(!waitingList.get(k).patient.getFirstName().equals("E")){
                System.out.println("Adding waiting list patient");
                System.out.println("Removing patient from waiting list...");

                if((AZcount<=2) && (SPcount<=2) && (PFcount<=2)){

                    if (booth[BNUM].getVaccineName().equals(waitingList.get(k).getVaccineName())) {
                        //moving Patient attributes to booth
                        booth[BNUM].patient.setFirstName(waitingList.get(k).patient.getFirstName());
                        booth[BNUM].patient.setSurName(waitingList.get(k).patient.getSurName());
                        booth[BNUM].patient.setAge(waitingList.get(k).patient.getAge());
                        booth[BNUM].patient.setCity(waitingList.get(k).patient.getCity());
                        booth[BNUM].patient.setNIC_PassPortNumber(waitingList.get(k).patient.getNIC_PassPortNumber());

                        System.out.println("Patient successfully added to booth number: "+BNUM+"/ Vaccination :"+booth[BNUM].getVaccineName());
                        waitingList.get(k).patient.setFirstName("E");
                        break;
                    }

                }
            }
        }

    }

    public String getVaccineName() {
        return VaccineName;
    }

    public void setVaccineName(String vaccineName) {
        VaccineName = vaccineName;
    }

}
