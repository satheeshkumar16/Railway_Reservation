package zoho_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TicketBooker {
static int availableLowerBerths=3;//21
static int availableMiddleBerths=2;//21
static int availableUpperBerths=1;
static int availableRac=1;
static int availableWaitingList=1;

static Queue<Integer> waitingList=new LinkedList<>();
static Queue<Integer> racList=new LinkedList<>();
static List<Integer> bookedTicketList=new ArrayList();

static List<Integer> lowerBerthCount=new ArrayList<>(Arrays.asList(1,2,3));
static List<Integer> middleBerthCount=new ArrayList<>(Arrays.asList(1,2));
static List<Integer> upperBerthCount=new ArrayList<>(Arrays.asList(1));
static List<Integer> racCount=new ArrayList<>(Arrays.asList(1));
static List<Integer> waitingListCount=new ArrayList<>(Arrays.asList(1));

static Map<Integer,Passenger> passengers=new HashMap<>();//map of passenger id to passenger
public void bookTicket(Passenger p,int berthInfo,String allotedBerth) {
	p.seatNumber=berthInfo;
	p.alloted=allotedBerth;
	//add passenger to map
	passengers.put(p.passengerId,p);
	bookedTicketList.add(p.passengerId);
	System.out.println("...........Booked Successfully");
}
//adding Rac
public void addToRac(Passenger p,int racInfo,String allotedRac) {
	p.seatNumber=racInfo;
	p.alloted=allotedRac;
	//add passenger to map
	passengers.put(p.passengerId,p);
	racList.add(p.passengerId);
	availableRac--;
	racCount.remove(0);
	System.out.println("..............added to Rac sucessfully");
}
//adding to WL
public void addToWaitingList(Passenger p,int watingListInfo,String allotedWL) {
	p.seatNumber=watingListInfo;
	p.alloted=allotedWL;
	passengers.put(p.passengerId,p);
	waitingList.add(p.passengerId);
	availableWaitingList--;
	waitingListCount.remove(0);
	System.out.println("............added to waiting List successfully");
}
public void cancelTicket(int passengerId) {
	//remove passenger from map
	Passenger p=passengers.get(passengerId);
	passengers.remove(Integer.valueOf(passengerId));
	//remove booked ticket from list
	bookedTicketList.remove(Integer.valueOf(passengerId));
	//take booked position which is now free
	int positionBooked=p.seatNumber;
	System.out.println("..............cancelled successfully");
	//add free position to corresponding type of list(either L,M,U)
	if(p.alloted.equals("L")) {
		availableLowerBerths++;
		lowerBerthCount.add(positionBooked);
	}else if(p.alloted.equals("M")) {
		availableMiddleBerths++;
		middleBerthCount.add(positionBooked);
	}else if(p.alloted.equals("U")) {
		availableUpperBerths++;
		upperBerthCount.add(positionBooked);
	}
	//check if any Rac there
	if(racList.size()>0) {
		//take passenger from Rac and increase free space in Rac List and increase available Rac tickets
		Passenger passengerFromRac=passengers.get(racList.poll());
		int positionRac=passengerFromRac.seatNumber;
		racCount.add(positionRac);
		racList.remove(Integer.valueOf(passengerFromRac.passengerId));
		availableRac++;
		//check if any WL is there
		if(waitingList.size()>0) {
			//take passenger from wl and add them to rac, increase the free space is waiting list and increase available Wl and decrease available Rac by 1
		Passenger passengerFromWaitingList=passengers.get(waitingList.poll());
		int positionWL=passengerFromWaitingList.seatNumber;
		waitingListCount.add(positionWL);
		waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));
		passengerFromWaitingList.seatNumber=racCount.get(0);
		passengerFromWaitingList.alloted="Rac";
		racCount.add(passengerFromWaitingList.passengerId);
		availableWaitingList++;
		availableRac--;
		}
		//now we have apassenger from rac to whom we can book a ticket
		App.bookTicket(passengerFromRac);
	}
}
//print all available seats
public void printAvailable() {
	System.out.println("Available Lower berths:"+ availableLowerBerths);
	System.out.println("Available Middle berths:"+ availableMiddleBerths);
	System.out.println("Available Upper berths:"+ availableUpperBerths);
	System.out.println("Available Rac:"+ availableRac);
	System.out.println("Available WaitingList:"+ availableWaitingList);
}
public void printPassengers() {
	if(passengers.size()==0) {
		System.out.println("No details of passengers");
		return;
	}
	for(Passenger p:passengers.values()) {
		System.out.println("passenger Id"+ p.passengerId);
		System.out.println("Name"+ p.name);
		System.out.println("Age"+ p.age);
		System.out.println("Berth"+ p.seatNumber+p.alloted);
	}
}
}
