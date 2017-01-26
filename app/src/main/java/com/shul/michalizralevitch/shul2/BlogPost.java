package com.shul.michalizralevitch.shul2;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by michalizralevitch on 29/07/16.
 */

@JsonIgnoreProperties(ignoreUnknown=true)

//Class to get data from Firebase about one shul each time.
public class BlogPost {

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





    public BlogPost() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public String getShulName() {
        return shulName;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
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




    public void setChoir(String choir) {
        this.choir = choir;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public void setMesseges(String messeges) {
        this.messeges = messeges;
    }

    public void setMessegesTitle(String messegesTitle) {
        this.messegesTitle = messegesTitle;
    }

    public void setTimesLine3(String timesLine3) {
        this.timesLine3 = timesLine3;
    }

    public void setTimesLine4(String timesLine4) {
        this.timesLine4 = timesLine4;
    }

    public void setTimesLine5(String timesLine5) {
        this.timesLine5 = timesLine5;
    }

    public void setTimesLine6(String timesLine6) {
        this.timesLine6 = timesLine6;
    }

    public void setTimesLine7(String timesLine7) {
        this.timesLine7 = timesLine7;
    }

    public void setTimesLine8(String timesLine8) {
        this.timesLine8 = timesLine8;
    }

    public void setHadlakatNerot(String hadlakatNerot) {
        this.hadlakatNerot = hadlakatNerot;
    }

    public void setHavdala(String havdala) {
        this.havdala = havdala;
    }
    public void setShalom(String shalom) {
        this.shalom = shalom;
    }

    public void setParasha(String parasha) {
        this.parasha = parasha;
    }

}
