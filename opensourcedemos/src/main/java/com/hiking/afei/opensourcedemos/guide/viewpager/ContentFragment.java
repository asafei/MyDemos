package com.hiking.afei.opensourcedemos.guide.viewpager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hiking.afei.opensourcedemos.R;
import com.hiking.afei.opensourcedemos.guide.main.GuideMainActivity;

public class ContentFragment extends Fragment {
    private int[] bgRes={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3,R.drawable.guide_4};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_content, container, false);
        Button btn=(Button) view.findViewById(R.id.btn_viewpager_enter);
        RelativeLayout rl=(RelativeLayout) view.findViewById(R.id.rl_viewpager);


        int index=getArguments().getInt("index");
        rl.setBackgroundResource(bgRes[index]);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GuideMainActivity.class));
                getActivity().finish();
            }
        });

        btn.setVisibility(index==3?View.VISIBLE:View.GONE);
        return view;
    }

}
