package com.example.android.cfcalculator.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

import com.example.android.cfcalculator.R;
import com.example.android.cfcalculator.RoomDBClasses.MeasurementDatabase;
import com.example.android.cfcalculator.RoomDBClasses.Measurements;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MetersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MetersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MetersFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MetersFragment() {
        // Required empty public constructor
    }

    EditText lengthMeters;
    EditText lengthCM;

    //Handles the widths for centimeters and meters
    EditText widthMeters;
    EditText widthCM;

    //Handles the height for centimeters and meters
    EditText heightMeters ;
    EditText heightCM;

    TextView metersResult;
    TextView centimetersResult ;

    double totalM;
    double totalCM;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MetersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MetersFragment newInstance(String param1, String param2) {
        MetersFragment fragment = new MetersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_meters, container, false);
        //Handles the lengths for centimeters and meters

        setHasOptionsMenu(true);
        lengthMeters = v.findViewById(R.id.editText3_m);
        lengthCM = v.findViewById(R.id.len_in_m);

        //Handles the widths for centimeters and meters
        widthMeters = v.findViewById(R.id.ft_width_m);
        widthCM = v.findViewById(R.id.in_width_m);

        //Handles the height for centimeters and meters
        heightMeters = v.findViewById(R.id.ft_h_m);
        heightCM = v.findViewById(R.id.in_h_m);

        metersResult = v.findViewById(R.id.meters_result);
        centimetersResult = v.findViewById(R.id.centimeters_result);



        FloatingActionButton fab = v.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                double lenM_to_Cm = 0;
                double widthM_to_CM = 0;
                double heightM_to_CM = 0;
                double lenCM = 0;
                totalCM = 0;

                //Converts each meters value into centimeters, and combines the two values
                if((TextUtils.isEmpty(lengthMeters.getText().toString()) || TextUtils.isEmpty(widthMeters.getText().toString()) || TextUtils.isEmpty(heightMeters.getText().toString())))
                {
                    lenM_to_Cm = 0;
                    widthM_to_CM = 0;
                    heightM_to_CM = 0;

                    lenCM = (Double.parseDouble(lengthCM.getText().toString()) * Double.parseDouble(widthCM.getText().toString()) * Double.parseDouble(heightCM.getText().toString()));
                    totalCM = (lenCM) / 1000000;
                    centimetersResult.setText(""+totalCM);

                }
                else
                {
                     lenM_to_Cm = ((Double.parseDouble(lengthMeters.getText().toString()) * 100) + Double.parseDouble(lengthCM.getText().toString()));
                     widthM_to_CM = ((Double.parseDouble(widthMeters.getText().toString()) * 100) + Double.parseDouble(widthCM.getText().toString()));
                     heightM_to_CM = ((Double.parseDouble(heightMeters.getText().toString()) * 100) + Double.parseDouble(heightCM.getText().toString()));

                     totalM = (Integer.parseInt(lengthMeters.getText().toString())) * (Integer.parseInt(widthMeters.getText().toString()))
                            * (Integer.parseInt(heightMeters.getText().toString()));
                     lenCM = (Double.parseDouble(lengthCM.getText().toString()) * Double.parseDouble(widthCM.getText().toString()) * Double.parseDouble(heightCM.getText().toString()));
                     totalCM = (lenCM) / 1000000;

                     centimetersResult.setText(""+totalCM);
                     metersResult.setText(""+totalM);
                }

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(1000);
            }
        });

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
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.save:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");

                if(prev != null)
                {
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

                ok.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        final MeasurementDatabase appDb = MeasurementDatabase.getDatabase(getActivity());

                        final Measurements m = new Measurements(null,
                                ed.getText().toString(), null, null,
                                metersResult.getText().toString(),
                                centimetersResult.getText().toString());

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
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
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
