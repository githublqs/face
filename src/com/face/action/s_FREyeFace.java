
package com.face.action;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;


public class s_FREyeFace extends Structure  {
	/**
	 * 
	 */
	public s_FRFace faceRect;	// �������壬�ֱ�Ϊ��������������������
	public s_FRPoint lEyePt;   //
	public s_FRPoint rEyePt;   //
	public s_FREye   sFREye;
	public static class ByValue extends s_FREyeFace implements
	Structure.ByValue {
	}

	public static class ByReference extends s_FREyeFace implements
	Structure.ByReference {
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[]{"faceRect","lEyePt","rEyePt","sFREye"});
	}
}
