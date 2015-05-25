package com.stef_developer.dailytask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stef_developer.dailytask.R;
import com.stef_developer.dailytask.view.NavigationMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsaqova on 5/25/15.
 */
public class NavigationAdapter extends ArrayAdapter<NavigationMenu> {

    private final Context context;
    private final List<NavigationMenu> modelsArrayList;

    public NavigationAdapter(Context context, List<NavigationMenu> modelsArrayList) {

        super(context, R.layout.navigation_item, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater

        View rowView = null;
        rowView = inflater.inflate(R.layout.navigation_item, parent, false);
        // 3. Get icon,title & counter views from the rowView
        ImageView imgView = (ImageView) rowView.findViewById(R.id.item_icon);
        TextView titleView = (TextView) rowView.findViewById(R.id.item_title);
        // 4. Set the text for textView
        imgView.setImageResource(modelsArrayList.get(position).getIcon());
        titleView.setText(modelsArrayList.get(position).getTitle());

        return rowView;
    }

}
