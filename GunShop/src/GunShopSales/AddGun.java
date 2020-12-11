package GunShopSales;

import java.util.*;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

public class AddGun extends JPanel implements ActionListener
{
	private GunSalesSystem gunSystem;
	private JLabel headingLabel = new JLabel("Add a Gun");
	private JButton changeButton = new JButton ("Change");
	private JButton resetButton = new JButton("Reset");
	private JButton saveButton = new JButton("Save");
	private JPanel buttonPanel = new JPanel();
	private GunDetailsComponents gunComponents = new GunDetailsComponents();

	public AddGun(GunSalesSystem gunSys)
	{
		gunSystem = gunSys;

		resetButton.addActionListener(this);
		changeButton.addActionListener(this);
		saveButton.addActionListener(this);
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(resetButton);
		buttonPanel.add(changeButton);
		buttonPanel.add(saveButton);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		gunComponents.add(buttonPanel, "Center");
		add(gunComponents);
	}


	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == resetButton)
			resetButtonClicked();
		else if (ev.getSource() == saveButton)
			saveButtonClicked();
		else if (ev.getSource() == changeButton)
			changeButtonClicked();
	}

	private void resetButtonClicked()
	{
		gunComponents.clearTextFields();
	}
	private void changeButtonClicked()
	{
		gunComponents.clearTextFields();
	}

	private void saveButtonClicked()
	{
		String manufacturer = "";
		String model = "";
		String info = "";
		String type ="";
		String paint = "";
		int price = 0;
		int year = 0;
		boolean valid = false;
		try
		{
			manufacturer = gunComponents.getManufacturerText().trim();
			model = gunComponents.getModelText().trim();
			type = gunComponents).getTypeText().trim();
			info = gunComponents.getInfoText().trim();
			price = Integer.parseInt(gunComponents.getPriceText().trim());
			year = Integer.parseInt(gunComponents.getYearText().trim());
			paint = gunComponents.getPaintText().trim();

			
			if (validateString(manufacturer))
			{
				if (year >= 1000 && year <= 9999)
				{
					if (validateString(model))
					

						{
							valid = true;
						}
						else
							JOptionPane.showMessageDialog(gunSystem, "An error has occured due to incorrect \"Km Traveled\" text field data.\nThis text field must contain a number with one decimal place only.", "Invalid field", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(gunSystem, "An error has occured due to incorrect \"Model\" text field data.\nThis text field must contain any string of at least two non-spaced characters.", "Invalid field", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(gunSystem, "An error has occured due to incorrect \"Year\" text field data.\nThis text field must be in the form, YYYY. ie, 2007.", "Invalid field", JOptionPane.ERROR_MESSAGE);
			
		}
		catch (NumberFormatException exp)
		{
			JOptionPane.showMessageDialog(gunSystem, "An unknown error has occured. Please ensure your fields meet the following requirements:\n" +
			"The \"Year\" field must contain four numeric digits only\nThe \"Price\" field must contain a valid integer with no decimal places\nThe \"Km Traveled\" field must contain a number which can have a maximum of one decimal place", "Invalid field", JOptionPane.ERROR_MESSAGE);
		}

		if (valid)
		{
			
			Gun myGun = new Gun(manufacturer, model, info, type);
			myGun.setPrice(price);
			myGun.setYear(year);
			myGun.setType(type);
			myGun.setPaint(paint);

			
			int result = gunSystem.addNewGun(myGun);

			if (result == GunsCollection.NO_ERROR)
			{
				gunSystem.setGunsUpdated();
				JOptionPane.showMessageDialog(gunSystem, "Record added.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				resetButtonClicked();
				gunComponents.setFocusManufacturerTextField();
			}
			else if (result == GunsCollection.GUNS_MAXIMUM_REACHED)
				JOptionPane.showMessageDialog(gunSystem, "The maximum amount of guns for that manufacturer has been reached.\nUnfortunately you cannot add any further ns to this manufacturer", "Problem adding gun", JOptionPane.WARNING_MESSAGE);
			else if (result == GunsCollection.MANUFACTURERS_MAXIMUM_REACHED)
				JOptionPane.showMessageDialog(gunSystem, "The maximum amount of manufacturers in the gun system has been reached.\nUnfortunately you cannot add any further manufacturers to this system", "Problem adding gun", JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean validateString(String arg)
	{
		boolean valid = false;
		String[] splitted = arg.split(" "); 

		for (int i = 0; i < splitted.length; i++)
		{
			valid = (splitted[i].length() > 2);
			if (valid)
				break;
		}

		return valid;
	}

	private boolean validateKilometers(String distance)
	{
		boolean valid = false;
		String rem;
		StringTokenizer tokens = new StringTokenizer(distance, ".");

		tokens.nextToken();

		if (tokens.hasMoreTokens())
		{
			
			rem = tokens.nextToken();
			
			if (rem.length() == 1)
				valid = true;
			else
			{
				if ((Integer.parseInt(rem)) % (Math.pow(10, rem.length() - 1)) == 0)
					valid = true;
				else
					valid=false;
			}
		}
		else 
			valid = true;

		return valid;
	}
}
