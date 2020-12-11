package GunShopSales;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ShowAllGuns extends JPanel implements ActionListener, ChangeListener
{
	private GunSalesSystem gunSystem;
	private Gun[] gunList;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Show all makes and models");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JPanel buttonPanel = new JPanel();
	private GunDetailsComponents gunComponents = new GunDetailsComponents();
	private boolean gunsUpdated = false;

	public ShowAllGuns(GunSalesSystem gunSys)
	{
		gunSystem = gunSys;
		gunList = gunSystem.getAllGuns();

		if (gunList.length > 0)
			gunComponents.displayDetails(gunList[0]);

		gunSys.addGunUpdateListener(this);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		gunComponents.add(buttonPanel, "Center");
		add(gunComponents);

		gunList = gunSystem.getAllGuns();
	}

	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
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
			
			if (tab.getSelectedIndex() == 2)
			{
				
				if (gunsUpdated)
				{
					gunList = gunSystem.getAllGuns();
					if (!(gunList == null))
						gunComponents.displayDetails(gunList[currentIndex]);
					
					gunsUpdated = false;
				}
			}
		}
	}

	
	private void nextButtonClicked()
	{
		if (currentIndex < gunList.length - 1)
		{
			currentIndex++;
			gunComponents.displayDetails(gunList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(gunSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	
	private void previousButtonClicked()
	{
		if (currentIndex > 0)
		{
			currentIndex--;
			gunComponents.displayDetails(gunList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(gunSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}
}
