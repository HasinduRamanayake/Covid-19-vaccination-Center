package Task5;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class Receipt {

    @FXML
    Label lblFname;
    @FXML
    Label lblVaccine;
    @FXML
    Label lblBooth;
    @FXML
    Label lblDate;

    @FXML
    public void GenerateReceipt(String Fullname, String Boothnum, String Vaccine , String Date) {


        lblFname.setText(Fullname);
        lblBooth.setText(Boothnum);
        lblVaccine.setText(Vaccine);
        lblDate.setText(Date);

    }

}
