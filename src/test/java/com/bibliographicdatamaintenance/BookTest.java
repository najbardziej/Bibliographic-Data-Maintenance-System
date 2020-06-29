package com.bibliographicdatamaintenance;

import com.bibliographicdatamaintenance.Models.Book;
import de.saxsys.javafx.test.JfxRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JfxRunner.class)
public class BookTest {
    @Test
    public void TestToString(){
        Book book = new Book("Pan Lodowego Ogrodu - tom 1",
                             "Jarosław Grzędowicz",
                             "Fabryka Słów",
                             (short)2012);
        String expected = "Pan Lodowego Ogrodu - tom 1 Jarosław Grzędowicz Fabryka Słów 2012 ";
        assertEquals(book.toString(), expected);
    }
}
