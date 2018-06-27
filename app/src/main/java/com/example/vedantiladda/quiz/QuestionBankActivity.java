package com.example.vedantiladda.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionBankActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, EasyFragment.EasyQuestions,
        HardFragment.HardQuestions,MediumFragment.MediumQuestions{

    ViewPager viewPager;
    PagerAdapter adapter;
    public List<QuestionDTO> selectedQuestionDTOS = new ArrayList<>();

    public void setSelectedQuestionDTOS(List<QuestionDTO> selectedQuestionDTOS) {
        this.selectedQuestionDTOS.addAll(selectedQuestionDTOS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_question_bank);
        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tabLayout
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        TextView easy= new TextView(this);
        easy.setText("EASY");
        easy.setTextColor(Color.WHITE);
        easy.setGravity(Gravity.CENTER);
        TextView medium= new TextView(this);
        medium.setText("MEDIUM");
        medium.setGravity(Gravity.CENTER);
        TextView hard= new TextView(this);
        hard.setText("HARD");
        hard.setGravity(Gravity.CENTER);
        tabLayout.addTab(tabLayout.newTab().setCustomView(easy));
        tabLayout.addTab(tabLayout.newTab().setCustomView(medium));
        tabLayout.addTab(tabLayout.newTab().setCustomView(hard));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int n = tabLayout.getTabCount();
                TabLayout.Tab tab;
                TextView text;
                for(int i=0;i<n;i++){
                    tab = tabLayout.getTabAt(i);
                    text = (TextView)tab.getCustomView();
                    if(i==position){
                        text.setTextColor(Color.WHITE);
                    }
                    else{
                        text.setTextColor(Color.BLACK);
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

        Button submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Selected", selectedQuestionDTOS.toString());
                Intent publish = new Intent(QuestionBankActivity.this, PublishActivity.class);
                publish.putExtra("questions", (Serializable) selectedQuestionDTOS);
                startActivity(publish);
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        TextView text = (TextView)tab.getCustomView();
        text.setTextColor(Color.WHITE);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        TextView text = (TextView)tab.getCustomView();
        text.setTextColor(Color.BLACK);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public void onEasyDataPass(List<QuestionDTO> selectedQuestionDTOS) {
        setSelectedQuestionDTOS(selectedQuestionDTOS);
    }

    @Override
    public void onHardDataPass(List<QuestionDTO> selectedQuestionDTOS) {
        setSelectedQuestionDTOS(selectedQuestionDTOS);
    }

    @Override
    public void onMediumDataPass(List<QuestionDTO> selectedQuestionDTOS) {
        setSelectedQuestionDTOS(selectedQuestionDTOS);
    }
}
