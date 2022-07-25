package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField tfAccount;
    @FXML
    private TextField tfCompany;
    @FXML
    private TextField tfBookie;
    @FXML
    private TextField tfBot;
    @FXML
    private TextField tfChannel;
    @FXML
    private TextField tfRule;

    //Table view

    //Account
    @FXML
    private TableView<Account> accountTableView;
    @FXML private TableColumn<Account, String> accountCol;
    @FXML private TableColumn<Account, String> companyCol;
    @FXML private TableColumn<Account, String> bookieCol;
    @FXML private TableColumn<Account, String> botCol;
    @FXML private TableColumn<Account, String> channelCol;
    @FXML private TableColumn<Account, Integer> ruleCol;
    @FXML private TableColumn<Account, String> stateCol;

    //Dashboard
    @FXML private TableView<Account> dashboardTableView;
    @FXML private TableColumn<Account, String> accountColDashboard;
    @FXML private TableColumn<Account, String> bookieColDashboard;
    @FXML private TableColumn<Account, String> channelColDashboard;
    @FXML private TableColumn<Account, Integer> ruleColDashboard;

    //Company
    @FXML private TableView<Account> companyTableView;
    @FXML private TableColumn<Account, String> accountColCompany;
    @FXML private TableColumn<Account, String> companyColCompany;

    //Bookie
    @FXML private TableView<Account> bookieTableView;
    @FXML private TableColumn<Account, String> accountColBookie;
    @FXML private TableColumn<Account, String> bookieColBookie;

    //Bot
    @FXML private TableView<Account> botTableView;
    @FXML private TableColumn<Account, String> accountColBot;
    @FXML private TableColumn<Account, String> botColBot;

    //Channel
    @FXML private TableView<Account> channelTableView;
    @FXML private TableColumn<Account, String> accountColChannel;
    @FXML private TableColumn<Account, String> channelColChannel;

    //Rule
    @FXML private TableView<Account> ruleTableView;
    @FXML private TableColumn<Account, String> accountColRule;
    @FXML private TableColumn<Account, String> ruleColRule;


    //Button
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnReset;

    @FXML private CheckBox stateLogin;

    //FUNCTION


    //Handle button action
    @FXML
    protected void handleButtonAction(ActionEvent event){

        try{
            if(event.getSource() == btnAdd){
                try{
                    Integer.parseInt(tfRule.getText());
                    insertAccount();
                    clearData();
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Failed\nRule: numbers only");
                }

            } else if (event.getSource() == btnUpdate){
                try{
                    Integer.parseInt(tfRule.getText());
                    updateAccount();
                    clearData();
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Failed\nRule: numbers only");
                }

            } else if (event.getSource() == btnDelete){
                try{
                    Integer.parseInt(tfRule.getText());
                    deleteAccount();
                    clearData();
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Failed\nRule: numbers only");
                }
            } else if(event.getSource() == btnReset){
                clearData();
            }
        } catch (Exception ignored){}
    }

    @FXML
    protected void handleCheckBox(){
        if (stateLogin.isIndeterminate()){
            stateLogin.setText("Undefined");
        } else if (stateLogin.isSelected()){
            stateLogin.setText("Login");
        } else {
            stateLogin.setText("Out");
        }
    }

    //Handle mouse action
    @FXML
    protected void handleMouseAction(MouseEvent event){

        try{
            Account account = accountTableView.getSelectionModel().getSelectedItem();
            tfAccount.setText(""+account.getAccount());
            tfCompany.setText(""+account.getCompany());
            tfBookie.setText(""+account.getBookie());
            tfBot.setText(""+account.getBot());
            tfChannel.setText(""+account.getChannel());
            tfRule.setText(""+account.getRule());
            stateLogin.setText("" +account.getState());

            if(stateLogin.getText().equals("Login")){
                stateLogin.setSelected(true);
            } else if (stateLogin.getText().equals("Out")) {
                stateLogin.setSelected(false);
            }
        } catch (Exception ignored){}
    }

    //DATA

    //Connector sql
    public Connection getConnection() {
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "");
            return conn;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public ObservableList<Account> getAccountsList() {
        ObservableList<Account> accountslist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM account";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Account accounts;
            while(rs.next()) {
                accounts = new Account(rs.getString("account"), rs.getString("company"), rs.getString("bookie"),
                        rs.getString("bot"), rs.getString("channel"), rs.getInt("rule"), rs.getString("state"));
                accountslist.add(accounts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountslist;
    }

    //Show account
    public void showAccount(){
        ObservableList<Account> list  = getAccountsList();

        //Account Table
        accountCol.setCellValueFactory(new PropertyValueFactory<Account, String>("account"));
        companyCol.setCellValueFactory(new PropertyValueFactory<Account, String>("company"));
        bookieCol.setCellValueFactory(new PropertyValueFactory<Account, String>("bookie"));
        botCol.setCellValueFactory(new PropertyValueFactory<Account, String>("bot"));
        channelCol.setCellValueFactory(new PropertyValueFactory<Account, String>("channel"));
        ruleCol.setCellValueFactory(new PropertyValueFactory<Account, Integer>("rule"));
        stateCol.setCellValueFactory(new PropertyValueFactory<Account, String>("state"));

        //Dashboard Table
        accountColDashboard.setCellValueFactory(new PropertyValueFactory<Account, String>("account"));
        bookieColDashboard.setCellValueFactory(new PropertyValueFactory<Account, String>("bookie"));
        channelColDashboard.setCellValueFactory(new PropertyValueFactory<Account, String>("channel"));
        ruleColDashboard.setCellValueFactory(new PropertyValueFactory<Account, Integer>("rule"));

        //Company Table
        accountColCompany.setCellValueFactory(new PropertyValueFactory<Account, String>("account"));
        companyColCompany.setCellValueFactory(new PropertyValueFactory<Account, String>("company"));

        //Bookie Table
        accountColBookie.setCellValueFactory(new PropertyValueFactory<Account, String>("account"));
        bookieColBookie.setCellValueFactory(new PropertyValueFactory<Account, String>("bookie"));

        //Bot Table
        accountColBot.setCellValueFactory(new PropertyValueFactory<Account, String>("account"));
        botColBot.setCellValueFactory(new PropertyValueFactory<Account, String>("bot"));

        //Channel Table
        accountColChannel.setCellValueFactory(new PropertyValueFactory<Account, String>("account"));
        channelColChannel.setCellValueFactory(new PropertyValueFactory<Account, String>("channel"));

        //Rule Table
        accountColRule.setCellValueFactory(new PropertyValueFactory<Account, String>("account"));
        ruleColRule.setCellValueFactory(new PropertyValueFactory<Account, String>("rule"));

        accountTableView.setItems(list);
        dashboardTableView.setItems(list);
        companyTableView.setItems(list);
        bookieTableView.setItems(list);
        botTableView.setItems(list);
        channelTableView.setItems(list);
        ruleTableView.setItems(list);
    }

    //Update account
    private void updateAccount(){
        String query = "UPDATE  account SET company  = '" + tfCompany.getText() + "', bookie = '" + tfBookie.getText() + "', bot = '" +
                tfBot.getText() + "', channel = '" + tfChannel.getText() + "', rule = '" + tfRule.getText() + "', " +
                "state = '" +stateLogin.getText() + "' WHERE account = '" + tfAccount.getText() + "'";
        executeQuery(query);
        showAccount();
    }

    //Delete account
    private void deleteAccount(){
        String query = "DELETE FROM account WHERE account = '" + tfAccount.getText() + "' ";
        executeQuery(query);
        showAccount();
    }

    //Insert account
    private void insertAccount(){
        String query = "INSERT INTO account VALUES ('" + tfAccount.getText()+ "', '" + tfCompany.getText()+ "', '" + tfBookie.getText()+
                "', '" + tfBot.getText() + "', '" + tfChannel.getText() + "', '" + tfRule.getText()+ "', '" + stateLogin.getText() +"' " +
                ")" ;
        executeQuery(query);
        showAccount();
    }
    

    //Clear data
    private void clearData(){
        tfAccount.clear();
        tfCompany.clear();
        tfBookie.clear();
        tfBot.clear();
        tfChannel.clear();
        tfRule.clear();
        stateLogin.setSelected(false);
    }

    private void executeQuery(String query){
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAccount();
    }
}