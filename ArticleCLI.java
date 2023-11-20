package Assignment1PLC23WS;

import java.io.*;
import java.text.DecimalFormat;
import java.util.List;

public class ArticleCLI {

	public static void main(String[] args) {
		try {
			if (args.length < 2) {
				throw new IllegalArgumentException("Error: Invalid parameter.");
			}

			String fileName = args[0];
			String command = args[1];
			if (command.equals("count")) {
				if (args.length == 2) {
					ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
					ArticleManagement articleManagement = new ArticleManagement(articleDAO);
					System.out.println(articleManagement.getTotalNumberOfArticles());
				} else if (args.length == 3) {
					String type = args[2];
					if (type.equals("book")) {
						ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
						ArticleManagement articleManagement = new ArticleManagement(articleDAO);
						System.out.println(articleManagement.getTotalNumberOfBooks());
					} else if (type.equals("dvd")) {
						ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
						ArticleManagement articleManagement = new ArticleManagement(articleDAO);
						System.out.println(articleManagement.getTotalNumberOfDVDs());
					} else {
						throw new IllegalArgumentException("Error: Invalid parameter.");
					}

				}
			} else if (command.equals("meanprice")) {
				ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
				ArticleManagement articleManagement = new ArticleManagement(articleDAO);
				double meanPrice = articleManagement.getMeanPrice();
				DecimalFormat df = Article.getDecimalFormat();
				System.out.println(df.format(meanPrice));
			} else if (command.equals("oldest")) {
				ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
				ArticleManagement articleManagement = new ArticleManagement(articleDAO);
				for (Integer id : articleManagement.getOldestArticles()) {
					System.out.println("Id: " + id);
				}
			}

			else if (command.equals("list")) {
				if (args.length == 2) {
					ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
					ArticleManagement articleManagement = new ArticleManagement(articleDAO);
					List<Article> helpList = articleManagement.getAllArticles();
					for (Article elem : helpList) {
						System.out.println(elem);
						System.out.println();

					}

				} else {
					try {
						if (args.length == 3) {
							int id = Integer.valueOf(args[2]);
							ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
							ArticleManagement articleManagement = new ArticleManagement(articleDAO);
							Article foundArticle = articleManagement.getArticle(id);
							if (foundArticle == null) {
								throw new IllegalArgumentException("Error: Article not found. (id=" + id + ")");
							} else {
								System.out.println(articleManagement.getArticle(id));
							}

						} else {
							throw new IllegalArgumentException("Error: Invalid parameter.");
						}
					} catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Error: Invalid parameter.");
					}

				}
			} else if (command.equals("delete")) {
				try {
					if (args.length != 3) {
						throw new IllegalArgumentException("Error: Invalid parameter.");
					}
					int id = Integer.valueOf(args[2]);
					ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
					ArticleManagement articleManagement = new ArticleManagement(articleDAO);
					articleManagement.deleteArticle(id);
					System.out.println("Info: Article " + id + " deleted.");

				} catch (NumberFormatException ex) {
					throw new IllegalArgumentException("Error: Invalid parameter.");
				}

			} else if (command.equals("add")) {
				if (args.length == 9) {
					if (args[2].equals("book")) {
						try {
							int id = Integer.valueOf(args[3]);
							String titel = args[4];
							String publisher = args[5];
							int releaseYear = Integer.valueOf(args[6]);
							double price = Double.valueOf(args[7]);
							int pages = Integer.valueOf(args[8]);

							Article book = new Book(id, titel, releaseYear, publisher, price, pages);
							ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
							ArticleManagement articleManagement = new ArticleManagement(articleDAO);
							articleManagement.addNewArticle(book);
							System.out.println("Info: Article " + articleManagement.getArticle(id).getId() + " added.");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Error: Invalid parameter.");
						}

					} else {
						throw new IllegalArgumentException("Error: Invalid parameter.");
					}

				} else if (args.length == 10) {
					if (args[2].equals("dvd")) {
						try {
							int id = Integer.valueOf(args[3]);
							String titel = args[4];
							String publisher = args[5];
							int releaseYear = Integer.valueOf(args[6]);
							double price = Double.valueOf(args[7]);
							int length = Integer.valueOf(args[8]);
							int ageReating = Integer.valueOf(args[9]);

							Article dvd = new DVD(id, titel, releaseYear, publisher, price, length, ageReating);
							ArticleDAO articleDAO = new SerializedArticleDAO(fileName);
							ArticleManagement articleManagement = new ArticleManagement(articleDAO);
							articleManagement.addNewArticle(dvd);
							System.out.println("Info: Article " + articleManagement.getArticle(id).getId() + " added.");

						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Error: Invalid parameter.");
						}
					} else {
						System.err.println("Error: Invalid parameter.");

					}

				} else {
					throw new IllegalArgumentException("Error: Invalid parameter.");

				}

			} else {
				throw new IllegalArgumentException("Error: Invalid parameter.");
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
