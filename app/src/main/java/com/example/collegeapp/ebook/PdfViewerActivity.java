package com.example.collegeapp.ebook;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.collegeapp.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdfView);

        Intent intent = getIntent();
        String pdfUri = intent.getStringExtra("pdfUri");

        if (pdfUri != null && !pdfUri.isEmpty()) {
            // Load PDF from URI
            Uri uri = Uri.parse(pdfUri);
            pdfView.fromUri(uri)
                    .enableSwipe(true) // Allows horizontal swipe to navigate
                    .swipeHorizontal(false) // Set to true if you want horizontal swipe
                    .enableDoubletap(true) // Enable double tap to zoom
                    .load();
        } else {
            // Handle case when URI is missing
            Toast.makeText(this, "PDF URI is missing or invalid", Toast.LENGTH_LONG).show();
        }
    }
}
