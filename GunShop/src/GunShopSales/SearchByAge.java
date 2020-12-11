package GunShopSales;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchByAge extends JPanel implements ActionListener
{
	private final String[] age = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
		"11-15", "16-20", "21-25", "26-30", "31+"};
	private Gun[] gunList;
	private GunSalesSystem gunSystem;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Search on age");
	private JLabel ageLabel = new JLabel("Gun Age");
	private JButton searchButton = new JButton("Search");
	private JButton resetButton = new JButton("Reset");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JComboBox ageCombo = new JComboBox(age);
	private JPanel topPanel = new JPanel();
	private JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel searchButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel navigateButtonsPanel = new JPanel();
	private GunDetailsComponents gunComponents = new GunDetailsComponents();

	
	public SearchByAge(GunSalesSystem gunSys)
	{
		gunSystem = gunSys;
		setLayout(new BorderLayout());
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		resetButton.addActionListener(this);
		searchButton.addActionListener(this);

		agePanel.add(ageLabel);
		agePanel.add(ageCombo);
		searchButtonsPanel.add(searchButton);
		searchButtonsPanel.add(resetButton);
		navigateButtonsPanel.add(previousButton);
		navigateButtonsPanel.add(nextButton);
		agePanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));
		searchButtonsPanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));

		headingLabel.setAlignmentX(0.5f);

		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(headingLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(agePanel);
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
		else if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
		else if (ev.getSource() == resetButton)
			resetButtonClicked();
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
		ageCombo.setSelectedIndex(0);
	}

	
	private void searchButtonClicked()
	{
		
		double[] range = GunSalesSystem.convertToRange((String)ageCombo.getSelectedItem());

		if (range[0] >= 0)
		{
			gunList = gunSystem.search((int)range[0], (int)range[1]);
		}

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
