package com.example.android.cfcalculator.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cfcalculator.CustomDialogClass;
import com.example.android.cfcalculator.R;
import com.example.android.cfcalculator.RoomDBClasses.MeasurementDatabase;
import com.example.android.cfcalculator.RoomDBClasses.Measurements;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StandardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StandardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText len_ft;
    private EditText width_ft;
    private EditText height_ft;

    private EditText len_in;
    private EditText width_in;
    private EditText height_in;

    public static TextView cubicFeet;
    public static  TextView cubicInches;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<String> measurements;

    private OnFragmentInteractionListener mListener;

    public StandardFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StandardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StandardFragment newInstance(String param1, String param2) {
        StandardFragment fragment = new StandardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_standard, container, false);

        len_ft = v.findViewById(R.id.editText3);
        width_ft = v.findViewById(R.id.ft_width);
        height_ft = v.findViewById(R.id.ft_h);


        len_in = v.findViewById(R.id.len_in);
        width_in = v.findViewById(R.id.in_width);
        height_in = v.findViewById(R.id.in_h);

        cubicFeet = v.findViewById(R.id.finalR);
        cubicInches = v.findViewById(R.id.finalR_in);


        FloatingActionButton fab = v.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                double convertLenFt = 0;
                double convertWFt =0;
                double convertHFt =0;
                if(!(TextUtils.isEmpty(len_ft.getText().toString()) || TextUtils.isEmpty(width_ft.getText().toString()) || TextUtils.isEmpty(height_ft.getText().toString())))
                {
                    convertLenFt = Double.parseDouble(len_ft.getText().toString()) * 12;
                    convertWFt = Double.parseDouble(width_ft.getText().toString()) * 12;
                    convertHFt = Double.parseDouble(height_ft.getText().toString()) * 12;
                }
                else
                {
                    convertLenFt = 0;
                    convertWFt = 0;
                    convertHFt = 0;
                }



                    double totalIn = (Double.parseDouble(len_in.getText().toString()) + convertLenFt)
                            * (Double.parseDouble(width_in.getText().toString()) + convertWFt)
                            * (Double.parseDouble(height_in.getText().toString()) + convertHFt);


                    final double cubicMeasure = (double) Math.round((totalIn / 1728) * 1000) / 1000;

                    final double cubicInMeasure = (double) Math.round((totalIn) * 1000) / 1000;


                    Animation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setInterpolator(new DecelerateInterpolator());
                    fadeIn.setDuration(1000);

                    cubicInches.setAnimation(fadeIn);
                    cubicFeet.setAnimation(fadeIn);

                    cubicInches.setText(cubicInMeasure + " in.");
                    cubicFeet.setText(cubicMeasure + " ft.");
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.save:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.show();


                TextView ok = dialog.findViewById(R.id.ok_tv);
                TextView close = dialog.findViewById(R.id.close);
                final EditText ed = dialog.findViewById(R.id.measure_name);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        final MeasurementDatabase appDb = MeasurementDatabase.getDatabase(getActivity());


                        final Measurements m = new Measurements(
                                ed.getText().toString(),
                                null,
                                cubicFeet.getText().toString(),
                                cubicInches.getText().toString(),
                                null,
                                null
                        );

                        Completable.fromAction(() -> appDb.measurementDAO().insertMeasurements(m))
                                .subscribeOn(Schedulers.io())
                                .subscribe();

                        dialog.dismiss();
                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



                return true;

            case R.id.clear:
                if(len_ft.getText().toString().equals("0") && width_ft.getText().toString().equals("0")
                        && height_ft.getText().toString().equals("0") && len_in.getText().toString().equals("0")
                        && width_in.getText().toString().equals("0") && height_in.getText().toString().equals("0")) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Notice")
                            .setMessage("You have no information or results to clear")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }
                else
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Confirmation")
                            .setMessage("Are you sure you'd like to clear your input values, and result?")
                            .setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    len_ft = null;
                                    width_ft = null;
                                    height_ft = null;

                                    len_in = null;
                                    width_in = null;
                                    height_in = null;

                                    cubicInches.setText("0 in.");
                                    cubicFeet.setText("0 ft.");
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }


                return true;
        }
        return false;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
