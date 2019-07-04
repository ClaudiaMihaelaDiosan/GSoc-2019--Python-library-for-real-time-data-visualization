package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Connectivity.ConnectivityReceiver;
import gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.R;

public class LoginActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener,
        ConnectivityReceiver.ConnectivityReceiverListener {


    // View
    TextView forgotPassword;
    private View loginForm;

    //EditText
    private EditText emailView;
    private EditText passwordView;


    //ImageView
    private ImageView noInternetConnection;

    private SharedPreferences prefs;
    private ProgressBar progressBar;

    //Buttons
    Button registerBtn;
    Button loginBtn;

    private BroadcastReceiver connectivityReceiver = null;
    Boolean connectivityOn;


    private static final String PACKAGE_NAME = "gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = this.getSharedPreferences(
                PACKAGE_NAME, MODE_PRIVATE);

        progressBar = findViewById(R.id.progress_bar);
        emailView = findViewById(R.id.email_register);
        passwordView = findViewById(R.id.password);
        loginForm = findViewById(R.id.login_form);
        noInternetConnection = findViewById(R.id.no_connectivity);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.forgot_password).setOnClickListener(this);
        findViewById(R.id.create_account_button).setOnClickListener(this);




   /*     registerBtn = findViewById(R.id.create_account_button);

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                builder.setMessage(getString(R.string.conditions_register))
                        .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                    startActivity(intent);
                            }
                        }).setNegativeButton("Cancel", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }); */

        connectivityReceiver = new ConnectivityReceiver();

    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectivityReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectivityReceiver.connectivityReceiverListener = (ConnectivityReceiver.ConnectivityReceiverListener) this;
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.create_account_button) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

            builder.setMessage(getString(R.string.conditions_register))
                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancel", null);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (v.getId() == R.id.sign_in_button) {
            // signIn(emailView.getText().toString(), passwordView.getText().toString());

        } else if (v.getId() == R.id.forgot_password) {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        }
    }


    private boolean validateForm() {

        emailView.setError(null);
        passwordView.setError(null);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // If password, check if is valid
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_password_too_short));
            focusView = passwordView;
            cancel = true;
        }

        //If email, check if is valid
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) focusView.requestFocus();

        return !cancel;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showForm(final boolean show){

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            loginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
        }else {
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }

        if (show){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNetworkConnectionChanged(String status) {

        connectivityOn = prefs.getBoolean(getResources().getString(R.string.connectivity_on), false);

        if (status.equals(getResources().getString(R.string.wifi_connected))){
            loginForm.setVisibility(View.VISIBLE);
            noInternetConnection.setVisibility(View.GONE);
        }else if (status.equals(getResources().getString(R.string.mobile_internet))){
            loginForm.setVisibility(View.VISIBLE);
            noInternetConnection.setVisibility(View.GONE);
        }else {
            loginForm.setVisibility(View.INVISIBLE);
            noInternetConnection.setVisibility(View.VISIBLE);

        }
    }






    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.about_us:
                Intent intent1 = new Intent(this, AboutUsActivity.class);
                this.startActivity(intent1);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);



                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
