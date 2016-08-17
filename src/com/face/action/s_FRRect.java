package com.face.action;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class s_FRRect extends Structure {
	public int left;
	public int top;
	public	int right;
	public	int bottom;
	public static class ByValue extends s_FRRect implements
	Structure.ByValue {
	}

	public static class ByReference extends s_FRRect implements
	Structure.ByReference {
	}

	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[]{"left","top","right","bottom"});
	}
}
