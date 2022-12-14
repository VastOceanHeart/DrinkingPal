package com.example.drinkingpal.worker;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.drinkingpal.MainActivity;
import com.example.drinkingpal.R;
import com.example.drinkingpal.retrofit.MemeResponse;
import com.example.drinkingpal.retrofit.MemeRetrofitClient;
import com.example.drinkingpal.retrofit.MemeRetrofitInterface;

import java.net.URL;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SobrietyReminderWorker extends Worker {
    private final Random randomBuilder;
    private NotificationCompat.Builder builder;


    public SobrietyReminderWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
        randomBuilder = new Random();
    }

    @NonNull
    @Override
    public Result doWork() {

        Context applicationContext = getApplicationContext();
        try {
            //notification push
            configNotification();
            // Indicate whether the work finished successfully with the Result
            return Result.success();
        } catch (Exception e) {
            return Result.failure();
        }
    }

    /**
     * config the notification
     * <p>
     * Easy Tuto. (2020). Youtube.com. Retrieved 15 September 2022, from https://www.youtube.com/watch?v=4BuRMScaaI4.
     */
    @SuppressLint("UnspecifiedImmutableFlag")
    public void configNotification() {

        //Config notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("Sobriety Reminder", "Sobriety Reminder", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //Configuring click redirection for notification
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sentence.yourdictionary.com/alcohol"));
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DrinkingPalSharedPreferences", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString("targetFragment", "alarmFragment");
        spEditor.apply();

        PendingIntent pi;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        }


        //Configure the content of notification
        builder = new NotificationCompat.Builder(getApplicationContext(), "Sobriety Reminder");
        builder.setContentTitle("Sobriety Reminder");

        final String[] dailySentence = {"One less glass of wine a day keeps the doctor away"};
        builder.setContentText(dailySentence[0]);

        //Pass the sentence of the day into the alarmFragment display box
        spEditor.putString("dailySentence", dailySentence[0]);
        spEditor.apply();

        builder.setSmallIcon(R.mipmap.app_image_autofix);
        builder.setAutoCancel(true);
        builder.setContentIntent(pi);
        MemeRetrofitInterface memeRetrofitInterface = MemeRetrofitClient.getRetrofitService();

        int max = 49;
        int min = 11;
        Call<MemeResponse> memeCallAsync = memeRetrofitInterface.customSearch(randomBuilder.nextInt((max - min) + 1) + min);

        //After callback, config the notification
        memeCallAsync.enqueue(new Callback<MemeResponse>() {
            @Override
            public void onResponse(@NonNull Call<MemeResponse> memeCall, @NonNull Response<MemeResponse> memeResponse) {
                //If the image url is successfully retrieved, it will be used as the large icon part of the notification
                if (memeResponse.isSuccessful()) {

                    //Get the image link from api
                    assert memeResponse.body() != null;
                    dailySentence[0] = memeResponse.body().getTexts().toString();
                    builder.setContentText(dailySentence[0]);
                    spEditor.putString("dailySentence", dailySentence[0]);
                    spEditor.apply();

                    String link = memeResponse.body().getUrl();
                    new ConvertUrlToBitmap().execute(link);

                    spEditor.putString("reminderImageLink", link);
                    spEditor.apply();
                }
                //If the daily api limit is exceeded, large images will not be downloaded
                else {
                    NotificationManagerCompat notificationManCom = NotificationManagerCompat.from(getApplicationContext());
                    notificationManCom.notify(1, builder.build());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MemeResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                NotificationManagerCompat notificationManCom = NotificationManagerCompat.from(getApplicationContext());
                notificationManCom.notify(1, builder.build());
            }
        });

    }

    /**
     * Used to download Bitmap from URL and set the notification if successfully get the lint from url
     * <p>
     * ??????????????????????. (2016). Cannot convert url to bitmap in Android. Stack Overflow. Retrieved 18 September 2022, from https://stackoverflow.com/questions/37060782/cannot-convert-url-to-bitmap-in-android.
     */
    @SuppressLint("StaticFieldLeak")
    private class ConvertUrlToBitmap extends AsyncTask<String, Long, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                builder.setLargeIcon(bitmap);
                NotificationManagerCompat notificationManCom = NotificationManagerCompat.from(getApplicationContext());
                notificationManCom.notify(1, builder.build());
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                // download was successful

            } else {
                // download failed
            }
        }
    }
}
