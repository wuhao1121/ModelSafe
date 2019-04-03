package com.example.modelsafe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.entity.PopItem;
import com.example.modelsafe.R;

import java.util.ArrayList;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class popWindowsAdapter extends BaseAdapter {
    private ArrayList<PopItem> list = new ArrayList<>();
    private Context context;

    public popWindowsAdapter(ArrayList<PopItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PopItem item = (PopItem) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.popitem,null);
        TextView tv = convertView.findViewById(R.id.text);
        tv.setText(item.name);
        return convertView;
    }
}
