package com.shul.michalizralevitch.shul2.fragments;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.shul.michalizralevitch.shul2.BlogPost;
import com.shul.michalizralevitch.shul2.IgetOneShulData;
import com.shul.michalizralevitch.shul2.MyListenerCons;
import com.shul.michalizralevitch.shul2.R;

import java.util.regex.Pattern;

/**
 * Created by michalizralevitch on 08/07/16.
 */
public class HandleView extends AppCompatActivity implements IgetOneShulData {

    /*
    Class that handle the fragments.
    I thought of giving opportunity for self ads and not only from google admobe, so i built a bar.
     */

    Button btnSaveChanges;

    private Fragment[] fragments;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Firebase ref;
    String shulName = "";
    BlogPost post;//getting here the data from Firebase
    MyListenerCons listenerCons;

    //The followings are for my add or for googleAdView
    int[] images = {R.drawable.rectangle1, R.drawable.rectangle2,
            R.drawable.rectangle3, R.drawable.rectangle4};
    TextView myAdd;
    int indexForChangeAddBackground = 0;
    private AdView googleAdView;
    private ImageView animator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handle_view);

        Intent intent = getIntent();
        if (intent != null) {
            //in order to give the requested data from Firebase
            shulName = intent.getStringExtra("chosenShul");

        }
        addWidgets();

        myAdvertismentBar();
        setMyAnimation();

    }

    private void addWidgets() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        animator = (ImageView) findViewById(R.id.animator);

        myAdd = (TextView) findViewById(R.id.myAdd);
        googleAdView = (AdView) findViewById(R.id.googleAdView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        googleAdView.loadAd(adRequest);

        getSpecificShul(shulName);

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);
    }


    @Override
    public void onGetOneShul() {

        // Saving the data into singelton, cause they are not going to change + i need it in all
        // fragments.

        Singleton.getSingleInstance().setBlogPost(post);
        getTheManagersOfTheShul();
        if (Singleton.getSingleInstance().getBlogPost().getAdvertisement() != "") {
            //only one add is playing, if i dont have my add, i use googleAd, and of course i lunch
            // my add from the other application..

            googleAdView.setVisibility(View.GONE);
            myAdd.setText(Singleton.getSingleInstance().getBlogPost().getAdvertisement());
            myAdd.setVisibility(View.VISIBLE);
        }

        //lunch the data in the fragments:

        ((MessagesFrag) fragments[0]).onSwipe();
        ((MainFrag) fragments[1]).onSwipe();
        ((TimesFrag) fragments[2]).onSwipe();

    }

    public void getSpecificShul(String shulName) {

        ref = new Firebase("https://someadress.firebaseio.com/" + shulName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                post = snapshot.getValue(BlogPost.class);
                listenerCons = new MyListenerCons(HandleView.this);
                listenerCons.listenerMark();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


    //only for a "Shul manager":
    public void changeData() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.changed), Toast.LENGTH_LONG).show();
        ref.setValue(Singleton.getSingleInstance().getBlogPost());
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            fragments = new Fragment[3];

            //allows normal swipe for Hebrew
            Configuration config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                fragments[0] = new TimesFrag();
                fragments[1] = new MainFrag();
                fragments[2] = new MessagesFrag();
            }
            fragments[0] = new MessagesFrag();
            fragments[1] = new MainFrag();
            fragments[2] = (new TimesFrag());
        }


        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getResources().getString(R.string.messages);
            } else if (position == 1) {
                return getResources().getString(R.string.main);
            } else {
                return getResources().getString(R.string.times);
            }

        }


    }

    private void getTheManagersOfTheShul() {

        //I have to know if the user is a manager of the shul, if he is a manager he can efit the
        // data.
        // I get this information by checking his email address. Earlier in the "sign  up" i asked
        // 2 email addresses of 2 managers- in order to use it here.


        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getBaseContext()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                if (possibleEmail.equals(Singleton.getSingleInstance().getBlogPost().getEmail1())
                        || possibleEmail.equals(Singleton.getSingleInstance().getBlogPost().getEmail2())) {
                    Singleton.getSingleInstance().setManager(true);
                }
            }
        }
        lunchBtn();

    }


    //only for a "shul manager":
    public void lunchBtn() {
        btnSaveChanges = (Button) findViewById(R.id.btnSaveChanges);
        if (Singleton.getSingleInstance().getManager()) {
            btnSaveChanges.setVisibility(View.VISIBLE);
        }
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeData();
            }
        });
    }

    private void myAdvertismentBar() {
        //I wanted ma add to be also interesting, I did it by changing its background

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    indexForChangeAddBackground++;
                    updateColor();
                }
            }
        }).start();
    }

    private void updateColor() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myAdd.setBackgroundResource(images[indexForChangeAddBackground % images.length]);

            }
        });
    }

    private void makeAnimation() {
        //an animation of icon that would play at the top of the screen, i thought it would be nice.

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.my_animation);
                animator.startAnimation(animation1);
            }
        });
    }


    private void setMyAnimation() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    makeAnimation();
                }
            }
        }).start();
    }


    @Override
    protected void onPause() {
        if (googleAdView != null) {
            googleAdView.pause();
        }
        super.onPause();
        Singleton.getSingleInstance().setManager(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (googleAdView != null) {
            googleAdView.resume();
        }

    }

    @Override
    public void onDestroy() {
        if (googleAdView != null) {
            googleAdView.destroy();
        }
        finish();
        super.onDestroy();
    }


}
