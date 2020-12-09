package com.example.photofastfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.photofastfinal.R;

import java.util.List;

import Cards.Cards;

public class arrayAdapter extends ArrayAdapter<Cards> {

    Context context;

    public arrayAdapter(Context context, int resourceId, List<Cards> items){
        super(context, resourceId, items);
    }

        public View getView(int position, View convertView, ViewGroup parent){
            Cards card_item = getItem(position);

            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
            }

            TextView name = convertView.findViewById(R.id.name);
            ImageView image = convertView.findViewById(R.id.image);

            name.setText(card_item.getName());
            switch (card_item.getProfileImagemUrl()){
                case "default":
                    image.setImageResource(R.mipmap.ic_baseline_person_24);
                    break;
                default:
                    Glide.clear(image);
                    Glide.with(convertView.getContext()).load(card_item.getProfileImagemUrl()).into(image);
                break;
            }

        return convertView;
    }
}
