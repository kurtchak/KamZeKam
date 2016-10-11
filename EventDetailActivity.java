package org.blackbell.kamzekam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import static org.blackbell.kamzekam.Content.events;

public class EventDetailActivity extends AppCompatActivity {

    private int position;
    private Event event;
    private TextView date;
    private TextView time;
    private TextView name;
    private TextView desc;
    private TextView place;
    private ImageView image;
    private ImageButton earlier;
    private ImageButton later;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Bundle bundle = getIntent().getExtras();
        position = getIntent().getIntExtra(Content.EVENT_POSITION, 0);
//        if (bundle.getSerializable(EventListFragment.EVENTS) == null) {
//            Log.e("Error", "No event given to detail screen");
//            finish();
//        }
//        Event event = (Event) getIntent().getSerializableExtra(EventListFragment.EVENT);
        event = events.get(position);
        date = (TextView) findViewById(R.id.date);
        date.setText(event.getDate());
        time = (TextView) findViewById(R.id.time);
        time.setText(event.getTime());
        name = (TextView) findViewById(R.id.name);
        name.setText(event.getName());
        place = (TextView) findViewById(R.id.place);
        place.setText(event.getPlace());
        desc = (TextView) findViewById(R.id.description);
        desc.setText(event.getDescription());

        Bitmap bitmap = null;
        try {
            bitmap = new DownloadImageTask().execute(event.getImage()).get();

            image = (ImageView) findViewById(R.id.image);
            image.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        earlier = (ImageButton) findViewById(R.id.earlier);
        earlier.setEnabled(position > 0);
        earlier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
                intent.putExtra(Content.EVENT_POSITION, position - 1);
                startActivity(intent);
            }
        });

        later = (ImageButton) findViewById(R.id.later);
        later.setEnabled(position < events.size() - 1);
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
                intent.putExtra(Content.EVENT_POSITION, position + 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int action = MotionEventCompat.getActionMasked(e);

        switch(action) {
            case (MotionEvent.ACTION_DOWN):
//                earlier.setVisibility(View.VISIBLE);
//                later.setVisibility(View.VISIBLE);
//                time.setVisibility(View.VISIBLE);
//                date.setText(event.getDate());
//                date.animate().translationXBy(50f).scaleYBy(5).setDuration(500);
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.i("ACTION", "MOVE");
                return true;
            case (MotionEvent.ACTION_UP):
//                earlier.setVisibility(View.GONE);
//                later.setVisibility(View.GONE);
//                time.setVisibility(View.GONE);
//                date.setText(event.getDate() + " " + event.getTime());
//                date.animate().translationXBy(-50f).scaleYBy(-5).setDuration(500);
                return true;
            case (MotionEvent.ACTION_CANCEL):
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                return true;
            default:
                return super.onTouchEvent(e);
        }
    }
}
