package org.youfood.android.activity.list;

import android.widget.BaseAdapter;
import org.youfood.android.R;
import org.youfood.android.YouFoodGridViewActivity;
import org.youfood.android.activity.item.ItemCollectionAdapter;

/**
 * User: GJean
 * Date: 17/06/12
 * Time: 03:10
 */
public abstract class YouFoodListItemActivity extends YouFoodGridViewActivity {

    private BaseAdapter adapter;

    public YouFoodListItemActivity(final int viewId) {
        super(viewId);
    }

    public int getContentViewId() {
        return R.layout.items_view;
    }

    @Override
    protected BaseAdapter createBaseAdapter() {
        adapter = new ItemCollectionAdapter(this, this);

        return adapter;
    }

    public void refreshAdapter() {
        adapter.notifyDataSetChanged();
    }


}
