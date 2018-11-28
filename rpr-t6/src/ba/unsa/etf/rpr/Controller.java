package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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

    public String getFirstName(){
        return firstName.get();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studyYearField.getSelectionModel().selectFirst();
        odsjekField.getSelectionModel().selectFirst();
        ciklusField.getSelectionModel().selectFirst();
        //placeField.getSelectionModel().selectFirst();
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                firstName.set(newValue);
                if(newValue.trim().equals("")){
                    //crvena
                    System.out.println(newValue+"1234536");
                    name.getStyleClass().removeAll("dugmeValid");
                    name.getStyleClass().add("dugmeInvalid");
                }
                else
                {
                    name.getStyleClass().removeAll("dugmeInvalid");
                    name.getStyleClass().add("dugmeValid");
                    //zelena
                }
                //System.out.println(newValue);
            }
        });
    }

    public void evaluate(ActionEvent actionEvent) {
        System.out.println(firstName.get());
    }
}
