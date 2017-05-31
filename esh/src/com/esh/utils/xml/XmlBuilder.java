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
 * 根据传入的xml文件生成document和root节点
 * @author Administrator
 *
 */
public class XmlBuilder {
	
	private String path=null;//xml文件路径
	private Document doc=null;//xml文件对应的document
	private Element root=null;//xml文件节点
	private Logger logger=Logger.getLogger(getClass().getName());

	public XmlBuilder(String path)
	{
		this.path=path;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// TODO Auto-generated method stub
		buildDocument();
		buildRoot();
	}
	
	/**
	 * 生成document
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
			logger.debug("创建docuemnt构造器成功");
			doc=builder.parse(new File(path));
			logger.debug("创建xml文件成功");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.debug("创建文件构造器错误"+e);
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.debug("创建xml错误"+e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug("读取xml错误"+e);
			e.printStackTrace();
		}
		
	}

	/**
	 * 生成节点
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
