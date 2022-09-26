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
import com.example.drinkingpal.databinding.FragmentAlcoholTypeInfoBinding;


public class AlcoholTypeInfoFragment extends Fragment {

    private FragmentAlcoholTypeInfoBinding alcoholTypeInfoBinding;

    public AlcoholTypeInfoFragment() {
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
        alcoholTypeInfoBinding = FragmentAlcoholTypeInfoBinding.inflate(inflater, container, false);
        View view = alcoholTypeInfoBinding.getRoot();

        //Back button
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
        alcoholTypeInfoBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.informationChildFragment, new SelectionFragment()).commit();
            }
        });
    }

    /**
     * Configure the collapse function for each menu
     */
    private void collapseConfig() {
        //beer
        alcoholTypeInfoBinding.beerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.beer, new AutoTransition());
                if (alcoholTypeInfoBinding.beerText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.beerText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.beerDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.beerText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.beerDropDown.setImageResource(R.drawable.ic_up);

                }
            }
        });

        //wine
        alcoholTypeInfoBinding.wineTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.wine, new AutoTransition());
                if (alcoholTypeInfoBinding.wineText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.wineText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.wineDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.wineText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.wineDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //whiskey
        alcoholTypeInfoBinding.whiskeyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.whiskey, new AutoTransition());
                if (alcoholTypeInfoBinding.whiskeyText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.whiskeyText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.whiskeyDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.whiskeyText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.whiskeyDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //vodka
        alcoholTypeInfoBinding.vodkaTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.vodka, new AutoTransition());
                if (alcoholTypeInfoBinding.vodkaText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.vodkaText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.vodkaDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.vodkaText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.vodkaDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //rum
        alcoholTypeInfoBinding.rumTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.rum, new AutoTransition());
                if (alcoholTypeInfoBinding.rumText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.rumText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.rumDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.rumText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.rumDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //gin
        alcoholTypeInfoBinding.ginTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.gin, new AutoTransition());
                if (alcoholTypeInfoBinding.ginText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.ginText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.ginDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.ginText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.ginDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //tequila
        alcoholTypeInfoBinding.tequilaTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.tequila, new AutoTransition());
                if (alcoholTypeInfoBinding.tequilaText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.tequilaText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.tequilaDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.tequilaText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.tequilaDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //brandy
        alcoholTypeInfoBinding.brandyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(alcoholTypeInfoBinding.brandy, new AutoTransition());
                if (alcoholTypeInfoBinding.brandyText.getVisibility() == View.VISIBLE) {
                    alcoholTypeInfoBinding.brandyText.setVisibility(View.GONE);
                    alcoholTypeInfoBinding.brandyDropDown.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    alcoholTypeInfoBinding.brandyText.setVisibility(View.VISIBLE);
                    alcoholTypeInfoBinding.brandyDropDown.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }
}