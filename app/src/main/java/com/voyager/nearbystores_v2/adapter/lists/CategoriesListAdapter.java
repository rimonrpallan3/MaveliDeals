package com.voyager.nearbystores_v2.adapter.lists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.classes.Category;
import com.squareup.picasso.Picasso;

import java.util.List;



public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.mViewHolder> {


    private LayoutInflater infalter;
    private List<Category> data;
    private Context context;
    private ClickListener clickListener;






    public CategoriesListAdapter(Context context, List<Category> data){
        this.data = data;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CategoriesListAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = infalter.inflate(R.layout.item_category, parent, false);
       // TypefaceHelper.typeface(rootView);
        mViewHolder holder = new mViewHolder(rootView);

        return holder;
    }


    @Override
    public void onBindViewHolder(CategoriesListAdapter.mViewHolder holder, int position) {

        holder.name.setText(data.get(position).getNameCat());

        com.voyager.nearbystores_v2.utils.Utils.setFontBold(context,holder.name);

        if(data.get(position).getImages()!=null){

            Picasso.with(context)
                    .load(data.get(position).getImages().getUrl200_200())
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

    public Category getItem(int position){

        try{
            return  data.get(position);
        }catch (Exception e){
            return  null;
        }

    }




    public void addItem(Category item){

        int index = (data.size());
        data.add(item);
        //notifyItemInserted(index);
    }



    @Override
    public int getItemCount() {
        return data.size();
    }





    public  class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



        public TextView name;
        public ImageView image;


        public mViewHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.cat_name);
            image = (ImageView) itemView.findViewById(R.id.image);


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


    public void setClickListener(ClickListener clicklistener){

        this.clickListener = clicklistener;

    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }


}
