package com.example.vedantiladda.quiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        questionHolder.questionContent.setText(questionDTO.getQuestionContent());
        questionHolder.questionDifficulty.setText(questionDTO.getDifficulty());
        final Boolean switchState = questionHolder.status.isChecked();
        questionHolder.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    communicator.onSetSwitch(questionDTO.getQuestionId());
                    questionHolder.status.setText("Approve");
                }
                else{
                    communicator.onReleaseSwitch(questionDTO.getQuestionId());
                    questionHolder.status.setText("Reject");
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return questionDTOList.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder{
        public TextView questionContent;
        public Switch status;
        public TextView questionDifficulty;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            questionContent = itemView.findViewById(R.id.questionContent);
            questionDifficulty = itemView.findViewById(R.id.questionDifficulty);
            status = itemView.findViewById(R.id.switch1);


        }
    }

    public interface Communicator {
        void onSetSwitch(String id);
        void onReleaseSwitch(String id);

    }
}
