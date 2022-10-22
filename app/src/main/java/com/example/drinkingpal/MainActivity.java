package com.example.drinkingpal;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.drinkingpal.databinding.ActivityMainBinding;
import com.example.drinkingpal.entity.Alcohol;
import com.example.drinkingpal.toolClass.Validation;
import com.example.drinkingpal.viewmodel.AlcoholViewModel;
import com.example.drinkingpal.worker.SobrietyReminderWorker;
import com.google.android.material.navigation.NavigationView;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * MainActivity for DrinkingPal
 *
 * @author Wenxiao Wu
 * Kunal Jain
 * Defu
 * Joe
 * Yang
 * @version 16/09/2022
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;
    public Boolean BackPressedTwice;
    private AlcoholViewModel alcoholViewModel;
    int hour;
    int minute;

    private String userInformation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Register this activity in activityContainer
        activityContainer.getInstance().addActivity(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

//        //Extend the duration of launch page
//        long startTime = System.currentTimeMillis();
//        view.getViewTreeObserver().addOnPreDrawListener(
//                new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                    public boolean onPreDraw() {
//                        long endTime = System.currentTimeMillis();
//                        long runTime = endTime - startTime;
//
//                        //The duration of launch page (in ms)
//                        long duration = 2000;
//
//                        // Check if the initial data is ready.
//                        if (runTime > duration) {
//                            // The content is ready; start drawing.
//                            view.getViewTreeObserver().removeOnPreDrawListener(this);
//                            return true;
//                        } else {
//                            //Suspend to the setting duration.
//                            return false;
//                        }
//                    }
//                });

        setContentView(view);

        //Used to control the back button for home fragment
        BackPressedTwice = false;

        //Interactive with the room
        alcoholViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(AlcoholViewModel.class);
        initialDatabase();

        //Use the toolbar to instead actionbar
        setSupportActionBar(binding.appBar.toolbar);

        //Configure the navigation drawer
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_information_fragment,
                R.id.timed_sobriety_reminder_switch,
                R.id.nav_calculator_fragment,
                R.id.nav_quiz_fragment,
                R.id.nav_consequence_fragment,
                R.id.nav_achievement_fragment,
                R.id.nav_data_fragment)
                .setOpenableLayout(binding.drawerLayout).build();

        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.navView, navController);

        NavigationUI.setupWithNavController(binding.appBar.toolbar, navController, appBarConfiguration);

        configMenu(view);

        //Use SharedPreferences to control the targetFragment that needs to be accessed, or the main page if there is no targetFragment.
        SharedPreferences sharedPreferences = this.getSharedPreferences("DrinkingPalSharedPreferences", this.MODE_PRIVATE);
        String targetFragment = sharedPreferences.getString("targetFragment", "main");
        switch (targetFragment) {
            case "alarmFragment":
                navController.navigate(R.id.timed_sobriety_reminder_switch);
                //Reset the targetFragment in SharedPreferences after successfully entering the targetFragment
                sharedPreferences.edit().putString("targetFragment", "main").apply();
                break;
            default:
                break;
        }
    }


    /**
     * Config items in navigation drawer menu
     */
    private void configMenu(View view) {

        NavigationView navigationView = binding.navView;
        SharedPreferences sharedPreferences = this.getSharedPreferences("DrinkingPalSharedPreferences", MODE_PRIVATE);

        /*
         *Config the onboarding
         */
        MenuItem onboarding = navigationView.getMenu().findItem(R.id.nav_onboarding_activity);
        onboarding.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(MainActivity.this, OnboardingActivity.class);
                sharedPreferences.edit().putBoolean("onboarding", false).apply();
                startActivity(i);
                return false;
            }
        });

        /*
         * Config the sobriety reminder switch
         *
         * DEV, M. (2022). How to add switch button in navigation drawer. Stack Overflow. Retrieved 15 September 2022, from https://stackoverflow.com/questions/56638356/how-to-add-switch-button-in-navigation-drawer/72574145#72574145.
         */
        MenuItem sobrietyReminder = navigationView.getMenu().findItem(R.id.timed_sobriety_reminder_switch); // first insialize MenuItem
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sobrietyReminderSwitch = (Switch) sobrietyReminder.getActionView().findViewById(R.id.darkModeSwitch);
        Button startingTimeBtn = ((Button) sobrietyReminder.getActionView().findViewById(R.id.startingTime));
        Button endingTimeBtn = ((Button) sobrietyReminder.getActionView().findViewById(R.id.endingTime));

        //The timed reminder is also maintained after a click in from a notification
        String targetFragment = sharedPreferences.getString("targetFragment", "main");
        if (targetFragment.equals("alarmFragment")) {
            sobrietyReminderSwitch.setChecked(true);
            startingTimeBtn.setText(sharedPreferences.getString("startTime", "Start"));
            endingTimeBtn.setText(sharedPreferences.getString("endTime", "End"));
        }


        //Configure the start time selection button
        startingTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        startingTimeBtn.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
                    }
                };


                TimePickerDialog timePickerDialog = new TimePickerDialog(new ContextThemeWrapper(view.getContext(),R.style.TimePicker), onTimeSetListener, hour, minute, true);
                timePickerDialog.setTitle("Select Start Time");
                timePickerDialog.show();
            }
        });

        //Configure the end time selection button
        endingTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        endingTimeBtn.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(new ContextThemeWrapper(view.getContext(),R.style.TimePicker), onTimeSetListener, hour, minute, true);
                timePickerDialog.setTitle("Select End Time");
                timePickerDialog.show();
            }
        });


        // if you are using Switch in your @layout/switch_item then use Switch or use SwitchCompact.
        sobrietyReminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Switch on for turn on regular push of a daily sobriety quote


                    if (startingTimeBtn.getText().length() > 0 && endingTimeBtn.getText().length() > 0) {
                        String starting, ending;
                        starting = startingTimeBtn.getText().toString();
                        ending = endingTimeBtn.getText().toString();

                        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("DrinkingPalSharedPreferences", view.getContext().MODE_PRIVATE);
                        sharedPreferences.edit().putString("startTime", starting).apply();
                        sharedPreferences.edit().putString("endTime", ending).apply();

                        if (Validation.checkStartAndEndTimePoint(starting, ending)) {
                            //Configure how many reminders in that time period with a random number
                            Random randomBuilder = new Random();
                            int remindTimes = randomBuilder.nextInt(3) + 1;
                            for (int currentRemindTimes = 0; currentRemindTimes < remindTimes; currentRemindTimes++)
                                scheduleNotification(view, starting, ending);

//                            Only for test
//                            WorkRequest firstTImeWorkRequest = new OneTimeWorkRequest.Builder(SobrietyReminderWorker.class)
//                                    .build();
//
//                            WorkManager.getInstance(view.getContext()).enqueue(firstTImeWorkRequest);

                            //Print tips
                            Toast.makeText(view.getContext(), "Daily "
                                    + starting + " to " + ending + " timed push successfully enabled", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            sobrietyReminderSwitch.setChecked(false);
                            Toast.makeText(view.getContext(), "The end time should be after the start time!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        sobrietyReminderSwitch.setChecked(false);
                        Toast.makeText(view.getContext(), "Please select the time range for the timed reminder!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    //Switch off for turn off regular push of a daily sobriety quote
                    WorkManager.getInstance(view.getContext()).cancelAllWork();
                    //Print tips
                    Toast.makeText(view.getContext(), "Daily scheduled push have been turned off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Used to pre-import data into the database and not duplicate it if alcohol already has it in the database
     */
    public void initialDatabase() {
        LiveData<List<Alcohol>> alcohols = alcoholViewModel.getAllAlcohols();
        alcohols.observe(this, new Observer<List<Alcohol>>() {
            @Override
            public void onChanged(List<Alcohol> alcohols) {
                //Initialisation will only be executed when the database is empty
                if (alcohols.isEmpty()) {
                    //This is the place to initialise data into the database
                    //Full strength beer
                    alcoholViewModel.insert(new Alcohol(1, "Full strength beer", 285, 4.8, 1.1));
                    alcoholViewModel.insert(new Alcohol(2, "Full strength beer", 425, 4.8, 1.6));
                    alcoholViewModel.insert(new Alcohol(3, "Full strength beer", 375, 4.8, 1.4));

                    //Mid strength beer
                    alcoholViewModel.insert(new Alcohol(4, "Mid strength beer", 285, 3.5, 0.8));
                    alcoholViewModel.insert(new Alcohol(5, "Mid strength beer", 425, 3.5, 1.2));
                    alcoholViewModel.insert(new Alcohol(6, "Mid strength beer", 375, 3.5, 1.0));

                    //Low strength beer
                    alcoholViewModel.insert(new Alcohol(7, "Low strength beer", 285, 2.7, 0.6));
                    alcoholViewModel.insert(new Alcohol(8, "Low strength beer", 425, 2.7, 0.9));
                    alcoholViewModel.insert(new Alcohol(9, "Low strength beer", 375, 2.7, 0.8));

                    //Red Wine
                    alcoholViewModel.insert(new Alcohol(10, "Average restaurant serving of red wine", 150, 13.5, 1.6));
                    alcoholViewModel.insert(new Alcohol(11, "Standard serve of red wine", 100, 13.5, 1.0));
                    alcoholViewModel.insert(new Alcohol(12, "Bottle of red wine", 750, 13.5, 8));
                    alcoholViewModel.insert(new Alcohol(13, "Cask of red wine", 4000, 13.5, 43));
                    alcoholViewModel.insert(new Alcohol(14, "Cask of red wine", 2000, 13.5, 21));
                    alcoholViewModel.insert(new Alcohol(15, "Standard serve of port", 60, 17.5, 0.9));
                    alcoholViewModel.insert(new Alcohol(16, "Cask of port", 2000, 17.5, 28));

                    //White wine
                    alcoholViewModel.insert(new Alcohol(17, "Average restaurant serving of white wine", 150, 11.5, 1.4));
                    alcoholViewModel.insert(new Alcohol(18, "Standard serve of white wine", 100, 11.5, 0.9));
                    alcoholViewModel.insert(new Alcohol(19, "Bottle of white wine", 750, 11.5, 6.8));
                    alcoholViewModel.insert(new Alcohol(20, "Cask of white wine", 4000, 11.5, 36));
                    alcoholViewModel.insert(new Alcohol(21, "Cask of white wine", 2000, 11.5, 18));

                    //Champagne
                    alcoholViewModel.insert(new Alcohol(22, "Average restaurant serve of champagne", 150, 12, 1.4));
                    alcoholViewModel.insert(new Alcohol(23, "Bottle of champagne", 750, 12, 7.1));

                    //Spirits
                    //Straight spirits
                    alcoholViewModel.insert(new Alcohol(24, "High strength straight spirits", 30, 40, 1.0));
                    alcoholViewModel.insert(new Alcohol(25, "High strength bottle straight spirits", 700, 40, 22));

                    //Ready to drink spirits
                    alcoholViewModel.insert(new Alcohol(26, "Full strength ready to drink spirits", 275, 5.0, 1.1));
                    alcoholViewModel.insert(new Alcohol(27, "Full strength ready to drink spirits", 330, 5.0, 1.2));
                    alcoholViewModel.insert(new Alcohol(28, "Full strength ready to drink spirits", 660, 5.0, 2.6));
                    alcoholViewModel.insert(new Alcohol(29, "High strength ready to drink spirits", 275, 7.0, 1.5));
                    alcoholViewModel.insert(new Alcohol(30, "High strength ready to drink spirits", 330, 7.0, 1.8));
                    alcoholViewModel.insert(new Alcohol(31, "High strength ready to drink spirits", 660, 7.0, 3.6));

                    //Pre-mixed spirits
                    alcoholViewModel.insert(new Alcohol(32, "Full strength pre-mixed spirits", 250, 5.0, 1.0));
                    alcoholViewModel.insert(new Alcohol(33, "Full strength pre-mixed spirits", 300, 5.0, 1.2));
                    alcoholViewModel.insert(new Alcohol(34, "Full strength pre-mixed spirits", 375, 5.0, 1.5));
                    alcoholViewModel.insert(new Alcohol(35, "Full strength pre-mixed spirits", 440, 5.0, 1.7));
                    alcoholViewModel.insert(new Alcohol(36, "High strength pre-mixed spirits", 250, 7.0, 1.4));
                    alcoholViewModel.insert(new Alcohol(37, "High strength pre-mixed spirits", 250, 10.0, 1.9));
                    alcoholViewModel.insert(new Alcohol(38, "High strength pre-mixed spirits", 300, 7.0, 1.6));
                    alcoholViewModel.insert(new Alcohol(39, "High strength pre-mixed spirits", 375, 7.0, 2.1));
                    alcoholViewModel.insert(new Alcohol(40, "High strength pre-mixed spirits", 440, 7.0, 2.4));
                }
            }
        });
    }

    /**
     * Handle the back button for a specific fragment
     * The home screen (i.e. the information screen) provides the ability to exit the program only
     * when the back button is pressed twice.
     *
     * <p>
     * Shynline. (2018). How to handle back button when at the starting destination of the navigation component. Retrieved 11 May 2022, from https://stackoverflow.com/questions/50937116/how-to-handle-back-button-when-at-the-starting-destination-of-the-navigation-com
     */
    @Override
    public void onBackPressed() {
        if (Navigation.findNavController(this, R.id.nav_host_fragment)
                .getCurrentDestination().getId() == R.id.nav_information_fragment && !BackPressedTwice) {
            //If the user is already in the home page (home fragment), the system will reject his request to continue back
            Toast.makeText(this, "Press once more to exit DrinkingPai", Toast.LENGTH_LONG).show();
            BackPressedTwice = true;
        } else {
            BackPressedTwice = false;
            super.onBackPressed();
        }
    }

    /**
     * Configure timed alerts to automatically push messages locally at the configured time
     *
     * @param view     current view
     * @param starting The Start of time range
     * @param ending   End of time range
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scheduleNotification(View view, String starting, String ending) {

        //Config time related variables
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.of(Integer.parseInt(starting.split(":")[0]), Integer.parseInt(starting.split(":")[1]));
        LocalTime endTime = LocalTime.of(Integer.parseInt(ending.split(":")[0]), Integer.parseInt(ending.split(":")[1]));

        Random randomBuilder = new Random();
        int startAndEndGapSecond = randomBuilder.nextInt((int) Duration.between(startTime, endTime).getSeconds());


        Duration durationCurrentAndStart = Duration.between(currentTime, startTime);
        Duration durationCurrentAndEnd = Duration.between(currentTime, endTime);


        //Config constraints
        Constraints.Builder constraints = new Constraints.Builder();

        //Initial the two work request
        PeriodicWorkRequest regularWorkRequest;
        WorkRequest firstTImeWorkRequest;

        //Enable the schedule notification executed in standby mode，need API 23
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            constraints.setRequiresDeviceIdle(true);
        }


        /*
        If the current time does not exceed the set start time,
        then notification is set at a random point in the start and end time range.
         */
        if (durationCurrentAndStart.getSeconds() > 0) {
            //First push
            firstTImeWorkRequest = new OneTimeWorkRequest.Builder(SobrietyReminderWorker.class)
                    .setInitialDelay(durationCurrentAndStart.plusSeconds(startAndEndGapSecond))
                    .build();

            //Subsequent daily same-time push
            regularWorkRequest = new PeriodicWorkRequest.Builder(SobrietyReminderWorker.class, 1, TimeUnit.DAYS)
                    .setConstraints(constraints.build())
                    .setInitialDelay(durationCurrentAndStart.plusSeconds(startAndEndGapSecond))
                    .build();

        }
        /*
        If the current time exceeds the set start time but not the set end time,
        then notification is set randomly within the range of the current time and the end time.
         */
        else if (durationCurrentAndStart.getSeconds() <= 0 && durationCurrentAndEnd.getSeconds() > 0) {
            int currentAndEndGapSecond = randomBuilder.nextInt((int) Duration.between(currentTime, endTime).getSeconds());
            //First push
            firstTImeWorkRequest = new OneTimeWorkRequest.Builder(SobrietyReminderWorker.class)
                    .setInitialDelay(Duration.between(currentTime, currentTime).plusSeconds(currentAndEndGapSecond))
                    .build();
            //Subsequent daily same-time push
            regularWorkRequest = new PeriodicWorkRequest.Builder(SobrietyReminderWorker.class, 1, TimeUnit.DAYS)
                    .setConstraints(constraints.build())
                    .setInitialDelay(Duration.between(currentTime, currentTime).plusSeconds(currentAndEndGapSecond))
                    .build();

        }
        /*
        If the current time exceeds the set end time,
        the first notification is set for tomorrow according to the set start and end times.
         */
        else {
            //First push
            firstTImeWorkRequest = new OneTimeWorkRequest.Builder(SobrietyReminderWorker.class)
                    .setInitialDelay(durationCurrentAndStart.plusSeconds(startAndEndGapSecond).plusDays(1))
                    .build();
            //Subsequent daily same-time push
            regularWorkRequest = new PeriodicWorkRequest.Builder(SobrietyReminderWorker.class, 1, TimeUnit.DAYS)
                    .setConstraints(constraints.build())
                    .setInitialDelay(durationCurrentAndStart.plusSeconds(startAndEndGapSecond).plusDays(1))
                    .build();
        }

        //Enqueue two work quest
        WorkManager.getInstance(view.getContext()).enqueue(firstTImeWorkRequest);
        WorkManager.getInstance(view.getContext()).enqueue(regularWorkRequest);
    }
}
