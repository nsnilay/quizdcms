package com.example.vedantiladda.quiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.util.List;

public class PublishAdapter extends RecyclerView.Adapter<PublishAdapter.QuestionHolder> {
    private List<QuestionDTO> questionDTOList;

    public PublishAdapter(List<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }

    @NonNull
    @Override
    public PublishAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.publish_holder, viewGroup,false);

        return new PublishAdapter.QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublishAdapter.QuestionHolder questionHolder, int i) {
        final QuestionDTO questionDTO = questionDTOList.get(i);

        questionHolder.questionType.setText(questionDTO.getQuestionType());
        questionHolder.questionContent.setText(questionDTO.getQuestionContent());
        questionHolder.questionDifficulty.setText(questionDTO.getDifficulty());

    }

    @Override
    public int getItemCount() {
        return questionDTOList.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder{
        public TextView questionContent;
        public TextView questionType;
        public TextView questionDifficulty;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            questionContent = itemView.findViewById(R.id.questionContent);
            questionType = itemView.findViewById(R.id.questionType);
            questionDifficulty = itemView.findViewById(R.id.questionDifficulty);

        }
    }
}
