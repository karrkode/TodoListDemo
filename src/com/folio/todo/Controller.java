package com.folio.todo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea textAreaView;

    @FXML
    private Label labelDueDate;


    public void initialize() {
        TodoItem item1 = new TodoItem("Email Ben","You met him at DNC Fundraiser", LocalDate.of(2018, Month.MAY,20));
        TodoItem item2 = new TodoItem("Email Sarah","She is wanting to do vollunteer work at Lincoln Park Community Shelter", LocalDate.of(2018, Month.APRIL,20));
        TodoItem item3 = new TodoItem("Contact Andrew","His phone is 618-977-2684. He is new to chicago", LocalDate.of(2018, Month.JUNE,23));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    textAreaView.setText(item.getDetails());
                    labelDueDate.setText(item.getDeadline().toString());
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        textAreaView.setText(item.getDetails());
        labelDueDate.setText(item.getDeadline().toString());
    }

}
