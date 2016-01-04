package app.bicintime.wolf.tryapp;

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

/**
 * Created by wolf on 1/3/2016.
 */
public class YellowFragment extends Fragment implements View.OnKeyListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yellow_fragment_layout, container, false);

        Button button = (Button) view.findViewById(R.id.button_yellow);

        Log.d("BACK", "Referencia de button");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("BACK", "Dentro del listener del boton");
                /*
                BlueFragment blueFragment = new BlueFragment();
                BlackFragment blackFragment = new BlackFragment();

                */

                BlueFragment blueFragment = new BlueFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(YellowFragment.class.getName());



                fragmentTransaction.replace(R.id.root_framelayout, blueFragment).commit();
                fragmentManager.executePendingTransactions();



            }
        });



        return view;
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
