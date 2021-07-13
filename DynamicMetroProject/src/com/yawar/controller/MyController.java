package com.yawar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metro.bean.MetroCard;
import com.metro.bean.Station;
import com.metro.bean.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import com.metro.service.MetroService;
import com.station.service.StationService;

import java.util.Collection;
@Controller
public class MyController {
	
	boolean swipedIn = false;
	int idCard;
	
	@Autowired
	private MetroService metroService;
	@Autowired
	private StationService stationService;
	
	
	
	@RequestMapping("/")
	public String openingPageController() {
		return "index";
	}
	@RequestMapping("/enterId")
	public String IdPageController() {
		return "enterId";
	}
	

	@RequestMapping("/checkBal")
	public String checkBalPageController() {
		return "checkBal";
	}
	@RequestMapping("/buyCardPage")
	public String buyCardPageController() {
		return "buyCard";
	}
	@RequestMapping("/surrenderCard")
	public String surrenderCardPageController() {
		return "surrenderCard";
	}
	@RequestMapping("/adminPanel")
	public String adminPanelPageController() {
		return "hasCard";
	}

	@RequestMapping("/addBal")
	public String addBalancePageController() {
		return "addBal";
	}
	@RequestMapping("/swipeIn")
	public String swipeInPageController() {
		return "swipeIn";
	}
	@RequestMapping("/swipeOut")
	public ModelAndView swipeOutPageController() throws ClassNotFoundException, SQLException {
		Collection<Station> allStations = null;
		try {
			allStations = stationService.getAllStations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("swipeOut", "allStations",  allStations);
	}
	
	
	@RequestMapping("/checkBalance")
	public ModelAndView checkBalanceController(@RequestParam("cardId") int cardId) {
		try {
			int balance = metroService.balance(cardId);
			
				return new ModelAndView("output","message","Your Card with ID "+cardId+" has balance: "+balance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("output","message","Something went potentially wrong! Please try again.");
		
	}
	
	@RequestMapping("/addBalance")
	public ModelAndView addBalanceController(@RequestParam("cardId") int cardId, @RequestParam("bal") int bal) {
		try {
			metroService.addBalance(cardId, bal);
			int balance = metroService.balance(cardId);
			
				return new ModelAndView("output","message","Success! New Balance is: "+balance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("output","message","Something went potentially wrong! Please try again.");
		
	}
	
	@RequestMapping("/transactionSheet")
	public ModelAndView getAllTransactionController(@RequestParam("cardId") int cardId) {
		Collection<Transaction> card_Transactions =null;
		try {
			card_Transactions = metroService.getAllTransactions(cardId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("transactionSheet", "card_Transactions", card_Transactions);
	}
	
	
	@RequestMapping("/buyCard")
	public ModelAndView buyCardController(@RequestParam("balance") int balance) {
		try {
			
			int id = metroService.addCard(balance);
			
				return new ModelAndView("output","message","Please collect Your New Metro Card with CardID: "+id+"and balance: "+balance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("output","message","Something went potentially wrong! Please try again.");
		
	}
	
	@RequestMapping("/surrenderYourCard")
	public ModelAndView surrenderCardController(@RequestParam("cardID") int cardID) {
		try {
			
			MetroCard cardd = null;
			cardd = metroService.getCard(cardID);
			int balSurrender = metroService.balance(cardID);
			if(metroService.removeCard(cardID)) {
				if(cardd == null) {
					return new ModelAndView("output","message","Card doesn't exist!");
				}else
				return new ModelAndView("output","message","Card surrendered Success! Please collect Rps: "+balSurrender);
			
				
			}
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("output","message","Something went potentially wrong! Please try again.");
		
	}

	
	@RequestMapping("/hasCard")
	public String hasCardPageController() {
		if(!swipedIn) {
			return "hasCard";	
		} return "hasCardSwipedIn";
		
	}
	@RequestMapping("/travel")
	public String travelCardPageController() {
		return "travel";
	}
	
//	@RequestMapping("/cardIntoSwipeIn")
//	public void swipeInCardController(@RequestParam("cardId") int cardId) throws ClassNotFoundException, SQLException {
//		
//			takeStationID(cardId);
//			
//		
//	}

	
	
	
	@RequestMapping("/stationList")
	public ModelAndView takeStationID(int cardId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		idCard = cardId;
		swipedIn = true;
		Collection<Station> allStations = null;
		try {
			allStations = stationService.getAllStations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("stationList", "allStations",  allStations);
	}

	@RequestMapping("/stationId")
	public ModelAndView stationIdController(@RequestParam("stationId") int stationId) throws ClassNotFoundException, SQLException {
		Collection<Station> allStations = null;
		try {
			
			if(metroService.swipeIn(idCard,stationId)) {
				return new ModelAndView("output","message","CheckIn SucessFull at stationId: "+stationId);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ModelAndView("output","message","Something went potentially wrong! Please try again.");
		
	}

	
	@RequestMapping("/swipeOutStation")
	public ModelAndView swipeOutController(@RequestParam("stationOutId") int stationOutId) throws ClassNotFoundException, SQLException {
		swipedIn = false;
		try {
			
			if(metroService.swipeOut(idCard,stationOutId)) {
				return new ModelAndView("output","message","CHECK OUT AT StationID: "+stationOutId);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ModelAndView("output","message","Something went potentially wrong! Please try again.");
		
	}


//	Collection<Station> stations = null;
//	try {
//		stations = stationService.getAllStations();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	try {
//		metroService.swipeIn(id,stationId);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();  metroService.swipeOut(id,stationId1);
	}
	
	
	

