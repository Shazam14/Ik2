package com.ikode.helper;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

/**
 * Created by viezara on 19/02/2016.
 *
 *
 */
public class ArticleBuilder extends SpannableStringBuilder {

    public ArticleBuilder append(CharSequence text, boolean newline, Object... spans) {
        int start = this.length();
        this.append(Html.fromHtml(text + "<br/>" + (newline ? "<br/>" : "")));
        for (Object span : spans) {
            this.setSpan(span, start, this.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return this;
    }
}
