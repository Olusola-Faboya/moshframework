package moshproject.common;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.query.space.grid.GridCell;
import repast.simphony.random.RandomHelper;

public class MOSHUtils {
	public static <T> T randomElementOf(final List<T> list) {
		 return list.get(RandomHelper.nextIntFromTo(0, list.size() - 1));
	 }

	
	public static <T> List<GridCell<T>> getObjectList(
            final List<GridCell<T>> coverageArea) {
				final ArrayList<GridCell<T>> neighborhoodList = new ArrayList<GridCell<T>>();
					for (final GridCell<T> cell : coverageArea) {
						if (cell.size()>0) 
							neighborhoodList.add(cell);
						}
					return neighborhoodList;
	}
}
