package com.interview.guru.gallery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.interview.guru.gallery.pojo.AlbumData;
import com.interview.guru.gallery.interfaces.OnItemClickListener;
import com.interview.guru.gallery.R;
import com.interview.guru.gallery.pojo.AlbumData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AlbumDataRecyclerAdapter extends RecyclerView.Adapter<AlbumDataRecyclerAdapter.ViewHolder> implements Filterable {


    private  int mLayout;
    private List<AlbumData> mReceivedList;
    OnItemClickListener mSetClickListener;
    private List<AlbumData> mFilteredList;

    public AlbumDataRecyclerAdapter(int mLayout, List<AlbumData> mReceivedList) {
        this.mLayout = mLayout;
        this.mReceivedList = mReceivedList;

        mFilteredList = mReceivedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context lContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(lContext);
        View lAlbumListView = layoutInflater.inflate(mLayout, parent, false);

        AlbumDataRecyclerAdapter.ViewHolder viewHolder = new AlbumDataRecyclerAdapter.ViewHolder(lAlbumListView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AlbumData albumData = mFilteredList.get(position);
        TextView Title = holder.title;
        TextView id = holder.id;
        ImageView imageView = holder.image;
        CheckBox checkBox = holder.quiTxt;


        Title.setText(("Title: "+albumData.getTitle()));
        id.setText(("ID: "+albumData.getId()));
        Picasso.get().load(albumData.getUrl()).into(imageView);

        if (albumData.getTitle().contains("qui"))
        {
            checkBox.setChecked(true);
        }



    }


    public void setClickListener(OnItemClickListener onItemClickListener)
    {
        this.mSetClickListener = onItemClickListener;

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchString = constraint.toString();

                if (searchString.isEmpty()) {

                    mFilteredList = mReceivedList;
                } else {

                    ArrayList<AlbumData> filteredList = new ArrayList<>();

                    for (AlbumData albumData : mReceivedList) {

                        if (albumData.getTitle().contains(searchString)) {

                            filteredList.add(albumData);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mFilteredList = (List<AlbumData>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title,id;
        CheckBox quiTxt;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            id = itemView.findViewById(R.id.id);
            quiTxt = itemView.findViewById(R.id.checkbox_qui);
            image = itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {

            mSetClickListener.onClick(v,getAdapterPosition());

        }
    }
}
