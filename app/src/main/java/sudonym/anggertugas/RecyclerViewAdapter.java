package sudonym.anggertugas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static sudonym.anggertugas.R.string.nama;

/**
 * Created by sudonym on 24/03/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.textViewId.setText(Integer.toString(result.getId()));
        holder.textViewNama.setText(result.getNama());
        holder.textViewAlamat.setText(result.getAlamat());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textId) TextView textViewId;
        @BindView(R.id.textNama) TextView textViewNama;
        @BindView(R.id.textAlamat) TextView textViewAlamat;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Integer id = Integer.valueOf(textViewId.getText().toString());
            String id = textViewId.getText().toString();
            String nama = textViewNama.getText().toString();
            String alamat = textViewAlamat.getText().toString();

            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("id", id);
            i.putExtra("nama", nama);
            i.putExtra("alamat", alamat);
            context.startActivity(i);
        }
    }
}