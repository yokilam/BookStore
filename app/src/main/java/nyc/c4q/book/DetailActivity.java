package nyc.c4q.book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private TextView id, cat, title, author, sequence, genre, inStock, price, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupViews();

        try {
            JSONObject jsonObject= new JSONObject(getIntent().getStringExtra("jsonObject"));
            setupText(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupText(JSONObject jsonObject) throws JSONException {
        title.setText("Title: " + jsonObject.getString("name"));
        id.setText("ID: " + jsonObject.getString("id"));
        cat.setText("Category: "+ jsonObject.getJSONArray("cat").toString());
        author.setText("Author: " + jsonObject.getString("author"));
        sequence.setText("Sequence: " + String.valueOf(jsonObject.getInt("sequence_i")));
        genre.setText("Genre: " +jsonObject.getString("genre_s"));
        inStock.setText("InStock: " + String.valueOf(jsonObject.getBoolean("inStock")));
        price.setText("Price: " + String.valueOf(jsonObject.getInt("price")));
        pages.setText("Pages: " + String.valueOf(jsonObject.getInt("pages_i")));
    }

    private void setupViews() {
        title= findViewById(R.id.title);
        genre= findViewById(R.id.genre);
        id= findViewById(R.id.id);
        cat= findViewById(R.id.cat);
        author= findViewById(R.id.author);
        sequence= findViewById(R.id.sequence);
        inStock= findViewById(R.id.inStock);
        price= findViewById(R.id.price);
        pages= findViewById(R.id.page);
    }
}
