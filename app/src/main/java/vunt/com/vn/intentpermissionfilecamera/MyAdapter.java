package vunt.com.vn.intentpermissionfilecamera;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mPaths;

    public MyAdapter(List<String> paths) {
        mPaths = paths;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gallery, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mPaths.get(position));
    }

    @Override
    public int getItemCount() {
        return mPaths != null ? mPaths.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageGallery;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageGallery = itemView.findViewById(R.id.image_item_gallery);
        }

        public void bindData(String string) {
            Glide.with(itemView.getContext()).load(string).into(mImageGallery);
        }
    }
}
