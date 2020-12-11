package GunShopSales;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GunSalesSystem extends JFrame implements ActionListener, ComponentListener
{
	
	public static final int GUNS_COUNT = 0;
	public static final int MANUFACTURERS_COUNT = 1;
	public static final int AVERAGE_PRICE = 2;
	public static final int AVERAGE_DISTANCE = 3;
	public static final int AVERAGE_AGE = 4;
	public static final int NAME_COUNT = 5;
	

	private String file;
	private Dialog aboutDlg;
	private Background color;
	private boolean gunsUpdated = false;
	private Vector registeredListeners = new Vector();
	private GunsCollection gunCollection;
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel titlePanel = new JPanel(new GridLayout(2, 1));
	private JLabel pictureLabel = new JLabel();
	private JLabel background = new JLabel();
	private JLabel gunCoLabel = new JLabel("Buy and Sell Guns", JLabel.CENTER);
	private JLabel salesSysLabel = new JLabel("Brought to you by NAKATA CHOLOV 2001717003", JLabel.CENTER);
	private JLabel textSysLabel = new JLabel("Best Gun Shop in the world visit us or be afraid", JLabel.CENTER);
	private JTabbedPane theTab = new JTabbedPane(JTabbedPane.TOP);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("About");
	private JMenuItem aboutItem = new JMenuItem("About");
	private JMenuItem exitItem = new JMenuItem("Exit");
	private WindowCloser closer = new WindowCloser();

	public GunSalesSystem(String f)
	{
		super("Gun Sales");
		addWindowListener(closer);
		addComponentListener(this);

		setSize(800, 480);

		Container c = getContentPane();
		gunCollection = new GunsCollection();

		file = f;
		
		String currentFont = gunCoLabel.getFont().getName();
		gunCoLabel.setFont(new Font(currentFont, Font.BOLD, 26));
		salesSysLabel.setFont(new Font(currentFont, Font.PLAIN, 16));

		menuBar.add(fileMenu);
		fileMenu.add(aboutItem);
		fileMenu.add(exitItem);
		aboutItem.addActionListener(this);

		setJMenuBar(menuBar);

		pictureLabel.setIcon(new ImageIcon("vu.png"));
		titlePanel.add(gunCoLabel);
		titlePanel.add(salesSysLabel);
		topPanel.add(pictureLabel, "East");
		topPanel.add(titlePanel, "West");		

		Login log = new Login(this);
		Welcome welcome = new Welcome(this, f);
		AddGun addGun = new AddGun(this);
		ShowAllGuns showAllGuns = new ShowAllGuns(this);
		SearchByAge searchByAge = new SearchByAge(this);
		SearchByOther searchByOther = new SearchByOther(this);
		Exit exit = new Exit(this, f);
				
		theTab.add("Welcome", welcome);
		theTab.add("Add a Gun", addGun);
		theTab.add("Show all types and models", showAllGuns);
		theTab.add("Search on age", searchByAge);
		theTab.add("Search on Prices", searchByOther);	
		theTab.add("Exit", exit);

		theTab.addChangeListener(showAllGuns);
		theTab.addChangeListener(welcome);

		theTab.setSelectedIndex(0);

		c.setLayout(new BorderLayout());
		c.add(topPanel, "North");
		c.add(theTab, "Center");
	}

	public void aboutMenuItemClicked()
	{
		
		if (aboutDlg == null)
			aboutDlg = new Dialog(this, "About", true);
		aboutDlg.showAbout();
	}

	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == aboutItem)
			aboutMenuItemClicked();
		else if (ev.getSource() == exitItem)
			closing();
	}

	
	public void addGunUpdateListener(Object listener)
	{
		if (!(listener == null))
			registeredListeners.add(listener);
	}
	
	public int addNewGun(Gun c)
	{
		return gunCollection.addGun(c);
	}

	public void closing()
	{
		boolean ok;

		if (gunsUpdated)
		{
			do
			{
				try
				{
					gunCollection.saveGuns(file);
					ok = true;
				}
				catch (java.io.IOException exp)
				{
					int result = JOptionPane.showConfirmDialog(this, "The data file could not be written, possibly because you don't have access to this location.\nIf you chose No to retry you will lose all gun data from this session.\n\nWould you like to reattempt saving the data file?", "Problem saving data", JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.YES_OPTION)
						ok = false;
					else
						ok = true;
				}
			}
			while (!ok);
		}

		System.exit(0);
	}

	public void componentHidden(ComponentEvent ev) {}

	public void componentMoved(ComponentEvent ev) {}

	
	public void componentResized(ComponentEvent ev)
	{
		if (this == ev.getComponent())
		{
			Dimension size = getSize();

			if (size.height < 530)
				size.height = 530;
			if (size.width < 675)
				size.width = 675;

			setSize(size);
		}
	}

	public void componentShown(ComponentEvent ev) {}

	
	public static double[] convertToRange(String s)
	{
		String[] parts = s.trim().split("-");
		double[] bounds = new double[2];

		try
		{
			if (parts.length == 1)
			{
				String c = s.substring(s.length() - 1);

				
				if (c.equals("+"))
				{
					
					bounds[0] = Double.parseDouble(s.substring(0, s.length() - 1));
					
					bounds[1] = -1;
				}
				else
				{
					
					bounds[0] = Double.parseDouble(s);
					bounds[1] = bounds[0];
				}
			}
			
			else if(parts.length == 2)
			{
				bounds[0] = Double.parseDouble(parts[0]);
				bounds[1] = Double.parseDouble(parts[1]);
				if (bounds[0] > bounds[1])
				{
					
					bounds[0] = -1;
					bounds[1] = -1;
				}
			}
		}
		catch (NumberFormatException exp)
		{
			
			bounds[0] = -1;
			bounds[1] = -1;
		}

		return bounds;
	}

	
	public Gun[] getAllGuns()
	{
		return gunCollection.getAllGuns();
	}


	
	public boolean getGunsUpdated()
	{
		return gunsUpdated;
	}

	
	public double getStatistics(int type)
	{
		double result = 0;

		if (type == GUNS_COUNT)
		{
			result	= gunCollection.gunsCount();
		}
		else if (type == MANUFACTURERS_COUNT)
		{
			result = gunCollection.manufacturerCount();
		}
		else if (type == AVERAGE_PRICE)
		{
			result = gunCollection.getAveragePrice();
		}
		else if (type == AVERAGE_AGE)
		{
			result = gunCollection.getAverageAge();
		}

		return result;
	}

	
	public static void main (String[] args)
	{
		GunSalesSystem gunSales = new GunSalesSystem("guns.dat");
		gunSales.setVisible(true);
	}

	
	public Gun[] search(int minAge, int maxAge)
	{
		return gunCollection.search(minAge, maxAge);
	}

	
	public Gun[] search(int minPrice, int maxPrice, double minDistance, double maxDistance)
	{
		return gunCollection.search(minPrice, maxPrice,  minDistance, maxDistance);
	}

	
	public void setGunsUpdated()
	{
		gunsUpdated = true;

		for (int i = 0; i < registeredListeners.size(); i++)
		{
			Class[] paramType = {GunUpdateEvent.class};
			Object[] param = {new GunUpdateEvent(this)};

			try
			{
				
				java.lang.reflect.Method callingMethod = registeredListeners.get(i).getClass().getMethod("carsUpdated", paramType);
				
				callingMethod.invoke(registeredListeners.get(i), param);
			}
			catch (NoSuchMethodException exp)
			{
				System.out.println("Warning, 'public carsUpdated(CarEvent)' method does not exist in " + registeredListeners.get(i).getClass().getName() + ". You will not receive any car update events");
			}
			catch (IllegalAccessException exp)
			{
				System.out.println("Warning, the 'public gunUpdated(GunEvent)' method couldn't be called for unknown reasons, You will not receive any car update events");
			}
			catch (Exception exp){}
		}
	}
	
	public static Gun[] vectorToGun(Vector v)
	{
		Gun[] ret = new Gun[v.size()];

		for (int i = 0; i < v.size(); i++)
		{
			ret[i] = (Gun)v.elementAt(i);
		}

		return ret;
	}

	public JLabel getTextSysLabel() {
		return textSysLabel;
	}

	public void setTextSysLabel(JLabel textSysLabel) {
		this.textSysLabel = textSysLabel;
	}

	class WindowCloser extends WindowAdapter
	{
		
		public void windowClosing(WindowEvent ev)
		{
			closing();
		}
	}
}
