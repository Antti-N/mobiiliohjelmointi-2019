package com.example.fragmentassignment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment {

    Button get;
    Button show;
    private ButtonClickListener listener;

    public interface ButtonClickListener {
        void onGetClicked();
        void onShowClicked();
    }

    public ButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_button, container, false);

        get = rootView.findViewById(R.id.btnGet);
        show = rootView.findViewById(R.id.btnShow);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGetClicked();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onShowClicked();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ButtonClickListener)
            listener = (ButtonClickListener)context;
        else
            throw new RuntimeException();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
