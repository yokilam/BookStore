package nyc.c4q.book;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yokilam on 1/4/18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{
    public List<JSONObject> bookList= new ArrayList <>();


    public BookAdapter(List <JSONObject> bookList) {
        this.bookList = bookList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childview= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_itemview, parent, false);
        return new BookViewHolder(childview);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(bookList.get(position), position);

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void remove(int position){
        bookList.remove(position);
        this.notifyItemRemoved(position);
    }

//    @Override
//    public void listener(int position) {
//        bookList.remove(position);
//        this.notifyItemRemoved(position);
//    }
}
