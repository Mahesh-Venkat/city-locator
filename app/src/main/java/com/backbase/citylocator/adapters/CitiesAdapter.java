package com.backbase.citylocator.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.backbase.citylocator.R;
import com.backbase.citylocator.transferobjects.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<City> cityList;
    private List<City> cityListFiltered;
    private CitiesAdapter.OnItemClickListener onCityItemClickListener;

    public CitiesAdapter(Context mContext, List<City> mcityList) {
        this.context = mContext;
        this.cityList = mcityList;
        this.cityListFiltered = mcityList;
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

        final City city = cityListFiltered.get(actualPosition);

        String cityName = context.getResources().getString(R.string.label_city_name, city.getName(), city.getCountry());
        holder.textForTile.setText(cityName);
        holder.parentLayout.setContentDescription(cityName); // Making App functional for ADA

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCityItemClickListener.onItemClicked(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityListFiltered.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(City city);
    }

    public void setOnMainMenuItemClickListener(OnItemClickListener onItemClickListener) {
        this.onCityItemClickListener = onItemClickListener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence searchContent) {
                String searchText = searchContent.toString();

                if (searchText.isEmpty()) {
                    cityListFiltered = cityList;
                } else {
                    cityListFiltered = filterCities(searchText);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cityListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cityListFiltered = (ArrayList<City>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public List<City> filterCities(String searchText) {
        List<City> filteredList = new ArrayList<>();

        for (City city : cityList) {
            if (city.getName().toLowerCase().startsWith(searchText.toLowerCase())) {
                filteredList.add(city);
            }
        }

        return filteredList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parentLayout;
        TextView textForTile;

        ViewHolder(View view) {
            super(view);

            parentLayout = view.findViewById(R.id.constraintLayout_recyclerview_city_item_parent_layout);
            textForTile = view.findViewById(R.id.textview_recyclerview_city_name);
        }
    }
}

