package com.esh.utils.xml;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlOperUtil {

	
	private static Logger logger=Logger.getLogger("SmlOpr");
	
	public XmlOperUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��ȡ���ڵ�������ӽڵ�
	 * @param parent
	 * @return
	 */
	public static NodeList getNodeList(Element parent)
	{
		return parent.getChildNodes();
	}
	
	/**
	 * �ڸ��ڵ��в���ָ�����Ƶ��ӽڵ㼯��
	 * @param parent
	 * @param name
	 * @return
	 * 	Element[]
	 */
	public static Element[] getElementByName(Element parent,String name)
	{
		ArrayList<Node> res=new ArrayList<Node>();
		NodeList nodeList=getNodeList(parent);
		for(int i=0;i<nodeList.getLength();i++)
		{
			Node node=nodeList.item(i);
			if(node.getNodeName().equals(name))
			{
				res.add(node);
			}
		}
		Element[] resElem=new Element[res.size()];
		for(int i=0;i<res.size();i++)
		{
			resElem[i]=(Element) res.get(i);
		}
		logger.debug(parent.getNodeName()+"�ڵ���ӽڵ� "+name+"������:"+resElem.length);
		return resElem;
	}
	
	/**
	 * ��ȡָ���ڵ������
	 * @param element
	 * @return
	 */
	public static String getElementName(Element element)
	{
		return element.getNodeName();
	}
	
	/**
	 * ��ȡָ���ڵ��ֵ
	 * @param element
	 * @return
	 */
	public static String getElementValue(Element element)
	{
		NodeList nodeList=element.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++)
		{
			if(nodeList.item(i).getNodeType()==Node.TEXT_NODE)
			{
				logger.debug(element.getNodeName()+"ӵ���ı��ڵ�");
				return element.getFirstChild().getNodeValue();
			}
		}
		logger.error(element.getNodeName()+"�������ı��ڵ�");
		return null;
	}
	
	/**
	 * ��ȡָ��Element��attr����ֵ
	 * @param element
	 * @param attr
	 * @return
	 */
	public static String getElementAttr(Element element, String attr)
	{
		return element.getAttribute(attr);
	}
	
	/**
	 * ����ָ��Element��ֵ
	 * @param element
	 * @param value
	 */
	public static void setElementValue(Element element, String value)
	{
		Node node=element.getOwnerDocument().createTextNode(value);
		NodeList nodeList=element.getChildNodes();
		for(int i=0; i<nodeList.getLength();i++)
		{
			if(nodeList.item(i).getNodeType()==Node.TEXT_NODE)
			{
				nodeList.item(i).setNodeValue(value);
				logger.debug("����"+element.getNodeName()+"ֵ�ɹ�");
				return;
			}
		}
		logger.debug("����"+element.getNodeName()+"ֵ�ɹ�");
		element.appendChild(node);
	}
	
	/**
	 * ���ýڵ�����
	 * @param element
	 * @param attr
	 * @param attrValue
	 */
	public static void setElementAttr(Element element, String attr, String attrValue)
	{
		element.setAttribute(attr, attrValue);
	}
	
	/**
	 * �ڸ��ڵ��������ڵ�
	 * @param parent
	 * @param child
	 */
	public static void addElement(Element parent, Element child)
	{
		parent.appendChild(child);
	}
	
	
	/**
	 * �ڸ��ڵ����������ַ���tagName�Ľڵ�
	 * @param parent
	 * @param tagName
	 */
	public static void addElement(Element parent, String tagName)
	{
		Document doc=parent.getOwnerDocument();
		Element child=doc.createElement(tagName);
		parent.appendChild(child);
	}
	
	/**
	 * ��parent�ڵ�������tagName�ڵ㣬�ڵ�ֵΪtext
	 * @param parent
	 * @param tagName
	 * @param text
	 */
	public static void addElement(Element parent, String tagName, String text)
	{
		Document doc=parent.getOwnerDocument();
		Element child=doc.createElement(tagName);
		setElementValue(child, text);
		parent.appendChild(child);
	}
	
	/**
	 * �Ƴ�parent�ڵ��µ�ָ���ڵ�����ΪtagName�Ľڵ�
	 * @param parent
	 * @param tagName
	 */
	public static void removeeElement(Element parent, String tagName)
	{
		logger.debug("�Ƴ�"+parent.getNodeName()+"���ӽڵ�"+tagName+"��ʼ");
		NodeList nodeList=parent.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++)
		{
			Node node=nodeList.item(i);
			if(node.getNodeName().equals(tagName))
			{
				parent.removeChild(node);
				logger.debug("�Ƴ��ڵ�"+node+"�ɹ�");
			}
		}
		logger.debug("�Ƴ�"+parent.getNodeName()+"���ӽڵ�"+tagName+"����");
	}
	
}
