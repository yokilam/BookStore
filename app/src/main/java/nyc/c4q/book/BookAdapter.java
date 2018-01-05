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
    private OnClickListener listener;
//    public onItemClickListener listener;
    private List<JSONObject> bookList= new ArrayList <>();

    public List <JSONObject> getBookList() {
        return bookList;
    }

    public BookAdapter(List <JSONObject> bookList, OnClickListener listener) {
        this.bookList = bookList;
        this.listener= listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childview= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_itemview, parent, false);
        return new BookViewHolder(childview);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(getBookList().get(position));
    }

    @Override
    public int getItemCount() {
        return getBookList().size();
    }

    public void removeItem(int position){
        getBookList().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getBookList().size()-1);
    }

    public interface onItemClickListener{
        void removeBook(int position);
    }

//    @Override
//    public void listener(int position) {
//        bookList.remove(position);
//        this.notifyItemRemoved(position);
//    }
}
