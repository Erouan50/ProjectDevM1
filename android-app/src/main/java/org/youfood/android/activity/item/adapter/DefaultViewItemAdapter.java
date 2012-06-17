package org.youfood.android.activity.item.adapter;

import android.view.View;
import android.widget.TextView;
import org.interakting.inlab.activity.ActivityManager;
import org.interakting.inlab.activity.IActivity;
import org.interakting.inlab.view.ViewUtils;

/**
 * User: GJean
 * Date: 17/06/12
 * Time: 03:41
 */
public abstract class DefaultViewItemAdapter {

    protected <T> T findViewById(Class<? extends T> klass, View view, int id) {
        return ViewUtils.getInstance().findViewById(klass, view, id);
    }

    public void updateText(TextView textView, String text) {
        if (null != text && null != textView) {
            if (text.startsWith("@string/")) {
                String value = text.substring("@string/".length());

                IActivity activity = (IActivity) ActivityManager.getInstance().getCurrentActivity();

                textView.setText(activity.getResource(value, "string"));
            } else {
                textView.setText(text);
            }
        }
    }


}
