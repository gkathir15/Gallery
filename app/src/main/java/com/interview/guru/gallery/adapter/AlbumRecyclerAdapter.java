package com.interview.guru.gallery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interview.guru.gallery.pojo.Album;
import com.interview.guru.gallery.interfaces.OnItemClickListener;
import com.interview.guru.gallery.R;

import java.util.List;

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.ViewHolder> {

    private  int mLayout;
    private List<Album> mReceivedList;
    OnItemClickListener mSetClickListener;

    public AlbumRecyclerAdapter(int pLayout, List<Album> pReceivedList) {
        this.mLayout = pLayout;
        this.mReceivedList = pReceivedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context lContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(lContext);
        View lAlbumListView = layoutInflater.inflate(mLayout, parent, false);

        ViewHolder viewHolder = new ViewHolder(lAlbumListView);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull AlbumRecyclerAdapter.ViewHolder holder, int position) {

        Album lAlbum = mReceivedList.get(position);
        TextView Title = holder.title;
        TextView userId = holder.userId;
        TextView id = holder.id;

        Title.setText(("Title: "+lAlbum.getTitle()));
        userId.setText(("UserID: "+String.valueOf(lAlbum.getUserId())));
        id.setText(("ID: "+String.valueOf(lAlbum.getId())));

    }

    public void setClickListener(OnItemClickListener onItemClickListener)
    {
        this.mSetClickListener = onItemClickListener;

    }



    @Override
    public int getItemCount() {
        return mReceivedList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title,userId,id;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.album_title);
            userId = itemView.findViewById(R.id.user_id);
            id = itemView.findViewById(R.id.id);
            itemView.setOnClickListener(this);




        }





        @Override
        public void onClick(View v) {

            mSetClickListener.onClick(v,getAdapterPosition());

        }
    }
}
