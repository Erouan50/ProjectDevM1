package org.youfood.android.activity.impl.loading;

import android.util.Log;
import android.widget.ProgressBar;
import org.interakting.inlab.activity.ActivityManager;
import org.youfood.android.R;
import org.youfood.android.YouFood;
import org.youfood.android.activity.YouFoodActivity;
import org.youfood.android.activity.impl.category.CategoryActivity;
import org.youfood.android.activity.webservice.impl.ClientWebService;
import org.youfood.android.database.DatabaseHelper;

/**
 * User: GJean Date: 28/03/12 Time: 16:42
 */
public class LoadingActivity extends YouFoodActivity {

    private ProgressBar progressBar;

    @Override
    public int getContentViewId() {

        return R.layout.loading_view;

    }

    @Override
    public void loadView() {
        super.loadView();
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

        this.post(new LoadingRunnable(this));
        if (null != progressBar)
            this.load();

    }

    public void load() {
        this.initHelper();
        this.initData();
        this.initGridView();
        ActivityManager.getInstance().pushActivity(CategoryActivity.class);
    }

    private void initHelper() {
        DatabaseHelper.init(this, YouFood.DATABASE_NAME, YouFood.DATABASE_VERSION);
        this.progressBar.setProgress(25);
    }

    public void initData() {

        try {
            new ClientWebService("http://10.19.241.191:8080/restaurant-web-app-0.1/resources/menus");

        } catch (Exception e) {
            //EventManager.getInstance().sendEvent(new ExceptionEvent(e));
            Log.e("JSON", e.toString());
        }
        this.progressBar.setProgress(50);
    }


    public void initGridView() {
        this.progressBar.setProgress(100);
    }

    @Override
    protected void onDestroy() {
        DatabaseHelper.getInstance().close();
        super.onDestroy();


    }

}
