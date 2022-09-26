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
import com.example.drinkingpal.databinding.FragmentAfterDrinkBinding;

public class AfterDrinkFragment extends Fragment {

    private FragmentAfterDrinkBinding afterDrinkBinding;

    public AfterDrinkFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        afterDrinkBinding = FragmentAfterDrinkBinding.inflate(inflater, container, false);
        View view = afterDrinkBinding.getRoot();

        //Configure the items
        itemsConfig();

        //Configure the collapse menu
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
        afterDrinkBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new HangoverRemediesInfoFragment()).commit();
            }
        });
    }

    /**
     * Configure the collapse function for each menu
     */
    private void collapseConfig() {
        //sleep
        afterDrinkBinding.sleepTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(afterDrinkBinding.sleep, new AutoTransition());
                if (afterDrinkBinding.sleepText.getVisibility() == View.VISIBLE) {
                    afterDrinkBinding.sleepText.setVisibility(View.GONE);
                    afterDrinkBinding.sleepDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    afterDrinkBinding.sleepText.setVisibility(View.VISIBLE);
                    afterDrinkBinding.sleepDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //drink water
        afterDrinkBinding.drinkWaterTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(afterDrinkBinding.drinkWater, new AutoTransition());
                if (afterDrinkBinding.drinkWaterText.getVisibility() == View.VISIBLE) {
                    afterDrinkBinding.drinkWaterText.setVisibility(View.GONE);
                    afterDrinkBinding.drinkWaterDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    afterDrinkBinding.drinkWaterText.setVisibility(View.VISIBLE);
                    afterDrinkBinding.drinkWaterDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //vitamin
        afterDrinkBinding.vitaminTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(afterDrinkBinding.vitamin, new AutoTransition());
                if (afterDrinkBinding.vitaminText.getVisibility() == View.VISIBLE) {
                    afterDrinkBinding.vitaminText.setVisibility(View.GONE);
                    afterDrinkBinding.vitaminDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    afterDrinkBinding.vitaminText.setVisibility(View.VISIBLE);
                    afterDrinkBinding.vitaminDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //food
        afterDrinkBinding.foodTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(afterDrinkBinding.food, new AutoTransition());
                if (afterDrinkBinding.foodText.getVisibility() == View.VISIBLE) {
                    afterDrinkBinding.foodText.setVisibility(View.GONE);
                    afterDrinkBinding.foodDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    afterDrinkBinding.foodText.setVisibility(View.VISIBLE);
                    afterDrinkBinding.foodDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //exercise
        afterDrinkBinding.exerciseTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(afterDrinkBinding.exercise, new AutoTransition());
                if (afterDrinkBinding.exerciseText.getVisibility() == View.VISIBLE) {
                    afterDrinkBinding.exerciseText.setVisibility(View.GONE);
                    afterDrinkBinding.exerciseDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    afterDrinkBinding.exerciseText.setVisibility(View.VISIBLE);
                    afterDrinkBinding.exerciseDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //shower
        afterDrinkBinding.showerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(afterDrinkBinding.shower, new AutoTransition());
                if (afterDrinkBinding.showerText.getVisibility() == View.VISIBLE) {
                    afterDrinkBinding.showerText.setVisibility(View.GONE);
                    afterDrinkBinding.showerDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    afterDrinkBinding.showerText.setVisibility(View.VISIBLE);
                    afterDrinkBinding.showerDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //hair
        afterDrinkBinding.hairTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(afterDrinkBinding.hair, new AutoTransition());
                if (afterDrinkBinding.hairText.getVisibility() == View.VISIBLE) {
                    afterDrinkBinding.hairText.setVisibility(View.GONE);
                    afterDrinkBinding.hairDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    afterDrinkBinding.hairText.setVisibility(View.VISIBLE);
                    afterDrinkBinding.hairDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }
}