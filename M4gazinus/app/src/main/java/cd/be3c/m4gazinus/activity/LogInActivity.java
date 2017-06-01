package cd.be3c.m4gazinus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cd.be3c.m4gazinus.MainActivity;
import cd.be3c.m4gazinus.R;
import cd.be3c.m4gazinus.database.MagazinusDatabase;
import cd.be3c.m4gazinus.model.User;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private final  AppCompatActivity activity=LogInActivity.this;

    EditText _username;
    EditText _password;
    Button _btnvalider;
    TextView _forgot;

    private MagazinusDatabase database;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        database=new MagazinusDatabase(activity);
        user=new User();

        _username=(EditText)findViewById(R.id.input_username);
        _password=(EditText)findViewById(R.id.input_password);
        _btnvalider=(Button) findViewById(R.id.btn_login);
        _forgot=(TextView) findViewById(R.id.link_forgot);

        //OnClickListener sur le bouton
        _btnvalider.setOnClickListener(this);
        _forgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //le code de fonctionnement lors du clic
                /*if(_username.getText().toString().equals("TOTO") && _password.getText().toString().equals("TATA")){

                   // Intent activity=new Intent(LogInActivity.this, SignUpActivity.class);
                    Intent activity=new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(activity);

                    *//*Toast.makeText(getApplicationContext(),"CONNEXION REUSSIE",Toast.LENGTH_LONG)
                            .show();
                }*/

                if(database.checkIfUserExist(_username.getText().toString().trim(),_password.getText().toString().trim()))
                {

                    Intent new_intent=new Intent(LogInActivity.this, MainActivity.class);
                    //passage des valeurs
                    new_intent.putExtra("usermail",_username.getText().toString().trim());
                    //lancement de l'activity
                    startActivity(new_intent);
                }

                break;
            case R.id.link_forgot:
                Intent new_intent=new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(new_intent);
                break;
        }
    }
}
