package nyc.c4q.book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yokilam on 1/4/18.
 */

class BookViewHolder extends RecyclerView.ViewHolder {
    BookAdapter bookAdapter;
    private TextView title, author, price;
    private Button addToCart;

    public BookViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        author = itemView.findViewById(R.id.author);
        price = itemView.findViewById(R.id.price);
        addToCart = itemView.findViewById(R.id.add_to_cart);
    }

    public void bind(final JSONObject jsonObject, final int position) {
        try {
            title.setText(jsonObject.getString("name"));
            author.setText(jsonObject.getString("author"));
            StringBuilder priceString = new StringBuilder();
            priceString.append("Price: $").append(jsonObject.getString("price")).toString();
            price.setText(priceString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "CLICK" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("jsonObject", jsonObject.toString());
                itemView.getContext().startActivity(intent);

            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cartList.add(jsonObject);
                bookAdapter.bookList.remove(position);

            }
        });

    }
}
