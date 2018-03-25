package com.web.CurrencyFair.endpoints.modules;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class TradeMessage {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int primaryKey;
	@Column
	private int userId;
	@Column 					
	private String currencyFrom;
	@Column 			
	private String currencyTo;
	@Column 				
	private int amountSell;
	@Column 				
	private int amountBuy;
	@Column 				
	private double rate;
	@Column 					
	private String timePlaced;
	@Column	
	private String originatingCountry;
	
	public TradeMessage() {
		super();
	}	
	
	public TradeMessage(int userId, String currencyFrom, String currencyTo, int amountSell, int amountBuy, double rate,
			String timePlaced, String originatingCountry) {
		super();
		this.userId = userId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amountSell = amountSell;
		this.amountBuy = amountBuy;
		this.rate = rate;
		this.timePlaced = timePlaced;
		this.originatingCountry = originatingCountry;
	}


	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCurrencyFrom() {
		return currencyFrom;
	}
	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public String getCurrencyTo() {
		return currencyTo;
	}
	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}
	public int getAmountSell() {
		return amountSell;
	}
	public void setAmountSell(int amountSell) {
		this.amountSell = amountSell;
	}
	public int getAmountBuy() {
		return amountBuy;
	}
	public void setAmountBuy(int amountBuy) {
		this.amountBuy = amountBuy;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getTimePlaced() {
		return timePlaced;
	}
	public void setTimePlaced(String timePlaced) {
		this.timePlaced = timePlaced;
	}
	public String getOriginatingCountry() {
		return originatingCountry;
	}
	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	@Override
	public String toString() {
		return "TradeMessage [primaryKey=" + primaryKey + ", userId=" + userId + ", currencyFrom=" + currencyFrom
				+ ", currencyTo=" + currencyTo + ", amountSell=" + amountSell + ", amountBuy=" + amountBuy + ", rate="
				+ rate + ", timePlaced=" + timePlaced + ", originatingCountry=" + originatingCountry + "]";
	}

	
	
}
