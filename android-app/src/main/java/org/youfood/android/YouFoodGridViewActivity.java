package org.youfood.android;

import android.widget.AdapterView;
import org.interakting.inlab.activity.collection.grid.GridViewActivity;

/**
 * User: GJean
 * Date: 16/06/12
 * Time: 18:56
 */
public abstract class YouFoodGridViewActivity extends GridViewActivity {

    public YouFoodGridViewActivity(int viewId) {
        super(viewId);

    }

    @Override
    protected AdapterView.OnItemClickListener getOnItemClickListener() {
        return super.getOnItemClickListener();
    }


}
