package com.jessejojojohnson.residentwatch.fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.jessejojojohnson.residentwatch.R;
import com.jessejojojohnson.residentwatch.SelectTypeActivity;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardingFragment extends Fragment {

    public OnboardingFragment() {
        // Required empty public constructor
    }

    public static OnboardingFragment newInstance(String heading, String text, Boolean hasButton){
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putString("heading", heading);
        args.putString("text", text);
        args.putBoolean("button", hasButton);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        TextView textViewHeading = (TextView) rootView.findViewById(R.id.slideHead);
        TextView textView = (TextView) rootView.findViewById(R.id.slideText);
        Bundle icicle = getArguments();
        textViewHeading.setText(icicle.getString("heading"));
        textView.setText(icicle.getString("text"));
        Typeface mainFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        textViewHeading.setTypeface(mainFont);
        textView.setTypeface(mainFont);

        if(icicle.getBoolean("button")){
//            ButtonRectangle getStarted = (ButtonRectangle) rootView.findViewById(R.id.getstarted);
            Button getStarted = (Button) rootView.findViewById(R.id.getstarted);
            getStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(rootView.getContext(), SelectTypeActivity.class));
                }
            });
        }
        return rootView;
    }
}
