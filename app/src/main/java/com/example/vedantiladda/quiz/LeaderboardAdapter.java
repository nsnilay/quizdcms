package com.example.vedantiladda.quiz;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vedantiladda.quiz.dto.UserDTO;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardHolder> {

    private List<UserDTO> users;
    private LeaderboardAdapter.LeaderboardCommunicator communicator;

    public LeaderboardAdapter(List<UserDTO> users, LeaderboardAdapter.LeaderboardCommunicator communicator) {
        this.users = users;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.LeaderboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_content_holder, parent,false);
        return new LeaderboardAdapter.LeaderboardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LeaderboardAdapter.LeaderboardHolder holder, int position) {
        final UserDTO userDTO = users.get(position);
        holder.username.setText(userDTO.getUserName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDTO userDTO = users.get(holder.getAdapterPosition());
                String id = userDTO.getUserId();
                communicator.onClickTextView(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderboardHolder extends RecyclerView.ViewHolder{
        public ConstraintLayout constraintLayout;
        public TextView username;

        public LeaderboardHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.lConstarintLayout);
            username = itemView.findViewById(R.id.UserName);
        }
    }

    public interface LeaderboardCommunicator{
        public void onClickTextView(String id);
    }
}

