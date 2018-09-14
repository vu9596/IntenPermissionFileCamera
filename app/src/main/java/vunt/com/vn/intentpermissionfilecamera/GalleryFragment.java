package vunt.com.vn.intentpermissionfilecamera;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    public static final String[] EXTENSIONS = {".jpg", ".png", ".jpeg"};
    public static final String RESOURCE_IMGAE_PATH = "storage/emulated/0/DCIM/Camera";
    private List<String> mPathFiles;
    private MyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_gallery);
        recyclerView.setHasFixedSize(true);
        mPathFiles = new ArrayList<>();
        mAdapter = new MyAdapter(mPathFiles);
        recyclerView.setAdapter(mAdapter);
        getImageFromGallery();
    }

    private void getImageFromGallery() {
        File parentFile = new File(RESOURCE_IMGAE_PATH);
        File[] files = parentFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                for (String extension : EXTENSIONS) {
                    if (file.getName().toLowerCase().endsWith(extension)) return true;
                }
                return false;
            }
        });
        for (File file : files) {
            mPathFiles.add(file.getPath());
            mAdapter.notifyDataSetChanged();
        }
    }
}
