package com.example.drinkingpal.Fragment.InformationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.drinkingpal.Fragment.CalculatorFragment;
import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentStandardDrinkInfoBinding;


public class StandardDrinkInfoFragment extends Fragment {

    private FragmentStandardDrinkInfoBinding standardDrinkInfoBinding;

    public StandardDrinkInfoFragment() {
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
        standardDrinkInfoBinding = FragmentStandardDrinkInfoBinding.inflate(inflater, container, false);
        View view = standardDrinkInfoBinding.getRoot();

        itemsConfig();
        return view;
    }

    /**
     * Configure the item for each click event within the page
     */
    private void itemsConfig() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Back button
        standardDrinkInfoBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new SelectionFragment()).commit();
            }
        });


        //go to standard drinking calculator
        standardDrinkInfoBinding.gotoCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new CalculatorFragment()).commit();
            }
        });
    }
}