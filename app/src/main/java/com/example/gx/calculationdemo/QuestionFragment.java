package com.example.gx.calculationdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.gx.R;
import com.example.gx.databinding.FragmentQuestionBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentQuestionBinding binding;
    private StringBuilder mAnswer = new StringBuilder();

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_question, container, false);
        final MyViewModel myViewModel = ViewModelProviders.of(requireActivity(),
                new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(MyViewModel.class);
        myViewModel.generateNew();
        myViewModel.getCurrentScore().setValue(0);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);
        binding.button5.setOnClickListener(this);
        binding.button6.setOnClickListener(this);
        binding.button7.setOnClickListener(this);
        binding.button8.setOnClickListener(this);
        binding.button9.setOnClickListener(this);
        binding.button10.setOnClickListener(this);
        binding.button11.setOnClickListener(this);

        binding.button12.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View v) {
                if (mAnswer.length() == 0) {
                    mAnswer.append("-1");
                }
                if (Integer.valueOf(mAnswer.toString()).intValue() == myViewModel.getAnswer().getValue()) {
                    myViewModel.answerCorrect();
                    mAnswer.setLength(0);
                    binding.textView9.setText(getString(R.string.correct_answer ));
                } else {
                    NavController navController = Navigation.findNavController(v);
                    if (myViewModel.mWinFlag) {
                        navController.navigate(R.id.action_questionFragment_to_winFragment);
                        myViewModel.mWinFlag = false;
                        myViewModel.save();
                    } else {
                        navController.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mAnswer.append("1");
                break;
            case R.id.button2:
                mAnswer.append("2");
                break;
            case R.id.button3:
                mAnswer.append("3");
                break;
            case R.id.button4:
                mAnswer.append("4");
                break;
            case R.id.button5:
                mAnswer.append("5");
                break;
            case R.id.button6:
                mAnswer.append("6");
                break;
            case R.id.button7:
                mAnswer.append("7");
                break;
            case R.id.button8:
                mAnswer.append("8");
                break;
            case R.id.button9:
                mAnswer.append("9");
                break;
            case R.id.button10:
                mAnswer.setLength(0);
                break;
            case R.id.button11:
                mAnswer.append("0");
                break;
            default:
                break;
        }
        if (mAnswer.length() == 0) {
            binding.textView9.setText(getString(R.string.input_indicator));
        } else {
            binding.textView9.setText(mAnswer.toString());
        }
    }
}
