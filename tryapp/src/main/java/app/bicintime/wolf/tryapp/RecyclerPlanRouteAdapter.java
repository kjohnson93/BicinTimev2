package app.bicintime.wolf.tryapp;

import android.content.Context;
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

    RecyclerPlanRouteAdapter(Context context, List<Information> data){

        Log.d("RECYCLER", "Entering at RecylerPlanRouteAdapter constructor");

        inflater = LayoutInflater.from(context);
        this.data = data;

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

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{



        TextView title;
        ImageView imageView1;
        ImageView imageView2;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.textview1);
            imageView1 = (ImageView) itemView.findViewById(R.id.img1);
            imageView2 = (ImageView) itemView.findViewById(R.id.img2);


        }
    }
}
