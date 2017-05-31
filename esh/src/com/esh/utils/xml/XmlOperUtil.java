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
	 * 获取父节点的所有子节点
	 * @param parent
	 * @return
	 */
	public static NodeList getNodeList(Element parent)
	{
		return parent.getChildNodes();
	}
	
	/**
	 * 在父节点中查找指定名称的子节点集合
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
		logger.debug(parent.getNodeName()+"节点的子节点 "+name+"，数量:"+resElem.length);
		return resElem;
	}
	
	/**
	 * 获取指定节点的名称
	 * @param element
	 * @return
	 */
	public static String getElementName(Element element)
	{
		return element.getNodeName();
	}
	
	/**
	 * 获取指定节点的值
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
				logger.debug(element.getNodeName()+"拥有文本节点");
				return element.getFirstChild().getNodeValue();
			}
		}
		logger.error(element.getNodeName()+"不存在文本节点");
		return null;
	}
	
	/**
	 * 获取指定Element的attr属性值
	 * @param element
	 * @param attr
	 * @return
	 */
	public static String getElementAttr(Element element, String attr)
	{
		return element.getAttribute(attr);
	}
	
	/**
	 * 设置指定Element的值
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
				logger.debug("更新"+element.getNodeName()+"值成功");
				return;
			}
		}
		logger.debug("创建"+element.getNodeName()+"值成功");
		element.appendChild(node);
	}
	
	/**
	 * 设置节点属性
	 * @param element
	 * @param attr
	 * @param attrValue
	 */
	public static void setElementAttr(Element element, String attr, String attrValue)
	{
		element.setAttribute(attr, attrValue);
	}
	
	/**
	 * 在父节点下新增节点
	 * @param parent
	 * @param child
	 */
	public static void addElement(Element parent, Element child)
	{
		parent.appendChild(child);
	}
	
	
	/**
	 * 在父节点下新生成字符串tagName的节点
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
	 * 在parent节点下生成tagName节点，节点值为text
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
	 * 移除parent节点下的指定节点名称为tagName的节点
	 * @param parent
	 * @param tagName
	 */
	public static void removeeElement(Element parent, String tagName)
	{
		logger.debug("移除"+parent.getNodeName()+"的子节点"+tagName+"开始");
		NodeList nodeList=parent.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++)
		{
			Node node=nodeList.item(i);
			if(node.getNodeName().equals(tagName))
			{
				parent.removeChild(node);
				logger.debug("移除节点"+node+"成功");
			}
		}
		logger.debug("移除"+parent.getNodeName()+"的子节点"+tagName+"结束");
	}
	
}
