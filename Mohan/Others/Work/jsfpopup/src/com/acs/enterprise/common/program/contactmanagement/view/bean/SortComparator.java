/*
 * Created on Nov 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.acs.enterprise.common.program.contactmanagement.view.bean;


import java.util.Comparator;

import javax.faces.model.SelectItem;

/**
 * @author bmadhu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SortComparator implements Comparator {
	public int compare(Object obj1, Object obj2) {
		String lable1 = ((SelectItem) obj1).getLabel();
		String lable2 = ((SelectItem) obj2).getLabel();
		if (lable1 != null && lable2 != null && !lable1.equals("")
				&& !lable2.equals("")) {
			int lable1Num = Integer.parseInt(lable1.trim());
			int lable2Num = Integer.parseInt(lable2.trim());

			if (lable1Num > lable2Num)
				return 1;
			else if (lable1Num < lable2Num)
				return -1;
			else
				return 0;
		} else {
			return -1;
		}
	}
}
