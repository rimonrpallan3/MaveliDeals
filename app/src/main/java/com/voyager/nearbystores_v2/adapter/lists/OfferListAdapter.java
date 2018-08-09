package com.voyager.nearbystores_v2.adapter.lists;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.MainActivity;
import com.voyager.nearbystores_v2.classes.Offer;
import com.voyager.nearbystores_v2.utils.DateUtils;
import com.voyager.nearbystores_v2.utils.OfferUtils;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.mViewHolder> {


    private LayoutInflater infalter;
    private List<Offer> data;
    private Context context;
    private ClickListener clickListener;
    private List<Offer> expiredDateList = new ArrayList<>();




    public OfferListAdapter(Context context, List<Offer> data) throws ParseException {
        this.data = data;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public OfferListAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = infalter.inflate(R.layout.fragment_offer_custom_item_v2, parent, false);

        TypefaceHelper.typeface(rootView);

        mViewHolder holder = new mViewHolder(rootView);

        return holder;
    }



    @Override
    public void onBindViewHolder(final OfferListAdapter.mViewHolder holder,final int position) {


            DateUtils.getDateByTimeZone(data.get(position).getDate_end(), "dd-MM-yyyy");
            int size = (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);

            if (Configuration.SCREENLAYOUT_SIZE_XLARGE == size) {
                holder.image.getLayoutParams().height = (int) (MainActivity.width / 2.8);
            } else {
                holder.image.getLayoutParams().height = (int) (MainActivity.width / 1.8);
            }

            if (data.get(position).getContent() != null) {

                if (data.get(position).getContent().getPercent() > 0 || data.get(position).getContent().getPercent() < 0) {
                    DecimalFormat decimalFormat = new DecimalFormat("#0");
                    holder.offer.setText(decimalFormat.format(data.get(position).getContent().getPercent()) + "%");
                    holder.offerPercentage.setVisibility(View.GONE);
                    holder.priceCutLayout.setVisibility(View.GONE);
                } else {
                    if (data.get(position).getContent().getPrice() != 0) {
                        holder.offerPercentage.setVisibility(View.VISIBLE);
                        holder.priceCutLayout.setVisibility(View.VISIBLE);
                        DecimalFormat precision = new DecimalFormat("0.00");
                        System.out.println("OfferListAdapter onBindViewHolder price : " + data.get(position).getContent().getPrice());
                        System.out.println("OfferListAdapter onBindViewHolder Actual : " + data.get(position).getContent().getActualPrice());
                        holder.offer.setText(
                                OfferUtils.parseCurrencyFormat(
                                        data.get(position).getContent().getPrice(),
                                        data.get(position).getContent().getCurrency()));
                        //holder.offer.setText(String.format("%.2f", data.get(position).getContent().getPrice()));
                        Drawable priceCutImg = new IconicsDrawable(context)
                                .icon(CommunityMaterial.Icon.cmd_close)
                                .color(ResourcesCompat.getColor(context.getResources(), R.color.white, null))
                                .sizeDp(1);

                        DecimalFormat decimalFormat = new DecimalFormat("#0");
                        //holder.offerMainPrice.setText(String.format("%.0f", data.get(position).getContent().getActualPrice()));
                        holder.offerMainPrice.setText(
                                OfferUtils.parseCurrencyFormat(
                                        data.get(position).getContent().getActualPrice(),
                                        data.get(position).getContent().getCurrency()
                                ));
                        holder.offerPercentage.setText(decimalFormat.format(data.get(position).getContent().getOfferPercent()) + "%");
                        holder.offerPriceImgCut.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.price_cut_draw));
                        holder.offerPriceImgCut.setScaleType(ImageView.ScaleType.FIT_XY);

                    } else {
                        holder.offer.setText(context.getString(R.string.promo));
                    }
                }

            } else {
                Picasso.with(context).load(R.drawable.def_logo).into(holder.image);
            }


            String symbole = com.voyager.nearbystores_v2.utils.Utils.getDistanceBy(
                    data.get(position).getDistance()
            );
            String distance = com.voyager.nearbystores_v2.utils.Utils.preparDistance(
                    data.get(position).getDistance()
            );

            holder.distance.setText(
                    String.format(context.getString(R.string.offerIn), distance + " " + symbole.toUpperCase())
            );

            holder.name.setText(data.get(position).getName());
            holder.description.setText(data.get(position).getStore_name());

            Drawable locationDrawable = new IconicsDrawable(context)
                .icon(CommunityMaterial.Icon.cmd_map_marker)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.iconColor, null))
                .sizeDp(12);

            holder.description.setCompoundDrawables(locationDrawable, null, null, null);
            holder.description.setCompoundDrawablePadding(14);

            if (data.get(position).getImages() != null) {
                System.out.println(" Url Image");
                /*Picasso.with(context).load(data.get(position).getImages().getUrl100_100())
                        .fit().centerCrop()
                        .into(holder.image);*/
                Picasso.with(context)
                        .load(data.get(position).getImages().getUrl500_500())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .resize(0, 200)
                        .into(holder.image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(context)
                                        .load(data.get(position).getImages().getUrl500_500())
                                        .error(R.drawable.def_logo)
                                        .resize(0, 200)
                                        .into(holder.image, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                            }
                                        });
                            }
                        });

            } else {
                System.out.println(" deafult Image");
                Picasso.with(context).load(R.drawable.def_logo)
                        .fit().centerCrop().into(holder.image);
            }

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


  /*  public static boolean CheckDates(String startDate,String endDate)   {
        DateUtils.getDateByTimeZone()
        boolean b = false;
        SimpleDateFormat dfDate  = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(dfDate.parse(startDate).before(dfDate.parse(endDate)))
            {
                b = true;//If start date is before end date
            }
            else if(dfDate.parse(startDate).equals(dfDate.parse(endDate)))
            {
                b = true;//If two dates are equal
            }
            else
            {
                b = false; //If start date is after the end date
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }*/

    public Offer getItem(int position){

        try{
            return  data.get(position);
        }catch (Exception e){
            return  null;
        }

    }




    public void addItem(Offer item){

        int index = (data.size());
        data.add(item);
        notifyDataSetChanged();
        //notifyItemInserted(index);
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(ClickListener clicklistener) {

        this.clickListener = clicklistener;

    }


    public interface ClickListener {
        void itemClicked(View view, int position);

        void iconImageViewOnClick(View v, int position);
    }

    public  class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ImageView image;
        public ImageView offerPriceImgCut;
        public TextView name;
        public TextView description;
        public TextView distance;
        public TextView offer;
        public TextView offerMainPrice;
        public TextView offerPercentage;
        public FrameLayout priceCutLayout;


        public mViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            offerPriceImgCut = (ImageView) itemView.findViewById(R.id.offerPriceImgCut);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.address);
            distance = (TextView) itemView.findViewById(R.id.distance);
            offer = (TextView) itemView.findViewById(R.id.offer);
            offerMainPrice = (TextView) itemView.findViewById(R.id.offerMainPrice);
            offerPercentage = (TextView) itemView.findViewById(R.id.offerPercentage);
            priceCutLayout = (FrameLayout) itemView.findViewById(R.id.priceCutLayout);


            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {


            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }

            //delete(getPosition());


        }
    }


}
