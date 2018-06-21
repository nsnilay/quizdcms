package com.example.vedantiladda.quiz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.vedantiladda.quiz.R;
import com.example.vedantiladda.quiz.dto.Category;

import java.util.List;

public class RVContestsAdapter extends RecyclerView.Adapter<RVContestsAdapter.PostsViewHolder>{

    private List<Category> categoryList;
    private Category category;
    private CategoryAdapterCommunicator icategoryAdapterCommunicator;


    public RVContestsAdapter(List<Category> categories, CategoryAdapterCommunicator categoryAdapterCommunicator) {
        this.categoryList = categories;
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
        category = categoryList.get(position);
        viewHolder.categoryName.setText(String.valueOf(category.getCategoryName()));
        viewHolder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = categoryList.get(position).getCategoryName();
                icategoryAdapterCommunicator.itemClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;

        public PostsViewHolder(View itemView){
            super(itemView);
            categoryName = itemView.findViewById(R.id.static_category_text);

        }
    }

    public interface  CategoryAdapterCommunicator{
        public void itemClick(String category);
    }
}
