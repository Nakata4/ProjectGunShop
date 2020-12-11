package GunShopSales;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchByOther extends JPanel implements ActionListener
{
	private final String[] price = {"50001-100000", "100001-200000", "200001-300000", "300001-400000"};
	private Gun[] gunList;
	private GunSalesSystem gunSystem;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Search on Prices");
	private JLabel priceLabel = new JLabel("Price");
	private JLabel distanceLabel = new JLabel("Distance traveled");
	private JButton searchButton = new JButton("Search");
	private JButton resetButton = new JButton("Reset");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JComboBox priceCombo = new JComboBox(price);
	private JPanel topPanel = new JPanel();
	private JPanel pricePanel = new JPanel();
	private JPanel distancePanel = new JPanel();
	private JPanel priceDistancePanel = new JPanel();
	private JPanel searchButtonsPanel = new JPanel();
	private JPanel navigateButtonsPanel = new JPanel();
	private GunDetailsComponents gunComponents = new GunDetailsComponents();

	
	public SearchByOther(GunSalesSystem gunSys)
	{
		gunSystem = gunSys;
		setLayout(new BorderLayout());
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		resetButton.addActionListener(this);
		searchButton.addActionListener(this);

		pricePanel.add(priceLabel);
		pricePanel.add(priceCombo);
		priceDistancePanel.add(pricePanel);
		priceDistancePanel.add(distancePanel);

		searchButtonsPanel.add(searchButton);
		searchButtonsPanel.add(resetButton);
		navigateButtonsPanel.add(previousButton);
		navigateButtonsPanel.add(nextButton);

		headingLabel.setAlignmentX(0.5f);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(headingLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(priceDistancePanel);
		topPanel.add(searchButtonsPanel);
		topPanel.add(Box.createVerticalStrut(15));
		gunComponents.add(navigateButtonsPanel, "Center");
		gunComponents.setVisible(false);

		add(topPanel, "North");
		add(gunComponents, "Center");
	}

	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == searchButton)
			searchButtonClicked();
		else if (ev.getSource() == resetButton)
			resetButtonClicked();
		else if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
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

	
	private void resetButtonClicked()
	{
		currentIndex = 0;
		gunList = null;
		gunComponents.setVisible(false);
		priceCombo.setSelectedIndex(0);
	}

	
	private void searchButtonClicked()
	{
		
		double[] priceRange = GunSalesSystem.convertToRange((String)priceCombo.getSelectedItem());


		if (gunList.length > 0)
		{
			currentIndex = 0;
			gunComponents.setVisible(true);
			gunComponents.displayDetails(gunList[0]);

			if (gunList.length == 1)
			{
				nextButton.setEnabled(false);
				previousButton.setEnabled(false);
			}
			else
			{
				nextButton.setEnabled(true);
				previousButton.setEnabled(true);
			}

			gunSystem.repaint();
		}
		else
			JOptionPane.showMessageDialog(gunSystem, "Sorry, no search results were returned", "Search failed", JOptionPane.WARNING_MESSAGE);
	}
}
