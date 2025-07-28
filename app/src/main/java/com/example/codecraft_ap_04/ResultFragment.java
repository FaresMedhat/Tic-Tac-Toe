package com.example.codecraft_ap_04;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.codecraft_ap_04.databinding.FragmentResultBinding;


public class ResultFragment extends Fragment {



    public ResultFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentResultBinding resultBinding  =FragmentResultBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
       View view =resultBinding.getRoot();
       return  view;
    }

}