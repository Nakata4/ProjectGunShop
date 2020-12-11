package GunShopSales;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class Exit extends JPanel implements ChangeListener
{
	private GunSalesSystem gunSystem;
	private JLabel headingLabel = new JLabel("Exit of the Gun Shop System", JLabel.CENTER);
	private JLabel gunsLabel = new JLabel();
	private JLabel manufacturersLabel = new JLabel();
	private JLabel avgPriceLabel = new JLabel();
	private JLabel avgAgeLabel = new JLabel();
	private JLabel versionLabel = new JLabel();
	private JLabel dataSizeLabel = new JLabel();
	private JPanel statsPanel = new JPanel();
	private JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
	private boolean gunsUpdated = false;
	private String file;

	public Exit(GunSalesSystem gunSys, String data)
	{
		gunSystem = gunSys;
		file = data;
		setLayout(new BorderLayout(0, 10));
		gunSys.addGunUpdateListener(this);

		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		centerPanel.add(statsPanel);
		headingLabel.setBorder(new EmptyBorder(new Insets(10, 0, 0, 0)));

		updateStats();

		statsPanel.add(gunsLabel);
		statsPanel.add(manufacturersLabel);
		statsPanel.add(avgPriceLabel);
		statsPanel.add(avgAgeLabel);
		statsPanel.add(Box.createVerticalStrut(20));
		statsPanel.add(dataSizeLabel);

		add(headingLabel, "North");
		add(centerPanel, "Center");
	}

	public void gunsUpdated(GunUpdateEvent ev)
	{
		if (ev.getSource() == gunSystem)
		{
			gunsUpdated = true;
		}
	}

	
	public void stateChanged(ChangeEvent ev)
	{
		if (ev.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tab = (JTabbedPane)ev.getSource();
			
			if (tab.getSelectedIndex() == 0)
			{
				
				if (gunsUpdated)
				{
					updateStats();
					gunsUpdated = false;
				}
			}
		}
	}
	
	private void updateStats()
	{
		
		int guns = (int)gunSystem.getStatistics(GunSalesSystem.GUNS_COUNT);
		int manufacturers = (int)gunSystem.getStatistics(GunSalesSystem.MANUFACTURERS_COUNT);
		double avgPrice = Math.floor(gunSystem.getStatistics(GunSalesSystem.AVERAGE_PRICE) * 10 + 0.5) / 10;
		double avgAge = Math.floor(gunSystem.getStatistics(GunSalesSystem.AVERAGE_AGE) * 10 + 0.5) / 10;
		java.io.File f = new java.io.File(file);
		long size = f.length(); 

		gunsLabel.setText("Total number of logins: " + String.valueOf(guns));
		manufacturersLabel.setText("Total number of manufacturers: " + String.valueOf(manufacturers));
		avgPriceLabel.setText("Average gun sales: " + String.valueOf(avgPrice));
		avgAgeLabel.setText("Thank you for visiting us: " + String.valueOf(avgAge));
		dataSizeLabel.setText("Size of data file: " + size + " bytes");
	}
}
