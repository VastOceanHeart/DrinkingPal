package com.example.drinkingpal.Fragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentAlarmBinding;

import java.io.InputStream;

public class AlarmFragment extends Fragment {
    private FragmentAlarmBinding alarmBinding;
    private SharedPreferences sharedPreferences;

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
            new DownloadImageTask((ImageView) alarmBinding.dailyImageContent)
                    .execute(alarmImageUrl);
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
                Bitmap bitmap = ((BitmapDrawable) alarmBinding.dailyImageContent.getDrawable()).getBitmap();
                //Download meme into gallery
                MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, "Today Meme", "Today Meme");
                Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        alarmBinding.dailyImageContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) alarmBinding.dailyImageContent.getDrawable()).getBitmap();
                //Download meme into gallery
                MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, "Today Meme", "Today Meme");
                Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_SHORT).show();
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
     * Load image from url
     * <p>
     * Clegg, K. (2012). Load image from url. Retrieved 3 May 2022, from https://stackoverflow.com/questions/5776851/load-image-from-url
     */
    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @SuppressLint("StaticFieldLeak")
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}