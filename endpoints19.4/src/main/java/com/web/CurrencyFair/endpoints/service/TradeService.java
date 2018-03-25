package com.web.CurrencyFair.endpoints.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.web.CurrencyFair.endpoints.helper.MyHelper;
import com.web.CurrencyFair.endpoints.modules.TradeMessage;
import com.web.CurrencyFair.endpoints.resources.beans.TradeMessageFilterBean;

public class TradeService {

	/*
	 * This file provides connection to the DB for the TradeMessage object
	 */
	Configuration configuration = null;
	StandardServiceRegistryBuilder ssrb = null;
	SessionFactory sessionFactory = null;
	Session session = null;

	public TradeService() {
		super();
		this.init();
	}

	private void init() {
		configuration = new Configuration().configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	public List<TradeMessage> getTradeMessages(TradeMessageFilterBean filterBean) {
		List<TradeMessage> queryResult = new ArrayList<>();

		// http://localhost:8080/endpoints/webapi/jsonTradeEndpoint?start=0&size=0&userId=0&year=0&originatingCountry=0&rate=0&currencyFrom=0&currencyTo=0&amountSell=0&amountBuy=0&timePlaced=0
		try {

			// Start Transaction with the DB
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(TradeMessage.class);
			
			if(filterBean.getPrimaryKey() != 0) {
				//when primary key is present, then full filter not needed.//TODO add option for exact instance
				TradeMessage tradeMessage = session.get(TradeMessage.class, filterBean.getPrimaryKey());

				queryResult.add(tradeMessage);
			}
			else {
				
			if (filterBean.getUserId() != 0) {
				criteria.add(Restrictions.eqOrIsNull("userId", filterBean.getUserId()));
			}
			if (filterBean.getCurrencyFrom() != null && filterBean.getCurrencyFrom() != ""
					&& filterBean.getCurrencyFrom() != "null") {
				criteria.add(Restrictions.eqOrIsNull("currencyFrom", filterBean.getCurrencyFrom()));
			}
			if (filterBean.getCurrencyTo() != null && filterBean.getCurrencyTo() != ""
					&& filterBean.getCurrencyTo() != "null") {
				criteria.add(Restrictions.eqOrIsNull("currencyTo", filterBean.getCurrencyTo()));
			}
			if (filterBean.getAmountSell() != 0) {
				criteria.add(Restrictions.eqOrIsNull("amountSell", filterBean.getAmountSell()));
			}
			if (filterBean.getAmountBuy() != 0) {
				criteria.add(Restrictions.eqOrIsNull("amountBuy", filterBean.getAmountBuy()));
			}
			if (filterBean.getRate() != null && filterBean.getRate() != 0) {
				criteria.add(Restrictions.eqOrIsNull("rate", filterBean.getRate()));
			}
			if (filterBean.getTimePlaced() != null) {			
				criteria.add(Restrictions.ilike("timePlaced", filterBean.getTimePlaced(), MatchMode.ANYWHERE));
			}
			if (filterBean.getOriginatingCountry() != null && filterBean.getOriginatingCountry() != ""
					&& filterBean.getOriginatingCountry() != "null") {
				criteria.add(Restrictions.eqOrIsNull("originatingCountry", filterBean.getOriginatingCountry()));
			}
			if (filterBean.getStart() != 0) {
				criteria.setFirstResult(filterBean.getStart());
			}
			if (filterBean.getSize() != 0) {
				criteria.setMaxResults(filterBean.getSize());
			}
			
			criteria.addOrder(Order.asc("primaryKey"));
			queryResult = criteria.list();
		}
			
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// session.close();
		}

		return queryResult;
	}

	public TradeMessage getTradeMessage(int primaryKey) {

		session = sessionFactory.openSession();
		session.beginTransaction();

		TradeMessage tradeMessage = session.get(TradeMessage.class, primaryKey);

		session.getTransaction().commit();
		session.close();
		return tradeMessage;
	}

	public List<TradeMessage> createTradeMessages(List<TradeMessage> tradeMessages) {

		List<TradeMessage> createdTradeMessages = new ArrayList<>();
		int createdId = 0;

		session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 0; i < tradeMessages.size(); i++) {
			createdId = (Integer) session.save(tradeMessages.get(i));
			System.out.println(createdId);
			createdTradeMessages.add((TradeMessage) session.get(TradeMessage.class, createdId));
		}

		session.getTransaction().commit();
		session.close();

		return createdTradeMessages;
	}

	public List<TradeMessage> updateTradeMessages(List<TradeMessage> tradeMessages) {

		TradeMessage bufferTradeMessage;
		TradeMessage dbInstace;
		List<TradeMessage> dbResult = new ArrayList<>();
		List<TradeMessage> updatedList = new ArrayList<>();

		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria;// = session.createCriteria(TradeMessage.class);

		for (int i = 0; i < tradeMessages.size(); i++) {

			bufferTradeMessage = tradeMessages.get(i);
			criteria = session.createCriteria(TradeMessage.class);
			//Projection p = Projections.property("primaryKey");// get only the primaryKey for optimisations

			System.out.println("Pending Update: " + bufferTradeMessage.toString());
			if (bufferTradeMessage.getPrimaryKey() != 0) {
				dbInstace = session.get(TradeMessage.class, bufferTradeMessage.getPrimaryKey());
				dbInstace.setAmountBuy(bufferTradeMessage.getAmountBuy());
				dbInstace.setAmountSell(bufferTradeMessage.getAmountSell());
				dbInstace.setCurrencyFrom(bufferTradeMessage.getCurrencyFrom());
				dbInstace.setCurrencyTo(bufferTradeMessage.getCurrencyTo());
				dbInstace.setOriginatingCountry(bufferTradeMessage.getOriginatingCountry());
				dbInstace.setRate(bufferTradeMessage.getRate());
				dbInstace.setTimePlaced(bufferTradeMessage.getTimePlaced());
				dbInstace.setUserId(bufferTradeMessage.getUserId());

				updatedList.add(dbInstace);
				System.out.println(dbInstace);
				session.update((TradeMessage) dbInstace);
			} else {
				criteria.add(Restrictions.eq("userId", bufferTradeMessage.getUserId()));
				criteria.add(Restrictions.eq("currencyFrom", bufferTradeMessage.getCurrencyFrom()));
				criteria.add(Restrictions.eq("currencyTo", bufferTradeMessage.getCurrencyTo()));
				criteria.add(Restrictions.eq("amountSell", bufferTradeMessage.getAmountSell()));
				criteria.add(Restrictions.eq("amountBuy", bufferTradeMessage.getAmountBuy()));
				criteria.add(Restrictions.eq("rate", bufferTradeMessage.getRate()));
				criteria.add(Restrictions.eq("timePlaced", bufferTradeMessage.getTimePlaced()));
				criteria.add(Restrictions.eq("originatingCountry", bufferTradeMessage.getOriginatingCountry()));

				dbResult = criteria.list();

				for (int j = 0; j < dbResult.size(); j++) {
					System.out.println("dbResult: " + dbResult.get(j).toString());
				}
				if (dbResult.isEmpty()) {
					System.out.println("no matching TradeMessage for Criteria");
				} else if (dbResult.size() == 1) {
					dbInstace = session.get(TradeMessage.class, dbResult.get(0).getPrimaryKey());
					dbInstace.setAmountBuy(bufferTradeMessage.getAmountBuy());
					dbInstace.setAmountSell(bufferTradeMessage.getAmountSell());
					dbInstace.setCurrencyFrom(bufferTradeMessage.getCurrencyFrom());
					dbInstace.setCurrencyTo(bufferTradeMessage.getCurrencyTo());
					dbInstace.setOriginatingCountry(bufferTradeMessage.getOriginatingCountry());
					dbInstace.setRate(bufferTradeMessage.getRate());
					dbInstace.setTimePlaced(bufferTradeMessage.getTimePlaced());
					dbInstace.setUserId(bufferTradeMessage.getUserId());

					updatedList.add(dbInstace);
					System.out.println(dbInstace);
					session.update((TradeMessage) dbInstace);
				} else if (dbResult.size() > 1) {
					System.out.println("multible criteria Found for passed instance");
				}
			}
		}

		session.getTransaction().commit();
		session.close();

		return updatedList;
	}

	public void deleteTradeMessages(List<TradeMessage> tradeMessages) {

		TradeMessage bufferTradeMessage;
		TradeMessage dbInstace;
		List<TradeMessage> dbResult = new ArrayList<>();
		List<TradeMessage> updatedList = new ArrayList<>();

		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria;// = session.createCriteria(TradeMessage.class);

		for (int i = 0; i < tradeMessages.size(); i++) {

			bufferTradeMessage = tradeMessages.get(i);
			criteria = session.createCriteria(TradeMessage.class);

			if (bufferTradeMessage.getPrimaryKey() != 0) {

				dbInstace = session.get(TradeMessage.class, bufferTradeMessage.getPrimaryKey());
				session.delete((TradeMessage) dbInstace);
			} else {

				criteria.add(Restrictions.eq("userId", bufferTradeMessage.getUserId()));
				criteria.add(Restrictions.eq("currencyFrom", bufferTradeMessage.getCurrencyFrom()));
				criteria.add(Restrictions.eq("currencyTo", bufferTradeMessage.getCurrencyTo()));
				criteria.add(Restrictions.eq("amountSell", bufferTradeMessage.getAmountSell()));
				criteria.add(Restrictions.eq("amountBuy", bufferTradeMessage.getAmountBuy()));
				criteria.add(Restrictions.eq("rate", bufferTradeMessage.getRate()));
				criteria.add(Restrictions.eq("timePlaced", bufferTradeMessage.getTimePlaced()));
				criteria.add(Restrictions.eq("originatingCountry", bufferTradeMessage.getOriginatingCountry()));

				dbResult = criteria.list();

				for (int j = 0; j < dbResult.size(); j++) {
					System.out.println("dbResult: " + dbResult.get(j).toString());
				}
				if (dbResult.isEmpty()) {
					System.out.println("no matching TradeMessage for Criteria");
				} else if (dbResult.size() == 1) {
					dbInstace = session.get(TradeMessage.class, dbResult.get(0).getPrimaryKey());
					session.delete((TradeMessage) dbInstace);
					;
				} else if (dbResult.size() > 1) {
					System.out.println("multible criteria Found for passed instance");
				}
			}
		}

		session.getTransaction().commit();
		session.close();

	}

	public void deleteTradeMessage(int primaryKey) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(session.get(TradeMessage.class, primaryKey));

		session.getTransaction().commit();
		session.close();
	}

	public List<TradeMessage> generateTradeMessages(int create) {
		List<TradeMessage> generationResult = new ArrayList<>();
		TradeMessage trademessage;
		List<String> months = Arrays.asList("JAN", "FEB", "MAR","APR","MAY","JUN","JUL","AUG","SET","OCT","NOV","DEC");
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < create; i++) {
			// make new record w
			
			System.out.println("monthsSize:"+months.size());
			String date = ""+(int) Math.round(Math.random() * (27 - 0) +1) + "-" +
					months.get((int) Math.round(Math.random() * (11 - 0))) + "-" +
					String.format("%02d", (int) Math.round(Math.random() * (18 - 0))) + " " +
					String.format("%02d",(int) Math.round(Math.random() * (23 - 0))) + ":" +
					String.format("%02d",(int) Math.round(Math.random() * (59 - 0))) + ":" +
					String.format("%02d",(int) Math.round(Math.random() * (59 - 0)));
					
			trademessage = new TradeMessage((int) Math.round(Math.random() * (100000 - 1000)), "EUR", "EUR",
					(int) Math.round(Math.random() * (10000 - 0)), (int) Math.round(Math.random() * (10000 - 0)),
					Math.random() * (100 - 0.01), date, "IE");

			// TODO populate db while testing for testing data
			session.save(trademessage);
			generationResult.add(trademessage);
			System.out.println("This trade message was saved to the DB: \n" + trademessage.toString());
		}

		session.getTransaction().commit();
		session.close();

		return generationResult;
	}

}
