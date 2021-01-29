package com.lzh.wuhantm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lzh.wuhantm.MyItemClickListener;
import com.lzh.wuhantm.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;


    private ArrayList<HashMap<String, Object>> listItem;
    private MyItemClickListener myItemClickListener;

    //构造函数，传入数据
    public MyAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }


    public void setOnItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.layout, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT);
        item.setLayoutParams(lp);
        return new Viewholder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Viewholder vh = (Viewholder) holder;
        vh.Title.setText((String)listItem.get(position).get("ItemTile"));
        vh.Text.setText((String)listItem.get(position).get("ItemText"));
        vh.ima.setImageResource((Integer)listItem.get(position).get("ItemImage"));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    //定义Viewholder
    class Viewholder extends RecyclerView.ViewHolder {
        private TextView Title, Text;
        private ImageView ima;

        public Viewholder(View root) {
            super(root);
            Title = (TextView) root.findViewById(R.id.Itemtitle);
            Text = (TextView) root.findViewById(R.id.ItemText);
            ima = (ImageView) root.findViewById(R.id.ItemImage);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemClickListener != null)
                        myItemClickListener.onItemClick(v, getPosition());
                }
            });
        }
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public ArrayList<HashMap<String, Object>> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<HashMap<String, Object>> listItem) {
        this.listItem = listItem;
    }

}
