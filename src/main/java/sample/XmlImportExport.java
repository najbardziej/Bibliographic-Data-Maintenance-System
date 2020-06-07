package sample;

import java.io.*;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlImportExport
{
    //from Java Object to XML String
    public String javaObjectToXmlString() throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(new MyJavaObject());
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
        return xmlMapper.readValue(string, MyJavaObject.class);
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
    public static Bibliography xmlStringToJavaObject(String string, Class c) throws IOException
    {
        // TODO: usunąć xmlStringToJavaObject zwracającą MyJavaObject i usunąć zbędny argument Class c
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(string, Bibliography.class);
    }

    //from Java Object to XML file
    public static void javaObjectToXmlFile(List<MyJavaObject> bibliography, String path) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File(path);
        xmlMapper.writeValue(file, bibliography);
    }
}