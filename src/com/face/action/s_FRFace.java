package com.face.action;


import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class s_FRFace extends Structure {
	/**
	 * 
	 */
	public	s_FRRect faceRect;	// �������壬�ֱ�Ϊ��������������������
	public int confidence;		// confidence �������Ŷ�,��0��100,0���,100���
	public	int quality;		// quality ������,��ʱ����
	public	float fRotAngle;	// degree unit [0, 359]
	public static class ByValue extends s_FRFace implements
	Structure.ByValue {
	}

	public static class ByReference extends s_FRFace implements
	Structure.ByReference {
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[]{"faceRect","confidence","quality","fRotAngle"});
	}
}
