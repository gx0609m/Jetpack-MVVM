package com.example.gx.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gx.R;

/**
 * Paging分页加载的适配器，继承自 PagedListAdapter
 * <p>
 * created by gaoxiang on 2020/9/21
 */
public class PagedAdapter extends PagedListAdapter<Student, PagedAdapter.MyPagedViewHolder> {


    public PagedAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getStudentNumber() == newItem.getStudentNumber();
            }
        });
    }

    @NonNull
    @Override
    public MyPagedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cell, parent, false);
        return new MyPagedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPagedViewHolder holder, int position) {
        Student student = getItem(position);
        // Paging分页加载，容器的数量可能会比数据的量多，因此需要加个判空
        if (null == student) {
            holder.textView.setText("loading...");
        } else {
            holder.textView.setText(String.valueOf(student.getStudentNumber()));
        }
    }

    static class MyPagedViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyPagedViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView15);
        }
    }
}
