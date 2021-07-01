
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ArithmeticCalculator extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        CalculatorForm calculatorForm=new CalculatorForm();
        calculatorForm.showForm(primaryStage);
    }
}

class CalculatorForm{
    
    Label firstNumberLbl;
    TextField firstNumberFld;
    Label secondNumberLbl;
    TextField secondNumberFld;
    ChoiceBox operators;
    Button calculate;
    Label errorlbl;
    
    public void showForm(Stage primaryStage){
        
        firstNumberLbl=new Label("Enter the First Number ");
        firstNumberFld=new TextField();
        secondNumberLbl=new Label("Enter the Second Number ");
        secondNumberFld=new TextField();
        operators=new ChoiceBox();
        operators.getItems().addAll("Addition","Subtraction","Multiplication","Division");
        calculate=new Button("Calculate");
        errorlbl=new Label();
        GridPane root = new GridPane();
        root.addRow(0, firstNumberLbl, firstNumberFld);
        root.addRow(1, secondNumberLbl, secondNumberFld);
        root.addRow(2, operators);
        root.addRow(3, calculate);
        root.addRow(4, errorlbl);
        root.setMinSize(500,300);
        root.setVgap(10);
        root.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Arithmetic Calculator");
        primaryStage.show();
        
        calculate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                boolean connect=validate_form();
                if(connect)
                {
                   calculator c=new calculator(Integer.parseInt(firstNumberFld.getText()),Integer.parseInt(secondNumberFld.getText()),(String)operators.getValue()); 
                   boolean result=c.insertCalculatedRecords();
                   if(result)
                   {
                        errorlbl.setText("Record inserted successfully");
                   }
                   else
                   {
                        errorlbl.setText("Record not inserted in the database");
                   }
                }
                else
                {
                    errorlbl.setTextFill(Color.RED);
                }
            }
        });
       
    }
    
    public boolean validate_form(){
        boolean proceed=true;
        if(firstNumberFld.getText().isEmpty() && proceed==true)
        {
            errorlbl.setText("Enter the first number");
            proceed=false;
        }
        if(secondNumberFld.getText().isEmpty() && proceed==true)
        {
            errorlbl.setText("Enter the second number");
            proceed=false;
        }
        if(operators.getValue()==null && proceed==true)
        {
            errorlbl.setText("Select the operator");
            proceed=false;
        }
        return proceed;
    }
}

class calculator{
    
    private Integer firstNumber;
    private Integer secondNumber;
    private String operator;
    private Double result;
    
    public calculator(Integer firstNumber, Integer secondNumber, String operator){
        this.firstNumber=firstNumber;
        this.secondNumber=secondNumber;
        this.operator=operator;
    }
    
    public boolean insertCalculatedRecords(){
        if(operator.equals("Addition")){
            result=new Double(firstNumber+secondNumber);
        }
        else if(operator.equals("Subtraction")){
            result=new Double(firstNumber-secondNumber);
        }
        else if(operator.equals("Multiplication")){
            result=new Double(firstNumber*secondNumber);
        }
        else{
            result=new Double(firstNumber/secondNumber);
        }
        try{
            String url="jdbc:mysql://localhost:3306/arithmetic";
            String uname="root";
            String pass="12345";
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load and register the driver
            Connection con=DriverManager.getConnection(url,uname,pass);
            String sql="insert into calculator values (?,?,?,?);";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1, firstNumber);
            stmt.setInt(2, secondNumber);
            stmt.setString(3, operator);
            stmt.setDouble(4, result);
            int i=stmt.executeUpdate();
            if(i>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

class dbmsconnection
{
    String url;
    String username;
    String password;
    
    public dbmsconnection(String url,String username,String password){
        this.url=url;
        this.username=username;
        this.password=password;
    }
    
    public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        Connection con=null;
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        con=DriverManager.getConnection(url,username,password);
        System.out.println("Connection Established Successfully");
        return con;
    }
    
    public void closeConnection(Connection con, Statement stmt) throws SQLException
    {
        stmt.close();
        con.close();
        System.out.println("The connection is closed");
    }
}

