package Assignment1PLC23WS;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Year;
import java.util.Date;

public abstract class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String titel;
	private int releaseYear;
	private String publisher;
	private double basePrice;

	public Article(int id, String titel, int releaseYear, String publisher, double basePrice) {
		int currentYear = Year.now().getValue();
		if (releaseYear > currentYear) {
			throw new IllegalArgumentException("Error: Invalid release year.");
		}
		if (publisher.length() < 1 || titel.length() < 1) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}

		this.id = id;
		this.titel = titel;
		this.releaseYear = releaseYear;
		this.publisher = publisher;
		this.basePrice = basePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public int getReleasYear() {
		return releaseYear;
	}

	public void setReleasYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	int getAge() {
		int currentYear = Year.now().getValue();
		int res = currentYear - releaseYear;
		return res;
	}

	abstract double getDiscount();

	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setDecimalSeparator('.');
		return new DecimalFormat("#.##", decimalFormatSymbols);
	}

	double getPrice() {
		double price = basePrice - ((basePrice * getDiscount()) / 100);
		DecimalFormat df = Article.getDecimalFormat();
		String formatetPrice = df.format(price);
		return Double.parseDouble(formatetPrice);
	}

}
