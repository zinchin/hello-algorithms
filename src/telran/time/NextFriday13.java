package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		  Temporal temporalFr=temporal;   
		  LocalDate fri13=(LocalDate) temporal;
		  
		  if (fri13.getDayOfMonth()==13 ) { 
		          fri13=fri13.plus(Period.ofDays(1));
		           temporalFr=temporalFr.plus(Period.ofDays(1));
		                                    }
		  while (fri13.getDayOfMonth()!=13 ) {
		  fri13 =(LocalDate) temporalFr.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)); 
       
		  temporalFr=temporalFr.plus(Period.ofDays(7));
		}
          
          
		return fri13; 
		  
	}

}