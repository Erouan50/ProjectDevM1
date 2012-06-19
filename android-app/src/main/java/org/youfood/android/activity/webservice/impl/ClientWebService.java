package org.youfood.android.activity.webservice.impl;

import android.util.Log;
import com.j256.ormlite.dao.Dao;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.youfood.android.activity.webservice.IClientWebService;
import org.youfood.android.database.DatabaseHelper;
import org.youfood.android.database.entity.CategoryEntity;
import org.youfood.android.database.entity.MenuEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * User: GJean
 * Date: 18/06/12
 * Time: 22:25
 */
public class ClientWebService implements IClientWebService {

    private String result;
    private String url;
    private Dao<MenuEntity, ?> MenuCategory;

    public ClientWebService(String url) {
        this.url = url;
        try {
            this.connect(url);
        } catch (Exception e) {
            Log.e("WebService", e.toString());
        }
    }

    @Override
    public void connect(String url) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);

        HttpResponse response;
        try {
            response = httpclient.execute(httpGet);
            Log.e("WebService", response.getStatusLine().toString());

            result = EntityUtils.toString(response.getEntity());
            Log.e("WebService", result);

            List<MenuEntity> menus = new ArrayList<MenuEntity>();
            this.MenuCategory = DatabaseHelper.getInstance().getDao(MenuEntity.class);
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                MenuEntity menuEntity = convertMenu(jsonArray.getJSONObject(i));
                MenuCategory.create(menuEntity);
                menus.add(menuEntity);


            }


        } catch (Exception e) {
            //EventManager.getInstance().sendEvent(new ExceptionEvent(e));
            Log.e("JSON", e.toString(), e);
        }
    }

    private MenuEntity convertMenu(JSONObject jsonMenu) throws JSONException, ParseException {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(jsonMenu.getInt("id"));
        menuEntity.setName(jsonMenu.getString("name"));
        menuEntity.setDescription(jsonMenu.getString("description"));
        menuEntity.setAvailableStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonMenu.getString("availableStartDate")));
        menuEntity.setAvailableEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonMenu.getString("availableEndDate")));
        menuEntity.setCategory(convertCategory(jsonMenu.getJSONObject("category")));
        return menuEntity;
    }

    private CategoryEntity convertCategory(JSONObject jsonCategory) throws JSONException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(jsonCategory.getInt("id"));
        categoryEntity.setName(jsonCategory.getString("name"));
        return categoryEntity;
    }


}
