package piscine;

import java.time.LocalDateTime;

public class Utilisation {
	private LocalDateTime dateUtilisation;
	private Code code; // cle etrangere
	private Piscine piscine; // cle etrangere

	public Utilisation(LocalDateTime dateUtilisation, Code code, Piscine piscine) {
		super();
		this.dateUtilisation = dateUtilisation;
		this.code = code;
		this.piscine = piscine;
	}

	public LocalDateTime getDateUtilisation() {
		return dateUtilisation;
	}

	public void setDateUtilisation(LocalDateTime dateUtilisation) {
		this.dateUtilisation = dateUtilisation;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Piscine getPiscine() {
		return piscine;
	}

	public void setPiscine(Piscine piscine) {
		this.piscine = piscine;
	}

	@Override
	public String toString() {
		return "Utilisation [dateUtilisation=" + dateUtilisation + ", code=" + code + ", piscine=" + piscine + "]";
	}

}