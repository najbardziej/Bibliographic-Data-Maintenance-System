package com.bibliographicdatamaintenance.DataAccess;

import java.io.*;

import com.bibliographicdatamaintenance.Models.Bibliography;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlImportExport
{
    public static void serializeToXmlFile(Bibliography bibliography, String path) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File(path);
        xmlMapper.writeValue(file, bibliography);
    }

    public static Bibliography deserializeXmlFile(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        String xmlString = sb.toString();

        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(xmlString, Bibliography.class);
    }
}