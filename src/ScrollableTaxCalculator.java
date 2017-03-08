import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;


public class ScrollableTaxCalculator extends JFrame {
	
	private JLabel purchaseAmountLabel;
	private JTextField purchaseAmount;
	private JLabel taxRateLabel;
	private JSlider taxRate;
	private JLabel taxAmountLabel;
	private JTextField taxAmount;
	private JLabel totalAmountLabel;
	private JTextField totalAmount;
	
	private JPanel panel;
	

	public ScrollableTaxCalculator() {
		//Set the title.
		setTitle("Tax Calculator");
			
		//Specify an action	for the close button.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		// Build the panel
		panel = new JPanel();
		
		// Create and add labels, text fields, and slider
		purchaseAmountLabel = new JLabel("Purchase amount: ");
		panel.add(purchaseAmountLabel);
		
		purchaseAmount = new JTextField(10);
		purchaseAmount.getDocument().addDocumentListener(new TaxListener());
		panel.add(purchaseAmount);
		
		taxRateLabel = new JLabel("Tax rate: ");
		panel.add(taxRateLabel);
		
		taxRate = new JSlider(JSlider.HORIZONTAL, 0, 10, 9);
		taxRate.setMajorTickSpacing(1);
		taxRate.setPaintTicks(true);
		taxRate.setPaintLabels(true);
		taxRate.addChangeListener(new TaxListener());
		panel.add(taxRate);
		
		taxAmountLabel = new JLabel("Tax amount: ");
		panel.add(taxAmountLabel);
		
		taxAmount = new JTextField(10);
		taxAmount.setEditable(false);
		panel.add(taxAmount);
		
		totalAmountLabel = new JLabel("Total amount: ");
		panel.add(totalAmountLabel);
		
		totalAmount = new JTextField(10);
		totalAmount.setEditable(false);
		panel.add(totalAmount);
		
		getContentPane().add(panel);
		
		this.setSize(280, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ScrollableTaxCalculator();
	}
	
	private class TaxListener implements DocumentListener, ChangeListener {
		
		private void update() {
			try {
				double purchaseAmt = Double.parseDouble(purchaseAmount.getText());
				double taxR = taxRate.getValue();
				taxAmount.setText(String.format("%, .2f", purchaseAmt * taxR / 100.0));
				totalAmount.setText(String.format("%, .2f", purchaseAmt * (1 + taxR / 100.0)));
			}
			catch (NumberFormatException e) {
				taxAmount.setText("");
				totalAmount.setText("");
			}
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			update();
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			update();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			update();
		}

		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO Auto-generated method stub
			update();
		}
		
	}
	

}
