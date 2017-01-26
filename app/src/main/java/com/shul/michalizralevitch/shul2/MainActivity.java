package com.shul.michalizralevitch.shul2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shul.michalizralevitch.shul2.fragments.HandleView;
import com.shul.michalizralevitch.shul2.fragments.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    /*
    About the app

       App for communities to share information.

       A community can sign up to the app and shares its shul's information, though other people can
        see the information and also get contact with the "gabbais" (= people who act like a "head" /
        managers of the community.).

        There are 1 or 2 managers ("gabbais") for each shul, and only them can manage the shul data.

        Note: I use Firebase to save the data.

     */

    /*
    This is the first screen of the app.

    In this screen the user chooses a shul that he is interested to its information.
    he has to know something about the shul, like its name/city, then he insert it in the window
    and by using "auto complete text view" it's easier for the user to find his request.

    if a user know that he usually want information about a specific shul, he can check: "remember
     choice".

    */

    private Toolbar toolbar;
    private List<String> lstShuls = new ArrayList<>();//I'll lunch the names from firebase to that list
    private CheckBox chkRememberCb;
    private ProgressBar progress;
    private AutoCompleteTextView autoCompleteTextView;
    private String chosenShul = "";//in order to lunch the specific data on the next activity
    private Firebase ref;

    public static final String MY_PREFERENCES = "MY_PREFERENCES";
    public static final String KEY_1 = "KEY1";
    private  int PERMISSIONS_REQUEST_GET_ACCOUNTS = 2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences =
                this.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String shulToRemember = sharedPreferences.getString(KEY_1, "");
        if (!shulToRemember.equalsIgnoreCase("")) {
            Intent intent = new Intent(MainActivity.this, HandleView.class);
            intent.putExtra("chosenShul", shulToRemember);
            startActivity(intent);
        }
        CheckPermission();

        Firebase.setAndroidContext(getApplicationContext());
        ref = new Firebase("https://someadress.firebaseio.com/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    BlogPost shul = postSnapshot.getValue(BlogPost.class);
                    lstShuls.add(shul.getShulName());
                }
                initilizeAdapter();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        chkRememberCb = (CheckBox) findViewById(R.id.chkRemember);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        autoCompleteTextView.setCursorVisible(false);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initilizeAdapter() {


        ArrayAdapter arrayAdapterObj = new ArrayAdapter(this, R.layout.my_list_view, lstShuls);
        autoCompleteTextView.setAdapter(arrayAdapterObj);
        autoCompleteTextView.setCursorVisible(true);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView myTextView = (TextView) view;
        chosenShul = myTextView.getText().toString();
        Singleton.getSingleInstance().setStr(chosenShul);
        chkRememberCb.setVisibility(View.VISIBLE);
        //hiding the keyboard:
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        //I want to give the user an opportunity to check the remember choice, so i give him time..

        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);

        progress.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoCompleteTextView.setText(null);
                progress.setVisibility(View.INVISIBLE);
                if (chkRememberCb.isChecked()) {
                    chkRememberCb.setChecked(false);
                }
            }
        }, 3000);

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, HandleView.class);
                intent.putExtra("chosenShul", chosenShul);
                startActivity(intent);

            }
        };
        Timer t = new Timer();
        t.schedule(task, 3000);
    }


    public void btnCreateNewShul(View view) {

        /*
        In order to create a new shul, i ask the user to send me email and i create a new shul for
         him in another app.
         The code of the other app doesnt appear in this project, bur of course the reference to Firebase
          there is the same ref as in this project.
         */


        String[] TO = {"miiizDeveloper@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Shuls: New Shul");

        //Asking the user to give a specific data:
        emailIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.ask_for_new_shul) + "\n" +
                getResources().getString(R.string.ask_new_shul_line2)
                + "\n" + getResources().getString(R.string.ask_new_shul_line3));
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(),
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public void chkRemember(View view) {
        if (chkRememberCb.isChecked()) {
            SharedPreferences sharedPreferences =
                    this.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_1, chosenShul);
            editor.commit();

        }
    }

    //ask "get accounts" permission for marshmelo

    public void CheckPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.GET_ACCOUNTS)) {
                //i didnt do anything yet.... i know.. i may use it in a further version
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.GET_ACCOUNTS},
                        PERMISSIONS_REQUEST_GET_ACCOUNTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
//            }
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}



