package com.hebert.xmaslist;

import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.hebert.xmaslist.adapter.IconRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddIcon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddIcon extends DialogFragment {

    private ArrayList<String> imageURLs = new ArrayList<>();

    public AddIcon() {
        // Required empty public constructor
    }

    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 90% of the screen width
        window.setLayout((int) (size.x * 1), (int)(size.y * 0.75));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }


    public static AddIcon newInstance() {
        AddIcon fragment = new AddIcon();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImageBitmaps();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_icon, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView recyclerView = view.findViewById(R.id.icon_recyclerView);

        initImageBitmaps();
        initiateRecyclerView(recyclerView);
    }


    private void initImageBitmaps() {
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://www.onlygfx.com/wp-content/uploads/2017/12/grunge-yes-no-icon-1.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://www.onlygfx.com/wp-content/uploads/2017/12/grunge-yes-no-icon-1.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://www.onlygfx.com/wp-content/uploads/2017/12/grunge-yes-no-icon-1.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://www.onlygfx.com/wp-content/uploads/2017/12/grunge-yes-no-icon-1.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/en/thumb/e/e0/WPVG_icon_2016.svg/1024px-WPVG_icon_2016.svg.png");
        imageURLs.add("https://www.onlygfx.com/wp-content/uploads/2017/12/grunge-yes-no-icon-1.png");
    }

    private void initiateRecyclerView(RecyclerView recyclerView) {
        IconRecyclerViewAdapter adapter = new IconRecyclerViewAdapter(getContext(), imageURLs);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }



}