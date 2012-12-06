package gmb.model;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.salespointframework.core.time.DefaultTime;

public class Timer extends DefaultTime 
{
	protected Duration offset = new Duration(0);
	
	public Timer()
	{
		super();
	}
	
//	public boolean isBeforeNow(DateTime date)
//	{
//		return date.isBefore(this.getDateTime()); 
//	}
	
	public void setReferenceDate(DateTime refDate)
	{
		offset = new Duration(super.getDateTime(), refDate);
	}
	
	public void addMinutes(int minuteCount){ this.offset = this.offset.plus(new Duration(1000*60 * minuteCount)); }
	public void addHours(int minuteCount){ this.offset = this.offset.plus(new Duration(1000*60*60 * minuteCount)); }
	public void addDays(int minuteCount){ this.offset = this.offset.plus(new Duration(1000*60*60*24 * minuteCount)); }
	public void addWeeks(int minuteCount){ this.offset = this.offset.plus(new Duration(1000*60*60*24*7 * minuteCount)); }
	public void addMonths(int minuteCount){ this.offset = this.offset.plus(new Duration(1000*60*60*24*30 * minuteCount)); }
	public void addYears(int minuteCount){ this.offset = this.offset.plus(new Duration(1000*60*60*24*356 * minuteCount)); }
	
	public void addToOffset(Duration offset){ this.offset = this.offset.plus(offset); }
	
	public DateTime getDateTime()
	{
		DateTime actualDateTime = super.getDateTime();	
		return actualDateTime.plus(offset);
	}
	
	public void setOffset(Duration offset){ this.offset = offset; }
	public void resetOffset(){ offset = new Duration(0); }
	public Duration getOffset(){ return offset; }
}
