package com.antoniopgr.menus;

import com.antoniopgr.utils.Screen;
import java.util.Scanner;

public class MainMenu {
	private final Scanner scann = new Scanner(System.in);
	private final PostsMenu posts_menu = new PostsMenu();
	private final CategoriesMenu categories_menu = new CategoriesMenu();
	private final TagsMenu tags_menu = new TagsMenu();
	private final EndMenu end_menu = new EndMenu();

	public void execute(){
		displayOptions();
		int option = choseOption();
		executeOption(option);
	}

	private void displayOptions(){
		Screen.jumpLine();
		Screen.printLn("1- Ver posts;");
		Screen.printLn("2- Filtrar por categoria;");
		Screen.printLn("3- Filtrar por tag; ");
		Screen.printLn("4- Buscar por titulo posts;");
		Screen.printLn("Digite '-1' para sair;");
		Screen.jumpLine();
	}

	private int choseOption(){
		int chosen_option = 0;
		try {
			Screen.printLn("O que deseja fazer hoje: ");
			chosen_option = Integer.parseInt(scann.next());
		} catch (NumberFormatException e){
			Screen.printLn("Por favor, insira um numero válido!");
			choseOption();
		}
		return chosen_option;
	}

	private void executeOption(int option){
		Screen.printLn(option);
		switch (option){
			case 1:
				posts_menu.listPosts();
				break;
			case 2:
				categories_menu.searchByCategory();
				break;
			case 3:
				tags_menu.searchByTag();
				break;
			case 4:
				posts_menu.searchByTitle();
				break;
			case -1:
				end_menu.endApp();
				break;
			default:
				Screen.printLn("Você escolheu uma opção invalida! tente denovo!");
				execute();
		}
	}

}
