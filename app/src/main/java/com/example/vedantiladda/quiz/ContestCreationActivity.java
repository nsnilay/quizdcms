package com.example.vedantiladda.quiz;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vedantiladda.quiz.dto.Category;
import com.example.vedantiladda.quiz.dto.Contest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;
import java.util.Set;

public class ContestCreationActivity extends AppCompatActivity {
    private EditText contestName,startTime,duration,bonus,number_of_question;
    private Spinner contestTypeSpinner;
    private Button createButton;
    Button selectCategoryButton;
    private Retrofit retrofit;
    private OkHttpClient client;
    final List<String> categoriesList = new ArrayList<>();
    final Contest contest = new Contest();
    final Map<String ,String> categoryListMap = new HashMap<>();
    String AdminEmailId ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_contest_layout);
        addListnerToStartTimeET();
        getCategortries();
        categorySelectButtonListner();
        addListenerOnButton();
        addListnerOnContestTypeItemSelection();


    }
    public void categorySelectButtonListner()
    {
        String popUpContents[] = new String[categoriesList.size()];
        categoriesList.toArray(popUpContents);


        // initialize pop up window
        final PopupWindow popupWindowCategory = popupWindowCategory();


        // button on click listener

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.select_category_button:
                        // show the list view as dropdown
                        popupWindowCategory.showAsDropDown(v, -5, 0);
                        break;
                }
            }
        };

        // our button
        selectCategoryButton = (Button) findViewById(R.id.select_category_button);
        selectCategoryButton.setOnClickListener(handler);
    }
    public PopupWindow popupWindowCategory() {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewCategory = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewCategory.setAdapter(CategoryAdapter(categoriesList));

        // set the item click listener
        listViewCategory.setOnItemClickListener(new CategoryWindowOnItemSelectListner());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewCategory);

        return popupWindow;
    }
    private ArrayAdapter<String> CategoryAdapter(List<String> categoryList) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String text = item;

                // visual settings for the list item
                TextView listItem = new TextView(ContestCreationActivity.this);

                listItem.setText(text);
                listItem.setTextSize(16);
                listItem.setPadding(8, 8, 8, 8);
                listItem.setTextColor(Color.WHITE);
                return listItem;
            }
        };

        return adapter;
    }


    public void addListnerOnContestTypeItemSelection()
    {
        selectCategoryButton = findViewById(R.id.select_category_button);
        contestTypeSpinner = (Spinner) findViewById(R.id.contest_type_spinner);
       // Log.i("CONTESTCREATION", contestTypeSpinner.getSelectedItem().toString());
        contestTypeSpinner.setOnItemSelectedListener(new CustomOnItemSelectListener());

    }

    private List<String> getCategortries()
        {
            final List<Category> categories = new ArrayList<>();

            client =  new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder().
                    baseUrl(getString(R.string.get_categories_base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            IApiCall iApiCall = retrofit.create(IApiCall.class);
            Call<List<Category>> getCategoriesCall = iApiCall.getCategories();
            getCategoriesCall.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    Toast.makeText(ContestCreationActivity.this,"Success",Toast.LENGTH_LONG).show();
                    categories.addAll(response.body());
                    for(Category category : categories)
                    {
                        categoriesList.add(category.getCategoryName());
                        categoryListMap.put(category.getCategoryName(),category.getCategoryId());
                    }

                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(ContestCreationActivity.this,"Fail",Toast.LENGTH_LONG).show();
                    categoriesList.add("Nothing to load::-1");

                }
            });

            return categoriesList;
        }

    public void  addListnerToStartTimeET()
    {   final EditText startTime = findViewById(R.id.start_time_et);
        startTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ContestCreationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }
    public Contest intiContest() throws ParseException {   final  SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        contestTypeSpinner = (Spinner) findViewById(R.id.contest_type_spinner);
        contestName = findViewById(R.id.contest_name_et);
        startTime = findViewById(R.id.start_time_et);
        duration = findViewById(R.id.duration_et);
        bonus = findViewById(R.id.bonus_et);
        number_of_question = findViewById(R.id.number_of_questions_et);
            contest.setCategoryName(selectCategoryButton.getText().toString());
            contest.setCategoryId(categoryListMap.get(selectCategoryButton.getText().toString()));
            contest.setAdminId(sharedPreferences.getString("userName","Admin"));
            contest.setBonus(Integer.parseInt(bonus.getText().toString()));
            contest.setContestType(contestTypeSpinner.getSelectedItem().toString());
            contest.setContestName(contestName.getText().toString());
            contest.setEmail(sharedPreferences.getString("Email","Admin@gmail.com"));
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd hh:mm:ss:SSS");

            Date date = new Date();
            String str = dateFormat.format(date).split(" ")[0].replaceAll("/","-") + " " +startTime.getText().toString()+":00";

            Date parsedTimeStamp = dateFormat.parse(str+":000");

            Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
            contest.setStartDate(new Timestamp(timestamp.getTime()));
            Timestamp endTime = new Timestamp(Timestamp.valueOf(str).getTime()+Integer.parseInt(duration.getText().toString())*60*1000);
            contest.setEndDate(endTime);
            contest.setNumberOfQuestions(Integer.parseInt(number_of_question.getText().toString()));
            contest.setQuestionVisibilityDuration(21);
            return contest;
}
    public void addListenerOnButton() {

        contestTypeSpinner = (Spinner) findViewById(R.id.contest_type_spinner);
        contestName = findViewById(R.id.contest_name_et);
        startTime = findViewById(R.id.start_time_et);
        duration = findViewById(R.id.duration_et);
        bonus = findViewById(R.id.bonus_et);
        number_of_question = findViewById(R.id.number_of_questions_et);
        createButton = (Button) findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {



                if((contestName.getText().toString().length()!=0)&&(number_of_question.getText().toString().length()!=0)&&(bonus.getText().toString().length()!=0)&&(startTime.getText().toString().length()!=0)&&(!contestTypeSpinner.getSelectedItem().toString().equals("Select")))
                {

                    Intent intent = new Intent(ContestCreationActivity.this,QuestionBankActivity.class);
                    intent.putExtra("ContestType",contestTypeSpinner.getSelectedItem().toString());
                    intent.putExtra("Contest_CategoryId",categoryListMap.get(selectCategoryButton.getText().toString()));
                    try {
                        intent.putExtra("Contest",(Serializable) intiContest());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);

            }
            else
                {
                    Toast.makeText(ContestCreationActivity.this,"Required Fields Missing",Toast.LENGTH_LONG).show();
                }
            }



        });
    }

    public class CustomOnItemSelectListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

            if(contestTypeSpinner.getSelectedItem().toString().equals("static"))
            {
                selectCategoryButton.setVisibility(View.VISIBLE);
            }
            else selectCategoryButton.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }


}