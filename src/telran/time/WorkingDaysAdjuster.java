package telran.time;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class WorkingDaysAdjuster implements TemporalAdjuster {

int[] daysOff;
int nDays;
public int[] getDaysOff() {
	return daysOff;
}
public void setDaysOff(int[] daysOff) {
	this.daysOff = daysOff;
}
public int getnDays() {
	return nDays;
}
public void setnDays(int nDays) {
	this.nDays = nDays;
}
public WorkingDaysAdjuster(int[] daysOff, int nDays) {
	
	this.daysOff = daysOff;
	this.nDays = nDays;
}
public WorkingDaysAdjuster() {
}
	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate res =(LocalDate) temporal;
		if (daysOff.length == 7) return res;
		if (daysOff.length==0) return res.plusDays(nDays);
		
		
		return res.plusDays(nDays+daysOffCount(temporal));
	}
	
	private int daysOffCount(Temporal temporal) {
	    Arrays.sort(daysOff);
		LocalDate tmp =(LocalDate) temporal;
		int res=0;
		for (int j=0; j<daysOff.length; j++ ) {
		     for (int i=0; i<nDays;i++) {
		        if ((tmp.getDayOfWeek().ordinal()+1)==daysOff[j]) res++;
		        tmp=tmp.plusDays(1);
		      }
		}
		return res;
	}

}