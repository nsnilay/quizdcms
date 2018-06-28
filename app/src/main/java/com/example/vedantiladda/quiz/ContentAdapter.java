package com.example.vedantiladda.quiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.QuestionHolder> {
    private List<QuestionDTO> questionDTOList;
    private ContentAdapter.Communicator communicator;

    public ContentAdapter(List<QuestionDTO> questionDTOList, ContentAdapter.Communicator communicator) {
        this.questionDTOList = questionDTOList;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public ContentAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_holder, viewGroup,false);

        return new ContentAdapter.QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionHolder questionHolder, int i) {
        final QuestionDTO questionDTO = questionDTOList.get(i);
        questionHolder.questionContent.setText(questionDTO.getQuestionText());
        questionHolder.questionDifficulty.setText(questionDTO.getDifficulty());
        final String id = questionDTO.getQuestionId();
        questionHolder.approve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    questionDTO.setChecked(true);
                    questionHolder.approve.setChecked(questionDTO.getChecked());
                    communicator.onApprove(id,true);
                }
                else {
                    questionDTO.setChecked(false);
                    questionHolder.approve.setChecked(questionDTO.getChecked());
                    communicator.onApprove(id,false);
                }
            }
        });
        questionHolder.approve.setChecked(questionDTO.getChecked());

    }


    @Override
    public int getItemCount() {
        return questionDTOList.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder{
        public TextView questionContent;
        public CheckBox approve;
        public TextView questionDifficulty;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            questionContent = itemView.findViewById(R.id.questionContent);
            questionDifficulty = itemView.findViewById(R.id.questionType);
            approve = itemView.findViewById(R.id.approve);


        }
    }

    public interface Communicator {
        void onApprove(String id, Boolean status);


    }
}
