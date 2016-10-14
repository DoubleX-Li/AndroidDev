package com.example.li.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {


    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private TextView apiLevel;
    private boolean mAnswerIsTrue;
    private boolean isAnswerShown = false;
    private int questionNumber;

    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.li.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.example.li.geoquiz.answer_shown";
    public static final String EXTRA_IS_ANSWER_SHOWN = "com.example.li.geoquiz.is_answer_shown";
    public static final String EXTRA_QUESTION_NUMBER = "com.example.li.geoquiz.question_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // 取出保存的isAnswerShown信息
        if (savedInstanceState != null) {
            isAnswerShown = savedInstanceState.getBoolean(EXTRA_IS_ANSWER_SHOWN);
        }

        // 取出MainActivity传入的选择,问题序号
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        questionNumber = getIntent().getIntExtra(EXTRA_QUESTION_NUMBER, 0);

        // findViewById
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        apiLevel = (TextView) findViewById(R.id.apiLevel);
        apiLevel.setText("API level " + Build.VERSION.SDK_INT);

        // 没按Cheat按钮的时候返回未作弊信息
        setAnswerShownResult(isAnswerShown);

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 取出传入的问题的中确答案，显示在屏幕上
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }

                // 按了Cheat按钮后返回作弊信息
                isAnswerShown = true;
                setAnswerShownResult(isAnswerShown);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXTRA_IS_ANSWER_SHOWN, isAnswerShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        data.putExtra(EXTRA_QUESTION_NUMBER, questionNumber);
        setResult(RESULT_OK, data);
    }
}
