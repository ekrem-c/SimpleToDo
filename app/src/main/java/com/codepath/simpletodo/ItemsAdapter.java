package com.codepath.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

  public interface OnLongClickListener {
    void onItemLongClicked(int position);
  }

  List<String> items;
  OnLongClickListener longClickListener;

  public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
    this.items = items;
    this.longClickListener = longClickListener;
  }

  @NonNull
  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    //Use layout inflater to inflate a view, wrap it inside a view holder and return it
    View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
    
    return new ViewHolder(todoView);
  }

  //Responsible for binding data to a particular view holder
  @Override
  public void onBindViewHolder(@NonNull @NotNull ItemsAdapter.ViewHolder holder, int position) {
    //Grab the item at the position
    
    String item = items.get(position);
    
    //Bind the item into the specified view holder
    holder.bind(item);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  //Container to provide easy access to views that represent each row of the list

  class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvItem;
    public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
      super(itemView);
      tvItem = itemView.findViewById(android.R.id.text1);
    }

    public void bind(String item) {
      tvItem.setText(item);
      tvItem.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          longClickListener.onItemLongClicked(getAdapterPosition());
          return true;
        }
      });
    }
  }

}
