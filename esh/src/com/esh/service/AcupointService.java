package com.esh.service;

import com.esh.entity.Acupoint;
import com.esh.entity.AcupointPicture;

public interface AcupointService {

	/**
	 * 通过穴位id获取穴位详细信息
	 * @param acpuointId
	 * @return
	 */
	public Acupoint getAcupointByAcupointId(int acupointId);
	
	/**
	 * 检查图片缓存，若不存在缓存，则更新
	 * @param picture
	 * @param parentPath
	 * @return
	 */
	public int initDiseasePicture(AcupointPicture picture, String parentPath);
}
