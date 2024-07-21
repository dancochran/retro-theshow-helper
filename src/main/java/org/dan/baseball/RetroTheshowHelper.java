package org.dan.baseball;

import javax.swing.UIManager;

public class RetroTheshowHelper
{
	public static final String TITLE = "Retrosheet TheShow Helper";

	private static void createAndShowGUI()
	{
		//RetroFrame rf = new RetroFrame(TITLE);
		
		RetroGridBagFrame rfgb = new RetroGridBagFrame(TITLE);
		rfgb.addComponentsToPane();
		rfgb.pack();
		rfgb.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
		
	}

}
