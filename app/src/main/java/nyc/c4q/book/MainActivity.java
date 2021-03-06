package nyc.c4q.book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<JSONObject> jsonObjectList= new ArrayList <>();
    static List<JSONObject> cartList= new ArrayList <>();
    private RecyclerView rv;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getResponseFromOKHTTP();
        rv= findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        bookAdapter= new BookAdapter(jsonObjectList, this);
        rv.setAdapter(bookAdapter);

    }

    private void getResponseFromOKHTTP() {
        String url= "https://raw.githubusercontent.com/tamingtext/book/master/apache-solr/example/exampledocs/books.json";
        OkHttpClient client= new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "onFailure: -----------------");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonResult = response.body().string();
                Log.d(TAG, "onResponse: IT IS WORKING" + jsonResult);

                try {
                    JSONArray jsonArray= new JSONArray(jsonResult);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        //Getting jsonObject in the jsonArray
                        jsonObjectList.add(jsonArray.getJSONObject(i));
                        Log.d(TAG, "onResponse: ADDING TO THE LIST" + jsonObjectList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

//    @Override
//    public void removeBook(int position) {
//        cartList.add(jsonObjectList.get(position));
//        bookAdapter.removeItem(position);
//
//    }

    @Override
    public void onClickedSelected(int position) {
        cartList.add(jsonObjectList.get(position));
        bookAdapter.removeItem(position);
    }

//    @Override
//    public void itemClick(int position) {
//        cartList.remove(position);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.cart:
                Intent cartIntent= new Intent(this, CartActivity.class );
                startActivity(cartIntent);
        }
        return true;
    }
}
