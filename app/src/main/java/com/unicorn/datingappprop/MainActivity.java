package com.unicorn.datingappprop;


import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvProvile;
    LinearLayout popupLayout, ivMatch;
    RelativeLayout linearLayout;
    ImageButton btnLike, btnDislike;
    ImageView ivMail, ivHearts;
    SwipeListener swipeListener;
    List<Match> listMatches = new ArrayList<>();
    List<Match> matchedMatches = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private Context mContext;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        populateList();

        swipeListener = new SwipeListener(linearLayout);

        ivMatch.setBackgroundResource(listMatches.get(0).getImage());
        tvName.setText(listMatches.get(0).getName());
        tvProvile.setText("Age " + listMatches.get(0).getAge() + ". " + listMatches.get(0).getProfile());

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvName.getText().toString() == "Baraka"){
                    startActivity(new Intent(MainActivity.this, BarakaActivity.class));
                } else {
                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    String mymatchname = tvName.getText().toString();
                    int mymatchpic = 0;
                    for (int i = 0; i<listMatches.size(); i++){
                        if (mymatchname == listMatches.get(i).getName()){
                            mymatchpic = listMatches.get(i).getImage();
                        }
                    }
                    intent.putExtra("name", mymatchname);
                    intent.putExtra("avatar", mymatchpic);
                    nextMatch(position);
                    startActivity(intent);
                }
            }
        });

        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMatch(position);
            }
        });

        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
            }
        });

        ivHearts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BarakaActivity.class));
            }
        });
    }

    private void populateList(){
        listMatches.clear();
        listMatches.add(new Match("Baraka", 25, "Tech Entrepreneur. Co-founder Statech Ltd.", R.drawable.match1));
        listMatches.add(new Match("Marcus", 28, "Attorney at Honeywell, Stoic & Howe.", R.drawable.match2));
        listMatches.add(new Match("Jacob", 24, "Real Estate Agent.", R.drawable.match3));
        listMatches.add(new Match("Steve", 26, "Insurance Underwriter.", R.drawable.match4));
        listMatches.add(new Match("Daniel", 27, "Bank Manager.", R.drawable.match5));
        listMatches.add(new Match("Chad", 29, "Tech Entrepreneur. Co-founder of Statech Ltd.", R.drawable.match6));
        listMatches.add(new Match("David", 23, "Electrical Engineering Student at United States International University.", R.drawable.match7));
        listMatches.add(new Match("Henry", 24, "Part time Sales Clerk, Part Time Ninja.", R.drawable.match8));
        listMatches.add(new Match("Noah", 27, "Owner, Skylight Technologies Ltd.", R.drawable.match9));
        listMatches.add(new Match("Nathan", 23, "Software Engineer, Moontech Ltd.", R.drawable.match10));
        listMatches.add(new Match("William", 27, "Photographer, Owner, Supreme Studio", R.drawable.match11));
        listMatches.add(new Match("Atticus", 25, "Social Media Manager, Influencer", R.drawable.match12));
        listMatches.add(new Match("Ángel", 24, "Model at Ford Models Intl.", R.drawable.match13));
        listMatches.add(new Match("Liam", 29, "Surgeon at Sacred Heart Hospital", R.drawable.match14));
        listMatches.add(new Match("James", 27, "Retail Sales Associate", R.drawable.match15));
        listMatches.add(new Match("Benjamin", 26, "Pharmacist at Green Tea Pharmacy", R.drawable.match16));
        listMatches.add(new Match("Lucas", 23, "Chauffeur at Elite Trips Ltd.", R.drawable.match17));
        listMatches.add(new Match("Elijah", 25, "Teller at Absa Bank", R.drawable.match18));
        listMatches.add(new Match("Oliver", 27, "Bartender at Club P3", R.drawable.match19));
        listMatches.add(new Match("Archer", 24, "International Business Administration Student at United States International University", R.drawable.match20));
        listMatches.add(new Match("Massimo", 28, "Nurse at M.P. Shah Hospital", R.drawable.match21));
        listMatches.add(new Match("Adam", 26, "Accountant", R.drawable.match22));
    }

    private void initViews(){
        mContext = getApplicationContext();
        linearLayout = findViewById(R.id.linear_layout);
        popupLayout = findViewById(R.id.popup_window);
        tvName = findViewById(R.id.match_name);
        tvProvile = findViewById(R.id.tv_profile);
        ivMatch = findViewById(R.id.match_pic);
        btnLike = findViewById(R.id.btn_like);
        btnDislike = findViewById(R.id.btn_dislike);
        ivMail = findViewById(R.id.iv_mail);
        ivHearts = findViewById(R.id.iv_hearts);
    }

    private class SwipeListener implements View.OnTouchListener {

        GestureDetector gestureDetector;

        SwipeListener(View view) {

            final int threshold = 100;
            final int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e1.getY();

                    try {
                        if (Math.abs(xDiff) > Math.abs(yDiff)) {
                            if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                                if (xDiff > 0) {
                                    prevMatch(position);
                                } else {
                                    nextMatch(position);
                                }
                                return true;
                            }
                        } else {
                            if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold) {
                                if (yDiff > 0) {
                                    System.out.print("Swiped Down");
                                } else {
                                    System.out.print("Swiped Up");
                                }
                                return true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return false;

                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }

    public void nextMatch(int currentPosition) {
        if (currentPosition < listMatches.size()) {
            currentPosition++;
        } else {
            currentPosition = 0;
        }
        position = currentPosition;

        ivMatch.setBackgroundResource(listMatches.get(currentPosition).getImage());
        tvName.setText(listMatches.get(currentPosition).getName());
        tvProvile.setText("Age " + listMatches.get(currentPosition).getAge() + ". " + listMatches.get(currentPosition).getProfile());
        matchPopUp();
    }

    public void prevMatch(int currentPosition) {
        if (currentPosition == 0){
            currentPosition = listMatches.size() - 1;
        } else {
            currentPosition--;
        }
        position = currentPosition;
        ivMatch.setBackgroundResource(listMatches.get(currentPosition).getImage());
        tvName.setText(listMatches.get(currentPosition).getName());
        tvProvile.setText("Age " + listMatches.get(currentPosition).getAge() + ". " + listMatches.get(currentPosition).getProfile());
    }

    public void matchPopUp() {
        int index = 0;
        Random random = new Random();
        ArrayList<Integer> numList = new ArrayList<Integer>();

        for (int i = 1; i < 12; i++) {
            numList.add(i);
        }
        for (int x = 0; x < 11; x++) {
            index = random.nextInt(numList.size());
        }

        if (index % 7 == 0 && !matchedMatches.contains(listMatches.get(index))) {
            matchedMatches.add(listMatches.get(index));
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_layout, null);

            CircleImageView image = popupView.findViewById(R.id.gotmatch_pic);
            image.setImageResource(listMatches.get(index).getImage());
            TextView textView = popupView.findViewById(R.id.tv_gotmatch_name);
            TextView sendMsg = popupView.findViewById(R.id.tv_sendmsg);
            textView.setText(listMatches.get(index).getName());
            ImageButton imageButton = popupView.findViewById(R.id.close_popup);

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x - 100;
            double height = size.y * 0.45;

            mPopupWindow = new PopupWindow(popupView);
            mPopupWindow.update(30, 30, width, (int) height);

            final int index2 = index;
            sendMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index2 == 0) {
                        startActivity(new Intent(MainActivity.this, BarakaActivity.class));
                    } else {
                        Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                        intent.putExtra("name", listMatches.get(index2).getName());
                        intent.putExtra("avatar", listMatches.get(index2).getImage());
                        mPopupWindow.dismiss();
                        startActivity(intent);
                    }
                }
            });

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });

            mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
        }
    }
}