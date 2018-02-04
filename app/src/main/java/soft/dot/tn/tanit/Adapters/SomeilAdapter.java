package soft.dot.tn.tanit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import soft.dot.tn.tanit.Entitites.Someil;
import soft.dot.tn.tanit.R;

/**
 * Created by sofien on 29/01/2018.
 */

public class SomeilAdapter extends RecyclerView.Adapter<SomeilAdapter.ViewHolder> {
    Context context;
    ArrayList<Someil> items;

    public SomeilAdapter(Context context, ArrayList<Someil> list) {
        this.items = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.someil_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Someil someil = items.get(position);
        String heure = someil.getHeure() + " : " + someil.getSeconde();
        holder.date.setText(someil.getDate());
        holder.nombreHeure.setText(heure);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.nombreHeure)
        TextView nombreHeure;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
