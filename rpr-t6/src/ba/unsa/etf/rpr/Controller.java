package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField name;
    public TextField surname;
    public TextField index;
    public Button evaluateBtn;
    public CheckBox cBox;
    public TextField jmbgField;
    public TextField dateField;
    public ComboBox placeField;
    public TextField adressField;
    public TextField telField;
    public TextField emailField;
    public ChoiceBox ciklusField;
    public ChoiceBox studyYearField;
    public ChoiceBox odsjekField;
    public RadioButton samofinansirajuciRadio;
    public ToggleGroup status;
    public RadioButton redovanRadio;
    private SimpleStringProperty firstName;
    private SimpleStringProperty birthPlace;
    Boolean isValidName;
    Boolean isValidSurname;
    Boolean isValidIndex;
    Boolean isValidJmbg;
    Boolean isValidBirthday;
    Boolean isValidBirthplace;
    Boolean isValidAdress;
    Boolean isValidNumber;
    Boolean isValidEmail;




    public Controller(){
        birthPlace = new SimpleStringProperty();
        firstName = new SimpleStringProperty();
    }

    public SimpleStringProperty birthPlaceProperty(){
        return birthPlace;
    }

    public String getBirthPlace(){
        return birthPlace.get();
    }

    public SimpleStringProperty firstNameProperty(){
        return firstName;
    }


    public Boolean isValidJmbgDatePlace(String jmbg, String date, String place){

            if( jmbg.matches("\\d{13}$")){
                ArrayList<Integer> numbers=new ArrayList<>();
                for(int i=0;i<13;i++){
                    numbers.add(Integer.parseInt(String.valueOf(jmbg.charAt(i))));
                }
                    //DDMMGGGRRBBBK = ABVGDĐEŽZIJKL
                    //L = 11 - (( 7*(A+E) + 6*(B+Ž) + 5*(V+Z) + 4*(G+I) + 3*(D+J) + 2*(Đ+K) ) % 11)
                return numbers.get(12)==11-((7*(numbers.get(0)+numbers.get(6))+6*(numbers.get(1)+numbers.get(7))
                        +5*(numbers.get(2)+numbers.get(8))+4*(numbers.get(3)+numbers.get(9))+3*(numbers.get(4)+numbers.get(10))+2*(numbers.get(5)+numbers.get(11)) )%11 );
            }
            return false;
    }

    public String getFirstName(){
        return firstName.get();
    }


    public void initialize(URL location, ResourceBundle resources) {
        studyYearField.getSelectionModel().selectFirst();
        odsjekField.getSelectionModel().selectFirst();
        ciklusField.getSelectionModel().selectFirst();
        //placeField.getSelectionModel().selectFirst();
        isValidIndex=false;
        isValidName=false;
        isValidSurname=false;
        isValidAdress=false;
        isValidBirthday=false;
        isValidBirthplace=false;
        isValidEmail=false;
        isValidJmbg=false;
        isValidNumber=false;
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                firstName.set(newValue);
                //System.out.println("1234562a891234".matches("\\d{13}"));
                if(newValue.trim().equals("")){
                    //crvena
                    name.getStyleClass().removeAll("dugmeValid");
                    name.getStyleClass().add("dugmeInvalid");
                    isValidName=false;
                }
                else
                {
                    name.getStyleClass().removeAll("dugmeInvalid");
                    name.getStyleClass().add("dugmeValid");
                    isValidName=true;
                    //zelena
                }
                //System.out.println(newValue);
            }
        });
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                isValidJmbg=false;
                if(isValidJmbgDatePlace(newValue,dateField.textProperty().get(),placeField.accessibleTextProperty().get())){
                        isValidJmbg=true;
                }

                if(isValidJmbg){
                    jmbgField.getStyleClass().removeAll("dugmeInvalid");
                    jmbgField.getStyleClass().add("dugmeValid");
                }
                else{
                    jmbgField.getStyleClass().removeAll("dugmeValid");
                    jmbgField.getStyleClass().add("dugmeInvalid");
                }
            }
        });
        dateField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                dateField.textProperty().set(newValue);
                isValidBirthday=isValidJmbgDatePlace(jmbgField.textProperty().get(), dateField.textProperty().get(),placeField.accessibleTextProperty().get());
                if(isValidBirthday){
                    dateField.getStyleClass().removeAll("dugmeInvalid");
                    dateField.getStyleClass().add("dugmeValid");
                }
                else{
                    dateField.getStyleClass().removeAll("dugmeValid");
                    dateField.getStyleClass().add("dugmeInvalid");
                }
            }
        });
        placeField.accessibleTextProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });
    }

    public void evaluate(ActionEvent actionEvent) {

        System.out.println(firstName.get()+"  "+surname.textProperty().get());
    }
}
