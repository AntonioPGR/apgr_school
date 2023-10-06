package com.antoniopgr.menus;

import com.antoniopgr.dao.PostsDAO;
import com.antoniopgr.models.Post;
import com.antoniopgr.models.User;
import com.antoniopgr.utils.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostsMenu {

	PostsDAO posts_dao = new PostsDAO();

	public void createPost(){
		Scanner scanner = new Scanner(System.in);
		Screen.printLn("Qual o titulo do seu Post?");
		String title = scanner.nextLine();
		Screen.printLn("Qual o Conteudo do seu Post?");
		String content = scanner.nextLine();
		Post post = new Post(title, content, new ArrayList<>(), new User(8));
		posts_dao.create(post);
	}

	public void listPosts() {
		int n_of_posts = 10;
		Screen.printLn("Aqui vai uma lista dos "+ n_of_posts+" posts mais recentes: ");
		List<Post> post_list = posts_dao.getLastPosts(n_of_posts);
		post_list.forEach(element -> {
			Screen.printLn("--" + element.getTitle());
			Screen.printLn(element.getContent());
			Screen.printLn("Wrote By:" + element.getAuthor().getFirstName());
		});
	}

	public void searchByTitle() {

	}
}
