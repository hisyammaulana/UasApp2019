package id.plugin.uasapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.plugin.uasapp.HasilActivity;
import id.plugin.uasapp.Model.DataModel;
import id.plugin.uasapp.R;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<DataModel> mList ;
    private Context ctx;

    public AdapterData(HasilActivity hasilActivity, List<DataModel> mItems) {
    }


    @Override
    public AdapterData.HolderData onCreateViewHolder( ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.nik.setText(dm.getNik());
        holder.nama.setText(dm.getNama());
        holder.kelas.setText(dm.getKelas());
        holder.jam.setText(dm.getJam());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class HolderData extends  RecyclerView.ViewHolder {
        TextView nik, nama, kelas, jam;
        DataModel dm;
        public HolderData (View v)
        {
            super(v);

            nik  = (TextView) v.findViewById(R.id.tvNIK);
            nama  = (TextView) v.findViewById(R.id.tvNama);
            kelas = (TextView) v.findViewById(R.id.tvKelas);
            jam = (TextView) v.findViewById(R.id.tvJam);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, HasilActivity.class);
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("nik", dm.getNik());
                    goInput.putExtra("nama", dm.getNama());
                    goInput.putExtra("kelas", dm.getKelas());
                    goInput.putExtra("jam", dm.getJam());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
