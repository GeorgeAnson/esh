package com.esh.service;

import com.esh.json.form.CueDevParma;

public interface CueDevService {

	/**
	 * ͨ������userId��acupointId��apuser�л�ȡ����
	 * ���������apoint�л�ȡ����
	 * @param cueDevParma
	 * @return
	 */
	public int getCueDevParma(CueDevParma cueDevParma);

	/**
	 * ������߸��¸��û��豸��������
	 * @param cueDevParma
	 * @return
	 */
	public int saveOrUpdateCueDevParma(CueDevParma cueDevParma);
}
