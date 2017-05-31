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
 * 疾病类型封装，输出生成菜单
 * @author Administrator
 *
 */
public class DiseaseNav implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Disease> diseases=null;//病症
	private Map<Integer, List<NavBean>> Navs=new HashMap<>();//疾病类型导航栏
	
	private List<NavBean> pNavs=new ArrayList<NavBean>();//父类菜单
	private List<NavBean> sNavs=new ArrayList<NavBean>();//子类菜单
	
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
	 * 排序菜单
	 * @return 
	 */
	private void Sort(List<NavBean> navs ,boolean isParentNode)
	{
		//父类菜单按id排序
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
			//将子菜单按父类id排序
			Collections.sort(navs, new Comparator<NavBean>(){
				@Override
				public int compare(NavBean former, NavBean latter) {
					return former.getParentNodeId().compareTo(latter.getParentNodeId());
				}
			});
		}
	}
	
	/**
	 * 分别生成父类菜单和子类菜单集合
	 * @return 
	 */
	private boolean buildTwoLevelSepraNav()
	{
		boolean flag=false;
		//菜单，分割父类菜单和子类菜单
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
	 * 组合成二级菜单集合
	 * @param diseases
	 * @return 
	 */
	private void CreateNavs()
	{
		//组合成导航栏
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
	 * 二级菜单创建器
	 * @return
	 */
	public boolean Create()
	{
		boolean flag=false;
		Sort(pNavs, true);//排序父类菜单
		Sort(sNavs, false);//排序子类菜单
		flag=buildTwoLevelSepraNav();//父类和子类菜单生成
		CreateNavs();//组成菜单
		//判断是否包含菜单
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
