package Assignment1PLC23WS;

import java.util.ArrayList;
import java.util.List;

public class ArticleManagement {

	private ArticleDAO articleDAO;

	public ArticleManagement(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public List<Article> getAllArticles() {
		return articleDAO.getArticleList();
	}

	public Article getArticle(int id) {
		return articleDAO.getArticle(id);
	}

	public void addNewArticle(Article article) {
		articleDAO.saveArticle(article);
	}

	public void deleteArticle(int id) {
		articleDAO.deleteArticle(id);
	}

	public int getTotalNumberOfArticles() {
		return articleDAO.getArticleList().size();
	}

	public int getTotalNumberOfBooks() {
		int counter = 0;
		for (Article elem : articleDAO.getArticleList()) {
			if (elem instanceof Book) {
				++counter;
			}
		}

		return counter;
	}

	public int getTotalNumberOfDVDs() {
		int counter = 0;
		for (Article elem : articleDAO.getArticleList()) {
			if (elem instanceof DVD) {
				++counter;
			}
		}
		return counter;
	}

	public double getMeanPrice() {
		double meanPrice = 0;
		double sum = 0;
		List<Article> articles = articleDAO.getArticleList();
		for (Article elem : articles) {
			sum = sum + elem.getPrice();
		}

		meanPrice = sum / articles.size();
		return meanPrice;
	}

	public List<Integer> getOldestArticles() {
		List<Article> articles = articleDAO.getArticleList();
		int oldestYear = Integer.MAX_VALUE;
		List<Integer> oldestIds = new ArrayList<>();
		for (Article elem : articles) {
			if (elem.getReleasYear() < oldestYear) {
				oldestYear = elem.getReleasYear();
				oldestIds.clear();
				oldestIds.add(elem.getId());
			} else if (elem.getReleasYear() == oldestYear) {
				oldestIds.add(elem.getId());
			}
		}
		return oldestIds;
	}

}
