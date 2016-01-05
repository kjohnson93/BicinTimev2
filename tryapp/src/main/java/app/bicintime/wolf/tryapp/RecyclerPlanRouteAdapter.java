package app.bicintime.wolf.tryapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by wolf on 1/5/2016.
 */
public class RecyclerPlanRouteAdapter extends RecyclerView.Adapter<RecyclerPlanRouteAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    private ClickListener clickListener;
    private Context context;
    public final static String DEFAULT = "Start";

    RecyclerPlanRouteAdapter(Context context, List<Information> data){

        Log.d("RECYCLER", "Entering at RecylerPlanRouteAdapter constructor");

        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_row, parent, false);
        Log.d("RECYCLER", "Entering at RecylerPlanRouteAdapter onCreateViewHolder");

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("RECYCLER", "Entering at RecylerPlanRouteAdapter onBindViewHolder");

        Information current = data.get(position);

        holder.title.setText(current.title);
        holder.imageView1.setImageResource(current.iconid1);
        holder.imageView2.setImageResource(current.iconid2);
        holder.title2.setText(current.title2);

        if(position==0) {

            SharedPreferences sharedPreferences = ((MainActivity) context).getSharedPreferences("MyData", Context.MODE_PRIVATE);


            String data = sharedPreferences.getString("Plan_route_test", DEFAULT);

            Log.d("RECYCLER", "My value of Blue_editor_text: " + data);


            SharedPreferences.Editor editor = sharedPreferences.edit();

            holder.title2.setText(data);

            editor.remove("Plan_route_test");
            editor.commit();

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(RecyclerPlanRouteAdapter.ClickListener clickListener){

        this.clickListener = clickListener;



    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        TextView title;
        ImageView imageView1;
        ImageView imageView2;
        TextView title2;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.textview1);
            title2 = (TextView) itemView.findViewById(R.id.textview2);
            imageView1 = (ImageView) itemView.findViewById(R.id.img1);
            imageView2 = (ImageView) itemView.findViewById(R.id.img2);


        }


        @Override
        public void onClick(View v) {

            if(clickListener!= null) {

                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager(); //WOW

                //SharedPreferences sharedPreferences = ((MainActivity) context).getSharedPreferences("MyData", Context.MODE_PRIVATE);


                switch (getAdapterPosition()){
                    case 0:

                        Log.d("RECYCLER", "Entering onClick on ViewHolder managed by Fragment" + getAdapterPosition());




                        /* PETA NULLpointeR!!!
                        TextView textView = (TextView) frag.getActivity().findViewById(R.id.textview2);

                        textView.setText(data);

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.remove("Blue_editor_text");
                        editor.commit();
                        */

                        PlanRouteFragmentStartA planRouteFragmentStartA = new PlanRouteFragmentStartA();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack(PlanRouteFragment.class.getName());
                        fragmentTransaction.replace(R.id.root_framelayout, planRouteFragmentStartA).commit();
                        fragmentManager.executePendingTransactions();
                        break;
                    case 1:

                        Log.d("RECYCLER", "Entering onClick on ViewHolder managed by Fragment" + getAdapterPosition());

                        YellowFragment yellowFragment = new YellowFragment();
                        FragmentManager fragmentManager2 = ((MainActivity) context).getSupportFragmentManager(); //WOW
                        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction(); //cambiar luego
                        fragmentTransaction2.addToBackStack(YellowFragment.class.getName());
                        fragmentTransaction2.replace(R.id.root_framelayout, yellowFragment).commit();
                        fragmentManager.executePendingTransactions();
                        break;
                    default: break;



                }

            }


        }
    }



    public interface ClickListener{

        public void itemClicked(View view, int position);

    }


}
