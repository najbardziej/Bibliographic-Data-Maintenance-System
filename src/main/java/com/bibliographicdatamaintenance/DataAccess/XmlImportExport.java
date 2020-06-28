package com.bibliographicdatamaintenance.DataAccess;

import java.io.*;
import java.util.List;

import com.bibliographicdatamaintenance.Models.Bibliography;
import com.bibliographicdatamaintenance.Models.Book;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlImportExport
{
    public static String serializeToXmlString(Bibliography bibliography) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(bibliography);
    }

    public static void serializeToXmlFile(Bibliography bibliography, String path) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File(path);
        xmlMapper.writeValue(file, bibliography);
    }

    public static Bibliography deserializeXmlString(String string) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(string, Bibliography.class);
    }

    public static String readXmlFileToString(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}