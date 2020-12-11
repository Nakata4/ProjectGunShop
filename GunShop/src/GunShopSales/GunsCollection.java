package GunShopSales;

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class GunsCollection
{
	
	public static final int NO_ERROR = 0;
	
	public static final int GUNS_MAXIMUM_REACHED = 1;
	
	public static final int MANUFACTURERS_MAXIMUM_REACHED = 2;

	private final int maxManufacturers = 20;
	private final int maxGuns = 20;

	private Manufacturer[] manufacturer = new Manufacturer[0];

	public GunsCollection(){}

	public GunsCollection(Manufacturer man)
	{
		addManufacturer(man);
	}

	
	public int addGun(Gun c)
	{
		Manufacturer man;
		String name = c.getManufacturer();
		int index = -1;
		int result = NO_ERROR;

		for (int i = 0; i < manufacturer.length; i++)
		{
			if (manufacturer[i].getManufacturerName().equalsIgnoreCase(name))
				index = i;
		}

		if (index == -1)
		{
			if (manufacturer.length < maxManufacturers)
			{
				man = new Manufacturer(name, c);
				addManufacturer(man);
			}
			else
				result = MANUFACTURERS_MAXIMUM_REACHED;
		}
		else
		{
			if (manufacturer[index].gunCount() < maxGuns)
				manufacturer[index].addGun(c);
			else
				result = GUNS_MAXIMUM_REACHED;
		}

		return result;
	}

	
	private void addManufacturer(Manufacturer man)
	{
		manufacturer = resizeArray(manufacturer, 1);
		manufacturer[manufacturer.length - 1] = man;
	}

	
	public int gunsCount()
	{
		int count = 0;

		for (int i = 0; i < manufacturer.length; i++)
			count += manufacturer[i].gunCount();

		return count;
	}

	
	public int manufacturerCount()
	{
		return manufacturer.length;
	}

	
	public Gun[] getAllGuns()
	{
		Vector result = new Vector();
		Gun[] gun;
		for (int i = 0; i < manufacturer.length; i++)
		{
			gun = manufacturer[i].getAllGuns();
			for (int j = 0; j < gun.length; j++)
			{
				result.addElement(gun[j]);
			}
		}

		return GunSalesSystem.vectorToGun(result);
	}

	public Manufacturer[] getAllManufacturers()
	{
		return manufacturer;
	}

	
	public double getAverageAge()
	{
		Gun[] gun;
		double result = 0;
		int count = 0;

		for (int i = 0; i < manufacturer.length; i++)
		{
			gun = manufacturer[i].getAllGuns();
			for (int j = 0; j < gun.length; j++)
			{
				result += gun[j].getAge();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}

	public double getAveragePrice()
	{
		Gun[] gun;
		double result = 0;
		int count = 0;

		for (int i = 0; i < manufacturer.length; i++)
		{
			gun = manufacturer[i].getAllGuns();
			for (int j = 0; j < gun.length; j++)
			{
				result += gun[j].getPrice();
				count++;
			}
		}
		if (count == 0)
			return 0;
		else
			return (result / count);
	}
	
	public void loadGuns(String file) throws IOException, ClassNotFoundException
	{

		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(file));
		manufacturer = (Manufacturer[])inp.readObject();
		inp.close();
	}

	
	private Manufacturer[] resizeArray(Manufacturer[] inArray, int extendBy)
	{
		Manufacturer[] result = new Manufacturer[inArray.length + extendBy];

		for (int i = 0; i < inArray.length; i++)
		{
			result[i] = inArray[i];
		}

		return result;
	}

	
	public void saveGuns(String file) throws IOException
	{
		int flag = 0;
		int items = manufacturer.length;
		Manufacturer temp;

		if (manufacturer.length > 0)
		{
			do
			{
				flag = 0;
				for (int i = 0; i < items; i++)
				{
					if (i + 1 < items)
					{
						if (manufacturer[i].getManufacturerName().compareTo(manufacturer[i + 1].getManufacturerName()) > 0)
						{
							temp = manufacturer[i];
							manufacturer[i] = manufacturer[i + 1];
							manufacturer[i + 1] = temp;
							flag++;
						}
					}
				}
			}
			while (flag > 0);

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			out.writeObject(manufacturer);
			out.close();
		}
	}

	
	public Gun[] search(int minPrice, int maxPrice, double minDistance, double maxDistance)
	{
		Vector result = new Vector();
		int price;
		double distance;
		Gun[] gun;
		gun = getAllGuns();

		for (int i = 0; i < gun.length; i++)
		{
			price = gun[i].getPrice();

 			{
					result.addElement(gun[i]);
			}
		}

		return GunSalesSystem.vectorToGun(result);
	}

	
	public Gun[] search(int minAge, int maxAge)
	{
		Gun[] gun;
		gun = getAllGuns();
		Vector result = new Vector();
		if (maxAge == -1)
		{
			for (int i = 0; i < gun.length; i++)
			{
				if (gun[i].getAge() >= minAge)
				{
					result.addElement(gun[i]);
				}
			}
		}
		else
		{
			for (int i = 0; i < gun.length; i++)
			{
				if (gun[i].getAge() >= minAge && gun[i].getAge() <= maxAge)
				{
					result.addElement(gun[i]);
				}
			}
		}

		return GunSalesSystem.vectorToGun(result);
	}
}