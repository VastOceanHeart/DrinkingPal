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
import com.example.drinkingpal.databinding.FragmentMoralConsequenceBinding;

public class MoralConsequenceFragment extends Fragment {

    FragmentMoralConsequenceBinding moralConsequenceBinding;

    public MoralConsequenceFragment() {
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
        moralConsequenceBinding =  FragmentMoralConsequenceBinding.inflate(inflater, container, false);
        View view = moralConsequenceBinding.getRoot();

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
        moralConsequenceBinding.backButton.setOnClickListener(new View.OnClickListener() {
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
        //moral definition
        moralConsequenceBinding.moralDefinitionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(moralConsequenceBinding.moralDefinition, new AutoTransition());
                if (moralConsequenceBinding.moralDefinitionText.getVisibility() == View.VISIBLE) {
                    moralConsequenceBinding.moralDefinitionText.setVisibility(View.GONE);
                    moralConsequenceBinding.moralDefinitionDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    moralConsequenceBinding.moralDefinitionText.setVisibility(View.VISIBLE);
                    moralConsequenceBinding.moralDefinitionDropDown.setImageResource(R.drawable.ic_up);

                }
            }
        });

        //alcohol moral
        moralConsequenceBinding.alcoholMoralTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(moralConsequenceBinding.alcoholMoral, new AutoTransition());
                if (moralConsequenceBinding.alcoholMoralText.getVisibility() == View.VISIBLE) {
                    moralConsequenceBinding.alcoholMoralText.setVisibility(View.GONE);
                    moralConsequenceBinding.alcoholMoralDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    moralConsequenceBinding.alcoholMoralText.setVisibility(View.VISIBLE);
                    moralConsequenceBinding.alcoholMoralDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }
}