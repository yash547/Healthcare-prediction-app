package com.example.healths.ui.HISTORY;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healths.R;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.myviewholder>
{
    ArrayList<Users> dataholder;

    public Myadapter(ArrayList<Users> dataholder) {

        this.dataholder = dataholder;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.sym.setText(dataholder.get(position).getSym());
        holder.predicDis.setText(dataholder.get(position).getPredicDis());
        holder.Datetime.setText(dataholder.get(position).getDatetime());

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
       TextView sym,predicDis,Datetime ;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            sym=itemView.findViewById(R.id.tvsym);
            predicDis=itemView.findViewById(R.id.tvpredictedDis);
            Datetime=itemView.findViewById(R.id.tvdatetime);
        }
    }
}
