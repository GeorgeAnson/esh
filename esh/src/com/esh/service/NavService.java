package com.esh.service;

import com.esh.json.form.DiseaseNav;

public interface NavService {

	/**
	 * 创建二级菜单
	 * @return
	 */
	public int createNavs(DiseaseNav diseaseNav);
}
