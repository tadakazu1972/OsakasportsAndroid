package tadakazu1972.osakasports;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tadakazu on 2017/07/17.
 */

public class EventDataAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<EventData> eventDataList;

    public EventDataAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setEventDataList(ArrayList<EventData> eventDataList){
        this.eventDataList = eventDataList;
    }

    @Override
    public int getCount(){
        return eventDataList.size();
    }

    @Override
    public Object getItem(int position){
        return eventDataList.get(position);
    }

    @Override
    public long getItemId(int position){
        return eventDataList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = layoutInflater.inflate(R.layout.listview_eventdata, parent, false);

        ((TextView)convertView.findViewById(R.id.eventDate)).setText(eventDataList.get(position).date);
        ((TextView)convertView.findViewById(R.id.eventName)).setText(eventDataList.get(position).name);
        ((TextView)convertView.findViewById(R.id.eventContent)).setText(eventDataList.get(position).top);

        //動的に背景色をつける　クラスの値を操作しておくこと
        if (eventDataList.get(position).color){
            convertView.setBackgroundColor(Color.rgb(248, 168, 133));
        }

        return convertView;
    }
}
