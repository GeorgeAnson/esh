package com.esh.utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;

/**
 * ���ݴ����xml�ļ�����document��root�ڵ�
 * @author Administrator
 *
 */
public class XmlBuilder {
	
	private String path=null;//xml�ļ�·��
	private Document doc=null;//xml�ļ���Ӧ��document
	private Element root=null;//xml�ļ��ڵ�
	private Logger logger=Logger.getLogger(getClass().getName());

	public XmlBuilder(String path)
	{
		this.path=path;
		init();
	}

	/**
	 * ��ʼ��
	 */
	private void init() {
		// TODO Auto-generated method stub
		buildDocument();
		buildRoot();
	}
	
	/**
	 * ����document
	 */
	private void buildDocument() {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			if(!new File(path).exists())
			{
				XmlCreater creater=new XmlCreater(path);
				Element root=creater.createRootElement("users");
				Element user=creater.createElement(root, "user");
				creater.createAttribute(user, "id", "1");
				creater.createElement(user, "id","1");
				creater.createElement(user, "account","sys");
		        creater.buildXmlFile();
			}
			DocumentBuilder builder=factory.newDocumentBuilder();
			logger.debug("����docuemnt�������ɹ�");
			doc=builder.parse(new File(path));
			logger.debug("����xml�ļ��ɹ�");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.debug("�����ļ�����������"+e);
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.debug("����xml����"+e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug("��ȡxml����"+e);
			e.printStackTrace();
		}
		
	}

	/**
	 * ���ɽڵ�
	 */
	private void buildRoot() {
		// TODO Auto-generated method stub
		root=doc.getDocumentElement();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public Element getRoot() {
		return root;
	}

	public void setRoot(Element root) {
		this.root = root;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}
