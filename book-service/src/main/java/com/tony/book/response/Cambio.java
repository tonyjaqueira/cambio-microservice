package com.tony.book.response;

import java.io.Serializable;

public class Cambio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	
	private String from;

	private String to;
	
	private Double conversionFactor;
	
	private Double conversuinValue;
	
	private String environment;
	
	public Cambio(Long id, String from, String to, Double conversionFactor, Double conversuinValue,
			String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionFactor = conversionFactor;
		this.conversuinValue = conversuinValue;
		this.environment = environment;
	}
	
	public Cambio() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getconversionFactor() {
		return conversionFactor;
	}

	public void setconversionFactor(Double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public Double getConversuinValue() {
		return conversuinValue;
	}

	public void setConversuinValue(Double conversuinValue) {
		this.conversuinValue = conversuinValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conversionFactor == null) ? 0 : conversionFactor.hashCode());
		result = prime * result + ((conversuinValue == null) ? 0 : conversuinValue.hashCode());
		result = prime * result + ((environment == null) ? 0 : environment.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cambio other = (Cambio) obj;
		if (conversionFactor == null) {
			if (other.conversionFactor != null)
				return false;
		} else if (!conversionFactor.equals(other.conversionFactor))
			return false;
		if (conversuinValue == null) {
			if (other.conversuinValue != null)
				return false;
		} else if (!conversuinValue.equals(other.conversuinValue))
			return false;
		if (environment == null) {
			if (other.environment != null)
				return false;
		} else if (!environment.equals(other.environment))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
	

}
