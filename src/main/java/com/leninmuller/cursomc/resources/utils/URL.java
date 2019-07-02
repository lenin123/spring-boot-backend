package com.leninmuller.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String paramUrl){
		String[] vetParam = paramUrl.split(",");
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i < vetParam.length; i++) {
			list.add(Integer.parseInt(vetParam[i]));
		}		
		
		return list;
	}
}
