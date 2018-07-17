package com.voyager.nearbystores_v2.adapter.lists;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.voyager.nearbystores_v2.GPS.GPStracker;
import com.voyager.nearbystores_v2.GPS.Position;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.MainActivity;
import com.voyager.nearbystores_v2.classes.Event;
import com.voyager.nearbystores_v2.utils.DateUtils;
import com.voyager.nearbystores_v2.utils.Utils;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.voyager.nearbystores_v2.appconfig.AppConfig.APP_DEBUG;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.mViewHolder> {


    private LayoutInflater infalter;
    private List<Event> data;
    private Context context;
    private ClickListener clickListener;



    public EventListAdapter(Context context, List<Event> data){
        this.data = data;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public EventListAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = infalter.inflate(R.layout.item_event_custom, parent, false);
        TypefaceHelper.typeface(rootView);
        mViewHolder holder = new mViewHolder(rootView);

        return holder;
    }



    @Override
    public void onBindViewHolder(EventListAdapter.mViewHolder holder, int position) {




        int size = (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);

        if (Configuration.SCREENLAYOUT_SIZE_XLARGE == size) {

            holder.image.getLayoutParams().height = (int) (MainActivity.width / 2.5);
        } else {
            holder.image.getLayoutParams().height = (int) (MainActivity.width / 1.5);
        }


        if(this.data.get(position).getListImages()!=null && data.get(position).getListImages().size()>0) {

            if(APP_DEBUG) {
                Log.e("image",data.get(position).getListImages()
                    .get(0).getUrl500_500());

            }

                Picasso.with(context)
                        .load(this.data.get(position).getListImages()
                                .get(0).getUrl500_500())
                        .fit().centerCrop().placeholder(R.drawable.def_logo)
                        .into(holder.image);

        }else {
            Picasso.with(context).load(R.drawable.def_logo).into(holder.image);
        }

        if(this.data.get(position).getListImages()==null)
            if(data.get(position).getType()==1 && data.get(position).getType()==2){
                holder.image.setImageResource(R.drawable.def_logo);
            }else if(data.get(position).getType()==3){
                holder.image.setImageResource(R.drawable.def_logo);
            }


        if(this.data.get(position).getListImages().size()>0){

            Picasso.with(context)
                    .load(this.data.get(position).getListImages().get(0).getUrl500_500())
                    .into(holder.image);
        }


        holder.name.setText(data.get(position).getName());

        holder.address.setText(
                String.format(context.getString(R.string.FromTo),
                        DateUtils.getDateByTimeZone(data.get(position).getDateB(),"dd MMMM yyyy"),
                        DateUtils.getDateByTimeZone(data.get(position).getDateE(),"dd MMMM yyyy")
                )
        );

        holder.distance.setText(
                Utils.preparDistance(data.get(position).getDistance())+" "+
                Utils.getDistanceBy(data.get(position).getDistance())
        );

        GPStracker gps = new GPStracker(context);
        if(gps.canGetLocation()){
            Position pos = new Position();
            Double distance  = pos.distance(
                    data.get(position).getLat(),data.get(position).getLng(),
                    gps.getLatitude(),
                    gps.getLongitude()
            );

            holder.distance.setText(
                    Utils.preparDistance(distance)+" "+
                            Utils.getDistanceBy(distance)
            );
        }


        Utils.setFont(context,holder.name,"");

    }



    public void removeAll(){
        int size = this.data.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.data.remove(0);
            }

            if(size>0)
            this.notifyItemRangeRemoved(0, size);


        }



    }

    public Event getItem(int position){

        try{
            return  data.get(position);
        }catch (Exception e){
            return  null;
        }

    }




    public void addItem(Event item){

        int index = (data.size());
        data.add(item);
        //notifyItemInserted(index);
    }



    @Override
    public int getItemCount() {
        return data.size();
    }





    public  class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ImageView image;
        public TextView name;
        public TextView address;
        public TextView distance;
        public ImageView location;



        public mViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            location = (ImageView) itemView.findViewById(R.id.location);
            name = (TextView) itemView.findViewById(R.id.name);
            address  = (TextView) itemView.findViewById(R.id.address);
            distance  = (TextView) itemView.findViewById(R.id.distance);


            itemView.setOnClickListener(this);

            //HIDE / SHOW THE COMMENT BTN
            location.setVisibility(View.GONE);
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*final CustomBaseDialog dialog = new CustomBaseDialog(context,4);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(true);*/

                    clickListener.iconImageViewOnClick(v,getPosition());
                }
            });
        }


        @Override
        public void onClick(View v) {


            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }

            //delete(getPosition());


        }
    }


    public void setClickListener(ClickListener clicklistener){

        this.clickListener = clicklistener;

    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
        public void iconImageViewOnClick(View v, int position);
    }


}
