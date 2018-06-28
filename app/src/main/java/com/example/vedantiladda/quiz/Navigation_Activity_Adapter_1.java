package com.example.vedantiladda.quiz;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vedantiladda.quiz.dto.ContestDTO;

import java.util.List;

public class Navigation_Activity_Adapter_1 extends RecyclerView.Adapter<Navigation_Activity_Adapter_1.DynamicContestHolder>{
    private List<ContestDTO> contestDTOS;
    private Navigation_Activity_communicator_1 communicator;

    public Navigation_Activity_Adapter_1(List<ContestDTO> contestDTOS, Navigation_Activity_communicator_1 communicator){
        this.contestDTOS = contestDTOS;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public Navigation_Activity_Adapter_1.DynamicContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_content_holder_1,parent,false);
        return new DynamicContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Navigation_Activity_Adapter_1.DynamicContestHolder dynamicContestHolder, int i) {
        final ContestDTO contestDTO = contestDTOS.get(i);
        dynamicContestHolder.dynContestName.setText(contestDTO.getContestName());
        dynamicContestHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContestDTO dynContest = contestDTOS.get(dynamicContestHolder.getAdapterPosition());
                String name = dynContest.getContestName();
                communicator.onClickTextView(name);
            }
        });
    }


    @Override
    public int getItemCount() {
        return contestDTOS.size();

    }

    public class DynamicContestHolder extends RecyclerView.ViewHolder{
        public TextView dynContestName;
        public ConstraintLayout constraintLayout;

        public DynamicContestHolder(View itemView) {
            super(itemView);
            dynContestName = itemView.findViewById(R.id.dynamicContestNames);
            constraintLayout = itemView.findViewById(R.id.nav1HolderConstraint);
        }
    }

    public interface Navigation_Activity_communicator_1{
        public void onClickTextView(String name);

    }
}
