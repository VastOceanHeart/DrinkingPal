package com.example.drinkingpal.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drinkingpal.Fragment.ConsequenceFragments.ConsequenceSelectionFragment;
import com.example.drinkingpal.Fragment.InformationFragments.SelectionFragment;
import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentConsequenceBinding;


public class ConsequenceFragment extends Fragment {

    private FragmentConsequenceBinding consequenceBinding;

    public ConsequenceFragment() {
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
        consequenceBinding = FragmentConsequenceBinding.inflate(inflater, container, false);
        View view = consequenceBinding.getRoot();
        return view;
    }

    public void onStart() {
        super.onStart();
        //information page， set four click listening even
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.consequenceChildFragment, new ConsequenceSelectionFragment()).commit();

    }
}