package com.example.radiobe.registrations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radiobe.R;
import com.example.radiobe.fragments.MainScreen;
import com.example.radiobe.models.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

public class Login extends AppCompatActivity {
    private static final String EMAIL = "email";
    private static final int RC_SIGN_IN = 9001;
    private EditText etName;
    private EditText etPassword;
    private CheckBox cbRemember;
    private TextView tvForgotPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private Button btnInstagram;
    private SignInButton signInButtonGoogle;
    private LoginButton loginButtonFacebook;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private AccessToken accessToken;
    private boolean isLoggedIn;
    private GoogleSignInOptions googleSignInbuilder;
    boolean isUserInDatabase = false;


    //CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupView();

        //CallbackManager callbackManager;
        callbackManager = CallbackManager.Factory.create();
        setupView();

        //Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }


        //Google SignIn
        googleSignInbuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //-------------------------------

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> { //TODO: query the data base and check if the user exits.
//            checkFirebaseUser(new User(etName.getText().toString(), etPassword.getText().toString()));
            String userName = etName.getText().toString();
            String password = etPassword.getText().toString();

            if (userName.length() < 1)
                etName.setError("Enter your user name");
            if (password.length() < 1)
                etPassword.setError("Enter your password");

            if (etName.getError() == null && etPassword.getError() == null) {
                userName = etName.getText().toString();
                password = etPassword.getText().toString();

                signIn(userName, password);


//                if (checkUserinDatabase(new User(userName, password))) {
//                    Intent intent = new Intent(this, MainScreen.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, "There is no such user! Sign Up!", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        loginButtonFacebook.setOnClickListener(v -> {
            FacebookLogIn();
            checkUserIsLoggedIn();
        });


    }


    private void checkUserIsLoggedIn() {
        accessToken = AccessToken.getCurrentAccessToken();
        isLoggedIn = accessToken != null && !accessToken.isExpired();
    }

    private void FacebookLogIn() {
        loginButtonFacebook.setReadPermissions("email", "public_profile");
        // If you are using in a fragment, call loginButtonFacebook.setFragment(this);

        // Callback registration
        loginButtonFacebook.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void signIn(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                        System.out.println("signInWithEmail:success");
                        //handle getting UserCredentials from server;
                        Intent intent = new Intent(this, MainScreen.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        System.out.println("signInWithEmail:failure" + task.getException());
                        Toast.makeText(this, "התחברות נכשלה, שם משתמש או סיסמא לא נכונים.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //TODO: Remember that i have changed firebaseAuth to FIrebase.

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            firebaseUser = firebaseAuth.getCurrentUser();
                            updateUI(firebaseUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }


    private void updateUI(FirebaseUser myFirebaseUser) {
        etName.setText(myFirebaseUser.getEmail());
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void setupView() {
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        loginButtonFacebook = findViewById(R.id.login_button);
    }
}


//Google SignIn
//    private void signIn() {
//        GoogleSignInApi mGoogleSignInClient;
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
// ---------------------------------

//    private void checkFirebaseUser(User user) {
//        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword());
//    }


//Google SignIn
//        signInButtonGoogle.setOnClickListener(v -> {
//            signIn();
//        });
//-------------------------
//    private boolean checkUserinDatabase(User newUser) {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
//        //single update
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //every child is basically a user object (not yet)
//                for (DataSnapshot child : dataSnapshot.getChildren()) {
////                    Object userObject = child.getValue();           //basically hashmap
////                    System.out.println(userObject);
//                    User user = child.getValue(User.class);
//                    if (user != null && user.getEmail().equals(newUser.getEmail())) {
//                        changeUserStatus();
//                        return;
//                    }
//                    System.out.println(user);
//                }
//
//                isUserInDatabase = false;
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
////                Toast.makeText(SignUp.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return isUserInDatabase;
//    }
//
//    private void changeUserStatus() {
//        isUserInDatabase = true;
//    }
