package com.example.drinkingpal.Fragment.ConsequenceFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.drinkingpal.Fragment.InformationFragments.AlcoholTypeInfoFragment;
import com.example.drinkingpal.Fragment.InformationFragments.HangoverRemediesInfoFragment;
import com.example.drinkingpal.Fragment.InformationFragments.QuitDrinkingTimelineInfoFragment;
import com.example.drinkingpal.Fragment.InformationFragments.StandardDrinkInfoFragment;
import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentConsequenceSelectionBinding;


public class ConsequenceSelectionFragment extends Fragment {

    FragmentConsequenceSelectionBinding consequenceSelectionBinding;

    public ConsequenceSelectionFragment() {
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
        consequenceSelectionBinding = FragmentConsequenceSelectionBinding.inflate(inflater, container, false);
        View view = consequenceSelectionBinding.getRoot();

        //initial the fragment manager to manager child fragments
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Config items
        itemsConfig();

        return view;
    }

    /**
     * Configure the item for each click event within the page
     */
    private void itemsConfig() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        consequenceSelectionBinding.lawConsequenceTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.consequenceChildFragment, new LawConsequenceFragment()).commit();
            }
        });

        consequenceSelectionBinding.healthyConsequenceTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.consequenceChildFragment, new HealthyConsequenceFragment()).commit();
            }
        });


        consequenceSelectionBinding.moralConsequenceTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.consequenceChildFragment, new MoralConsequenceFragment()).commit();
            }
        });
    }
}