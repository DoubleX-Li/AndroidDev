package com.example.li.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };  // 问题数组

    private int mCurrentIndex = 0;  // 保存当前问题序号
    private boolean[] mIsCheaterInQuestion = new boolean[mQuestionBank.length];

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final String IS_CHEATER = "is_cheater";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        getSupportActionBar().setSubtitle("Water Bodies");

        // findViewById
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mCheatButton = (Button) findViewById(R.id.cheat_button);

        // 设置监听
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 计算当前问题Id
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                updateQuestion();

                // 清除作弊信息
                for (boolean ischeater : mIsCheaterInQuestion) {
                    ischeater = false;
                }
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 计算当前问题Id
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                while (mCurrentIndex < 0) {
                    mCurrentIndex += mQuestionBank.length;
                }

                updateQuestion();

                // 清除作弊信息
                for (boolean ischeater : mIsCheaterInQuestion) {
                    ischeater = false;
                }
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                intent.putExtra(CheatActivity.EXTRA_QUESTION_NUMBER, mCurrentIndex);
                startActivityForResult(intent, 0);
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheaterInQuestion = savedInstanceState.getBooleanArray(IS_CHEATER);
        }

        updateQuestion();
    }

    // 调用onPause()、onStop()、onDestroy()方法前将当前问题序号，作弊信息保存
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBooleanArray(IS_CHEATER, mIsCheaterInQuestion);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        int number = data.getIntExtra(CheatActivity.EXTRA_QUESTION_NUMBER, 0);
        mIsCheaterInQuestion[number] = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    // 更新问题
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(question);
    }

    // 检查答案
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();

        int messageResId = 0;

        // 如果当前问题作弊，提示警告
        if (mIsCheaterInQuestion[mCurrentIndex]) {
            messageResId = R.string.judgment_toast;
        } else {
            // 否则提示选择是否正确
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
