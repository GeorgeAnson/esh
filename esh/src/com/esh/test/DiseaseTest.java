package com.esh.test;
import java.util.List;
import java.util.Map.Entry;
import org.junit.Test;
import com.esh.dao.DiseaseDao;
import com.esh.dao.jdbc.DiseaseDaoImpl;
import com.esh.entity.Disease;
import com.esh.json.form.DiseaseNav;
import com.esh.json.form.response.NavBean;

public class DiseaseTest {

	@Test
	public void read()
	{	
		DiseaseDao diseaseDao=new DiseaseDaoImpl();
		List<Disease> diseases=diseaseDao.getAllDisease();
		DiseaseNav diseaseNav=new DiseaseNav();
		diseaseNav.setDiseases(diseases);
		
		if(diseaseNav.Create())
		{
			for(Entry<Integer, List<NavBean>> entry:diseaseNav.getDiseaseNavs().entrySet())
			{
				System.out.println("×é±ð£º"+entry.getKey());
				for(NavBean d:entry.getValue())
				{
					System.out.println(d);
				}
			}
		}
	}
}
