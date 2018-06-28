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

public class Navigation_Activity_Adapter extends RecyclerView.Adapter<Navigation_Activity_Adapter.StaticContestHolder>{
    private List<ContestDTO> contestDTOS;
    private Navigation_Activity_communicator communicator;

    public Navigation_Activity_Adapter(List<ContestDTO> contestDTOS, Navigation_Activity_communicator communicator){
        this.contestDTOS = contestDTOS;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public Navigation_Activity_Adapter.StaticContestHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_content_holder,parent,false);
        return new StaticContestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Navigation_Activity_Adapter.StaticContestHolder holder, int position) {
        final ContestDTO contestDTO = contestDTOS.get(position);
        holder.staticContestName.setText(contestDTO.getContestName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContestDTO staticContest = contestDTOS.get(holder.getAdapterPosition());
                String name = staticContest.getContestName();
                communicator.onClickTextView(name);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contestDTOS.size();

    }

    public class StaticContestHolder extends RecyclerView.ViewHolder{
        public TextView staticContestName;
        public ConstraintLayout constraintLayout;

        public StaticContestHolder(View itemView) {
            super(itemView);
            staticContestName = itemView.findViewById(R.id.staticContestNames);
            constraintLayout = itemView.findViewById(R.id.navHolderConstraint);
        }
    }

    public interface Navigation_Activity_communicator{
        public void onClickTextView(String name);

    }
}
