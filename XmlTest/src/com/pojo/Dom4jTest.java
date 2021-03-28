package com.pojo;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jTest {
    @Test
    public void test1() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/books.xml");
        //System.out.println(document);
        Element rootElement = document.getRootElement();
        List<Element> books = rootElement.elements();
        for (Element book : books) {
            //根据元素名称获取元素
            Element name = book.element("name");
            String text = name.getText();
            //将XML元素转为XML字符串
            //System.out.println(book.asXML());
            //直接获取指定标签中的文本内容
            String price = book.elementText("price");
            System.out.println(price);
            //获取属性值
            String sn = book.attributeValue("sn");
            System.out.println(sn);
        }
    }
}
