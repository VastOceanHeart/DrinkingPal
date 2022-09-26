package com.example.drinkingpal.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.drinkingpal.databinding.FragmentFaceRecognitionBinding;


public class FaceRecognitionFragment extends Fragment {
    private FragmentFaceRecognitionBinding faceRecognitionBinding;

    public FaceRecognitionFragment() {
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
        faceRecognitionBinding = FragmentFaceRecognitionBinding.inflate(inflater, container, false);
        View view = faceRecognitionBinding.getRoot();
        faceRecognitionBinding.CollapseMenuContent.setVisibility(View.GONE);
        faceRecognitionBinding.CollapseMenuContent2.setVisibility(View.GONE);
        faceRecognitionBinding.CollapseMenuContent3.setVisibility(View.GONE);


        faceRecognitionBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (faceRecognitionBinding.CollapseMenuContent.getVisibility() == View.VISIBLE) {
                    faceRecognitionBinding.CollapseMenuContent.setVisibility(View.GONE);
                    faceRecognitionBinding.CollapseMenuContent2.setVisibility(View.GONE);
                    faceRecognitionBinding.CollapseMenuContent3.setVisibility(View.GONE);
                } else {
                    faceRecognitionBinding.CollapseMenuContent.setVisibility(View.VISIBLE);
                    faceRecognitionBinding.CollapseMenuContent2.setVisibility(View.VISIBLE);
                    faceRecognitionBinding.CollapseMenuContent3.setVisibility(View.VISIBLE);
                }
            }
        });


        return view;
    }
}