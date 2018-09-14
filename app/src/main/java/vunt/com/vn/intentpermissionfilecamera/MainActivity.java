package vunt.com.vn.intentpermissionfilecamera;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION = 9596;
    private GalleryFragment mGalleryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (askPermission(permissions) == false) {
            initViews();
        }
    }

    private boolean askPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < permissions.length; i++) {
                int permissionState = checkSelfPermission(permissions[i]);
                if (permissionState != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, REQUEST_PERMISSION);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                finish();
                return;
            }
        }
        initViews();
    }

    private void initViews() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mGalleryFragment = new GalleryFragment();
        fragmentTransaction.add(R.id.container, mGalleryFragment);
        fragmentTransaction.commit();
    }
}
