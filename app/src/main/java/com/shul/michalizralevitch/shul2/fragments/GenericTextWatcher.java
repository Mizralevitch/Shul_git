package com.shul.michalizralevitch.shul2.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.shul.michalizralevitch.shul2.R;

/**
 * Created by michalizralevitch on 08/08/16.
 */
public class GenericTextWatcher  implements TextWatcher {

    // This class is for editing options of the data for the managers of the shul (for each shul) i
    // have to pay attention if the text in the edit text is changing and change the singelton respectively.

    private View view;

    GenericTextWatcher(View view) {
        this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        switch(view.getId()) {
            case R.id.txtChoir:
                Singleton.getSingleInstance().getBlogPost().setChoir(text);
                break;
            case R.id.txtParasha:
                Singleton.getSingleInstance().getBlogPost().setParasha(text);
            case R.id.txtCantor:
                Singleton.getSingleInstance().getBlogPost().setCantor(text);
                break;
            case R.id.txtShalom:
                Singleton.getSingleInstance().getBlogPost().setShalom(text);
                break;
            case R.id.txtMesseges:
                Singleton.getSingleInstance().getBlogPost().setMesseges(text);
                break;
            case R.id.txtMessegesTitle:
                Singleton.getSingleInstance().getBlogPost().setMessegesTitle(text);
                break;
            case R.id.txtLightening:
                Singleton.getSingleInstance().getBlogPost().setHadlakatNerot(text);
                break;
            case R.id.txtHavdala:
                Singleton.getSingleInstance().getBlogPost().setHavdala(text);
                break;
            case R.id.txtTimeLine3:
                Singleton.getSingleInstance().getBlogPost().setTimesLine3(text);
                break;
            case R.id.txtTimeLine4:
                Singleton.getSingleInstance().getBlogPost().setTimesLine4(text);
                break;
            case R.id.txtTimeLine5:
                Singleton.getSingleInstance().getBlogPost().setTimesLine5(text);
                break;
            case R.id.txtTimeLine6:
                Singleton.getSingleInstance().getBlogPost().setTimesLine6(text);
                break;
            case R.id.txtTimeLine7:
                Singleton.getSingleInstance().getBlogPost().setTimesLine7(text);
                break;
            case R.id.txtTimeLine8:
                Singleton.getSingleInstance().getBlogPost().setTimesLine8(text);
                break;



        }
    }

}
