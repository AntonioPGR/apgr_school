package com.antoniopgr.menus;

import com.antoniopgr.utils.Screen;

import java.util.Scanner;

public class MainMenu {
	private final Scanner scann = new Scanner(System.in);
	private final PostsMenu posts_menu = new PostsMenu();
	private final CategoriesMenu categories_menu = new CategoriesMenu();
	//private final TagsMenu tags_menu = new TagsMenu();
	private final EndMenu end_menu = new EndMenu();

	public void execute(){
		int option = choseOption();
		executeOption(option);
	}

	private void displayOptions(){
		Screen.printHeader("MENU DE OPÇÕES");
		Screen.printLn("1- Ver posts;");
		Screen.printLn("2- Criar posts;");
		Screen.printLn("Digite '-1' para sair;");
		Screen.line();
		Screen.print("O que deseja fazer hoje: ");
	}

	private int choseOption() {
		int chosen_option;
		while (true){
			try {
				displayOptions();
				chosen_option = Integer.parseInt(scann.next());
				break;
			} catch (NumberFormatException e){
				Screen.printLn("Insira um numero válido!");
				Screen.jumpLine();
			}
		}
		return chosen_option;
	}

	private void executeOption(int option){
		switch (option){
			case 1:
				posts_menu.listPosts();
				break;
			case 2:
				posts_menu.createPost();
				break;
			/*
			case 2:
				categories_menu.searchByCategory();
				break;
			case 3:
				tags_menu.searchByTag();
				break;
			case 4:
				posts_menu.searchByTitle();
				break;*/
			case -1:
				end_menu.endApp();
				break;
			default:
				Screen.printLn("Você escolheu uma opção invalida! tente denovo!");
				execute();
		}
	}

}
