
import java.sql.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;


public class TrainTicketBooking extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        int choice;
        System.out.println("Do you want to \n1. Register\n2. Login\nEnter response: ");
        Scanner scan=new Scanner(System.in);
        choice=scan.nextInt();
        if(choice==1){
            RegistrationForm registrationForm=new RegistrationForm();
            registrationForm.showForm(primaryStage);
        }
        else if(choice==2){
            LoginForm loginForm=new LoginForm();
            loginForm.showForm(primaryStage);
        }
        else{
            System.out.println("Wrong choice entered");
        }
    }
    
}

class RegistrationForm{
    
    Label mobileNolbl;
    TextField mobileNoField;
    Label emailIdlbl;
    TextField emailIdField;
    Label firstNamelbl;
    TextField firstNameField;
    Label middleNamelbl;
    TextField middleNameField;
    Label lastNamelbl;
    TextField lastNameField;
    
    Label useridlbl;
    TextField useridField;
    Label passwordlbl;
    TextField passwordField;
    
    Label doblbl;
    DatePicker dobField;
    Label nationalitylbl;
    ChoiceBox nationalityField;
    Label occupationlbl;
    ChoiceBox occupationField;
    
    Label residenceAddrlbl;
    TextField residenceAddrField;
    
    Label streetlbl;
    TextField streetField;
    Label arealbl;
    TextField areaField;
    Label countrylbl;
    ChoiceBox countryField;
    Label pincodelbl;
    TextField pincodeField;
    Label citylbl;
    TextField cityField;
    Label statelbl;
    ChoiceBox stateField;
    Label postofficelbl;
    TextField postofficeField;
    
    Label errorlbl;
    Button register;
    
    String name="";
    String dob="";
    
    public void showForm(Stage primaryStage){
        
        mobileNolbl=new Label("Mobile number ");
        mobileNoField=new TextField();
        emailIdlbl=new Label("Email Id ");
        emailIdField=new TextField();
        firstNamelbl=new Label("First name ");
        firstNameField=new TextField();
        middleNamelbl=new Label("Middle name ");
        middleNameField=new TextField();
        lastNamelbl=new Label("Last name ");
        lastNameField=new TextField();
        
        useridlbl=new Label("User Id ");
        useridField=new TextField();
        passwordlbl=new Label("Password ");
        passwordField=new PasswordField();
        
        doblbl=new Label("DOB (dd/mm/yyyy) ");
        dobField=new DatePicker();
        nationalitylbl=new Label("Nationality ");
        nationalityField=new ChoiceBox();
        nationalityField.getItems().addAll("Indian","Pakistani","Bangladeshi","Nepali");
        occupationlbl=new Label("Occupation ");
        occupationField=new ChoiceBox();
        occupationField.getItems().addAll("Teacher","Lawyer","Engineer","Doctor","Others");
        
        residenceAddrlbl=new Label("Residential Address ");
        residenceAddrField=new TextField(); residenceAddrField.setPromptText("Enter your residential address");
        streetlbl=new Label("Street ");
        streetField=new TextField();
        arealbl=new Label("Area ");
        areaField=new TextField();
        countrylbl=new Label("Country ");
        countryField=new ChoiceBox();
        countryField.getItems().addAll("India","USA","UK");
        pincodelbl=new Label("Pincode ");
        pincodeField=new TextField(); pincodeField.setPromptText("XXXXXX");
        citylbl=new Label("City ");
        cityField=new TextField();
        statelbl=new Label("State ");
        stateField=new ChoiceBox();
        stateField.getItems().addAll("Delhi","Maharashtra","West Bengal","Tamil Nadu","Karnataka","Andhra Pradesh");
        postofficelbl=new Label("Post office ");
        postofficeField=new TextField();
        
        errorlbl=new Label();
        register=new Button(" Register ");
        
        GridPane root=new GridPane();
        root.addRow(0,mobileNolbl,mobileNoField);
        root.addRow(1,emailIdlbl,emailIdField);
        root.addRow(2,firstNamelbl,firstNameField,middleNamelbl,middleNameField,lastNamelbl,lastNameField);
        
        root.addRow(3,useridlbl,useridField);
        root.addRow(4,passwordlbl,passwordField);
        
        root.addRow(5,doblbl,dobField);
        root.addRow(6,nationalitylbl,nationalityField);
        root.addRow(7,occupationlbl,occupationField);
        
        root.addRow(8,residenceAddrlbl,residenceAddrField,streetlbl,streetField,arealbl,areaField);
        root.addRow(9,countrylbl,countryField);
        root.addRow(10,pincodelbl,pincodeField);
        root.addRow(11,citylbl,cityField);
        root.addRow(12,statelbl,stateField);
        root.addRow(13,postofficelbl,postofficeField);
        
        root.addRow(14,register);
        root.addRow(15,errorlbl);
        
        root.setVgap(10);
        root.setHgap(10);
        Scene sc=new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);
        root.setStyle("-fx-padding: 10;");
        primaryStage.setTitle("Train Booking User Registration");
        primaryStage.show();
        
        register.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                boolean connect=validate_form();
                if(connect)
                {
                    name+=firstNameField.getText()+" "+middleNameField.getText()+" "+lastNameField.getText();
                    dob+=dobField.getValue().toString();
                    
                    passenger p=new passenger(mobileNoField.getText(),emailIdField.getText(),name,useridField.getText(),passwordField.getText(),dob,nationalityField.getValue().toString(),occupationField.getValue().toString(),residenceAddrField.getText(),streetField.getText(),areaField.getText(),countryField.getValue().toString(),Integer.parseInt(pincodeField.getText()),cityField.getText(),stateField.getValue().toString(),postofficeField.getText());
                    
                    boolean result=p.insertRegisteredPassengerRecords();
                    if(result)
                    {
                        errorlbl.setText("Registration is successfull");
                    }
                    else
                    {
                        errorlbl.setText("Registration is not successfull");
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
        if(useridField.getText().isEmpty() && proceed==true)
        {
            errorlbl.setText("Enter your UserId");
            proceed=false;
        }
        if(passwordField.getText().isEmpty() && proceed==true)
        {
            errorlbl.setText("Enter your Password");
            proceed=false;
        }
        return proceed;
    }
}

class LoginForm{
    
    Label loginidlbl;
    TextField loginidField;
    Label passwordlbl;
    TextField passwordField;
    Label otplbl;
    TextField otpField;
    Label errorlbl;
    Button login;
    
    public int min=0;
    public int max=900000;
    public String otpstr="";
    
    public void showForm(Stage primaryStage){
        
        loginidlbl=new Label("Enter your LoginId ");
        loginidField=new TextField();
        passwordlbl=new Label("Enter your Password ");
        passwordField=new PasswordField();
        otplbl=new Label("Your OTP ");
        otpField=new TextField();
        errorlbl=new Label();
        login=new Button(" Login ");
        
        GridPane root=new GridPane();
        root.addRow(0,loginidlbl,loginidField);
        root.addRow(1,passwordlbl,passwordField);
        root.addRow(2,otplbl,otpField);
        root.addRow(3,login);
        root.addRow(4,errorlbl);
        root.setVgap(10);
        root.setHgap(10);
        otpstr+=(int)(Math.random()*(max-min+1)+min);
        otpField.setText(otpstr.toString());
        
        Scene sc=new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        root.setStyle("-fx-padding: 10;");
        primaryStage.setTitle("Train Booking User Login");
        primaryStage.show();
        
        login.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                boolean connect=validate_form();
                if(connect)
                {
                    passenger p=new passenger(loginidField.getText(),passwordField.getText());
                    boolean result=p.checkPassengerLogin();
                    if(result)
                    {
                        //errorlbl.setText("Login is successfull");
                        
                        //After successfull login
                        
                        GridPane log=new GridPane();
                        
                        //Passenger details
                        Label passId=new Label("Enter your passenger Id ");
                        TextField passIdField=new TextField();
                        
                        Label passNamelbl=new Label("Enter your name ");
                        TextField passNameField=new TextField();
                        
                        Label passAgelbl=new Label("Enter your age ");
                        TextField passAgeField=new TextField();
                        
                        Label passGenderlbl=new Label("Select gender ");
                        ToggleGroup groupGender = new ToggleGroup(); 
                        RadioButton maleRadio = new RadioButton("male"); 
                        maleRadio.setToggleGroup(groupGender); 
                        RadioButton femaleRadio = new RadioButton("female"); 
                        femaleRadio.setToggleGroup(groupGender);
                        
                        Label berthPreference=new Label("Berth preference ");
                        
                        //check box for upper berth 
                        CheckBox upperBerth = new CheckBox("Upper berth"); 
                        upperBerth.setIndeterminate(false); 
      
                        //check box for middle berth 
                        CheckBox middleBerth = new CheckBox("Middle berth"); 
                        middleBerth.setIndeterminate(false); 
                        
                        //check box for lower berth 
                        CheckBox lowerBerth = new CheckBox("Lower berth"); 
                        lowerBerth.setIndeterminate(false); 
                        
                        Label specialStatus=new Label("Choose special status ");
                        
                        ToggleGroup groupSplStatus = new ToggleGroup(); 
                        RadioButton childRadio = new RadioButton("Child"); 
                        childRadio.setToggleGroup(groupSplStatus); 
                        RadioButton seniorRadio = new RadioButton("Senior citizen"); 
                        seniorRadio.setToggleGroup(groupSplStatus);
                        
                        //berth needed, any quota, tatkal
                        
                        //display total ticket costs
                        
                        //Other details
                        Label fromStationlbl=new Label("From Station ");
                        TextField fromStationField=new TextField();
                        Label toStationlbl=new Label("To station ");
                        TextField toStationField=new TextField();
                        Label journeyDatelbl=new Label("Date of journey ");
                        DatePicker journeyDateField=new DatePicker();
                        Label availableTrains=new Label("Available trains for your journey ");
                        //Display available trains
                        //list View for all available trains 
                        ObservableList<String> trainids = FXCollections.observableArrayList( 
                        "953332", "954442", "955543", "976432", "983443", "986222", "986543"); 
                        ListView<String> trainidListView = new ListView<String>(trainids); 
                        Label availableTickets=new Label("Available tickets for different classes with their cost ");
                        //Display no. of tickets available classwise, and its cost
                        ObservableList<String> tickets = FXCollections.observableArrayList( 
                        "AC First Class Rs.987", "AC 2-Tier Rs.878", "AC 3-Tier Rs.767", "First Class Rs.656", "AC Chair Car Rs.452", "Sleeper Rs.388", "Second Sitting Rs.198"); 
                        ListView<String> ticketsListView = new ListView<String>(tickets); 
                        //Choose a train
                        
                        Button proceedButton=new Button(" Proceed ");
                        
                        Label errorlbl=new Label();
                        
                        Button proceedToPay=new Button(" Make Payment ");
                        
                        log.addRow(0,passId,passIdField);
                        log.addRow(1,passNamelbl,passNameField);
                        log.addRow(2,passAgelbl,passAgeField);
                        log.addRow(3,passGenderlbl);
                        log.addRow(4,maleRadio,femaleRadio);
                        
                        log.addRow(5,fromStationlbl,fromStationField,toStationlbl,toStationField);
                        log.addRow(6,journeyDatelbl,journeyDateField);
                        log.addRow(7,availableTrains,trainidListView);
                        log.addRow(8,availableTickets,ticketsListView);
                                
                        log.addRow(9,berthPreference);
                        log.addRow(10,upperBerth);
                        log.addRow(11,middleBerth);
                        log.addRow(12,lowerBerth);
                        log.addRow(13,specialStatus);
                        log.addRow(14,childRadio,seniorRadio);
                        log.addRow(15,proceedButton);
                        log.addRow(16,errorlbl);
                        
                        log.setVgap(10);
                        log.setHgap(10);
                        Scene logScene=new Scene(log);
                        primaryStage.setScene(logScene);
                        primaryStage.setWidth(800);
                        primaryStage.setHeight(600);
                        log.setStyle("-fx-padding: 10;");
                        primaryStage.setTitle("Passenger details");
                        primaryStage.show();
                        
                        proceedButton.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                String gender="";
                                String berth="";
                                String splstatus="";
                                String doj="";
                                String trainidListView="953332";
                                String ticketsListView="AC First Class Rs.987";
                                if(maleRadio.isSelected()){
                                    gender=maleRadio.getText();
                                }
                                else{
                                    gender=femaleRadio.getText();
                                }
                                if(upperBerth.isSelected()){
                                    berth+=upperBerth.getText()+" ";
                                }
                                if(middleBerth.isSelected()){
                                    berth+=middleBerth.getText()+" ";
                                }
                                if(lowerBerth.isSelected()){
                                    berth+=lowerBerth.getText();
                                }
                                if(childRadio.isSelected()){
                                    splstatus=childRadio.getText();
                                }
                                else{
                                    splstatus=seniorRadio.getText();
                                }
                                doj+=journeyDateField.getValue().toString();
                                passenger p=new passenger(Integer.parseInt(passIdField.getText()),passNameField.getText(),Integer.parseInt(passAgeField.getText()),gender,fromStationField.getText(),toStationField.getText(),doj,trainidListView,ticketsListView,berth,splstatus);
                                boolean result=p.insertPassengerDetails();
                                if(result)
                                {
                                    errorlbl.setText("Passenger details stored successfull");
                                    log.addRow(17,proceedToPay);
                                    proceedToPay.setOnAction(new EventHandler<ActionEvent>(){
                                        @Override
                                        public void handle(ActionEvent event){
                                            max=999999;
                                            min=100000;
                                            int a=(int)(Math.random()*(max-min+1)+min);
                                            String stra=Integer.toString(a);
                                            GridPane pay=new GridPane();
                                            Label loginidlbl=new Label("Login Id ");
                                            TextField loginidField=new TextField();
                                            Label passwordlbl=new Label("Password ");
                                            PasswordField passwordField=new PasswordField();
                                            Label otp=new Label("OTP ");
                                            TextField otpField=new TextField();
                                            otpField.setText(stra);
                                            Label fundtransfer=new Label("Fund Transfer (Enter Amount in INR) ");
                                            TextField fundtransferField=new TextField();
                                            Button confirm=new Button(" Confirm ");
                                            
                                            pay.addRow(0,loginidlbl,loginidField);
                                            pay.addRow(1,passwordlbl,passwordField);
                                            pay.addRow(2,otp,otpField);
                                            pay.addRow(3,fundtransfer,fundtransferField);
                                            pay.addRow(4,confirm);
                                            Scene payScene=new Scene(pay);
                                            primaryStage.setScene(payScene);
                                            primaryStage.setWidth(400);
                                            primaryStage.setHeight(400);
                                            pay.setStyle("-fx-padding: 10;");
                                            primaryStage.setTitle("Make Payment");
                                            primaryStage.show();
                                            
                                            confirm.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override 
                                                public void handle(ActionEvent event) {
                                                p.printReceipt();
                                                }
                                            });
                                        }
                                    });
                                }
                                else
                                {
                                    errorlbl.setText("Passenger details not stored successfull");
                                }
                        
                            }
                        });
                }
                else
                {
                    errorlbl.setTextFill(Color.RED);
                }
                }
                
            }
        });
        
    }
    
    public boolean validate_form(){
        boolean proceed=true;
        if(loginidField.getText().isEmpty() && proceed==true)
        {
            errorlbl.setText("Enter your LoginId");
            proceed=false;
        }
        if(passwordField.getText().isEmpty() && proceed==true)
        {
            errorlbl.setText("Enter your Password");
            proceed=false;
        }
        return proceed;
    }
    
}

class passenger{
    
    String mobnum;
    String email;
    String name;
    String userid;
    String password;
    String dob;
    String nationality;
    String occupation;
    String addr;
    String street;
    String area;
    String country;
    int pincode;
    String city;
    String state;
    String postoffice;
    
    int passid;
    String passname;
    int passage;
    String gender;
    String fromstn;
    String tostn;
    String doj;
    String trainlist;
    String ticketlist;
    String berth;
    String splstatus;
    
    public passenger(String userid,String password){
        this.userid=userid;
        this.password=password;
    }
    
    public passenger(String mobnum,String email,String name,String userid,String password,String dob,String nationality,String occupation,String addr,String street,String area,String country,int pincode,String city,String state,String postoffice) {
        this.mobnum=mobnum;
        this.email=email;
        this.name=name;
        this.userid=userid;
        this.password=password;
        this.dob=dob;
        this.nationality=nationality;
        this.occupation=occupation;
        this.addr=addr;
        this.street=street;
        this.area=area;
        this.country=country;
        this.pincode=pincode;
        this.city=city;
        this.state=state;
        this.postoffice=postoffice;
    }

    passenger(int parseInt, String text, int parseInt0, String gender, String text0, String text1, String doj, String trainidListView, String ticketsListView, String berth, String splstatus) {
        passid=parseInt;
        passname=text;
        passage=parseInt0;
        this.gender=gender;
        fromstn=text0;
        tostn=text1;
        this.doj=doj;
        trainlist=trainidListView;
        ticketlist=ticketsListView;
        this.berth=berth;
        this.splstatus=splstatus;
    }
    
    public boolean insertRegisteredPassengerRecords(){
        try{
            String url="jdbc:mysql://localhost:3306/trainticketbooking";
            String uname="root";
            String pass="12345";
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load and register the driver
            Connection con=DriverManager.getConnection(url,uname,pass);
            String sql="insert into registeredusers values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1, mobnum);
            stmt.setString(2, email);
            stmt.setString(3, name);
            stmt.setString(4, userid);
            stmt.setString(5, password);
            stmt.setString(6, dob);
            stmt.setString(7, nationality);
            stmt.setString(8, occupation);
            stmt.setString(9, addr);
            stmt.setString(10, street);
            stmt.setString(11, area);
            stmt.setString(12, country);
            stmt.setInt(13, pincode);
            stmt.setString(14, city);
            stmt.setString(15, state);
            stmt.setString(16, postoffice);
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
    
    public boolean checkPassengerLogin(){
        try{
            String url="jdbc:mysql://localhost:3306/trainticketbooking";
            String uname="root";
            String pass="12345";
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load and register the driver
            Connection con=DriverManager.getConnection(url,uname,pass);
            String sql="select password from registeredusers where userid='"+userid+"';";
            Statement stmt=con.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next())
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
    
    public boolean insertPassengerDetails(){
        try{
            String url="jdbc:mysql://localhost:3306/trainticketbooking";
            String uname="root";
            String pass="12345";
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load and register the driver
            Connection con=DriverManager.getConnection(url,uname,pass);
            String sql="insert into passengerdetails values (?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1, passid);
            stmt.setString(2, passname);
            stmt.setInt(3, passage);
            stmt.setString(4, gender);
            stmt.setString(5, fromstn);
            stmt.setString(6, tostn);
            stmt.setString(7, doj);
            stmt.setString(8, trainlist);
            stmt.setString(9, ticketlist);
            stmt.setString(10, berth);
            stmt.setString(11, splstatus);
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
    
    public void printReceipt(){
        System.out.println("-----------------------------------------------------");
        System.out.println("Payment Success!!!");
        System.out.println("Passenger Name : "+passname);
        System.out.println("Passenger Id : "+passid);
        System.out.println("Gender : "+gender);
        System.out.print("From Station : "+fromstn+"      ");
        System.out.println("To Station : "+tostn);
        System.out.println("Date of journey : "+doj);
        System.out.println("Train Booked : "+trainlist);
        System.out.println("Ticket type : "+ticketlist);
        System.out.println("Coach No : A2");
        System.out.println("Berth assigned : "+berth);
        System.out.println("Special status applied for : "+splstatus);
        System.out.println("-----------------------------------------------------");
        System.out.println("Thank you for using IRCTC Train reservation portal!!!");
        System.out.println("-----------------------------------------------------");
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

