package org.youfood.android.activity.impl.category;

import android.os.Bundle;
import org.interakting.inlab.activity.collection.IItem;
import org.interakting.inlab.activity.collection.IProvider;
import org.youfood.android.R;
import org.youfood.android.activity.list.YouFoodListItemActivity;

/**
 * User: GJean
 * Date: 17/06/12
 * Time: 04:11
 */
public class CategoryActivity extends YouFoodListItemActivity {
    public CategoryActivity() {
        super(R.id.GridView);
    }

    @Override
    protected IProvider<IItem> createProvider() {
        //final int parentActivity = this.initParent();

        return new CategoryItemsProvider("@string/global.undefined", "@drawable/menu_icon_book");
    }

    private int initParent() {
        if (null != this.getIntent()) {
            final Bundle bundle = this.getIntent().getExtras();

            if (null != bundle && bundle.containsKey("PARENT_ACTIVITY_ID"))
                return bundle.getInt("PARENT_ACTIVITY_ID");
        }

        return 0;
    }
}
