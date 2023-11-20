package Assignment1PLC23WS;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class SerializedArticleDAO implements ArticleDAO {

	private String fileName;
	private List<Article> articles;

	public SerializedArticleDAO(String fileName) {
		this.fileName = fileName;
		this.articles = new ArrayList<Article>();

		File f = new File(this.fileName);
		if (!f.exists()) {
			saveArticles();
		}
		loadArticles();
	}

	private void loadArticles() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			articles = (List<Article>) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error during deserialization.");
			System.exit(1);
		}
	}

	private void saveArticles() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(articles);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.err.println("Error during serialization.");
			System.exit(1);
		}
	}

	@Override
	public List<Article> getArticleList() {
		return articles;
	}

	@Override
	public Article getArticle(int id) {
		for (Article elem : articles) {
			if (elem.getId() == id)
				return elem;
		}
		return null;
	}

	@Override
	public void saveArticle(Article article) {
		for (Article exist : articles) {
			if (exist.getId() == article.getId()) {
				throw new IllegalArgumentException("Error: Article already exists. (id=" + article.getId() + ")");
			}
		}
		articles.add(article);
		saveArticles();
	}

	@Override
	public void deleteArticle(int id) {
		Article articleToRemove = null;
		for (Article elem : articles) {
			if (elem.getId() == id) {
				articleToRemove = elem;
				break;
			}
		}
		if (articleToRemove != null) {
			articles.remove(articleToRemove);
			saveArticles();

		} else {
			throw new IllegalArgumentException("Error: Article not found. (id=" + id + ")");
		}
	}

}
