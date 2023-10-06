package com.antoniopgr.utils;

import java.util.Arrays;

public class Screen {

	public static void printLn(Object o){
		String s = String.valueOf(o);
		System.out.println(s);
	}

	public static void print(Object o){
		String s = String.valueOf(o);
		System.out.print(s);
	}

	public static void line(){
		printLn("-----------------------------");
	}

	public static void printHeader(String title){
		line();
		printLn(title);
		line();
	};

	public static void jumpLine() {
		printLn("");
	}
}
