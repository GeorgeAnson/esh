package com.esh.json.form.response;
import java.io.Serializable;

/**
 * ���ɵ������б�
 * @author Administrator
 *
 */
public class NavBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer nodeId=0;//�ڵ�id
	private Integer parentNodeId=0;//����ڵ�id
	private String diseaseName=null;//�ڵ�����
	
	public NavBean() {
		
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(Integer parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	@Override
	public String toString() {
		return "NavBean [nodeId=" + nodeId + ", parentNodeId=" + parentNodeId + ", diseaseName=" + diseaseName + "]";
	}
}
