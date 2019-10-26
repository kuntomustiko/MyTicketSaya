package com.minara.kirana.myticketsaya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder>{

    Context context;
    ArrayList<MyTicket> myTicketArrayList;

    public TicketAdapter(Context context, ArrayList<MyTicket> myTicketArrayList){
        this.context = context;
        this.myTicketArrayList = myTicketArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder((LayoutInflater.from(context).inflate(R.layout.item_myticket, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvNamaWisata.setText(myTicketArrayList.get(position).getNamaWisata());
        holder.tvLokasi.setText(myTicketArrayList.get(position).getLokasi());
        holder.tvJumlahTicket.setText(myTicketArrayList.get(position).getJumlahTicket() + " TIckets ");

        final String xnama_wisata = myTicketArrayList.get(position).getNamaWisata();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotomyticketdetail = new Intent(context,MyTicketDetailActivity.class);

                gotomyticketdetail.putExtra("nama_wisata", xnama_wisata);
                context.startActivity(gotomyticketdetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myTicketArrayList.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tvNamaWisata, tvJumlahTicket, tvLokasi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaWisata = itemView.findViewById(R.id.item_myticket_namawisata);
            tvJumlahTicket = itemView.findViewById(R.id.item_myticket_jumlahticket);
            tvLokasi = itemView.findViewById(R.id.item_myticket_lokasi);

        }
    }
}
