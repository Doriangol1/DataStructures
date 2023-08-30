package app;

import java.util.Comparator;


public class InsertionSorter<T> extends AbstractSorter<T> {

	public InsertionSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO sort
		int i = 0;
		int j = 0;

		for (i=0;i<list.size()-1;i++){
			j=i;
			while (j >= 0 && list.compare(j, j+1, comparator) > 0){
				list.swap(j, j+1);
				--j;
				
			}
		}
		return list;
	}
}
