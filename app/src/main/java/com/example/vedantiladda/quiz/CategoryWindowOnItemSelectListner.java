package com.example.vedantiladda.quiz;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryWindowOnItemSelectListner implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

            // get the context and main activity to access variables
            Context mContext = v.getContext();
            ContestCreationActivity contestCreationActivity = ((ContestCreationActivity) mContext);

            // add some animation when a list item was clicked
            Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
            fadeInAnimation.setDuration(10);
            v.startAnimation(fadeInAnimation);

            // dismiss the pop up
            contestCreationActivity.popupWindowCategory().dismiss();

            // get the text and set it as the button text
            String selectedItemText = ((TextView) v).getText().toString();
            contestCreationActivity.selectCategoryButton.setText(selectedItemText);


        }

    }

