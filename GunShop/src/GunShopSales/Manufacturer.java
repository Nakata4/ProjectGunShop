package GunShopSales;

public class Manufacturer implements java.io.Serializable
{
	private String manufacturer;		
	private Gun[] gun = new Gun[0];		
	
	public Manufacturer(String nam, Gun c)
	{
		manufacturer = nam.toUpperCase();
		addGun(c);
	}

	
	public void addGun(Gun c)
	{
		gun = resizeArray(gun, 1);
		gun[gun.length - 1] = c;
	}

	
	public int gunCount()
	{
		return gun.length;
	}

	
	public Gun[] getAllGuns()
	{
		return gun;
	}



	public String getManufacturerName()
	{
		return manufacturer;
	}

	
	private Gun[] resizeArray(Gun[] c, int extendBy)
	{
		Gun[] result = new Gun[c.length + extendBy];

		for (int i = 0; i < c.length; i++)
		{
			result[i] = c[i];
		}

		return result;
	}

	
	public void setManufacturersName(String nam)
	{
		manufacturer = nam.toUpperCase();
	}
}