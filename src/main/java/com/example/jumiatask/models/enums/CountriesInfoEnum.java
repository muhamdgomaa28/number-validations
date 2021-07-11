package com.example.jumiatask.models.enums;

public enum CountriesInfoEnum {
	CAMEROON("Cameroon", "237", "\\(237\\)\\s?[2368]\\d{7,8}$"),
	ETHIOPIA("Ethiopia", "251", "\\(251\\)\\s?[1-59]\\d{8}$"),
	MOROCCO("Morocco", "212", "\\(212\\)\\s?[5-9]\\d{8}$"),
	MOZABIQUE("Mozambique", "258", "\\(258\\)\\s?[28]\\d{7,8}$"),
	UGANDA("Uganda", "256", "\\(256\\)\\s?\\d{9}$");





	private final String country;
	private final String countryCode;
	private final String regex;

    private CountriesInfoEnum(final String country, final String countryCode, final String regex) {
		this.country = country;
		this.countryCode = countryCode;
		this.regex = regex;
	}

	public static CountriesInfoEnum getCountryByCode(final String countryCode) {

		for (CountriesInfoEnum country : CountriesInfoEnum.values()) {
			if (countryCode.equals(country.getCountryCode())) {
				return country;
			}
		}
		return null;
	}



	public String getCountry() {
		return country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getRegex() {
		return regex;
	}
}
