package com.detroitlabs.trafficapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class EventArrayAdapter extends ArrayAdapter<Events>{

    private final Context mContext;

    public ArrayList<Events> thisWeeksEvents;


    public EventArrayAdapter(Context context, ArrayList<Events> thisWeeksEvents) {
        super(context, R.layout.event_item, thisWeeksEvents);
        Log.d("ViewCalled", "array started");
        this.mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisRow = inflater.inflate(R.layout.event_item, parent, false);
        Events thisObject = getItem(position);
       Log.d("ViewCalled", "view is called");
        thisWeeksEvents = ListViewFragment.mEventsArrayList;

        // Format logic
        String preTimeFormat = thisObject.getTime();
        String ampm = "am";
        Integer cutTimeFormat = Integer.parseInt(preTimeFormat.substring(0,2));
        System.out.println(cutTimeFormat);
        if (cutTimeFormat > 12) {
            cutTimeFormat -= 12;
            ampm = "pm";
        }
        String postTimeFormat = (cutTimeFormat + preTimeFormat.substring(2,5)+ ampm);

        String eventFormat = thisObject.getEventName();
        int maxEventLength = 60;
        if (eventFormat.length() > maxEventLength)
            eventFormat = (eventFormat.substring(0,maxEventLength)+"...");
        // End format logic
      //  Log.i("eventTime",thisObject.getEventDate().toString());

        TextView eventTitle = (TextView) thisRow.findViewById(R.id.event_text);
        eventTitle.setText(eventFormat);

        TextView eventTime = (TextView) thisRow.findViewById(R.id.event_time);
        eventTime.setText(postTimeFormat);

        return thisRow;

    }
}
