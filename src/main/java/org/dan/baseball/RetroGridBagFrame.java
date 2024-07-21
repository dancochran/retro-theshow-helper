package org.dan.baseball;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class RetroGridBagFrame extends JFrame
{

	private static final long serialVersionUID = 6752118759799234023L;

	public RetroGridBagFrame(String title)
	{
		super(title);		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void addComponentsToPane()
	{
		Container pane = this.getContentPane();
		JTextArea txtBatterSplits = new JTextArea(12, 100);
		JTextArea txtBatterDef = new JTextArea(6, 100);

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		txtBatterSplits.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Batter Splits"));
		txtBatterSplits.setTransferHandler(new BatterSplitTransferHandler());
		txtBatterSplits.setPreferredSize(new Dimension(800, 400));
		pane.add(txtBatterSplits, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		txtBatterDef.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Batter Defense"));
		txtBatterDef.setTransferHandler(new BatterDefTransferHandler());
		txtBatterDef.setPreferredSize(new Dimension(800, 200));
		pane.add(txtBatterDef, c);
		
	}
}
