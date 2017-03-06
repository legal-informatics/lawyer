package rs.ac.uns.ftn.informatika.legal.lawyer.util;

import java.util.Comparator;

public class IdComparator implements Comparator<String>{

	@Override
	public int compare(String arg0, String arg1) {
		String[] tokens1 = arg0.split("-");
		int art1 = 0;
		int it1 = 0;
		int pt1 = 0;
		int spt1 = 0;
		int ln1 = 0;
		int p1 = 0;
		for (String token: tokens1) {
			if (token.startsWith("art")) {
				art1 = Integer.parseInt(token.substring(3));
			} else if (token.startsWith("it")) {
				it1 = Integer.parseInt(token.substring(2));
			} else if (token.startsWith("pt")) {
				pt1 = Integer.parseInt(token.substring(2));
			} else if (token.startsWith("spt")) {
				spt1 = Integer.parseInt(token.substring(3));
			} else if (token.startsWith("ln")) {
				ln1 = Integer.parseInt(token.substring(2));
			} else if (token.startsWith("p")) {
				p1 = Integer.parseInt(token.substring(1));
			}
		}
		
		String[] tokens2 = arg1.split("-");
		int art2 = 0;
		int it2 = 0;
		int pt2 = 0;
		int spt2 = 0;
		int ln2 = 0;
		int p2 = 0;
		for (String token: tokens2) {
			if (token.startsWith("art")) {
				art2 = Integer.parseInt(token.substring(3));
			} else if (token.startsWith("it")) {
				it2 = Integer.parseInt(token.substring(2));
			} else if (token.startsWith("pt")) {
				pt2 = Integer.parseInt(token.substring(2));
			} else if (token.startsWith("spt")) {
				spt2 = Integer.parseInt(token.substring(3));
			} else if (token.startsWith("ln")) {
				ln2 = Integer.parseInt(token.substring(2));
			} else if (token.startsWith("p")) {
				p2 = Integer.parseInt(token.substring(1));
			}
		}
		
		if (art1 < art2)
			return -1;
		else if (art1 > art2)
			return 1;
		
		if (it1 < it2)
			return -1;
		else if (it1 > it2)
			return 1;
		
		if (pt1 < pt2)
			return -1;
		else if (pt1 > pt2)
			return 1;
		
		if (spt1 < spt2)
			return -1;
		else if (spt1 > spt2)
			return 1;
		
		if (ln1 < ln2)
			return -1;
		else if (ln1 > ln2)
			return 1;
		
		if (p1 < p2)
			return -1;
		else if (p1 > p2)
			return 1;
		
		return 0;
	}

}
