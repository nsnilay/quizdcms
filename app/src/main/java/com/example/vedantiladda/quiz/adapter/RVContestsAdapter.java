package com.example.vedantiladda.quiz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vedantiladda.quiz.R;
import com.example.vedantiladda.quiz.dto.CategoryDTO;

import java.util.List;

public class RVContestsAdapter extends RecyclerView.Adapter<RVContestsAdapter.PostsViewHolder>{

    private List<CategoryDTO> categoryDTOList;
    private CategoryDTO categoryDTO;
    private CategoryAdapterCommunicator icategoryAdapterCommunicator;


    public RVContestsAdapter(List<CategoryDTO> categories, CategoryAdapterCommunicator categoryAdapterCommunicator) {
        this.categoryDTOList = categories;
        this.icategoryAdapterCommunicator = categoryAdapterCommunicator;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_static_contests_adapter, viewGroup, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder viewHolder, final int position) {
        categoryDTO = categoryDTOList.get(position);
        viewHolder.categoryName.setText(String.valueOf(categoryDTO.getCategoryName()));
        viewHolder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDTO categoryDTO = categoryDTOList.get(position);
                icategoryAdapterCommunicator.itemClick(categoryDTO);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDTOList.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;

        public PostsViewHolder(View itemView){
            super(itemView);
            categoryName = itemView.findViewById(R.id.static_category_text);

        }
    }

    public interface  CategoryAdapterCommunicator{
        public void itemClick(CategoryDTO categoryDTO);
    }
}
