package me.leolin.android.design.swipedismiss;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        cardView = (CardView) findViewById(R.id.cardView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) cardView.getLayoutParams();

        final SwipeDismissBehavior<CardView> behavior = new SwipeDismissBehavior();
        behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
        behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(final View view) {
                Snackbar.make(coordinatorLayout, "Done", Snackbar.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onDragStateChanged(int i) {
            }
        });
        params.setBehavior(behavior);

        cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return behavior.onTouchEvent(coordinatorLayout, cardView, event);
            }
        });
    }


}
