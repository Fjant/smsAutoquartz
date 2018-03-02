package cn.zhuoqin.platform.util;

import java.io.File;
import java.io.InputStream;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXML
{
  private static Logger log = Logger.getLogger(ParseXML.class);
  private Document document = null;
  private Element element = null;

  public void initXmlByFile(String xmlPath)
  {
    try
    {
      SAXReader reader = new SAXReader();
      this.document = reader.read(new File(xmlPath));
      this.element = this.document.getRootElement();
    } catch (Exception e) {
      log.error("初始化出错：" + xmlPath, e);
    }
  }

  public void initXmlByText(String xmlText)
  {
    try
    {
      this.document = DocumentHelper.parseText(xmlText);
      this.element = this.document.getRootElement();
    } catch (Exception e) {
      log.error("初始化出错：" + xmlText, e);
    }
  }

  public void initXmlByInputStream(InputStream in)
  {
    try
    {
      SAXReader reader = new SAXReader();
      this.document = reader.read(in);
      this.document.setXMLEncoding("GBK");
      this.element = this.document.getRootElement();
    } catch (Exception e) {
      log.error("初始化出错：", e);
    }
  }

  public Document getDocument() { return this.document; }

  public Element getElement()
  {
    return this.element;
  }

  public String addMulData(String nodeName, String[] subNodeNames, String[][] data) {
    try {
      if (data.length != 0) {
        Element ele = this.element.element(nodeName);
        if (data.length == 1) {
          for (int i = 0; i < subNodeNames.length; i++)
            ele.element("List").element(subNodeNames[i]).setText(data[0][i]);
        }
        else {
          for (int i = 0; i < subNodeNames.length; i++) {
            ele.element("List").element(subNodeNames[i]).setText(data[0][i]);
          }
          for (int i = 1; i < data.length; i++) {
            Element e = ele.addElement("List");
            for (int j = 0; j < subNodeNames.length; j++) {
              e.addElement(subNodeNames[j]).setText(data[i][j]);
            }
          }
        }
      }
      return this.document.asXML(); } catch (Exception e1) {
    }
    return null;
  }

  public String addData(String nodeName, String[] subNodeNames, String[] data)
  {
    try {
      if (data.length > 0) {
        Element ele = this.element.element(nodeName);
        for (int i = 0; i < subNodeNames.length; i++) {
          ele.element(subNodeNames[i]).setText(data[i]);
        }
      }
      return this.document.asXML(); } catch (Exception e) {
    }
    return null;
  }
}