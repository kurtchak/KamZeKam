package org.blackbell.kamzekam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
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
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        mDetector = new GestureDetectorCompat(this, new GestureListener());

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
        this.mDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 25;
        private static final int SWIPE_VELOCITY_THRESHOLD = 25;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
        Log.i("GESTURE", "Swipe Right");
        if (position > 0) {
            Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
            intent.putExtra(Content.EVENT_POSITION, position - 1);
            startActivity(intent);
            finish();
        }
    }

    public void onSwipeLeft() {
        Log.i("GESTURE", "Swipe Left");
        if (position < Content.events.size() - 1) {
            Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
            intent.putExtra(Content.EVENT_POSITION, position + 1);
            startActivity(intent);
            finish();
        }
    }

    public void onSwipeTop() {
        Log.i("GESTURE", "Swipe Top");
        //TODO:
    }

    public void onSwipeBottom() {
        Log.i("GESTURE", "Swipe Bottom");
        //TODO:
    }

}
