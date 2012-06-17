package org.youfood.android.activity;

import org.interakting.inlab.activity.AbstractActivity;
import org.youfood.android.activity.builder.YouFoodActivityManager;

/**
 * User: GJean Date: 28/03/12 Time: 15:16
 */
abstract public class YouFoodActivity extends AbstractActivity {

    @Override
    protected void loadView() {
        super.loadView();
        YouFoodActivityManager.getInstance().build(this);
    }

}
