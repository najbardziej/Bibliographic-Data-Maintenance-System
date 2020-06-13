package com.bibliographicdatamaintenance.DataAccess;

import java.io.*;
import java.util.List;

import com.bibliographicdatamaintenance.Models.Bibliography;
import com.bibliographicdatamaintenance.Models.Book;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlImportExport
{
    //from Java Object to XML String
    public static String javaObjectToXmlString(Bibliography bibliography) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(bibliography);
    }

    //from Java Object to XML file
    public static void javaObjectToXmlFile(Bibliography bibliography, String path) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File(path);
        xmlMapper.writeValue(file, bibliography);
    }

    //from XML String to Java Object
    public static Bibliography xmlStringToJavaObject(String string) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(string, Bibliography.class);
    }

    //from XML file to String
    public static String xmlFileToString(InputStream is) throws IOException
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