package com.esh.utils.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 创建DOM并生成xml文件
 * @author Administrator
 *
 */
public class XmlCreater {
	private Document doc=null;//doc
	private String path=null;//全路径
	private Logger logger=Logger.getLogger(getClass().getName());
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * 构造
	 * @param path
	 * 		xml文件存储路径
	 */
	public XmlCreater(String path) {
		// TODO Auto-generated constructor stub
		this.path=path;
		init();
	}

	/**
	 * 初始化,创建DOM
	 */
	private void init() {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc=builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.debug("初始化，创建DOM错误"+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建根节点
	 * @param rootTagName
	 * @return
	 * 		root
	 */
	public Element createRootElement(String rootTagName)
	{
		if(doc.getDocumentElement()==null)
		{
			logger.debug("创建根节点"+rootTagName+"成功");
			Element root=doc.createElement(rootTagName);
			doc.appendChild(root);
			return root;
		}
		logger.warn("根节点已经存在");
		return doc.getDocumentElement();
	}
	
	/**
	 * 在父类节点下新增子节点
	 * @param parent
	 * @param tagName
	 * @return
	 * 		child
	 */
	public Element createElement(Element parent, String tagName)
	{
		Document doc=parent.getOwnerDocument();
		Element child=doc.createElement(tagName);
		parent.appendChild(child);
		return child;
	}
	
	/**
	 * 在父类节点下新增值为value的子节点tagName
	 * @param parent
	 * @param tagName
	 * @return
	 * 	child
	 */
	public Element createElement(Element parent, String tagName, String value)
	{
		Document doc=parent.getOwnerDocument();
		Element child=doc.createElement(tagName);
		XmlOperUtil.setElementValue(child, value);
		parent.appendChild(child);
		return child;
	}
	
	/**
	 * 在parent节点下增加属性
	 * @param parent
	 * @param attrName
	 * @param attrValue
	 */
	public void createAttribute(Element parent, String attrName, String attrValue)
	{
		XmlOperUtil.setElementAttr(parent, attrName, attrValue);
	}
	
	/**
	 * 根据DOM生成xml文件
	 */
	public void buildXmlFile()
	{
		TransformerFactory factory=TransformerFactory.newInstance();
		try {
			Transformer transformer=factory.newTransformer();
			DOMSource source=new DOMSource(doc);
			logger.debug("创建DOM成功");
			StreamResult result=new StreamResult(new File(path));
			logger.debug("创建StreamResult成功");
			transformer.setOutputProperty("encoding", "UTF-8");
			transformer.transform(source, result);
			logger.debug("创建XML文件，路径"+path);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			logger.debug("创建事物出错"+e);
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			logger.debug("创建XML文件出错"+e);
			e.printStackTrace();
		}
		
	}
}
