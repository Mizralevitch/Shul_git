package com.shul.michalizralevitch.shul2;

/**
 * Created by michalizralevitch on 29/07/16.
 */
public class Shul {

    //Class to create a "Shul" object, that can be "send" to Firebase

    private String shulName;
    private String email1;
    private String email2;
    private String hadlakatNerot;
    private String havdala;
    private String timesLine3;
    private String timesLine4;
    private String timesLine5;
    private String timesLine6;
    private String timesLine7;
    private String timesLine8;
    private String messegesTitle;
    private String messeges;
    private String cantor;
    private String choir;
    private String adress;
    private String counter;
    private String advertisement;
    private String shalom;
    private String parasha;

    public Shul() {
    }

    public Shul(String shulName, String email1, String email2,
                String messeges, String messegesTitle, String hadlakatNerot, String havdala,
                String timesLine3, String timesLine4, String timesLine5,
                String timesLine6, String timesLine7, String timesLine8, String cantor, String choir,String shalom,
                String adress,String counter, String advertisement, String parasha) {
        this.timesLine8 = timesLine8;
        this.cantor = cantor;
        this.choir = choir;
        this.shalom = shalom;
        this.email1 = email1;
        this.email2 = email2;
        this.hadlakatNerot = hadlakatNerot;
        this.havdala = havdala;
        this.messeges = messeges;
        this.messegesTitle = messegesTitle;
        this.shulName = shulName;
        this.timesLine3 = timesLine3;
        this.timesLine4 = timesLine4;
        this.timesLine5 = timesLine5;
        this.timesLine6 = timesLine6;
        this.timesLine7 = timesLine7;
        this.adress = adress;
        this.counter = counter;
        this.advertisement = advertisement;
        this.parasha = parasha;
    }


    public String getShulName() {
        return shulName;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail1() {
        return email1;
    }

    public String getHadlakatNerot() {
        return hadlakatNerot;
    }

    public String getCantor() {
        return cantor;
    }

    public String getChoir() {
        return choir;
    }

    public String getHavdala() {
        return havdala;
    }

    public String getMesseges() {
        return messeges;
    }

    public String getMessegesTitle() {
        return messegesTitle;
    }

    public String getTimesLine3() {
        return timesLine3;
    }

    public String getTimesLine4() {
        return timesLine4;
    }

    public String getTimesLine5() {
        return timesLine5;
    }

    public String getTimesLine6() {
        return timesLine6;
    }

    public String getTimesLine7() {
        return timesLine7;
    }

    public String getTimesLine8() {
        return timesLine8;
    }

    public String getAdress() {
        return adress;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public String getCounter() {
        return counter;
    }

    public String getShalom() {
        return shalom;
    }

    public String getParasha() {
        return parasha;
    }


}
