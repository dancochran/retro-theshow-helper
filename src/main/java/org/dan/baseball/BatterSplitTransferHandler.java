package org.dan.baseball;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class BatterSplitTransferHandler extends TransferHandler
{
	private static final long serialVersionUID = 4493539172361032610L;

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

		// Get the string that is being dropped.
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
		
		String[] strArray = data.lines().flatMap(s -> Stream.of(s.replaceAll("i", ""))).toArray(String[]::new);
		
		//String[] strArray = data.lines().toArray(String[]::new);
		
		String[] splitrh1 = strArray[0].split("\\s+");
//		for (int i = 0; i < splitrh1.length; i++)
//		{
//			splitrh1[i] = splitrh1[i].replaceAll("[^0-9.]", "");
//		}
		
		String[] splitlh1 = strArray[1].split("\\s+");

		String[] splitrh2 = strArray[2].split("\\s+");

		String[] splitlh2 = strArray[3].split("\\s+");

		System.out.println("finished removing i");
		System.out.println("splitrh1 = <" + Arrays.toString(splitrh1) + ">");
		System.out.println("num lines = " + strArray.length);
		
		int rh_ab = Integer.parseInt(splitrh1[2]) + Integer.parseInt(splitrh2[2]);
		System.out.println("got rh_ab: " + Integer.toString(rh_ab));
		int lh_ab = Integer.parseInt(splitlh1[2]) + Integer.parseInt(splitlh2[2]);
		System.out.println("got lh_ab");
		int rh_hit = Integer.parseInt(splitrh1[4]) + Integer.parseInt(splitrh2[4]);
		System.out.println("got rh_hit");
		int lh_hit = Integer.parseInt(splitlh1[4]) + Integer.parseInt(splitlh2[4]);
		System.out.println("got lh_hit");
		int rh_2b = Integer.parseInt(splitrh1[5]) + Integer.parseInt(splitrh2[5]);
		System.out.println("got rh_2b");
		int lh_2b = Integer.parseInt(splitlh1[5]) + Integer.parseInt(splitlh2[5]);
		System.out.println("got lh_2b");
		int rh_3b = Integer.parseInt(splitrh1[6]) + Integer.parseInt(splitrh2[6]);
		System.out.println("got rh_3b");
		int lh_3b = Integer.parseInt(splitlh1[6]) + Integer.parseInt(splitlh2[6]);
		System.out.println("got lh_3b");
		int rh_hr = Integer.parseInt(splitrh1[7]) + Integer.parseInt(splitrh2[7]);
		System.out.println("got rh_hr");
		int lh_hr = Integer.parseInt(splitlh1[7]) + Integer.parseInt(splitlh2[7]);
		System.out.println("got lh_hr");
		int tot_rbi = Integer.parseInt(splitrh1[8]) + Integer.parseInt(splitrh2[8]) + Integer.parseInt(splitlh1[8]) + Integer.parseInt(splitlh2[8]);
		System.out.println("got tot_rbi");
		int tot_bb = Integer.parseInt(splitrh1[9]) + Integer.parseInt(splitrh2[9]) + Integer.parseInt(splitlh1[9]) + Integer.parseInt(splitlh2[9]);
		System.out.println("got tot_bb");
		int tot_k = Integer.parseInt(splitrh1[10]) + Integer.parseInt(splitrh2[10]) + Integer.parseInt(splitlh1[10]) + Integer.parseInt(splitlh2[10]);
		System.out.println("got tot_k: " + Integer.toString(tot_k));
		int tot_hbp = Integer.parseInt(splitrh1[11]) + Integer.parseInt(splitrh2[11]) + Integer.parseInt(splitlh1[11]) + Integer.parseInt(splitlh2[11]);
		System.out.println("got tot_hbp");
		int tot_sh = Integer.parseInt(splitrh1[12]) + Integer.parseInt(splitrh2[12]) + Integer.parseInt(splitlh1[12]) + Integer.parseInt(splitlh2[12]);
		System.out.println("got tot_sh");
		int tot_sb = Integer.parseInt(splitrh1[14]) + Integer.parseInt(splitrh2[14]) + Integer.parseInt(splitlh1[14]) + Integer.parseInt(splitlh2[14]);
		System.out.println("got tot_sb");
		int tot_pa = rh_ab + lh_ab + tot_bb + tot_hbp + tot_sh;
		System.out.println("finished summarizing stats");
		
		float rh_avg = (float)rh_hit / (float)rh_ab;
		float lh_avg = (float)lh_hit / (float)lh_ab;
		float rh_iso = (float)(rh_2b + (2 * rh_3b) + (3 * rh_hr)) / (float)rh_ab;
		float lh_iso = (float)(lh_2b + (2 * lh_3b) + (3 * lh_hr)) / (float)lh_ab;
		float k_pct = (float) ((float)tot_k / (float)tot_pa * 100.0);
		float bb_pct = (float) ((float)tot_bb / (float)tot_pa * 100.0);
		
		int conr = (int) ((rh_avg * 479.59) - 43.84);
		int conl = (int) ((lh_avg * 479.59) - 43.84);
		int powr = (int) ((rh_iso * 269.75) + 29.981);
		int powl = (int) ((lh_iso * 269.75) + 29.981);
		int vision = (int) ((k_pct * -2.7022) + 131.02);
		int disc = (int) ((bb_pct * 6.051) + 23.165);
		int clutch = (int) (tot_rbi * 0.9);
		int speed = (int) ((tot_sb + rh_2b + lh_2b + rh_3b + lh_3b) * 1.36);
		int bragg = (int) ((tot_sb + rh_2b + lh_2b + rh_3b + lh_3b) * 1.15);
		
		
		System.out.println("conr: <" + conr + ">");
		System.out.println("conl: <" + conl + ">");
		System.out.println("powr: <" + powr + ">");
		System.out.println("powl: <" + powl + ">");
		System.out.println("vision: <" + vision + ">");
		System.out.println("disc: <" + disc + ">");
		System.out.println("clutch: <" + clutch + ">");
		System.out.println("speed: <" + speed + ">");
		System.out.println("bragg: <" + bragg + ">");
		
		
		return true;
	}
}
