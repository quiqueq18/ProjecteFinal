package com.example.projectefinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.projectefinal.databinding.FragmentIniciBinding;

import java.util.ArrayList;

public class IniciFragment extends Fragment {
    private FragmentIniciBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIniciBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnEstadios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();

                NavHostFragment.findNavController(IniciFragment.this)
                        .navigate(R.id.action_iniciFragment_to_nav_mapas, args);
            }
        });

    }
}