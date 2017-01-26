package com.shul.michalizralevitch.shul2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shul.michalizralevitch.shul2.R;


/**
 * Created by michalizralevitch on 01/05/16.
 */
public class MessagesFrag extends Fragment {

    private EditText txtCbMesseges, txtCbMessegesTitle;
    private Button btnCbContactGabay,btnOrderAnAdd;
    private String[]TO = new String[2];
    private String SUBJECT;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.messages_frag, container, false);

        txtCbMesseges = (EditText)v.findViewById(R.id.txtMesseges);
        txtCbMesseges.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.txtMesseges) {
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                        case MotionEvent.ACTION_DOWN:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                        default:
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                return false;
            }
        });
        txtCbMesseges.addTextChangedListener(new GenericTextWatcher(txtCbMesseges));
        txtCbMessegesTitle = (EditText)v.findViewById(R.id.txtMessegesTitle);
        txtCbMessegesTitle.addTextChangedListener(new GenericTextWatcher(txtCbMessegesTitle));
        btnCbContactGabay=(Button)v.findViewById(R.id.btnContactGabay);
        btnOrderAnAdd=(Button)v.findViewById(R.id.btnOrderAnAdd);
        return v;
    }

    public void onSwipe() {
        if (txtCbMesseges==null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    onSwipe();
                }
            }, 50);
        }
        if (txtCbMesseges!= null) {
            txtCbMesseges.setText(Singleton.getSingleInstance().getBlogPost().getMesseges());
            txtCbMessegesTitle.setText(Singleton.getSingleInstance().getBlogPost().getMessegesTitle());
            if (!Singleton.getSingleInstance().getManager()) {
                txtCbMesseges.setKeyListener(null);
                txtCbMessegesTitle.setKeyListener(null);
                if(txtCbMesseges.getText().length() ==0)
                    txtCbMesseges.setVisibility(View.GONE);
                if (txtCbMessegesTitle.getText().length() == 0){
                    txtCbMessegesTitle.setVisibility(View.GONE);
                }
            }
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendEmail(v);
                }
            };
            btnCbContactGabay.setOnClickListener(onClickListener);
            btnOrderAnAdd.setOnClickListener(onClickListener);
        }
    }

    protected void sendEmail(View v) {

        if (v.getId()==R.id.btnContactGabay){
            TO[0]=Singleton.getSingleInstance().getBlogPost().getEmail1();
            TO[1]  =Singleton.getSingleInstance().getBlogPost().getEmail2();
            SUBJECT  =  Singleton.getSingleInstance().getBlogPost().getShulName();
        }else {
            TO[0] ="MiiizDeveloper@gmail.com";
            SUBJECT = "Shuls: Ask for an ad";
        }


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,SUBJECT);
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(),
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
