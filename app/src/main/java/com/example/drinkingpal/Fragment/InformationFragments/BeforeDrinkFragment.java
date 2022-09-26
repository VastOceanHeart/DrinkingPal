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
import com.example.drinkingpal.databinding.FragmentBeforeDrinkBinding;

public class BeforeDrinkFragment extends Fragment {


    private FragmentBeforeDrinkBinding beforeDrinkBinding;


    public BeforeDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Inflate the layout for this fragment

        beforeDrinkBinding = FragmentBeforeDrinkBinding.inflate(inflater, container, false);
        View view = beforeDrinkBinding.getRoot();

        //Back button
        beforeDrinkBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new HangoverRemediesInfoFragment()).commit();
            }
        });

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
        beforeDrinkBinding.backButton.setOnClickListener(new View.OnClickListener() {
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
        //eat
        beforeDrinkBinding.eatTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(beforeDrinkBinding.eat, new AutoTransition());
                if (beforeDrinkBinding.eatText.getVisibility() == View.VISIBLE) {
                    beforeDrinkBinding.eatText.setVisibility(View.GONE);
                    beforeDrinkBinding.eatDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    beforeDrinkBinding.eatText.setVisibility(View.VISIBLE);
                    beforeDrinkBinding.eatDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //Stay Hydrated
        beforeDrinkBinding.hydratedTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(beforeDrinkBinding.hydrated, new AutoTransition());
                if (beforeDrinkBinding.hydratedText.getVisibility() == View.VISIBLE) {
                    beforeDrinkBinding.hydratedText.setVisibility(View.GONE);
                    beforeDrinkBinding.hydratedDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    beforeDrinkBinding.hydratedText.setVisibility(View.VISIBLE);
                    beforeDrinkBinding.hydratedDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //fast
        beforeDrinkBinding.fastTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(beforeDrinkBinding.fast, new AutoTransition());
                if (beforeDrinkBinding.fastText.getVisibility() == View.VISIBLE) {
                    beforeDrinkBinding.fastText.setVisibility(View.GONE);
                    beforeDrinkBinding.fastDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    beforeDrinkBinding.fastText.setVisibility(View.VISIBLE);
                    beforeDrinkBinding.fastDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //limit
        beforeDrinkBinding.limitTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(beforeDrinkBinding.limit, new AutoTransition());
                if (beforeDrinkBinding.limitText.getVisibility() == View.VISIBLE) {
                    beforeDrinkBinding.limitText.setVisibility(View.GONE);
                    beforeDrinkBinding.limitDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    beforeDrinkBinding.limitText.setVisibility(View.VISIBLE);
                    beforeDrinkBinding.limitDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //count
        beforeDrinkBinding.countTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(beforeDrinkBinding.count, new AutoTransition());
                if (beforeDrinkBinding.countText.getVisibility() == View.VISIBLE) {
                    beforeDrinkBinding.countText.setVisibility(View.GONE);
                    beforeDrinkBinding.countDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    beforeDrinkBinding.countText.setVisibility(View.VISIBLE);
                    beforeDrinkBinding.countDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //say no
        beforeDrinkBinding.noTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(beforeDrinkBinding.no, new AutoTransition());
                if (beforeDrinkBinding.noText.getVisibility() == View.VISIBLE) {
                    beforeDrinkBinding.noText.setVisibility(View.GONE);
                    beforeDrinkBinding.noDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    beforeDrinkBinding.noText.setVisibility(View.VISIBLE);
                    beforeDrinkBinding.noDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //drive
        beforeDrinkBinding.driveTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(beforeDrinkBinding.drive, new AutoTransition());
                if (beforeDrinkBinding.driveText.getVisibility() == View.VISIBLE) {
                    beforeDrinkBinding.driveText.setVisibility(View.GONE);
                    beforeDrinkBinding.driveDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    beforeDrinkBinding.driveText.setVisibility(View.VISIBLE);
                    beforeDrinkBinding.driveDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }
}