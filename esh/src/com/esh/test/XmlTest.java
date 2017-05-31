package com.esh.test;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.esh.utils.xml.XmlBuilder;
import com.esh.utils.xml.XmlCreater;
import com.esh.utils.xml.XmlOperUtil;

public class XmlTest {

	@Test
	public void writer()
	{
		XmlCreater creater=new XmlCreater("E:\\test.xml");
		Element root=creater.createRootElement("languaue");
		Element user=creater.createElement(root, "user");
		creater.createAttribute(user, "id", "1");
		creater.createElement(user, "id","1");
		creater.createElement(user, "name","java");
		creater.createElement(user, "age","23");
		Element user2=creater.createElement(root, "user");
		creater.createAttribute(user2, "id", "2");
		creater.createElement(user2, "id","2");
		creater.createElement(user2, "name","java2");
		creater.createElement(user2, "age","24");
        creater.buildXmlFile();
	}
	
	@Test
	public void read()
	{
		XmlBuilder builder=new XmlBuilder("E:\\test.xml");
        Element root1=builder.getRoot();
     
        Element[] elements=XmlOperUtil.getElementByName(root1, "user");
        
        for(Element element:elements)
        {
        	System.out.println(XmlOperUtil.getElementAttr(element, "id"));
        	NodeList list=XmlOperUtil.getNodeList(element);
        	for(int i=0;i<list.getLength();i++)
        	{
        		Element e=(Element) list.item(i);
        		System.out.println(XmlOperUtil.getElementName(e)+"  "+XmlOperUtil.getElementValue(e));
        	}
        }
	}
	
	@Test
	public void update()
	{
		
		XmlBuilder builder=new XmlBuilder("E:\\test.xml");
		
		Element root=builder.getRoot();
		Element[] users=XmlOperUtil.getElementByName(root, "user");
		 for(Element user:users)
	        {
//	        	System.out.println(XmlOperUtil.getElementAttr(user, "id"));
	        	XmlOperUtil.addElement(user, "gender", "ÄÐ");
//	        	XmlOperUtil.removeeElement(user, "gender");
	        }
		 XmlCreater creater=new XmlCreater(builder.getPath());
		creater.setDoc(builder.getDoc());
		creater.buildXmlFile();
	}
}
