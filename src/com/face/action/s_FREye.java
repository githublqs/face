package com.face.action;


import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class s_FREye extends Structure {
	/**
	 * 
	 */
	public int xLeft;			// xLeft�������ĵ�����
	public int yLeft;			// yLeft �������ĵ������
	public int leftWd;			// leftWd ���ۿ��
	public int leftHt;			// leftHt ���۸߶�
	public int xRight;			// xRight �������ĵ�����
	public int yRight;			// yRight �������ĵ������
	public	int rightWd;		// rightWd ���ۿ��
	public	int rightHt;		// rightHt ���۸߶�
	public	int confidence;		// confidence ���ۺ����ۼ��������Ŷ�,��0��100,0���,100���
	public	int lQuality;		// lQuality rQuality ������,��ʱ����
	public	int rQuality;
	public static class ByValue extends s_FREye implements
	Structure.ByValue {
	}

	public static class ByReference extends s_FREye implements
	Structure.ByReference {
	}
	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[]{"xLeft","yLeft","leftWd","leftHt",
				"xRight","yRight","rightWd","rightHt","confidence","lQuality","rQuality"});
	}
}
