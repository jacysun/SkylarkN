import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import controller.AirportParser;
import controller.FlightParser;
import controller.MyTime;
import model.Airport;
import model.Flight;

public class Test {

	public static void main(String[] args) {
//		long millSec = 0;
//		Calendar localCal = Calendar.getInstance();
//		localCal.set(Calendar.HOUR_OF_DAY, 20);
//		Calendar gmtCal = Calendar.getInstance();
//		if(localCal.get(Calendar.HOUR_OF_DAY)>19){
//			millSec = gmtCal.getTimeInMillis()+86400000;
//		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
//		String depDateString = format.format(millSec);
//		
//		System.out.println("local: "+ localCal.getTime());
//		System.out.println("gmt: "+ depDateString);
		MyTime m = new MyTime();
	}

}
