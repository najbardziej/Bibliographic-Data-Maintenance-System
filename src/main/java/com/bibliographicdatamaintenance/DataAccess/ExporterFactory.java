package com.bibliographicdatamaintenance.DataAccess;

public class ExporterFactory {
    public static IExporter getExporter(String extension)
    {
        switch (extension)
        {
            case ".docx":
                return new DocxExporter();
            case ".bib":
                return new BibTeXExporter();
            case ".txt":
                return new TxtExporter();
            case ".rtf":
                return new RtfExporter();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
