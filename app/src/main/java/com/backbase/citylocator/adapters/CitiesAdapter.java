package com.backbase.citylocator.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.backbase.citylocator.R;
import com.backbase.citylocator.transferobjects.City;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {
    private Context context;
    private List<City> cityList;
    private CitiesAdapter.OnItemClickListener onCityItemClickListener;

    public CitiesAdapter(Context mContext, List<City> mcityList) {
        context = mContext;
        this.cityList = mcityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_city_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int actualPosition = holder.getAdapterPosition();

        final City city = cityList.get(actualPosition);

        String cityName = context.getResources().getString(R.string.label_city_name, city.getName(), city.getCountry());
        holder.textForTile.setText(cityName);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCityItemClickListener.onItemClicked(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(City city);
    }

    public void setOnMainMenuItemClickListener(OnItemClickListener onItemClickListener) {
        this.onCityItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parentLayout;
        TextView textForTile;

        ViewHolder(View view) {
            super(view);

            parentLayout = view.findViewById(R.id.constraintLayout_recyclerview_city_item_parent_layout);
            textForTile = (TextView) view.findViewById(R.id.textview_recyclerview_city_name);
        }
    }
}

