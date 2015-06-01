package me.leolin.android.design.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Leolin
 */
public class PagerFragment extends Fragment {
    static PagerFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(String.valueOf(getArguments().getInt("position")));
    }
}
