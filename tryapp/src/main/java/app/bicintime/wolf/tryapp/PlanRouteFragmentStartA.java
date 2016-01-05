package app.bicintime.wolf.tryapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by wolf on 12/31/2015.
 */
public class PlanRouteFragmentStartA extends Fragment implements View.OnKeyListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_route_start_a, container, false);

        Button button = (Button) rootView.findViewById(R.id.buttonStartA);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("BACK", "Entrando a PlanRouteFragmentStartA");
                PlanRouteFragmentStartA2 planRouteFragmentStartA2 = new PlanRouteFragmentStartA2();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                //

                YellowFragment yellowFragment = new YellowFragment();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(PlanRouteFragmentStartA.class.getName());
                fragmentTransaction.replace(R.id.root_framelayout, yellowFragment).commit();
                fragmentManager.executePendingTransactions();

            }
        });

        Button button1 = (Button) rootView.findViewById(R.id.button_to_planroute);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText editText = (EditText) getActivity().findViewById(R.id.editText_to_planroute);

                String data = editText.getText().toString();

                Log.d("RECYCLER", "My value as input in sharedfragment test is : " + data);


                PlanRouteFragment planRouteFragment = new PlanRouteFragment();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Plan_route_test", data);
                editor.commit();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(PlanRouteFragmentStartA.class.getName());
                fragmentTransaction.replace(R.id.root_framelayout, planRouteFragment).commit();
                fragmentManager.executePendingTransactions();




            }
        });



        return rootView;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK )
        {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
            return true;
        }
        return false;
    }
}
