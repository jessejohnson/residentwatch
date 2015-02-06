package com.jessejojojohnson.residentwatch.util;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.YoYo;
import com.jessejojojohnson.residentwatch.R;
import com.jessejojojohnson.residentwatch.ReportActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by odette on 2/6/15.
 */
public class TypeListItemAdapter extends RecyclerView.Adapter<TypeListItemAdapter.TypeViewHolder> {

    ArrayList<TypeListItem> typeListItems;

    public TypeListItemAdapter(ArrayList<TypeListItem> typeListItems) {
        this.typeListItems = typeListItems;
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        return new TypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TypeViewHolder typeViewHolder, int i) {
        TypeListItem typeListItem = typeListItems.get(i);
        typeViewHolder.typeName.setText(typeListItem.getName());
    }

    @Override
    public int getItemCount() {
        return typeListItems.size();
    }

    public static class TypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView typeName;

        public TypeViewHolder(View itemView) {
            super(itemView);
            typeName = (TextView) itemView.findViewById(R.id.typeName);
            Typeface mainFont = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
            typeName.setTypeface(mainFont);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            Intent intent = new Intent(itemView.getContext(), ReportActivity.class);
            intent.putExtra("name", typeName.getText().toString());
            itemView.getContext().startActivity(intent);
        }
    }
}
