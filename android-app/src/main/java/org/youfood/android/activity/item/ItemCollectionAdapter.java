package org.youfood.android.activity.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import org.interakting.inlab.activity.collection.ICollectionActivity;
import org.interakting.inlab.activity.collection.IItemAdapter;
import org.interakting.inlab.activity.collection.adapter.CollectionAdapter;
import org.youfood.android.R;
import org.youfood.android.activity.item.adapter.SimpleItemAdapter;

/**
 * User: GJean
 * Date: 17/06/12
 * Time: 03:35
 */
public class ItemCollectionAdapter extends CollectionAdapter {


    public ItemCollectionAdapter(final Context context, final ICollectionActivity activity) {
        super(context, activity);
    }

    @Override
    protected View createView(ViewGroup parent) {
        return this.getInflater().inflate(R.layout.grid_view_button, parent, false);
    }

    @Override
    protected IItemAdapter createAdapter() {
        return new SimpleItemAdapter();
    }
}
