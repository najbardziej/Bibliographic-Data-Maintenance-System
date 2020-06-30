package com.bibliographicdatamaintenance;

import com.bibliographicdatamaintenance.DataAccess.*;
import com.bibliographicdatamaintenance.Models.Bibliography;
import com.bibliographicdatamaintenance.Models.Book;
import de.saxsys.javafx.test.JfxRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertTrue;

@RunWith(JfxRunner.class)
public class BibliographyTest{
    private Bibliography bibliography;
    @Before
    public void initialize() {
        List<Book> bookList = new ArrayList<>();
        Book sampleBook = new Book("Pan Lodowego Ogrodu - tom 1",
                                   "Jarosław Grzędowicz",
                                   "Fabryka Słów",
                                   (short)2012);
        bookList.add(sampleBook);
        bibliography = new Bibliography(bookList);
    }
    @Test
    public void testExportToTxt(){
        IExporter exporter = ExporterFactory.getExporter(".txt");
        assertThat(exporter, instanceOf(TxtExporter.class));

        String fileName = "testExport.txt";
        bibliography.exportToFile(exporter, fileName);
        File file = new File(fileName);
        assertTrue(file.exists());
        file.delete();
    }
    @Test
    public void testExportToRtf(){
        IExporter exporter = ExporterFactory.getExporter(".rtf");
        assertThat(exporter, instanceOf(RtfExporter.class));

        String fileName = "testExport.rtf";
        bibliography.exportToFile(exporter, fileName);
        File file = new File(fileName);
        assertTrue(file.exists());
        file.delete();
    }
    @Test
    public void testExportToBibTex(){
        IExporter exporter = ExporterFactory.getExporter(".bib");
        assertThat(exporter, instanceOf(BibTeXExporter.class));

        String fileName = "testExport.bib";
        bibliography.exportToFile(exporter, fileName);
        File file = new File(fileName);
        assertTrue(file.exists());
        file.delete();
    }
    @Test
    public void testExportToDocx(){
        IExporter exporter = ExporterFactory.getExporter(".docx");
        assertThat(exporter, instanceOf(DocxExporter.class));

        String fileName = "testExport.docx";
        bibliography.exportToFile(exporter, fileName);
        File file = new File(fileName);
        assertTrue(file.exists());
        file.delete();
    }

}
