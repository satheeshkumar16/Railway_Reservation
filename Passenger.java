package zoho_app;

public class Passenger {
static int id=1;
String name;
int age;
String berthPreference;//u or l or m
int passengerId;// id of passenger created successfully
String alloted;//alloted type(l,m,u,rac,wl)
int seatNumber;//
public Passenger(String name,int age,String berthPreference) {
	this.name=name;
	this.age=age;
	this.berthPreference=berthPreference;
	this.passengerId=id++;
	//alloted="";
	//seatNumber=0;
}
}
