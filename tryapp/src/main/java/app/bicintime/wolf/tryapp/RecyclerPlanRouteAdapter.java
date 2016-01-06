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

//This class provides the view layout for the recycler view, using very specific methods and classes for it
public class RecyclerPlanRouteAdapter extends RecyclerView.Adapter<RecyclerPlanRouteAdapter.MyViewHolder> {//it has to use class made manually by me that extends ViewHolder

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList(); //On this array i will save the data coming from the fragment
    private ClickListener clickListener; //this is useful for the approach of implementing click listener on recyclerview using the slidenerd 2nd method approach
    private Context context; //i will need the context
    public final static String DEFAULT = "Start";

    RecyclerPlanRouteAdapter(Context context, List<Information> data){ //to initialize the adapter

        Log.d("RECYCLER", "Entering at RecylerPlanRouteAdapter constructor");

        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //onCreateViewHolder creates the view into java code for every single row

        View view = inflater.inflate(R.layout.custom_row, parent, false);
        Log.d("RECYCLER", "Entering at RecylerPlanRouteAdapter onCreateViewHolder");

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    //this method is tricky because of the recyclerview optimization model, it will be called every time
    //that a row gets visible on the recyclerview, that's why the word recycler, it gets only the references but don't creates
    //them from the beggining, avoiding calling onCreateViewHolder everytime which gives a lot of overload code
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) { //gives the holder reference which is a single row?? and its position
        Log.d("RECYCLER", "Entering at RecylerPlanRouteAdapter onBindViewHolder");

        Information current = data.get(position);

        //on this part i set the content to the recyclerview item
        holder.title.setText(current.title);
        holder.imageView1.setImageResource(current.iconid1);
        holder.imageView2.setImageResource(current.iconid2);
        holder.title2.setText(current.title2);

        if(position==0) { //if i only want to set the content for a single row, this code part can get improved a lot

            SharedPreferences sharedPreferences = ((MainActivity) context).getSharedPreferences("MyData", Context.MODE_PRIVATE); //for reading info from another screens or fragments
                                                                                                                        //also MyData is the file, and the mode means only this app can access it


            String data = sharedPreferences.getString("Plan_route_test", DEFAULT); //the default will be the textview text atribute (should be)

            Log.d("RECYCLER", "My value of Blue_editor_text: " + data);


            SharedPreferences.Editor editor = sharedPreferences.edit(); //I need an editor instance to make changes on the files

            holder.title2.setText(data);

            editor.remove("Plan_route_test");  //I have to remove it after using it so I could use it more times
            editor.commit();                    //must be commited to see the changes, otherwise nothing will happen.

        }

    }

    //similar to the view pager adapter getItemCount()
    @Override
    public int getItemCount() {
        return data.size();
    }

    //this method is created because i am using the approach proposed at the video of slidenerd recyclerview item click part 1
    public void setClickListener(RecyclerPlanRouteAdapter.ClickListener clickListener){

        this.clickListener = clickListener;



    }


    //As i commented before, this class must be crated and  it musts extends ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        TextView title;
        ImageView imageView1;
        ImageView imageView2;
        TextView title2;

        //In this method i am getting the references in java code from the xml layouts for every single item
        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.textview1);
            title2 = (TextView) itemView.findViewById(R.id.textview2);
            imageView1 = (ImageView) itemView.findViewById(R.id.img1);
            imageView2 = (ImageView) itemView.findViewById(R.id.img2);


        }

        //on this method I am processing what will happen if I click an items
        @Override
        public void onClick(View v) {

            if(clickListener!= null) {

                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager(); //WOW

                //SharedPreferences sharedPreferences = ((MainActivity) context).getSharedPreferences("MyData", Context.MODE_PRIVATE);

                //I want to open specific fragments in case of clicking specific rows so I am making use of the getAdapterPosition() to know what row I am clicking
                switch (getAdapterPosition()){
                    case 0: //simply open a new fragment

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
                    case 1:  //simply open a new fragment

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



    public interface ClickListener{ //my interface implemented because following the approach of slidenerd

        public void itemClicked(View view, int position);

    }


}
