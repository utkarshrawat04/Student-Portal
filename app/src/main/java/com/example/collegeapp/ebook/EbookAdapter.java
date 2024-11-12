package com.example.collegeapp.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {

    private Context context;
    private List<EbookData> list;

    public EbookAdapter(Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout, parent, false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, int position) {
        EbookData ebookData = list.get(position);
        holder.ebookName.setText(ebookData.getPdfName());
        holder.ebookTitle.setText(ebookData.getPdfTitle());

        // Handle item click to open PdfViewerActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdfUri = ebookData.getPdfUri();
                if (pdfUri != null && !pdfUri.isEmpty()) {
                    Intent intent = new Intent(context, PdfViewerActivity.class);
                    intent.putExtra("pdfUri", pdfUri);
                    context.startActivity(intent);
                }
            }
        });

        // Handle ebook download click
        if (holder.ebookDownload != null) {
            holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pdfUri = ebookData.getPdfUri();
                    if (pdfUri != null && !pdfUri.isEmpty()) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(pdfUri));
                        context.startActivity(intent);
                    }
                }
            });
        } else {
            // Log or handle the error as needed

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView ebookName;
        private TextView ebookTitle;
        private ImageView ebookDownload;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);
            ebookName = itemView.findViewById(R.id.ebookname);
            ebookTitle = itemView.findViewById(R.id.ebookTitle);

        }
    }
}
