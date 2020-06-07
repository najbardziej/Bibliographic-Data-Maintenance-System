package com.bibliographicdatamaintenance;

import java.io.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlImportExport
{
    //from Java Object to XML String
    public String javaObjectToXmlString() throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(new MyJavaObject());
        return xml;
    }

    //from Java Object to XML file
    public void javaObjectToXmlFile(MyJavaObject obj, String path) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File(path);
        xmlMapper.writeValue(file, obj);
    }

    //from XML String to Java Object
    public MyJavaObject xmlStringToJavaObject(String string) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        MyJavaObject value = xmlMapper.readValue(string, MyJavaObject.class);
        return value;
    }

    //from XML file to String
    public String xmlFileToString(InputStream is) throws IOException
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