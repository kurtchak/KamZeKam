package org.blackbell.kamzekam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kurtcha on 8.10.2016.
 */

public class EventListFragment extends ListFragment {
    public static final String EVENT = "event";
    private EventListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.events_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new EventListFragment.EventListAdapter(getActivity(), R.layout.events_item, Content.events);
        setListAdapter(adapter);
    }

    private class EventListAdapter extends ArrayAdapter<Event> {

        private List<Event> items;

        public EventListAdapter(Context context, int textViewResourceId, List<Event> events) {
            super(context, textViewResourceId, events);
            this.items = events;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.events_item, null);
            }

            final Event event = items.get(position);

            TextView eventName = (TextView) v.findViewById(R.id.eventName);
            eventName.setText(event.getName());
            TextView eventDate = (TextView) v.findViewById(R.id.eventDate);
            eventDate.setText(event.getDate() + "  " + event.getTime());

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), EventDetailActivity.class);
                    Bundle bundle = null;
//                    intent.putExtra(EVENT, event);
                    intent.putExtra(Content.EVENT_POSITION, position);
                    startActivity(intent, bundle);
                }
            });
            return v;
        }
    }


}
