package GunShopSales;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Gun implements java.io.Serializable
{
	private String model;
	private String manufacturer;
	private String information;
	private String Type;
	private int year;
	private int price;

	public Gun(){}

	public Gun(String man, String mod, String info, String typ)
	{
		model = mod;
		manufacturer = man.toUpperCase();
		information = info;
		Type = typ;
	}

	public int getAge()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		return (calendar.get(Calendar.YEAR) - year);
	}

	public String getInformation()
	{
		return information;
	}

	public String getManufacturer()
	{
		return manufacturer;
	}

	public String getModel()
	{
		return model;
	}

	public String getType()
	{
		return Type;
	}
	
	public int getPrice()
	{
		return price;
	}

	public int getYear()
	{
		return year;
	}

	public void setInformation(String info)
	{
		information = info;
	}
	

	public void setManufacturer(String man)
	{
		manufacturer = man.toUpperCase();
	}

	public void setModel(String mod)
	{
		model = mod;
	}

	public void setType(String typ)
	{
		Type = typ;
	}
	
	public void setPrice(int cost)
	{
		price = cost;
	}

	public void setYear(int yr)
	{
		year = yr;
	}
}
