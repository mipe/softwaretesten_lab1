package at.ticketline.entity;

public enum ArtikelKategorie {
	TShirt, Poster, CD, DVD, Sonstiges;

	public static String[] toStringArray() {
		return new String[] { "T-Shirt", "Poster", "CD", "DVD", "Sonstiges" };
	}

	public static ArtikelKategorie getValueOf(String value) {
		if (value == null) {
			return null;
		}
		try {
			return ArtikelKategorie.valueOf(value);
		} catch (IllegalArgumentException e) {
			if ("T-Shirt".equals(value)) {
				return ArtikelKategorie.TShirt;
			}
			return null;
		}
	}
}
