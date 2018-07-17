package com.voyager.nearbystores_v2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.RetorHelper.Child;
import com.voyager.nearbystores_v2.RetorHelper.MainOffers;
import com.voyager.nearbystores_v2.views.IOfferFilterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rimon on 05-04-2018.
 */

public class OfferInnerChildListAdapter extends RecyclerView.Adapter<OfferInnerChildListAdapter.MyViewHolder> {


    IOfferFilterView iOfferFilterView;
    List<MainOffers> mainOffersList;
    List<MainOffers> childMainOffersList;

    public OfferInnerChildListAdapter(List<MainOffers> mainOffersList, IOfferFilterView iOfferFilterView) {
        this.mainOffersList = mainOffersList;
        this.iOfferFilterView = iOfferFilterView;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_item_list_inner_two_content, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MainOffers mainOffers = mainOffersList.get(position);
        holder.filterSubDetailsTitle.setText(mainOffers.getName());
        if (mainOffersList.size() > 0) {
            childMainOffersList = new ArrayList<>();
            Child child = mainOffers.getChild();
            childMainOffersList.add(child.getZeroOffer());
            childMainOffersList.add(child.getFirstOffer());
            childMainOffersList.add(child.getSecondOffer());
            childMainOffersList.add(child.getThirdOffer());
            childMainOffersList.add(child.getFourOffer());
            childMainOffersList.add(child.getFiveOffer());
            childMainOffersList.add(child.getSixOffer());
            childMainOffersList.add(child.getEightOffer());
            childMainOffersList.add(child.getNineOffer());
            childMainOffersList.add(child.getTenOffer());
            childMainOffersList.removeAll(Collections.singleton(null));

            if(!mainOffers.getChecked())
                iOfferFilterView.onItemInnerUncheck(mainOffers.getId_offertype());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //holder.checkBoxContentFileUpload.setChecked(!holder.checkBoxContentFileUpload.isChecked());
                    if ( holder.checkBox.isChecked()) {
                        System.out.println(mainOffers.getId_offertype());
                        iOfferFilterView.onItemInnerCheck(mainOffers.getId_offertype());
                    } else {
                        System.out.println(mainOffers.getId_offertype());
                        iOfferFilterView.onItemInnerUncheck(mainOffers.getId_offertype());
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mainOffersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView filterSubDetailsTitle;
        public TextView selectedDetailListTxt;
        public LinearLayout selectedDetailListLayout;
        public LinearLayout itemFilterClick;
        public CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            filterSubDetailsTitle = (TextView) view.findViewById(R.id.filterSubChildInnerDetailsTitle);
            selectedDetailListTxt = (TextView) view.findViewById(R.id.selectedChildInnerDetailListTxt);
            selectedDetailListLayout = (LinearLayout) view.findViewById(R.id.selectedChildInnerDetailListLayout);
            itemFilterClick = (LinearLayout) view.findViewById(R.id.itemChildInnerFilterClick);
            checkBox = (CheckBox) view.findViewById(R.id.checkInnerBox);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                /*if(clickListener!=null){
                    clickListener.itemClickedFilter(v,getPosition(),filterlists.getNumFilt());
                }*/
        }
    }
}

