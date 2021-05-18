package com.hebert.xmaslist;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import com.hebert.xmaslist.Gift.GiftActivity;
import com.hebert.xmaslist.data.ListDA;
import com.hebert.xmaslist.model.GiftList;


public class AddList extends DialogFragment {

    private Button button;
    private EditText listName;
    private GiftList giftList = new GiftList();

    private ListDA db = new ListDA();

    public AddList() {
        // Required empty public constructor
    }

    public static AddList newInstance() {
       AddList fragment = new AddList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 90% of the screen width
        window.setLayout((int) (size.x * 0.6), (int)(size.y * 0.5));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_list, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listName = view.findViewById(R.id.list_name);
        button = view.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                giftList.setName(listName.getText().toString());

                db.addNewList(giftList);
                Intent intent = new Intent(getActivity(), GiftActivity.class);
                startActivity(intent);
            }
        });
    }
}