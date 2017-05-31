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
 * ����DOM������xml�ļ�
 * @author Administrator
 *
 */
public class XmlCreater {
	private Document doc=null;//doc
	private String path=null;//ȫ·��
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
	 * ����
	 * @param path
	 * 		xml�ļ��洢·��
	 */
	public XmlCreater(String path) {
		// TODO Auto-generated constructor stub
		this.path=path;
		init();
	}

	/**
	 * ��ʼ��,����DOM
	 */
	private void init() {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc=builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.debug("��ʼ��������DOM����"+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * �������ڵ�
	 * @param rootTagName
	 * @return
	 * 		root
	 */
	public Element createRootElement(String rootTagName)
	{
		if(doc.getDocumentElement()==null)
		{
			logger.debug("�������ڵ�"+rootTagName+"�ɹ�");
			Element root=doc.createElement(rootTagName);
			doc.appendChild(root);
			return root;
		}
		logger.warn("���ڵ��Ѿ�����");
		return doc.getDocumentElement();
	}
	
	/**
	 * �ڸ���ڵ��������ӽڵ�
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
	 * �ڸ���ڵ�������ֵΪvalue���ӽڵ�tagName
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
	 * ��parent�ڵ�����������
	 * @param parent
	 * @param attrName
	 * @param attrValue
	 */
	public void createAttribute(Element parent, String attrName, String attrValue)
	{
		XmlOperUtil.setElementAttr(parent, attrName, attrValue);
	}
	
	/**
	 * ����DOM����xml�ļ�
	 */
	public void buildXmlFile()
	{
		TransformerFactory factory=TransformerFactory.newInstance();
		try {
			Transformer transformer=factory.newTransformer();
			DOMSource source=new DOMSource(doc);
			logger.debug("����DOM�ɹ�");
			StreamResult result=new StreamResult(new File(path));
			logger.debug("����StreamResult�ɹ�");
			transformer.setOutputProperty("encoding", "UTF-8");
			transformer.transform(source, result);
			logger.debug("����XML�ļ���·��"+path);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			logger.debug("�����������"+e);
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			logger.debug("����XML�ļ�����"+e);
			e.printStackTrace();
		}
		
	}
}
