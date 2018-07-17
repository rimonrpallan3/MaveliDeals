package com.voyager.nearbystores_v2.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.RetorHelper.Child;
import com.voyager.nearbystores_v2.RetorHelper.MainOffers;
import com.voyager.nearbystores_v2.custom.SmoothCheckBox;
import com.voyager.nearbystores_v2.views.IOfferFilterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rimon on 05-04-2018.
 */

public class OfferFilterListAdapter extends RecyclerView.Adapter<OfferFilterListAdapter.MyViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    IOfferFilterView iOfferFilterView;
    List<MainOffers> mainOffersList;
    List<MainOffers> childMainOffersList;
    Activity activity;
    private CardView headerView, footerView;

    public OfferFilterListAdapter(List<MainOffers> mainOffersList, IOfferFilterView iOfferFilterView, Activity activity) {
        this.mainOffersList = mainOffersList;
        this.iOfferFilterView = iOfferFilterView;
        this.activity = activity;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_item_list_content, parent, false);
        return new MyViewHolder(itemView);

    /*    if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item_list_content, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            //Inflating header_layout view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent, false);
            return new HeaderViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_layout, parent, false);
            return new FooterViewHolder(itemView);
        } else return null;*/
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) holder.itemFilterClick.getLayoutParams();
        if (mainOffersList.size() == (position + 1)) {
            mlp.setMargins(0, 0, 0, (int) activity.getResources().getDimension(R.dimen._80));
        } else {
            mlp.setMargins(0, 0, 0, 0);
        }

        final Drawable rightDrawable = new IconicsDrawable(activity)
                .icon(CommunityMaterial.Icon.cmd_menu_right)
                .color(ResourcesCompat.getColor(activity.getResources(),R.color.iconColor,null))
                .sizeDp(16);

        holder.innerArrayList.setImageDrawable(rightDrawable);
        final MainOffers mainOffers = mainOffersList.get(position);
        Child child = mainOffers.getChild();
        if(child!=null){
            holder.innerArrayList.setVisibility(View.VISIBLE);
        }else {
            holder.innerArrayList.setVisibility(View.GONE);
        }
        holder.filterSubDetailsTitle.setText(mainOffers.getName());
        if (mainOffersList.size() > 0) {
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //holder.checkBoxContentFileUpload.setChecked(!holder.checkBoxContentFileUpload.isChecked());
                    if ( holder.checkBox.isChecked()) {
                        iOfferFilterView.onItemCheck(mainOffers.getId_offertype());
                    } else {
                        iOfferFilterView.onItemUncheck(mainOffers.getId_offertype());
                    }
                }
            });

            holder.itemFilterClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    childMainOffersList = new ArrayList<>();
                    final Child child = mainOffers.getChild();
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
                    iOfferFilterView.setChildList(childMainOffersList);
                }
            });

        
       /* if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.headerTitle.setText("Header View");
            headerHolder.headerTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "You clicked at Header View!", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.footerText.setText("Footer View");
            footerHolder.footerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "You clicked at Footer View", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof ItemViewHolder) {
            final ItemViewHolder holder = (ItemViewHolder) holder;

            final MainOffers mainOffers = mainOffersList.get(position);
            Child child = mainOffers.getChild();
            if(child.getZeroOffer()!=null){
                holder.innerArrayList.setVisibility(View.VISIBLE);
            }else {
                holder.innerArrayList.setVisibility(View.GONE);
            }
            holder.filterSubDetailsTitle.setText(mainOffers.getName());
            if (mainOffersList.size() > 0) {
                holder.itemFilterClick.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        iOfferFilterView.setFilterListRecycle(v, position, Integer.parseInt(mainOffers.getId_offertype()));
                        return false;
                    }
                });

                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //holder.checkBoxContentFileUpload.setChecked(!holder.checkBoxContentFileUpload.isChecked());
                        if ( holder.checkBox.isChecked()) {
                            iOfferFilterView.onItemCheck(mainOffers.getId_offertype());
                        } else {
                            iOfferFilterView.onItemUncheck(mainOffers.getId_offertype());
                        }
                    }
                });

                holder.innerArrayList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        childMainOffersList = new ArrayList<>();
                        final Child child = mainOffers.getChild();
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
                        child.setMainOffersList(childMainOffersList);
                        iOfferFilterView.setChildList(child.getMainOffersList());
                    }
                });

                System.out.println("-----------OfferFilterListAdapter onBindViewHolder childMainOffersList ");
                holder.itemFilterClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });

            }*/
            
            
            
        }


        
    }

   /* @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == mainOffersList.size() + 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }*/

    @Override
    public int getItemCount() {
        return mainOffersList.size();
    }


  /*  private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;

        public HeaderViewHolder(View view) {
            super(view);
           // headerTitle = (TextView) view.findViewById(R.id.header_text);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView footerText;

        public FooterViewHolder(View view) {
            super(view);
            //footerText = (TextView) view.findViewById(R.id.footer_text);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView filterSubDetailsTitle;
        public TextView selectedDetailListTxt;
        public LinearLayout selectedDetailListLayout;
        public LinearLayout itemFilterClick;
        public ImageView innerArrayList;
        public CheckBox checkBox;

        public ItemViewHolder(View view) {
            super(view);
            filterSubDetailsTitle = (TextView) view.findViewById(R.id.filterSubDetailsTitle);
            selectedDetailListTxt = (TextView) view.findViewById(R.id.selectedDetailListTxt);
            innerArrayList = (ImageView) view.findViewById(R.id.innerArrayList);
            selectedDetailListLayout = (LinearLayout) view.findViewById(R.id.selectedDetailListLayout);
            itemFilterClick = (LinearLayout) view.findViewById(R.id.itemFilterClick);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView filterSubDetailsTitle;
        public TextView selectedDetailListTxt;
        public LinearLayout selectedDetailListLayout;
        public LinearLayout itemFilterClick;
        public ImageView innerArrayList;
        public CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            filterSubDetailsTitle = (TextView) view.findViewById(R.id.filterSubDetailsTitle);
            selectedDetailListTxt = (TextView) view.findViewById(R.id.selectedDetailListTxt);
            innerArrayList = (ImageView) view.findViewById(R.id.innerArrayList);
            selectedDetailListLayout = (LinearLayout) view.findViewById(R.id.selectedDetailListLayout);
            itemFilterClick = (LinearLayout) view.findViewById(R.id.itemFilterClick);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
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

