package zoho_app;

import java.util.Scanner;

public class App {
	public static void bookTicket(Passenger p) {
		TicketBooker booker=new TicketBooker();
		if(p.age>60&&TicketBooker.availableLowerBerths>0) {
			System.out.println("you are senior citizen allocated Lower Berth");
			booker.bookTicket(p,(TicketBooker.lowerBerthCount.get(0)),"L");
			//remove the booked position from available positions and also decrease available seat of that type
			TicketBooker.lowerBerthCount.remove(0);
			TicketBooker.availableLowerBerths--;
		}else if((p.berthPreference.equals("L")&&TicketBooker.availableLowerBerths>0)||
				(p.berthPreference.equals("M")&&TicketBooker.availableMiddleBerths>0)||
				(p.berthPreference.equals("U")&&TicketBooker.availableUpperBerths>0)) {
			//System.out.println("preferred berth available");
			if (p.berthPreference.equals("L")) {
				System.out.println("Lower Berth Given");
				//call booking function in the ticketbooker class
				booker.bookTicket(p,(TicketBooker.lowerBerthCount.get(0)),"L");
				//remove the booked position from available positions and also decrease available seat of that type
				TicketBooker.lowerBerthCount.remove(0);
				TicketBooker.availableLowerBerths--;
			}else if(p.berthPreference.equals("M")) {
				System.out.println("Middle Berth Given");
				//call booking function in the ticketbooker class
				booker.bookTicket(p,(TicketBooker.middleBerthCount.get(0)),"M");
				//remove the booked position from available positions and also decrease available seat of that type
				TicketBooker.middleBerthCount.remove(0);
				TicketBooker.availableMiddleBerths--;
			}else if(p.berthPreference.equals("U")) {
				System.out.println("Upper Berth Given");
				//call booking function in the ticketbooker class
				booker.bookTicket(p,(TicketBooker.upperBerthCount.get(0)),"U");
				//remove the booked position from available positions and also decrease available seat of that type
				TicketBooker.upperBerthCount.remove(0);
				TicketBooker.availableUpperBerths--;
			}
		}
		//preference not available
		else if(TicketBooker.availableLowerBerths>0) {
			System.out.println("Lower Berth Given");
			//call booking function in the ticketbooker 
			booker.bookTicket(p,(TicketBooker.lowerBerthCount.get(0)),"L");
			TicketBooker.lowerBerthCount.remove(0);
			TicketBooker.availableLowerBerths--;
		}else if(TicketBooker.availableMiddleBerths>0) {
			System.out.println("Middle Berth Given");
			//call booking function in the ticketbooker class
			booker.bookTicket(p,(TicketBooker.middleBerthCount.get(0)),"M");
			//remove the booked position from available positions and also decrease available seat of that type
			TicketBooker.middleBerthCount.remove(0);
			TicketBooker.availableMiddleBerths--;
		}else if(TicketBooker.availableUpperBerths>0) {
			System.out.println("Upper Berth Given");
			//call booking function in the ticketbooker class
			booker.bookTicket(p,(TicketBooker.upperBerthCount.get(0)),"U");
			//remove the booked position from available positions and also decrease available seat of that type
			TicketBooker.upperBerthCount.remove(0);
			TicketBooker.availableUpperBerths--;
		}
		//No berth go Rac
		else if(TicketBooker.availableRac>0) {
			System.out.println("Rac available");
			booker.addToRac(p,(TicketBooker.racCount.get(0)),"Rac");
		}else if(TicketBooker.availableWaitingList>0) {
			System.out.println("Added to waiting List");
			booker.addToWaitingList(p,(TicketBooker.waitingListCount.get(0)),"WL");
		}
	}
	public static void cancelTicket(int id) {
		TicketBooker booker=new TicketBooker();
		//check passenger id is valid
		if(!booker.passengers.containsKey(id)) {
			System.out.println("no passengerid found please check id ");
		}else {
			booker.cancelTicket(id);
		}
	}
public static void main(String[] args) {
	Scanner s=new Scanner(System.in);
	boolean loop=true;
	while(loop) {
		System.out.println("1.Book Ticket \n2.Cancel Ticket \n3.Available Ticket \n4.Booked Tickets\n5.Exit");
		System.out.println("enter the number to process");
		int choice=s.nextInt();
		switch(choice) {
		case 1:{
			if (TicketBooker.availableWaitingList==0) {
				System.out.println("No Tickets Available");
			}else {
			System.out.println("enter passenger name,age and berthpreference(L,M,U)");
			String name=s.next();
			int age=s.nextInt();
			String berthPreference=s.next();
			Passenger p=new Passenger(name,age,berthPreference);
			bookTicket(p);
			}
			break;
		}
		case 2:{
			System.out.println("enter passengerId to cancel");
			int id=s.nextInt();
			cancelTicket(id);
			break;
		}
		case 3:{
			TicketBooker booker=new TicketBooker();
			booker.printAvailable();
			break;
		}
		case 4:{
			TicketBooker booker=new TicketBooker();
			booker.printPassengers();
			break;
		}
		case 5:{
			loop=false;
		}
		}
	}
}
}
