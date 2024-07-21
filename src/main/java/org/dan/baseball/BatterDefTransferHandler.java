package org.dan.baseball;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class BatterDefTransferHandler extends TransferHandler
{

	private static final long serialVersionUID = 5453033305731509414L;

	public boolean canImport(TransferHandler.TransferSupport info)
	{
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor))
		{
			return false;
		}
		return true;
	}

	public int getSourceActions(JComponent c)
	{
		return TransferHandler.COPY_OR_MOVE;
	}

	public boolean importData(TransferHandler.TransferSupport info)
	{
		if (!info.isDrop())
		{
			return false;
		}

		Transferable t = info.getTransferable();
		String data;
		try
		{
			data = (String) t.getTransferData(DataFlavor.stringFlavor);
		} 
		catch (Exception e)
		{
			return false;
		}

		System.out.println("Received drop data: <" + data + ">");

		//List<String> droplines = data.lines().forEach(s -> s.replaceAll("Daily Splits", ""));
		//List<String> droplines = data.lines().collect(Collectors.reducing(
		//		s, s -> s.replaceAll("Daily Splits", "")
		//		));
		String[] strArray = data.lines().flatMap(s -> Stream.of(s.replaceAll("Daily Splits", "").replaceAll("i", ""))).toArray(String[]::new);
		
		//String[] strArray = data.lines().toArray(String[]::new);
		

		System.out.println("finished removing i");
		System.out.println("num lines = " + strArray.length);
		
		for (String line : strArray)
		{
			System.out.println("Def data line: " + line);
		}
		
		return true;
	}

}
