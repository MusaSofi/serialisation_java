package Assignment1PLC23WS;

public class DVD extends Article {
	private int length;
	private int ageRating;

	public DVD(int id, String titel, int releaseYear, String publisher, double basePrice, int length, int ageRating) {

		super(id, titel, releaseYear, publisher, basePrice);
		if (ageRating == 1 || ageRating == 2 || ageRating == 3 || ageRating == 4 || ageRating == 5) {
			throw new IllegalArgumentException("Error: Invalid age rating.");
		}
		this.setLength(length);
		this.setAgeRating(ageRating);

	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
	}

	@Override
	double getDiscount() {
		double discount = 0;
		if (ageRating == 0) {
			discount = 20;
		} else if (ageRating >= 6 && ageRating < 12) {
			discount = 15;
		} else if (ageRating >= 12 && ageRating < 16)
			discount = 10;
		else if (ageRating >= 16 && ageRating < 18) {
			discount = 5;
		} else if (ageRating >= 18) {
			discount = 0;
		}
		return discount;
	}

	@Override
	public String toString() {
		String res = "Type:       " + "DVD" + "\n";
		res = res + "Id:         " + getId() + "\n";
		res = res + "Titel:      " + getTitel() + "\n";
		res = res + "Year:       " + getReleasYear() + "\n";
		res = res + "Publisher:  " + getPublisher() + "\n";
		res = res + "Base price: " + getBasePrice() + "\n";
		res = res + "Price:      " + getPrice() + "\n";
		res = res + "Length:     " + getLength() + "\n";
		res = res + "Age rating: " + getAgeRating();
		return res;
	}

}
