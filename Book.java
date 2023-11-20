package Assignment1PLC23WS;

public class Book extends Article {
	private int pages;

	public int getPages() {
		return pages;
	}

	public Book(int id, String titel, int releaseYear, String publisher, double basePrice, int pages) {
		super(id, titel, releaseYear, publisher, basePrice);
		this.pages = pages;
	}

	@Override
	double getDiscount() {
		int age = getAge();
		int discount = age * 5;

		if (discount > 30) {
			discount = 30;
		}
		if (pages > 1000) {
			discount = discount + 3;
		}
		return discount;
	}

	@Override
	public String toString() {
		String res = "Type:       " + "Book" + "\n";
		res = res + "Id:         " + getId() + "\n";
		res = res + "Titel:      " + getTitel() + "\n";
		res = res + "Year:       " + getReleasYear() + "\n";
		res = res + "Publisher:  " + getPublisher() + "\n";
		res = res + "Base price: " + getBasePrice() + "\n";
		res = res + "Price:      " + getPrice() + "\n";
		res = res + "Pages:      " + getPages();
		return res;
	}

}
