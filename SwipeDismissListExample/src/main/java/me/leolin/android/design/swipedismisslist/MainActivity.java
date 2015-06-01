package me.leolin.android.design.swipedismisslist;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListAdapter());
    }

    class ListAdapter extends BaseAdapter {
        List<String> data = new LinkedList<String>();

        public ListAdapter() {
            data.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"));
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.listitem, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

            final String text = data.get(position);
            viewHolder.textView.setText(text);

            final CardView cardView = viewHolder.cardView;
            final CoordinatorLayout coordinatorLayout = viewHolder.coordinatorLayout;
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) cardView.getLayoutParams();
            final SwipeDismissBehavior<CardView> behavior = new SwipeDismissBehavior();
            behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
            behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
                @Override
                public void onDismiss(final View view) {
                    data.remove(position);
                    notifyDataSetChanged();
                    Snackbar.make(listView, "Removed:" + text, Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    data.add(position, text);
                                    notifyDataSetChanged();
                                }
                            })
                            .show();
                }

                @Override
                public void onDragStateChanged(int i) {
                }
            });
            params.setBehavior(behavior);

            coordinatorLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return behavior.onTouchEvent(coordinatorLayout, cardView, event);
                }
            });

            return convertView;
        }

        class ViewHolder {

            CoordinatorLayout coordinatorLayout;
            CardView cardView;
            TextView textView;

            public ViewHolder(View convertView) {
                textView = (TextView) convertView.findViewById(R.id.textView);
                coordinatorLayout = (CoordinatorLayout) convertView.findViewById(R.id.coordinatorLayout);
                cardView = (CardView) convertView.findViewById(R.id.cardView);
            }
        }
    }


}
