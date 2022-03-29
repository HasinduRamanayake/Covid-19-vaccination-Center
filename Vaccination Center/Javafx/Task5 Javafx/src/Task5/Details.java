package Task5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Locale;

public class Details {

    @FXML
    public TextField txtFirstName;
    @FXML
    public TextField txtSurName;
    @FXML
    public TextField txtAge;
    @FXML
    public TextField txtCity;
    @FXML
    public TextField txtNIC;
    @FXML
    public TextField txtVaccine;
    @FXML
    public TextField txtBoothnum;



    private Parent root;

    @FXML
    public void Receipt(ActionEvent actionEvent) throws Exception {

        String Fullname = txtFirstName.getText().toUpperCase(Locale.ROOT)+ " "+ txtSurName.getText().toUpperCase(Locale.ROOT);
        String Boothnum = txtBoothnum.getText();
        String Vaccine = txtVaccine.getText().toUpperCase(Locale.ROOT);
        String Date = GetDateAndTime().toString();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Receipt.fxml"));
        root = loader.load();

        Receipt ReceiptController = loader.getController();
        ReceiptController.GenerateReceipt(Fullname,Boothnum,Vaccine, Date);

        Stage newstage = new Stage();
        newstage.setTitle("Receipt");

        newstage.setScene(new Scene(root, 575, 400));
        newstage.show();

        Stage previousStage =(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        previousStage.close();


    }
    @FXML
    public void clear(){
        txtFirstName.clear();
        txtSurName.clear();
        txtAge.clear();
        txtCity.clear();
        txtNIC.clear();
        txtBoothnum.clear();

    }
    @FXML
    public Date GetDateAndTime(){
        Date currentDate = new Date();
        return currentDate;
    }


}
