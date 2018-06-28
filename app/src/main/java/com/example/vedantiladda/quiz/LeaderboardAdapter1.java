package com.example.vedantiladda.quiz;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vedantiladda.quiz.dto.ContestwiseDTO;
//import com.example.vedantiladda.quiz.dto.OverallDTO;
//import com.example.vedantiladda.quiz.dto.UserDTO;

import java.util.List;

public class LeaderboardAdapter1 extends RecyclerView.Adapter<LeaderboardAdapter1.LeaderboardHolder> {

    //private List<UserDTO> users;
    private List<ContestwiseDTO> contestwiseDTOS;
    private LeaderboardAdapter1.LeaderboardCommunicator1 communicator;

    public LeaderboardAdapter1(List<ContestwiseDTO> contestwiseDTOS, LeaderboardAdapter1.LeaderboardCommunicator1 communicator) {
        this.contestwiseDTOS = contestwiseDTOS;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public LeaderboardAdapter1.LeaderboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_content_holder_1, parent,false);
        return new LeaderboardAdapter1.LeaderboardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LeaderboardAdapter1.LeaderboardHolder holder, int position) {
        final ContestwiseDTO contestwiseDTO = contestwiseDTOS.get(position);
        holder.username.setText(contestwiseDTO.getUserId());
        holder.points.setText(String.valueOf(contestwiseDTO.getFinalPoints()));
        holder.rank.setText(String.valueOf(contestwiseDTO.getRank()));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContestwiseDTO contestwiseDTO1 = contestwiseDTOS.get(holder.getAdapterPosition());
                String id = contestwiseDTO1.getUserId();
                communicator.onClickTextView(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contestwiseDTOS.size();
    }

    public class LeaderboardHolder extends RecyclerView.ViewHolder{
        public ConstraintLayout constraintLayout;
        public TextView username;
        public TextView points;
        public TextView rank;

        public LeaderboardHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.lConstarintLayout1);
            username = itemView.findViewById(R.id.UserName1);
            points = itemView.findViewById(R.id.Points1);
            rank = itemView.findViewById(R.id.Rank1);
        }
    }

    public interface LeaderboardCommunicator1{
        public void onClickTextView(String id);
    }
}
