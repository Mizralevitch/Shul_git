package com.shul.michalizralevitch.shul2.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.shul.michalizralevitch.shul2.R;

/**
 * Created by michalizralevitch on 01/05/16.
 */
public class MainFrag extends Fragment {

    private TextView  lblCbAdress,lblShulName;

    private EditText txtCbParasha,txtCbCantor,txtCbChoir, txtCbShalom;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.main_frag, container, false);

        lblCbAdress = (TextView)v.findViewById(R.id.lblAdress);
        lblShulName=(TextView)v.findViewById(R.id.lblShullName);

        txtCbParasha=(EditText)v.findViewById(R.id.txtParasha);
        //Adding textChangedListener for editing option for the "managers":
        txtCbParasha.addTextChangedListener(new GenericTextWatcher(txtCbParasha));
        txtCbCantor=(EditText)v.findViewById(R.id.txtCantor);
        txtCbCantor.addTextChangedListener(new GenericTextWatcher(txtCbCantor));
        txtCbChoir=(EditText)v.findViewById(R.id.txtChoir);
        txtCbChoir.addTextChangedListener(new GenericTextWatcher(txtCbChoir));
        txtCbShalom = (EditText)v.findViewById(R.id.txtShalom);
        txtCbShalom.addTextChangedListener(new GenericTextWatcher(txtCbShalom));

        return v;
    }


    public void onSwipe() {
        //I have to play the data only after the editText had created
        if (lblCbAdress==null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    onSwipe();
                }
            }, 50);
        }
        if (lblCbAdress!=null) {
            lblCbAdress.setText(Singleton.getSingleInstance().getBlogPost().getParasha());
            txtCbShalom.setText(Singleton.getSingleInstance().getBlogPost().getShalom());
            txtCbCantor.setText(Singleton.getSingleInstance().getBlogPost().getCantor());
            txtCbParasha.setText(Singleton.getSingleInstance().getBlogPost().getParasha());
            txtCbChoir.setText(Singleton.getSingleInstance().getBlogPost().getChoir());
            lblCbAdress.setText(Singleton.getSingleInstance().getBlogPost().getAdress());
            lblShulName.setText(Singleton.getSingleInstance().getBlogPost().getShulName());

            if (!Singleton.getSingleInstance().getManager()) {
                //block the regular user from the editText and show it as a textView:
                txtCbCantor.setKeyListener(null);
                txtCbChoir.setKeyListener(null);
                txtCbShalom.setKeyListener(null);
                txtCbParasha.setKeyListener(null);

                //The followings are to normalized the view
                if (txtCbCantor.getText().toString().length()==0)
                    txtCbCantor.setVisibility(View.GONE);
                if (txtCbChoir.getText().toString().length()==0)
                    txtCbChoir.setVisibility(View.GONE);
                if(txtCbShalom.getText().toString().length()==0)
                    txtCbShalom.setVisibility(View.GONE);
                if (txtCbParasha.getText().toString().length()==0)
                    txtCbParasha.setVisibility(View.GONE);
            }
        }
    }

}
