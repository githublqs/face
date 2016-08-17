package com.face.action;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;


public class s_FRPoint extends Structure {
	/**
	 * 
	 */
	public int x;
	public int y;
	public static class ByValue extends s_FRPoint implements
	Structure.ByValue {
	}

	public static class ByReference extends s_FRPoint implements
	Structure.ByReference {
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[]{"x","y"});
	}
}
