package pets.sheltinder;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = getClass().getSimpleName();
    EditText etUsername, etPassword;
    Button bLogin;
    TextView tvRegisterLink, tvFailure;

//    UserLocalStore userLocalStore;

    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bLogin);

        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        tvFailure = (TextView) findViewById(R.id.tvFailure);

        bLogin.setOnClickListener(this);

        tvRegisterLink.setOnClickListener(this);

//        userLocalStore = new UserLocalStore(this);

        helper = new DataBaseHelper(this);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        switch (v.getId()) {
            case R.id.bLogin:

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (password.compareTo(helper.searchPassword(username)) == 0) {
                    tvFailure.setVisibility(View.GONE);

//                    User user = new User(null, null, false);
//                    userLocalStore.storeUserData(user);
//                    userLocalStore.setUserLoggedIn(true);

                    startActivity(new Intent(this, mainScreenActivity.class));
                } else {
                    tvFailure.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.tvRegisterLink:

                startActivity(new Intent(this, Register.class));

                break;
        }
    }
}
