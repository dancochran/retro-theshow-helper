package org.dan.baseball;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RetroFrame extends JFrame
{
	private static final long serialVersionUID = 2862926693471436520L;
	JLabel lblBatterSplits = new JLabel("Batter Splits");
	JLabel lblBatterDef = new JLabel("Batter Def");
	JTextArea txtBatterSplits = new JTextArea(12, 100);
	JTextArea txtBatterDef = new JTextArea(6, 100);
	
	public RetroFrame(String title)
	{
		super(title);
		System.out.println("RetroFrame initializing");
		
		
		txtBatterSplits.setTransferHandler(new BatterSplitTransferHandler());
		JPanel batterPanel = new JPanel();
		//batterPanel.setLayout(new GridLayout(0,2));
		batterPanel.setLayout(new GridBagLayout());
		batterPanel.setPreferredSize(getMaximumSize());

		GridBagConstraints lblSplitsConstraints = new GridBagConstraints();
		lblSplitsConstraints.gridx = 0;
		lblSplitsConstraints.gridy = 0;
		lblSplitsConstraints.gridwidth = 1;

		GridBagConstraints batterSplitsConstraints = new GridBagConstraints();
		batterSplitsConstraints.gridx = 1;
		batterSplitsConstraints.gridy = 0;
		batterSplitsConstraints.gridwidth = 5;
		batterSplitsConstraints.weightx = 1.0;
		txtBatterSplits.setPreferredSize(new Dimension(800, 400));
		
		batterPanel.add(lblBatterSplits, lblSplitsConstraints);
		batterPanel.add(new JScrollPane(txtBatterSplits), batterSplitsConstraints);
		
		//batterPanel.add(new JScrollPane(txtBatterSplits));
		
		GridBagConstraints lblDefConstraints = new GridBagConstraints();
		lblDefConstraints.gridx = 0;
		lblDefConstraints.gridy = 1;		
		lblDefConstraints.gridwidth = 1;

		GridBagConstraints batterDefConstraints = new GridBagConstraints();
		batterDefConstraints.gridx = 1;
		batterDefConstraints.gridy = 1;		
		batterDefConstraints.gridwidth = 5;
		batterDefConstraints.weightx = 1.0;

		txtBatterDef.setPreferredSize(new Dimension(800, 200));
		batterPanel.add(lblBatterDef, lblDefConstraints);
		batterPanel.add(new JScrollPane(txtBatterDef), batterDefConstraints);
		
		//batterPanel.add(new JScrollPane(txtBatterDef));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(batterPanel);
		this.setSize(800,600);
		this.setVisible(true);
		
		System.out.println("RetroFrame DONE initializing");
	}
	
}
