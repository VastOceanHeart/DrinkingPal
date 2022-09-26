package com.example.drinkingpal.Fragment.InformationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentHangoverRemediesInfoBinding;


public class HangoverRemediesInfoFragment extends Fragment {
    private FragmentHangoverRemediesInfoBinding hangoverRemediesInfoBinding;

    public HangoverRemediesInfoFragment() {
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
        hangoverRemediesInfoBinding = FragmentHangoverRemediesInfoBinding.inflate(inflater, container, false);
        View view = hangoverRemediesInfoBinding.getRoot();

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
        hangoverRemediesInfoBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new SelectionFragment()).commit();
            }
        });

        //Before drink
        //After drink
        hangoverRemediesInfoBinding.beforeDrinkTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new BeforeDrinkFragment()).commit();
            }
        });

        //After drink
        hangoverRemediesInfoBinding.afterDrinkTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new AfterDrinkFragment()).commit();
            }
        });
    }


    /**
     * Configure the collapse function for each menu
     */
    private void collapseConfig() {

    }
}