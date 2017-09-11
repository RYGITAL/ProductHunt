package com.rygital.producthunt.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rygital.producthunt.R;
import com.rygital.producthunt.models.ProductListData;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private final OnItemClickListener listener;
    private List<ProductListData> data;
    private Context context;

    public HomeAdapter(Context context, OnItemClickListener listener) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.tvName.setText(data.get(position).getName());
        holder.tvDesc.setText(data.get(position).getTagline());
        holder.tvUpvotes.setText("+" + data.get(position).getVotes_count());

        String images = data.get(position).getThumbnail().getImage_url();

        Glide.with(context)
                .load(images)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.background);
    }

    @Override
    public int getItemCount() {
        if(data == null) return 0;
        return data.size();
    }

    public void clear() {
        if (data == null) return;
        data.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ProductListData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(ProductListData Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc, tvUpvotes;
        ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
            tvUpvotes = (TextView) itemView.findViewById(R.id.tvUpvotes);
            background = (ImageView) itemView.findViewById(R.id.thumbnail);
        }

        public void click(final ProductListData productListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(productListData);
                }
            });
        }
    }
}
