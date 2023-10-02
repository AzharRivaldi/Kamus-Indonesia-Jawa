package com.azhar.kamusindojawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.kamusindojawa.R;
import com.azhar.kamusindojawa.database.DatabaseAccess;
import com.azhar.kamusindojawa.model.ModelKamus;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import cn.refactor.lib.colordialog.PromptDialog;

/**
 * Created by Azhar Rivaldi on 31-08-2023
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

public class JawaIndonesiaAdapter extends RecyclerView.Adapter<JawaIndonesiaAdapter.JawaIndonesiaHolder> {

    Context ctx;
    ArrayList<ModelKamus> modelKamusArrayList;

    public JawaIndonesiaAdapter(Context context, ArrayList<ModelKamus> modelKamus) {
        this.ctx = context;
        this.modelKamusArrayList = modelKamus;
    }

    @NonNull
    @Override
    public JawaIndonesiaAdapter.JawaIndonesiaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new JawaIndonesiaHolder(view);
    }

    @Override
    public void onBindViewHolder(JawaIndonesiaAdapter.JawaIndonesiaHolder holder, final int position) {
        final ModelKamus modelKamus = modelKamusArrayList.get(position);

        holder.tvListData.setText(modelKamus.getStrKata());

        holder.cvListData.setOnClickListener(v -> {
            String strSelected = modelKamusArrayList.get(position).getStrKata();
            getArti(strSelected);
        });
    }

    private void getArti(String selectedFromList) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();

        String strHasilArti = databaseAccess.getSelectedJawa(selectedFromList);
        databaseAccess.close();

        new PromptDialog(ctx)
                .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                .setAnimationEnable(true)
                .setTitleText(selectedFromList)
                .setContentText(strHasilArti)
                .setPositiveListener("TUTUP", PromptDialog::dismiss).show();
    }

    @Override
    public int getItemCount() {
        return modelKamusArrayList.size();
    }

    static class JawaIndonesiaHolder extends RecyclerView.ViewHolder {
        public MaterialCardView cvListData;
        public TextView tvListData;

        public JawaIndonesiaHolder(View itemView) {
            super(itemView);
            cvListData = itemView.findViewById(R.id.cvListData);
            tvListData = itemView.findViewById(R.id.tvListData);
        }
    }

}
