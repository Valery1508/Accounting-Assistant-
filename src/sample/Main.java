package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.view.MainFrame;

import java.sql.DriverManager;
import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String userName = "root";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost:3306/marketing";
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password)){
            System.out.println("we're connected");
        }

        MainFrame mainFrame = new MainFrame(primaryStage);
        mainFrame.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
