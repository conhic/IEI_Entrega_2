package es.upv.iei.view;/**
 * Created by Connor on 25/11/2016.
 */

import es.upv.iei.application.Mobile;
import es.upv.iei.application.PcComponentes;
import es.upv.iei.application.Search;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ApplicationView implements Initializable {

    @FXML
    private RadioButton pcComponentes;
    @FXML
    private RadioButton fnac;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<Mobile> mobiles;
    @FXML
    private TableColumn<Mobile, String> mobile;
    @FXML
    private TableColumn<Mobile, String> price;
    @FXML
    private Button search;

    private Search logic = new Search();


    public void rellenarCb(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "APPLE",
                        "HUAWEI",
                        "LENOVO",
                        "LG",
                        "MOTOROLA",
                        "ONEPLUS",
                        "SAMSUNG",
                        "SONY"
                );

        comboBox.setItems(options);
    }

    public void fillTableView(List<Mobile> list){
        mobile.setCellValueFactory(param -> new
                ReadOnlyObjectWrapper<>(param.getValue().getName()));
        price.setCellValueFactory(param -> new
                ReadOnlyObjectWrapper<>(param.getValue().getPrice()));

        ObservableList<Mobile> observableList = FXCollections.observableArrayList(list);
        mobiles.setItems(observableList);
    }

    public void find(){
        String model = comboBox.getSelectionModel().getSelectedItem();
        List<Mobile> list = new ArrayList<>();

        if(pcComponentes.isSelected()){
            list.addAll(logic.find(model, new PcComponentes()));
        }
        if(fnac.isSelected()){
            //list.addAll(logic.find(model, new Fnac()));
        }

        fillTableView(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rellenarCb();
    }
}
