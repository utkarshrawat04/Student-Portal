package com.example.collegeapp.ebook;

public class EbookData {
    private String pdfName;
    private String pdfTitle;
    private String pdfUri;

    public EbookData() {
        // Default constructor required for calls to DataSnapshot.getValue(EbookData.class)
    }

    public EbookData(String pdfName, String pdfTitle, String pdfUri) {
        this.pdfName = pdfName;
        this.pdfTitle = pdfTitle;
        this.pdfUri = pdfUri;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfTitle() {
        return pdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public String getPdfUri() {
        return pdfUri;
    }

    public void setPdfUri(String pdfUri) {
        this.pdfUri = pdfUri;
    }
}
