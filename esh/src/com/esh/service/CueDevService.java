package com.esh.service;

import com.esh.json.form.CueDevParma;

public interface CueDevService {

	/**
	 * 通过参数userId和acupointId从apuser中获取参数
	 * 不存在则从apoint中获取参数
	 * @param cueDevParma
	 * @return
	 */
	public int getCueDevParma(CueDevParma cueDevParma);

	/**
	 * 保存或者更新该用户设备操作参数
	 * @param cueDevParma
	 * @return
	 */
	public int saveOrUpdateCueDevParma(CueDevParma cueDevParma);
}
