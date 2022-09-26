package com.example.drinkingpal.Fragment.InformationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentSelectionBinding;


public class SelectionFragment extends Fragment {

    private FragmentSelectionBinding fragmentSelectionBinding;

    public SelectionFragment() {
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
        fragmentSelectionBinding = FragmentSelectionBinding.inflate(inflater, container, false);
        View view = fragmentSelectionBinding.getRoot();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Configure the items
        itemsConfig();

        return view;
    }

    /**
     * Configure the item for each click event within the page
     */
    private void itemsConfig() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentSelectionBinding.standardDrinkInfoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new StandardDrinkInfoFragment()).commit();
            }
        });

        fragmentSelectionBinding.alcoholTypeInfoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new AlcoholTypeInfoFragment()).commit();
            }
        });


        fragmentSelectionBinding.hangoverRemediesInfoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new HangoverRemediesInfoFragment()).commit();
            }
        });

        fragmentSelectionBinding.quitDrinkingTimelineInfoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new QuitDrinkingTimelineInfoFragment()).commit();
            }
        });
    }
}