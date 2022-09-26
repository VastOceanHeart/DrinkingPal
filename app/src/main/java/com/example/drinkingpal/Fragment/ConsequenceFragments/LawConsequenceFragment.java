package com.example.drinkingpal.Fragment.ConsequenceFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentLawConsequenceBinding;

public class LawConsequenceFragment extends Fragment {

    FragmentLawConsequenceBinding lawConsequenceBinding;

    public LawConsequenceFragment() {
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
        lawConsequenceBinding = FragmentLawConsequenceBinding.inflate(inflater, container, false);
        View view = lawConsequenceBinding.getRoot();

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
        lawConsequenceBinding.backButton.setOnClickListener(new View.OnClickListener() {
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
        //public drink
        lawConsequenceBinding.publicDrinkTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(lawConsequenceBinding.publicDrink, new AutoTransition());
                if (lawConsequenceBinding.publicDrinkText.getVisibility() == View.VISIBLE) {
                    lawConsequenceBinding.publicDrinkText.setVisibility(View.GONE);
                    lawConsequenceBinding.publicDrinkDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    lawConsequenceBinding.publicDrinkText.setVisibility(View.VISIBLE);
                    lawConsequenceBinding.publicDrinkDropDown.setImageResource(R.drawable.ic_up);

                }
            }
        });

        //buy alcohol
        lawConsequenceBinding.buyAlcoholTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(lawConsequenceBinding.buyAlcohol, new AutoTransition());
                if (lawConsequenceBinding.buyAlcoholText.getVisibility() == View.VISIBLE) {
                    lawConsequenceBinding.buyAlcoholText.setVisibility(View.GONE);
                    lawConsequenceBinding.buyAlcoholDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    lawConsequenceBinding.buyAlcoholText.setVisibility(View.VISIBLE);
                    lawConsequenceBinding.buyAlcoholDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //private drink
        lawConsequenceBinding.privateDrinkTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(lawConsequenceBinding.privateDrink, new AutoTransition());
                if (lawConsequenceBinding.privateDrinkText.getVisibility() == View.VISIBLE) {
                    lawConsequenceBinding.privateDrinkText.setVisibility(View.GONE);
                    lawConsequenceBinding.privateDrinkDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    lawConsequenceBinding.privateDrinkText.setVisibility(View.VISIBLE);
                    lawConsequenceBinding.privateDrinkDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //drink drive
        lawConsequenceBinding.drinkDriveTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(lawConsequenceBinding.drinkDrive, new AutoTransition());
                if (lawConsequenceBinding.drinkDriveText.getVisibility() == View.VISIBLE) {
                    lawConsequenceBinding.drinkDriveText.setVisibility(View.GONE);
                    lawConsequenceBinding.drinkDriveDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    lawConsequenceBinding.drinkDriveText.setVisibility(View.VISIBLE);
                    lawConsequenceBinding.drinkDriveDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }
}