package com.example.drinkingpal.Fragment.ConsequenceFragments;

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
import com.example.drinkingpal.databinding.FragmentHealthyConsequenceBinding;


public class HealthyConsequenceFragment extends Fragment {

    FragmentHealthyConsequenceBinding healthyConsequenceBinding;

    public HealthyConsequenceFragment() {
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
        healthyConsequenceBinding = FragmentHealthyConsequenceBinding.inflate(inflater, container, false);
        View view = healthyConsequenceBinding.getRoot();

        //Config items
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
        healthyConsequenceBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.consequenceChildFragment, new ConsequenceSelectionFragment()).commit();
            }
        });
    }

    /**
     * Configure the collapse function for each menu
     */
    private void collapseConfig() {
        //alcohol kills
        healthyConsequenceBinding.alcoholKillsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(healthyConsequenceBinding.alcoholKills, new AutoTransition());
                if (healthyConsequenceBinding.alcoholKillsText.getVisibility() == View.VISIBLE) {
                    healthyConsequenceBinding.alcoholKillsText.setVisibility(View.GONE);
                    healthyConsequenceBinding.alcoholKillsDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    healthyConsequenceBinding.alcoholKillsText.setVisibility(View.VISIBLE);
                    healthyConsequenceBinding.alcoholKillsDropDown.setImageResource(R.drawable.ic_up);

                }
            }
        });

        //short effect
        healthyConsequenceBinding.shortEffectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(healthyConsequenceBinding.shortEffect, new AutoTransition());
                if (healthyConsequenceBinding.shortEffectText.getVisibility() == View.VISIBLE) {
                    healthyConsequenceBinding.shortEffectText.setVisibility(View.GONE);
                    healthyConsequenceBinding.shortEffectDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    healthyConsequenceBinding.shortEffectText.setVisibility(View.VISIBLE);
                    healthyConsequenceBinding.shortEffectDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //long effect
        healthyConsequenceBinding.longEffectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(healthyConsequenceBinding.longEffect, new AutoTransition());
                if (healthyConsequenceBinding.longEffectText.getVisibility() == View.VISIBLE) {
                    healthyConsequenceBinding.longEffectText.setVisibility(View.GONE);
                    healthyConsequenceBinding.longEffectDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    healthyConsequenceBinding.longEffectText.setVisibility(View.VISIBLE);
                    healthyConsequenceBinding.longEffectDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //alcohol poison
        healthyConsequenceBinding.alcoholPoisonTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(healthyConsequenceBinding.alcoholPoison, new AutoTransition());
                if (healthyConsequenceBinding.alcoholPoisonText.getVisibility() == View.VISIBLE) {
                    healthyConsequenceBinding.alcoholPoisonText.setVisibility(View.GONE);
                    healthyConsequenceBinding.alcoholPoisonDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    healthyConsequenceBinding.alcoholPoisonText.setVisibility(View.VISIBLE);
                    healthyConsequenceBinding.alcoholPoisonDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //alcoholism
        healthyConsequenceBinding.alcoholismTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(healthyConsequenceBinding.alcoholism, new AutoTransition());
                if (healthyConsequenceBinding.alcoholismText.getVisibility() == View.VISIBLE) {
                    healthyConsequenceBinding.alcoholismText.setVisibility(View.GONE);
                    healthyConsequenceBinding.alcoholismDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    healthyConsequenceBinding.alcoholismText.setVisibility(View.VISIBLE);
                    healthyConsequenceBinding.alcoholismDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }
}