package com.example.drinkingpal.Fragment;

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
import com.example.drinkingpal.Fragment.InformationFragments.SelectionFragment;
import com.example.drinkingpal.Fragment.InformationFragments.StandardDrinkInfoFragment;
import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentInformationBinding;
import com.example.drinkingpal.viewmodel.SharedViewModel;

public class InformationFragment extends Fragment {
    private SharedViewModel model;
    private FragmentInformationBinding informationBinding;

    public InformationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment
        super.onCreate(savedInstanceState);
        informationBinding = FragmentInformationBinding.inflate(inflater, container, false);
        View view = informationBinding.getRoot();
        return view;
    }

    public void onStart() {
        super.onStart();
        //information page， set four click listening even
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.informationChildFragment, new SelectionFragment()).commit();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        informationBinding = null;
    }
}