package com.example.drinkingpal.Fragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentAlarmBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class AlarmFragment extends Fragment {
    private FragmentAlarmBinding alarmBinding;
    private SharedPreferences sharedPreferences;
    private static final int REQUEST_CODE = 01;

    public AlarmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        alarmBinding = FragmentAlarmBinding.inflate(inflater, container, false);
        View view = alarmBinding.getRoot();

        sharedPreferences = getContext().getSharedPreferences("DrinkingPalSharedPreferences", getContext().MODE_PRIVATE);

        String targetFragment = sharedPreferences.getString("dailySentence", null);
        String alarmImageUrl = sharedPreferences.getString("reminderImageLink", null);

        if (targetFragment != null) {
            alarmBinding.dailySentenceContent.setText(targetFragment);
        }

        if (alarmImageUrl != null) {
            Glide.with(this).load(alarmImageUrl).into(alarmBinding.dailyImageContent);
//
//            //load the gif from url into webview
//            alarmBinding.dailyImageContent.loadUrl(alarmImageUrl);
//            alarmBinding.dailyImageContent.getSettings().setUseWideViewPort(true);
//            alarmBinding.dailyImageContent.getSettings().setLoadWithOverviewMode(true);
        }

        //Configure the items
        itemsConfig();

        //Configure the collapse function for each menu
        collapseConfig();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        alarmBinding = null;
    }

    /**
     * Configure the item for each click event within the page
     */
    private void itemsConfig() {

        sharedPreferences = getContext().getSharedPreferences("DrinkingPalSharedPreferences", getContext().MODE_PRIVATE);
        String memeUrl = sharedPreferences.getString("reminderImageLink", null);

        alarmBinding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmBinding.dailySentenceContent.setText(null);
                sharedPreferences.edit().putString("dailySentence", null).apply();
                Toast.makeText(getContext(), "Clear successfully", Toast.LENGTH_SHORT).show();
            }
        });

        alarmBinding.copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmBinding.dailySentenceContent.getText().length() > 0) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getContext().CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Favorite Sentence", alarmBinding.dailySentenceContent.getText());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Copy Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Today's Favourite Sentence box is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alarmBinding.downloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable meme = alarmBinding.dailyImageContent.getDrawable();
                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveGitToGallery(memeUrl, meme);
                } else {
                    GetPermission();
                }
            }
        });

        alarmBinding.dailyImageContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Drawable meme = alarmBinding.dailyImageContent.getDrawable();
                if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveGitToGallery(memeUrl, meme);
                } else {
                    GetPermission();
                }
                return false;
            }
        });
    }


    /**
     * Configure the collapse function for each menu
     */
    private void collapseConfig() {
        //eat
        alarmBinding.explainTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alarmBinding.explain, new AutoTransition());
                if (alarmBinding.explainText.getVisibility() == View.VISIBLE) {
                    alarmBinding.explainText.setVisibility(View.GONE);
                    alarmBinding.explainDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alarmBinding.explainText.setVisibility(View.VISIBLE);
                    alarmBinding.explainDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }

    /**
     * Get the path of image
     *
     * @param imgUrl The url of that image
     * @return the path of the image
     */
    private String getImagePath(String imgUrl) {
        String path = null;
        FutureTarget<File> future = Glide.with(this)
                .load(imgUrl)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * @param oldPath original file path
     * @param newPath new path
     */
    public void copyFile(String oldPath, final String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //if the file already exist
                InputStream inStream = new FileInputStream(oldPath); //load the original file
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //Number of bytes (file size)
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }


                inStream.close();
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Download a image into gallery
     *
     * @param imageUrl The Url of that image
     * @param drawable The drawable object of that image
     */
    private void saveGitToGallery(String imageUrl, Drawable drawable) {
        String imagePath = null;

        if (drawable instanceof GifDrawable) {
            imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".gif";
        } else {
            imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";
        }

        /**
         * Download to specified path Save images and motion pictures
         */
        final String finalImagePath = imagePath;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = getImagePath(imageUrl);
                copyFile(path, finalImagePath);
                System.out.println("" + finalImagePath);
                Intent intentBroadcast = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File file = new File(finalImagePath);
                intentBroadcast.setData(Uri.fromFile(file));
                getActivity().sendBroadcast(intentBroadcast);

            }
        }).start();
    }

    /**
     * Get the permission to save meme to local device
     */
    private void GetPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sharedPreferences = getContext().getSharedPreferences("DrinkingPalSharedPreferences", getContext().MODE_PRIVATE);
                String memeUrl = sharedPreferences.getString("reminderImageLink", null);
                Drawable meme = alarmBinding.dailyImageContent.getDrawable();
                saveGitToGallery(memeUrl, meme);
            } else
                Toast.makeText(getContext(), "Provide permission", Toast.LENGTH_SHORT).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}