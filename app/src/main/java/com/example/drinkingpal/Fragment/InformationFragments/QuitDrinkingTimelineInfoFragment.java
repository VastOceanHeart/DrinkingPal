package com.example.drinkingpal.Fragment.InformationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentQuitDrinkingTimelineInfoBinding;


public class QuitDrinkingTimelineInfoFragment extends Fragment {

    private FragmentQuitDrinkingTimelineInfoBinding quitDrinkingTimelineInfoBinding;

    public QuitDrinkingTimelineInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        quitDrinkingTimelineInfoBinding = FragmentQuitDrinkingTimelineInfoBinding.inflate(inflater, container, false);
        View view = quitDrinkingTimelineInfoBinding.getRoot();

        //Configure the items
        itemsConfig();

        //Configure the collapse function for each menu
        collapseConfig();

        return view;
    }

    /**
     * Configure the item for each click event within the page
     */
    private void itemsConfig() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Back button
        quitDrinkingTimelineInfoBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new SelectionFragment()).commit();
            }
        });
    }


    /**
     * Configure the collapse function for each menu
     */
    private void collapseConfig() {
        //twelve hour
        quitDrinkingTimelineInfoBinding.twelveHourTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.twelveHour, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.twelveHourText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.twelveHourText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.twelveHourDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.twelveHourText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.twelveHourDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //one day
        quitDrinkingTimelineInfoBinding.oneDayTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.oneDay, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.oneDayText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.oneDayText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.oneDayDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.oneDayText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.oneDayDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //two day
        quitDrinkingTimelineInfoBinding.twoDayTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.twoDay, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.twoDayText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.twoDayText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.twoDayDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.twoDayText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.twoDayDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //three day
        quitDrinkingTimelineInfoBinding.threeDayTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.threeDay, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.threeDayText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.threeDayText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.threeDayDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.threeDayText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.threeDayDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //five day
        quitDrinkingTimelineInfoBinding.fiveDayTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.fiveDay, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.fiveDayText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.fiveDayText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.fiveDayDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.fiveDayText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.fiveDayDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //one week
        quitDrinkingTimelineInfoBinding.oneWeekTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.oneWeek, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.oneWeekText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.oneWeekText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.oneWeekDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.oneWeekText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.oneWeekDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //two week
        quitDrinkingTimelineInfoBinding.twoWeekTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.twoWeek, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.twoWeekText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.twoWeekText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.twoWeekDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.twoWeekText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.twoWeekDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //one month
        quitDrinkingTimelineInfoBinding.oneMonthTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.oneMonth, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.oneMonthText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.oneMonthText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.oneMonthDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.oneMonthText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.oneMonthDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //one year
        quitDrinkingTimelineInfoBinding.oneYearTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(quitDrinkingTimelineInfoBinding.oneYear, new AutoTransition());
                if (quitDrinkingTimelineInfoBinding.oneYearText.getVisibility() == View.VISIBLE) {
                    quitDrinkingTimelineInfoBinding.oneYearText.setVisibility(View.GONE);
                    quitDrinkingTimelineInfoBinding.oneYearDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    quitDrinkingTimelineInfoBinding.oneYearText.setVisibility(View.VISIBLE);
                    quitDrinkingTimelineInfoBinding.oneYearDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }
}