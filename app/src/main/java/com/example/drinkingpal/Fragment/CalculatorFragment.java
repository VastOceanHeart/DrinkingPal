package com.example.drinkingpal.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.drinkingpal.R;
import com.example.drinkingpal.databinding.FragmentCalculatorBinding;
import com.example.drinkingpal.entity.Alcohol;
import com.example.drinkingpal.viewmodel.AlcoholViewModel;

import java.util.List;

public class CalculatorFragment extends Fragment {
    private FragmentCalculatorBinding calculatorBinding;

    private Double beerTotalStandardDrink;
    private Double wineTotalStandardDrink;
    private Double whiteWineTotalStandardDrink;
    private Double champagneTotalStandardDrink;
    private Double spiritsTotalStandardDrink;
    private Double grandTotalStandardDrink;

    //Room related
    private AlcoholViewModel alcoholViewModel;

    public CalculatorFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {// Inflate the View for this fragment using the binding
        super.onCreate(savedInstanceState);
        calculatorBinding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View view = calculatorBinding.getRoot();

        //Interactive with the room
        alcoholViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AlcoholViewModel.class);

        //Get all alcohols via the live data and observe
        alcoholViewModel.getAllAlcohols().observe(getViewLifecycleOwner(), new Observer<List<Alcohol>>() {
            @Override
            public void onChanged(@NonNull List<Alcohol> alcohols) {
                //Set click events for clicking on collapsed menus
                collapseConfig();
                //Zeroing the counter
                zeroingInQuantity();

                plusAndMinusButtonConfig();
            }
        });


//        SharedViewModel model = new
//                ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        model.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                calculatorBinding.textMessage.setText(s);
//            }
//        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        calculatorBinding = null;
    }

    /**
     * Reset all counters to zero
     */
    private void zeroingInQuantity() {
        //Beer
        calculatorBinding.fullStrengthBeerSmallQuantity.setText("0");
        calculatorBinding.fullStrengthBeerLargeQuantity.setText("0");
        calculatorBinding.midStrengthBeerSmallQuantity.setText("0");
        calculatorBinding.midStrengthBeerLargeQuantity.setText("0");
        calculatorBinding.lowStrengthBeerSmallQuantity.setText("0");
        calculatorBinding.lowStrengthBeerLargeQuantity.setText("0");

        //Wine
        calculatorBinding.averageRestaurantServingRedWineQuantity.setText("0");
        calculatorBinding.standardServeRedWineQuantity.setText("0");
        calculatorBinding.bottleRedWineQuantity.setText("0");
        calculatorBinding.standardServePortQuantity.setText("0");

        //White wine
        calculatorBinding.averageRestaurantServingWhiteWineQuantity.setText("0");
        calculatorBinding.standardServeWhiteWineQuantity.setText("0");
        calculatorBinding.bottleWhiteWineQuantity.setText("0");

        //Champagne
        calculatorBinding.averageRestaurantServingChampagneQuantity.setText("0");
        calculatorBinding.bottleChampagneQuantity.setText("0");

        //Spirits
        calculatorBinding.highStrengthStraightSpiritsQuantity.setText("0");
        calculatorBinding.fullStrengthReadySpiritsQuantity.setText("0");
        calculatorBinding.highStrengthReadySpiritsQuantity.setText("0");
        calculatorBinding.fullStrengthMixedSpiritsQuantity.setText("0");
        calculatorBinding.highStrengthMixedSpiritsQuantity.setText("0");
    }


    /**
     * Configure the collapse function for each menu
     */
    private void collapseConfig() {
        //beer
        calculatorBinding.beerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(calculatorBinding.beer, new AutoTransition());
                if (calculatorBinding.beerContent.getVisibility() == View.VISIBLE) {
                    calculatorBinding.beerContent.setVisibility(View.GONE);
                    calculatorBinding.beerBackground.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    calculatorBinding.beerContent.setVisibility(View.VISIBLE);
                    calculatorBinding.beerBackground.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //wine
        calculatorBinding.wineTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(calculatorBinding.wine, new AutoTransition());
                if (calculatorBinding.wineContent.getVisibility() == View.VISIBLE) {
                    calculatorBinding.wineContent.setVisibility(View.GONE);
                    calculatorBinding.wineBackground.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    calculatorBinding.wineContent.setVisibility(View.VISIBLE);
                    calculatorBinding.wineBackground.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //white wine
        calculatorBinding.whiteWineTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(calculatorBinding.whiteWine, new AutoTransition());
                if (calculatorBinding.whiteWineContent.getVisibility() == View.VISIBLE) {
                    calculatorBinding.whiteWineContent.setVisibility(View.GONE);
                    calculatorBinding.whiteWineBackground.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    calculatorBinding.whiteWineContent.setVisibility(View.VISIBLE);
                    calculatorBinding.whiteWineBackground.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //champagne
        calculatorBinding.champagneTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(calculatorBinding.champagne, new AutoTransition());
                if (calculatorBinding.champagneContent.getVisibility() == View.VISIBLE) {
                    calculatorBinding.champagneContent.setVisibility(View.GONE);
                    calculatorBinding.champagneBackground.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    calculatorBinding.champagneContent.setVisibility(View.VISIBLE);
                    calculatorBinding.champagneBackground.setImageResource(R.drawable.ic_up);
                }
            }
        });

        //spirits
        calculatorBinding.spiritsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(calculatorBinding.spirits, new AutoTransition());
                if (calculatorBinding.spiritsContent.getVisibility() == View.VISIBLE) {
                    calculatorBinding.spiritsContent.setVisibility(View.GONE);
                    calculatorBinding.spiritsBackground.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    calculatorBinding.spiritsContent.setVisibility(View.VISIBLE);
                    calculatorBinding.spiritsBackground.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }

    /**
     * Configure the plus and minus symbols for each counters
     */
    private void plusAndMinusButtonConfig() {
        //Clear button
        calculatorBinding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeroingInQuantity();
                calculateTotalStandardDrink();
            }
        });

        //Beer
        //Glass Of Full Strength Beer
        calculatorBinding.fullStrengthBeerSmallAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthBeerSmallQuantity.getText().toString());
                calculatorBinding.fullStrengthBeerSmallQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.fullStrengthBeerSmallMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthBeerSmallQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.fullStrengthBeerSmallQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottle Of Full Strength Beer
        calculatorBinding.fullStrengthBeerLargeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthBeerLargeQuantity.getText().toString());
                calculatorBinding.fullStrengthBeerLargeQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.fullStrengthBeerLargeMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthBeerLargeQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.fullStrengthBeerLargeQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Glass Of Mid Strength Beer
        calculatorBinding.midStrengthBeerSmallAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.midStrengthBeerSmallQuantity.getText().toString());
                calculatorBinding.midStrengthBeerSmallQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.midStrengthBeerSmallMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.midStrengthBeerSmallQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.midStrengthBeerSmallQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottle Of Mid Strength Beer
        calculatorBinding.midStrengthBeerLargeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.midStrengthBeerLargeQuantity.getText().toString());
                calculatorBinding.midStrengthBeerLargeQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.midStrengthBeerLargeMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.midStrengthBeerLargeQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.midStrengthBeerLargeQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Glass Of Low Strength Beer
        calculatorBinding.lowStrengthBeerSmallAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.lowStrengthBeerSmallQuantity.getText().toString());
                calculatorBinding.lowStrengthBeerSmallQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.lowStrengthBeerSmallMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.lowStrengthBeerSmallQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.lowStrengthBeerSmallQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottle Of Low Strength Beer
        calculatorBinding.lowStrengthBeerLargeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.lowStrengthBeerLargeQuantity.getText().toString());
                calculatorBinding.lowStrengthBeerLargeQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.lowStrengthBeerLargeMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.lowStrengthBeerLargeQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.lowStrengthBeerLargeQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Wine
        //Average Restaurant Serving Of Red Wine
        calculatorBinding.averageRestaurantServingRedWineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.averageRestaurantServingRedWineQuantity.getText().toString());
                calculatorBinding.averageRestaurantServingRedWineQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.averageRestaurantServingRedWineMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.averageRestaurantServingRedWineQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.averageRestaurantServingRedWineQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Standard Serve Of Red Wine
        calculatorBinding.standardServeRedWineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.standardServeRedWineQuantity.getText().toString());
                calculatorBinding.standardServeRedWineQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.standardServeRedWineMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.standardServeRedWineQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.standardServeRedWineQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottle Of Red Wine
        calculatorBinding.bottleRedWineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.bottleRedWineQuantity.getText().toString());
                calculatorBinding.bottleRedWineQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.bottleRedWineMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.bottleRedWineQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.bottleRedWineQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Standard serve of port
        calculatorBinding.standardServePortAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.standardServePortQuantity.getText().toString());
                calculatorBinding.standardServePortQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.standardServePortMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.standardServePortQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.standardServePortQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //White wine
        //Average Restaurant Serving Of White Wine
        calculatorBinding.averageRestaurantServingWhiteWineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.averageRestaurantServingWhiteWineQuantity.getText().toString());
                calculatorBinding.averageRestaurantServingWhiteWineQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.averageRestaurantServingWhiteWineMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.averageRestaurantServingWhiteWineQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.averageRestaurantServingWhiteWineQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Standard Serve Of White Wine
        calculatorBinding.standardServeWhiteWineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.standardServeWhiteWineQuantity.getText().toString());
                calculatorBinding.standardServeWhiteWineQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.standardServeWhiteWineMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.standardServeWhiteWineQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.standardServeWhiteWineQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottle Of White Wine
        calculatorBinding.bottleWhiteWineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.bottleWhiteWineQuantity.getText().toString());
                calculatorBinding.bottleWhiteWineQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.bottleWhiteWineMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.bottleWhiteWineQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.bottleWhiteWineQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Champagne
        //Average Restaurant Serving Of Champagne
        calculatorBinding.averageRestaurantServingChampagneAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.averageRestaurantServingChampagneQuantity.getText().toString());
                calculatorBinding.averageRestaurantServingChampagneQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.averageRestaurantServingChampagneMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.averageRestaurantServingChampagneQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.averageRestaurantServingChampagneQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottle Of Champagne
        calculatorBinding.bottleChampagneAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.bottleChampagneQuantity.getText().toString());
                calculatorBinding.bottleChampagneQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.bottleChampagneMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.bottleChampagneQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.bottleChampagneQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Spirits
        //High Strength Straight Spirits
        calculatorBinding.highStrengthStraightSpiritsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.highStrengthStraightSpiritsQuantity.getText().toString());
                calculatorBinding.highStrengthStraightSpiritsQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.highStrengthStraightSpiritsMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.highStrengthStraightSpiritsQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.highStrengthStraightSpiritsQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Full Strength Ready To Drink Spirits
        calculatorBinding.fullStrengthReadySpiritsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthReadySpiritsQuantity.getText().toString());
                calculatorBinding.fullStrengthReadySpiritsQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.fullStrengthReadySpiritsMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthReadySpiritsQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.fullStrengthReadySpiritsQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //High Strength Ready To Drink Spirits
        calculatorBinding.highStrengthReadySpiritsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.highStrengthReadySpiritsQuantity.getText().toString());
                calculatorBinding.highStrengthReadySpiritsQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.highStrengthReadySpiritsMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.highStrengthReadySpiritsQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.highStrengthReadySpiritsQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //Full Strength Pre-mixed Spirits
        calculatorBinding.fullStrengthMixedSpiritsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthMixedSpiritsQuantity.getText().toString());
                calculatorBinding.fullStrengthMixedSpiritsQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.fullStrengthMixedSpiritsMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.fullStrengthMixedSpiritsQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.fullStrengthMixedSpiritsQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });

        //High Strength Pre-mixed Spirits
        calculatorBinding.highStrengthMixedSpiritsAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.highStrengthMixedSpiritsQuantity.getText().toString());
                calculatorBinding.highStrengthMixedSpiritsQuantity.setText(Integer.toString(quantity + 1));
                calculateTotalStandardDrink();
            }
        });

        calculatorBinding.highStrengthMixedSpiritsMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(calculatorBinding.highStrengthMixedSpiritsQuantity.getText().toString());
                if (quantity > 0) {
                    calculatorBinding.highStrengthMixedSpiritsQuantity.setText(Integer.toString(quantity - 1));
                    calculateTotalStandardDrink();
                } else
                    Toast.makeText(v.getContext(), "Cannot be less than 0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Based on user's input, calculate the total standard drink and provide corresponding recommendation
     */
    private void calculateTotalStandardDrink() {
        //Initialization
        beerTotalStandardDrink = 0.0;
        wineTotalStandardDrink = 0.0;
        whiteWineTotalStandardDrink = 0.0;
        champagneTotalStandardDrink = 0.0;
        spiritsTotalStandardDrink = 0.0;
        grandTotalStandardDrink = 0.0;

        //For beer
        beerTotalStandardDrink += Double.parseDouble(calculatorBinding.fullStrengthBeerSmallQuantity.getText().toString()) * 1.1;
        beerTotalStandardDrink += Double.parseDouble(calculatorBinding.fullStrengthBeerLargeQuantity.getText().toString()) * 1.4;
        beerTotalStandardDrink += Double.parseDouble(calculatorBinding.midStrengthBeerSmallQuantity.getText().toString()) * 0.8;
        beerTotalStandardDrink += Double.parseDouble(calculatorBinding.midStrengthBeerLargeQuantity.getText().toString()) * 1.0;
        beerTotalStandardDrink += Double.parseDouble(calculatorBinding.lowStrengthBeerSmallQuantity.getText().toString()) * 0.6;
        beerTotalStandardDrink += Double.parseDouble(calculatorBinding.lowStrengthBeerLargeQuantity.getText().toString()) * 0.8;

        //For wine
        wineTotalStandardDrink += Double.parseDouble(calculatorBinding.averageRestaurantServingRedWineQuantity.getText().toString()) * 1.6;
        wineTotalStandardDrink += Double.parseDouble(calculatorBinding.standardServeRedWineQuantity.getText().toString()) * 1.0;
        wineTotalStandardDrink += Double.parseDouble(calculatorBinding.bottleRedWineQuantity.getText().toString()) * 8.0;
        wineTotalStandardDrink += Double.parseDouble(calculatorBinding.standardServePortQuantity.getText().toString()) * 0.9;

        //For white wine
        whiteWineTotalStandardDrink += Double.parseDouble(calculatorBinding.averageRestaurantServingWhiteWineQuantity.getText().toString()) * 1.4;
        whiteWineTotalStandardDrink += Double.parseDouble(calculatorBinding.standardServeWhiteWineQuantity.getText().toString()) * 0.9;
        whiteWineTotalStandardDrink += Double.parseDouble(calculatorBinding.bottleWhiteWineQuantity.getText().toString()) * 6.8;

        //For Champagne
        champagneTotalStandardDrink += Double.parseDouble(calculatorBinding.averageRestaurantServingChampagneQuantity.getText().toString()) * 1.4;
        champagneTotalStandardDrink += Double.parseDouble(calculatorBinding.bottleChampagneQuantity.getText().toString()) * 7.1;

        //For Spirits
        spiritsTotalStandardDrink += Double.parseDouble(calculatorBinding.highStrengthStraightSpiritsQuantity.getText().toString()) * 1.0;
        spiritsTotalStandardDrink += Double.parseDouble(calculatorBinding.fullStrengthReadySpiritsQuantity.getText().toString()) * 1.1;
        spiritsTotalStandardDrink += Double.parseDouble(calculatorBinding.highStrengthReadySpiritsQuantity.getText().toString()) * 1.5;
        spiritsTotalStandardDrink += Double.parseDouble(calculatorBinding.fullStrengthMixedSpiritsQuantity.getText().toString()) * 1.0;
        spiritsTotalStandardDrink += Double.parseDouble(calculatorBinding.highStrengthMixedSpiritsQuantity.getText().toString()) * 1.4;

        //Grand Total
        grandTotalStandardDrink += beerTotalStandardDrink + wineTotalStandardDrink + whiteWineTotalStandardDrink + champagneTotalStandardDrink + spiritsTotalStandardDrink;
        calculatorBinding.totalStandardDrinkResult.setText(String.format("%.2f", grandTotalStandardDrink));

        if (grandTotalStandardDrink >= 0 && grandTotalStandardDrink < 1)
            calculatorBinding.recommendationContent.setText(R.string.recommendation_one);
        else if (grandTotalStandardDrink >= 1 && grandTotalStandardDrink < 2)
            calculatorBinding.recommendationContent.setText(R.string.recommendation_two);
        else if (grandTotalStandardDrink >= 2 && grandTotalStandardDrink < 4)
            calculatorBinding.recommendationContent.setText(R.string.recommendation_three);
        else if (grandTotalStandardDrink >= 4 && grandTotalStandardDrink < 10)
            calculatorBinding.recommendationContent.setText(R.string.recommendation_four);
        else
            calculatorBinding.recommendationContent.setText(R.string.recommendation_five);
    }
}