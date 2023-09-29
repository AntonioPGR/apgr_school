package com.antoniopgr;


import com.antoniopgr.menus.MainMenu;
import com.antoniopgr.utils.Screen;


public class Main {

	public static void main(String[] args) {
		MainMenu main_menu = new MainMenu();

		Screen.printHeader("Pacheco's Blog");
		Screen.jumpLine();
		Screen.printLn("Bem vindo ao blog do Antonio!");
		main_menu.execute();
	}
}