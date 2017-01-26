package com.shul.michalizralevitch.shul2;


/**
 * Created by michalizralevitch on 11/08/16.
 */
public class MyListenerCons {

    // A class to listen if i already get the whole data, after i got i can show the data in the
    //fragments..

    //an interface that i declare
    IgetOneShulData igetOneShulData;

    public MyListenerCons(IgetOneShulData igetOneShulData){
        this.igetOneShulData = igetOneShulData;
    }
    public void listenerMark(){
       igetOneShulData.onGetOneShul();

    }


}
