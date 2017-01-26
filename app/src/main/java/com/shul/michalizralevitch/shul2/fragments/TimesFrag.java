package com.shul.michalizralevitch.shul2.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.shul.michalizralevitch.shul2.R;

/**
 * Created by michalizralevitch on 01/05/16.
 */
public class TimesFrag extends Fragment {


    EditText txtCbTimeLine3, txtCbTimeLine4,txtCbTimeLine5,txtCbTimeLine6,
            txtCbTimeLine7,txtCbTimeLine8,txtCbHavdala,txtCbLightening;
    ScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.times_frag, container, false);


        txtCbHavdala=(EditText)v.findViewById(R.id.txtHavdala);
        txtCbHavdala.addTextChangedListener(new GenericTextWatcher(txtCbHavdala));

        txtCbLightening = (EditText)v.findViewById(R.id.txtLightening);
        txtCbLightening.addTextChangedListener(new GenericTextWatcher(txtCbLightening));

        txtCbTimeLine3=(EditText)v.findViewById(R.id.txtTimeLine3);
        txtCbTimeLine3.addTextChangedListener(new GenericTextWatcher(txtCbTimeLine3));

        txtCbTimeLine4=(EditText)v.findViewById(R.id.txtTimeLine4);
        txtCbTimeLine4.addTextChangedListener(new GenericTextWatcher(txtCbTimeLine4));

        txtCbTimeLine5=(EditText)v.findViewById(R.id.txtTimeLine5);
        txtCbTimeLine5.addTextChangedListener(new GenericTextWatcher(txtCbTimeLine5));

        txtCbTimeLine6=(EditText)v.findViewById(R.id.txtTimeLine6);
        txtCbTimeLine6.addTextChangedListener(new GenericTextWatcher(txtCbTimeLine6));

        txtCbTimeLine7=(EditText)v.findViewById(R.id.txtTimeLine7);
        txtCbTimeLine7.addTextChangedListener(new GenericTextWatcher(txtCbTimeLine7));

        txtCbTimeLine8=(EditText)v.findViewById(R.id.txtTimeLine8);
        txtCbTimeLine8.addTextChangedListener(new GenericTextWatcher(txtCbTimeLine8));

        scrollView = (ScrollView)v.findViewById(R.id.myScroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.myScroll) {
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
        return v;
    }




    public void onSwipe(){
        if (txtCbLightening==null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    onSwipe();
                }
            }, 50);
        }

        if (txtCbLightening!=null) {
            txtCbLightening.setText(Singleton.getSingleInstance().getBlogPost().getHadlakatNerot());
            txtCbHavdala.setText(Singleton.getSingleInstance().getBlogPost().getHavdala());
            txtCbTimeLine3.setText(Singleton.getSingleInstance().getBlogPost().getTimesLine3());
            txtCbTimeLine4.setText(Singleton.getSingleInstance().getBlogPost().getTimesLine4());
            txtCbTimeLine5.setText(Singleton.getSingleInstance().getBlogPost().getTimesLine5());
            txtCbTimeLine6.setText(Singleton.getSingleInstance().getBlogPost().getTimesLine6());
            txtCbTimeLine7.setText(Singleton.getSingleInstance().getBlogPost().getTimesLine7());
            txtCbTimeLine8.setText(Singleton.getSingleInstance().getBlogPost().getTimesLine8());

            if (!Singleton.getSingleInstance().getManager()) {
                txtCbLightening.setKeyListener(null);
                txtCbHavdala.setKeyListener(null);
                txtCbTimeLine3.setKeyListener(null);
                txtCbTimeLine4.setKeyListener(null);
                txtCbTimeLine5.setKeyListener(null);
                txtCbTimeLine6.setKeyListener(null);
                txtCbTimeLine7.setKeyListener(null);
                txtCbTimeLine8.setKeyListener(null);
                if (txtCbLightening.getText().length()==0)
                    txtCbLightening.setVisibility(View.GONE);
                if (txtCbHavdala.getText().length()==0)
                    txtCbHavdala.setVisibility(View.GONE);
                if (txtCbTimeLine3.getText().length()==0)
                    txtCbTimeLine3.setVisibility(View.GONE);
                if (txtCbTimeLine4.getText().length()==0)
                    txtCbTimeLine4.setVisibility(View.GONE);
                if (txtCbTimeLine5.getText().length()==0)
                    txtCbTimeLine5.setVisibility(View.GONE);
                if (txtCbTimeLine6.getText().length()==0)
                    txtCbTimeLine6.setVisibility(View.GONE);
                if (txtCbTimeLine7.getText().length()==0)
                    txtCbTimeLine7.setVisibility(View.GONE);
                if (txtCbTimeLine8.getText().length()==0)
                    txtCbTimeLine8.setVisibility(View.GONE);

            }
        }
    }
}



