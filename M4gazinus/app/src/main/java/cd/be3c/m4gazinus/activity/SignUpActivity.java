package cd.be3c.m4gazinus.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import cd.be3c.m4gazinus.R;
import cd.be3c.m4gazinus.database.MagazinusDatabase;
import cd.be3c.m4gazinus.model.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private final  AppCompatActivity activity=SignUpActivity.this;

    TextInputEditText _name,_email,_password,_repassword;
    AppCompatButton _btnSignup;

    private NestedScrollView nestedScrollView;
    private MagazinusDatabase database;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database=new MagazinusDatabase(activity);
        user=new User();

        _name=(TextInputEditText)findViewById(R.id.txtInput_name);
        _email=(TextInputEditText)findViewById(R.id.txtInput_email);
        _password=(TextInputEditText)findViewById(R.id.txtInput_password);
        _repassword=(TextInputEditText)findViewById(R.id.txtInput_re_password);
        nestedScrollView=(NestedScrollView)findViewById(R.id.nestedScrollview);

        _btnSignup=(AppCompatButton) findViewById(R.id.btn_signup);

        _btnSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup:
                //code d'insertion dans la base des données
                if(!database.checkIfEMailExist(_email.getText().toString().trim())){
                    user.setName(_name.getText().toString().trim());
                    user.setEmail(_email.getText().toString().trim());
                    user.setPassword(_password.getText().toString().trim());

                    database.AjoutUtilisateur(user);

                    //snackbar
                    Snackbar.make(nestedScrollView,getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
                    _name.setText("");
                    _email.setText("");
                    _password.setText("");
                    //
                }else{
                    Snackbar.make(nestedScrollView,getString(R.string.failed_message),Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
