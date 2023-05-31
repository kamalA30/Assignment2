import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AccountsManagementPage extends Application {
    private TableView<Account> tableView;
    private ObservableList<Account> accountList;
    private TextField idField;
    private TextField nameField;
    private TextField balanceField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Accounts Management Page");

        // Create the table view
        tableView = new TableView<>();
        accountList = FXCollections.observableArrayList();
        tableView.setItems(accountList);
        TableColumn<Account, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TableColumn<Account, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TableColumn<Account, Double> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(cellData -> cellData.getValue().balanceProperty().asObject());
        tableView.getColumns().addAll(idColumn, nameColumn, balanceColumn);

        // Create input fields
        idField = new TextField();
        idField.setPromptText("ID");
        nameField = new TextField();
        nameField.setPromptText("Name");
        balanceField = new TextField();
        balanceField.setPromptText("Balance");

        // Create buttons
        Button createButton = new Button("Create");
        createButton.setOnAction(e -> createAccount());
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateAccount());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteAccount());

        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(tableView, idField, nameField, balanceField, createButton, updateButton, deleteButton);

        // Set the scene
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createAccount() {
        String id = idField.getText();
        String name = nameField.getText();
        double balance = Double.parseDouble(balanceField.getText());
        Account newAccount = new Account(id, name, balance);
        accountList.add(newAccount);
        clearFields();
    }

    private void updateAccount() {
        Account selectedAccount = tableView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            selectedAccount.setId(idField.getText());
            selectedAccount.setName(nameField.getText());
            selectedAccount.setBalance(Double.parseDouble(balanceField.getText()));
            tableView.refresh();
            clearFields();
        }
    }

    private void deleteAccount() {
        Account selectedAccount = tableView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            accountList.remove(selectedAccount);
            clearFields();
        }
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        balanceField.clear();
    }
}

class Account {
    private final String id;
    private final String name;
    private final Double balance;

    public Account(String id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        // You can add validation logic here if needed
        // For simplicity, we assume the ID cannot be changed
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public StringProperty idProperty() {
        return new SimpleStringProperty(id);
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public DoubleProperty balanceProperty() {
        return new SimpleDoubleProperty(balance);
    }
}
