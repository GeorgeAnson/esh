package com.esh.json.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esh.entity.Disease;
import com.esh.json.form.response.NavBean;

/**
 * �������ͷ�װ��������ɲ˵�
 * @author Administrator
 *
 */
public class DiseaseNav implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Disease> diseases=null;//��֢
	private Map<Integer, List<NavBean>> Navs=new HashMap<>();//�������͵�����
	
	private List<NavBean> pNavs=new ArrayList<NavBean>();//����˵�
	private List<NavBean> sNavs=new ArrayList<NavBean>();//����˵�
	
	public DiseaseNav() {
		
	}
	

	public List<Disease> getDiseases() {
		return diseases;
	}


	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}

	public Map<Integer, List<NavBean>> getDiseaseNavs() {
		return Navs;
	}

	
	public List<NavBean> getpNavs() {
		return pNavs;
	}


	public void setpNavs(List<NavBean> pNavs) {
		this.pNavs = pNavs;
	}


	public List<NavBean> getsNavs() {
		return sNavs;
	}


	public void setsNavs(List<NavBean> sNavs) {
		this.sNavs = sNavs;
	}


	/**
	 * ����˵�
	 * @return 
	 */
	private void Sort(List<NavBean> navs ,boolean isParentNode)
	{
		//����˵���id����
		if(isParentNode)
		{
			Collections.sort(navs, new Comparator<NavBean>(){
				@Override
				public int compare(NavBean former, NavBean latter) {
					return former.getNodeId().compareTo(latter.getNodeId());
				}
			});
		}else
		{
			//���Ӳ˵�������id����
			Collections.sort(navs, new Comparator<NavBean>(){
				@Override
				public int compare(NavBean former, NavBean latter) {
					return former.getParentNodeId().compareTo(latter.getParentNodeId());
				}
			});
		}
	}
	
	/**
	 * �ֱ����ɸ���˵�������˵�����
	 * @return 
	 */
	private boolean buildTwoLevelSepraNav()
	{
		boolean flag=false;
		//�˵����ָ��˵�������˵�
		if(!diseases.isEmpty())
		{
			for(Disease disease:diseases)
			{
				NavBean nav=new NavBean();
				nav.setNodeId(disease.getDid());
				nav.setParentNodeId(disease.getDfid());
				nav.setDiseaseName(disease.getDname());
				if(disease.getDfid()==0)
				{
					pNavs.add(nav);
				}else
				{
					sNavs.add(nav);
				}
			}
			flag=true;
		}
		return flag;
	}
	
	/**
	 * ��ϳɶ����˵�����
	 * @param diseases
	 * @return 
	 */
	private void CreateNavs()
	{
		//��ϳɵ�����
		for(NavBean p:pNavs)
		{
			List<NavBean> res=new ArrayList<>();
			res.add(p);
			for(NavBean s:sNavs)
			{
				if(p.getNodeId()==s.getParentNodeId())
				{
					res.add(s);
				}
			}
			Navs.put(p.getNodeId(), res);
		}
	}
	
	/**
	 * �����˵�������
	 * @return
	 */
	public boolean Create()
	{
		boolean flag=false;
		Sort(pNavs, true);//������˵�
		Sort(sNavs, false);//��������˵�
		flag=buildTwoLevelSepraNav();//���������˵�����
		CreateNavs();//��ɲ˵�
		//�ж��Ƿ�����˵�
		if(Navs!=null)
		{
			flag=true;
		}
		return flag;
	}
	
	@Override
	public String toString() {
		return "DiseaseNav [diseases=" + diseases + ", Navs=" + Navs + ", pNavs=" + pNavs + ", sNavs=" + sNavs + "]";
	}
}
