package gmb.model.tip;

import gmb.model.Lottery;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.Entity;

import org.joda.time.DateTime;
import org.joda.time.Duration;

@Entity
public abstract class PermaTT extends TipTicket 
{
	@OneToMany(mappedBy="permaTT")
	protected List<SingleTip> tips;

	protected int durationType;	
	protected final static long millisecondsOfDay = 1000*60*60*24;

	protected boolean expired = false;

	@Deprecated
	protected PermaTT(){}

	public PermaTT(PTTDuration duration)
	{
		super();
		setDuration(duration);
		tips = new LinkedList<SingleTip>();
	}

	/**
	 * checks whether the ticket's duration has expired by now
	 * @return
	 */
	public boolean isExpired()
	{
		if(!expired)
		{						
			if(this.getDurationDate().isBefore(Lottery.getInstance().getTimer().getDateTime()))
				expired = true;

			return expired;
		}
		else
			return true;
	}

	public DateTime getDurationDate()
	{
		Duration duration;

		switch(durationType)
		{
		case 1 : duration = new Duration(millisecondsOfDay*182);
		case 2 : duration = new Duration(millisecondsOfDay*365);
		default : duration = new Duration(millisecondsOfDay*30);
		}

		return new DateTime(purchaseDate).plus(duration);
	}

	public boolean removeTip(SingleTip tip)
	{
		if(this.tips.contains(tip))
		{
			this.tips.remove(tip);
			return true;
		}
		else
			return false;
	}

	/**
	 * adds the tip to the "tips" list if the ticket's duration hasn't been expired
	 * @param tip
	 * @return
	 */
	protected int addTip(SingleTip tip, Class<?> tipType)
	{ 
		assert tip.getClass() == tipType : "Wrong type given to PermaTT.addTip(SingleTip tip)! Expected: " + tipType.getSimpleName() + " !";
		
		if(this.isExpired()) return -1;
		if(tips.contains(tip)) return 2;

		tips.add(tip); 
		return 0;	
	}

	public void setDuration(PTTDuration duration)
	{
		if(duration == PTTDuration.MONTH)
			durationType = 0;
		else
			if(duration == PTTDuration.HALFYEAR)
				durationType = 1;
			else
				durationType = 2;
	}

	public void setDurationType(int durationType){ this.durationType = durationType; }
	public void setExpired(boolean expired){ this.expired = expired; }

	public List<SingleTip> getTips(){ return tips; }	

	public PTTDuration getDuration()
	{
		switch(durationType)
		{
		case 1 : return PTTDuration.HALFYEAR;
		case 2 : return PTTDuration.YEAR;
		default : return PTTDuration.MONTH;
		}
	}

	public int getDurationType(){ return durationType; }
	public boolean getExpired(){ return expired; }
}
