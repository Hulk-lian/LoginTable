package julian.logintable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import julian.logintable.controllers.ILoginMVC;
import julian.logintable.controllers.LoginTable_Controller;


/**
 * @author Julián Troyano
 *
 *
 * */
public class LoginTable_Activity extends AppCompatActivity {


    //private LoginTable_Controller loginTableController;
    private ILoginMVC mLogin;

    //controles nomenclatura de google
    //toda variable definida en la clase empiezan por m
    private EditText medtUser,medtPassword;
    private Button mbtnOk,mbtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_table_);
        //inicializador clase Controladora del log
       // loginTableController= new LoginTable_Controller();
        mLogin=new LoginTable_Controller();

        //
        medtUser=(EditText)findViewById(R.id.edtUsuario);
        medtPassword=(EditText)findViewById(R.id.edtPass);

        mbtnOk=(Button)findViewById(R.id.btnOk);
        mbtnCancel=(Button)findViewById(R.id.btnCancel);

        //btnOK
        mbtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mens="";
                String user=medtUser.getText().toString();
                String password=medtPassword.getText().toString();

                    if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
                        Toast.makeText(LoginTable_Activity.this,getResources().getString(R.string.data_emptyES),Toast.LENGTH_LONG).show();
                    }
                    else {
                      int result=mLogin.validateCredentials(user,password);
                        switch (result){
                            case LoginTable_Controller.PASSWORD_DIGIT:
                                mens=getString(R.string.password_digit);
                                Toast.makeText(LoginTable_Activity.this,mens,Toast.LENGTH_LONG).show();
                                break;
                            case LoginTable_Controller.PASSWORD_CASE:
                                mens=getString(R.string.password_case);
                                Toast.makeText(LoginTable_Activity.this,mens,Toast.LENGTH_LONG).show();
                                break;
                            case LoginTable_Controller.PASSWORD_LENGTH:
                                mens=getString(R.string.password_length);
                                Toast.makeText(LoginTable_Activity.this,mens,Toast.LENGTH_LONG).show();
                                break;
                            case LoginTable_Controller.OK:
                                //throw the activity after the loggin.
                                break;
                        }
                    }
            }
        });

        //btnCancel
        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetValues();
            }
        });
    }

    private void resetValues() {
        medtPassword.setText("");
        medtUser.setText("");

    }
}
