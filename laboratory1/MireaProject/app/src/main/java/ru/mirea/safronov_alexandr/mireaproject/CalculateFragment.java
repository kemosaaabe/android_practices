package ru.mirea.safronov_alexandr.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onClickSum(View v) {
        EditText firstNum = getView().findViewById(R.id.firstNum);
        EditText secondNum = getView().findViewById(R.id.secondNum);
        TextView result = getView().findViewById(R.id.result);
        Integer num1 = Integer.valueOf(String.valueOf(firstNum.getText()));
        Integer num2 = Integer.valueOf(String.valueOf(secondNum.getText()));

        Integer sum = num1 + num2;
        String res = String.valueOf(sum);

        result.setText(res);
    }

    public void onClickMul(View v) {
        EditText firstNum = getView().findViewById(R.id.firstNum);
        EditText secondNum = getView().findViewById(R.id.secondNum);
        TextView result = getView().findViewById(R.id.result);
        Integer num1 = Integer.valueOf(String.valueOf(firstNum.getText()));
        Integer num2 = Integer.valueOf(String.valueOf(secondNum.getText()));

        Integer mul = num1 * num2;
        String res = String.valueOf(mul);

        result.setText(res);
    }

    public void onClickDiv(View v) {
        EditText firstNum = getView().findViewById(R.id.firstNum);
        EditText secondNum = getView().findViewById(R.id.secondNum);
        TextView result = getView().findViewById(R.id.result);
        Float num1 = Float.valueOf(String.valueOf(firstNum.getText()));
        Float num2 = Float.valueOf(String.valueOf(secondNum.getText()));

        Float div = num1 / num2;
        String res = String.valueOf(div);

        result.setText(res);
    }

    public void onClickSub(View v) {
        EditText firstNum = getView().findViewById(R.id.firstNum);
        EditText secondNum = getView().findViewById(R.id.secondNum);
        TextView result = getView().findViewById(R.id.result);
        Integer num1 = Integer.valueOf(String.valueOf(firstNum.getText()));
        Integer num2 = Integer.valueOf(String.valueOf(secondNum.getText()));

        Integer sub = num1 - num2;
        String res = String.valueOf(sub);

        result.setText(res);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        // Inflate the layout for this fragment
        Button addBtn = (Button)view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSum(v);
            }
        });

        Button subBtn = (Button)view.findViewById(R.id.subBtn);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSub(v);
            }
        });

        Button mulBtn = (Button)view.findViewById(R.id.mulBtn);
        mulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMul(v);
            }
        });

        Button divBtn = (Button)view.findViewById(R.id.divBtn);
        divBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDiv(v);
            }
        });

        return view;
    }
}