package com.web.CurrencyFair.endpoints.resources.beans;

import java.util.Date;

import javax.ws.rs.BeanParam;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TradeMessageFilterBean {

	private @QueryParam("start") int start;
	private @QueryParam("size") int size;
	private @QueryParam("primaryKey") int primaryKey;
	private @QueryParam("userId") int userId;
	private @QueryParam("originatingCountry") String originatingCountry;
	private @QueryParam("rate") Double rate;
	private @QueryParam("currencyFrom") String currencyFrom;
	private @QueryParam("currencyTo") String currencyTo;
	private @QueryParam("amountSell") int amountSell;
	private @QueryParam("amountBuy") int amountBuy;
	private @QueryParam("timePlaced") String timePlaced;

	/*
	 * getters and setters
	 */
	public String getStartName() {// TODO remove
		return this.getClass().getSimpleName();
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// public int getYear() {
	// return year;
	// }
	//
	// public void setYear(int year) {
	// this.year = year;
	// }

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
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

	public String getTimePlaced() {
		return timePlaced;
	}

	public void setTimePlaced(String timePlaced) {
		this.timePlaced = timePlaced;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public String toString() {
		return "start=" + start + "&size=" + size + "&userId=" + userId + "&originatingCountry=" + originatingCountry
				+ "&rate=" + rate + "&currencyFrom=" + currencyFrom + "&currencyTo=" + currencyTo + "&amountSell="
				+ amountSell + "&amountBuy=" + amountBuy + "&timePlaced=" + timePlaced;
	}

}
