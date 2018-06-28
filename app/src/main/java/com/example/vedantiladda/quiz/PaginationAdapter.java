package com.example.vedantiladda.quiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.QuestionHolder>{

    private List<QuestionDTO> questionDTOList;
    private Communicator communicator;




    public PaginationAdapter(List<QuestionDTO> questionDTOList, Communicator communicator ) {
        this.questionDTOList = questionDTOList;
        this.communicator = communicator;

    }


    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_holder, viewGroup,false);

        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionHolder questionHolder, final int i) {
        final QuestionDTO questionDTO = questionDTOList.get(i);

        questionHolder.questionType.setText(questionDTO.getQuestionType());
        questionHolder.questionContent.setText(questionDTO.getQuestionContent());
        final String id = questionDTO.getQuestionId();
        questionHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    questionDTO.setChecked(true);
                    questionHolder.select.setChecked(questionDTO.getChecked());
                    communicator.onClickCheckBox(id);
                }
                else {
                    questionDTO.setChecked(false);
                    questionHolder.select.setChecked(questionDTO.getChecked());
                    communicator.onClickCheckBox(id);
                }
            }
        });
        questionHolder.select.setChecked(questionDTO.getChecked());


    }

    @Override
    public int getItemCount() {
        return questionDTOList.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder{
        public TextView questionContent;
        public TextView questionType;
        public CheckBox select;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            questionContent = itemView.findViewById(R.id.questionContent);
            questionType = itemView.findViewById(R.id.questionDifficulty);
            select = itemView.findViewById(R.id.checkBox);

        }
    }
    public interface Communicator {
        public void onClickCheckBox(String id);
    }

}
