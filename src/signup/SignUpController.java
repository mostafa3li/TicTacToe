
package signup;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Player;
import helpers.DbManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import TicTacToeGame.TicTacToeGame;


public class SignUpController implements Initializable {

   
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField cpass;
    @FXML
    private Label found;

    Player player = new Player();
    DbManager db = new DbManager();

    @FXML
    private void signUp(ActionEvent event) throws ClassNotFoundException, SQLException {

        String firstName1 = firstname.getText();
        String lastName1 = lastname.getText();
        String email1 = email.getText();
        String password1 = pass.getText();
        String cpassword1 = cpass.getText();
        boolean connect = db.connect();
        if (!firstName1.equals("") && !lastName1.equals("") && !email1.equals("") && !password1.equals("") && !cpassword1.equals("")){
            if (password1.equals(cpassword1)) {

                if (connect == true) {

                    boolean add = db.addPlayer(firstName1, lastName1, email1, password1);
                    if (add == true) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/login/LoginFxml.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = TicTacToeGame.getgStage();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        found.setText("player already exist");
                        firstname.setText("");
                        lastname.setText("");
                        email.setText("");
                        pass.setText("");
                        cpass.setText("");
                    }

                } else {
                    firstname.setText("");
                    lastname.setText("");
                    email.setText("");
                    pass.setText("");
                    cpass.setText("");

                    found.setText("please connect to database");
                }
            } else {
              
                found.setText("please write same password");
                pass.setText("");
                cpass.setText("");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
