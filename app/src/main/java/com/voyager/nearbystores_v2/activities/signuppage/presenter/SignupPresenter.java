package com.voyager.nearbystores_v2.activities.signuppage.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.signuppage.view.ISignupView;

/**
 * Created by User on 20-Jul-18.
 */

public class SignupPresenter implements ISignupPresenter{

    Context context;
    ISignupView iSignupView;

    public SignupPresenter(Context context, ISignupView iSignupView) {
        this.context = context;
        this.iSignupView = iSignupView;
    }

    @Override
    public void setTermCondMsg(TextView v) {
        SpannableString ss = new SpannableString(context.getString(R.string.signup_term_cond));
        final ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(final View textView) {
                iSignupView.moveToTermsAndConductionPage();
            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.red));
                textPaint.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan,13,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        v.setText(ss);
        v.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
