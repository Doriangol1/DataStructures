package app;

import java.util.Comparator;


public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO sort
		int low = 0;
		int high = list.size()-1;
		/** 
	   	if (low >= high) {
      		return list;
   		}
		*/
/** 
   		//int lowEndIndex = Partition(list, low, high);
		int firstHighIndex = lowEndIndex;
		int newHighIndex = high + 1;
		while (lowEndIndex > 1){
			lowEndIndex = Partition(list, low, lowEndIndex);
		}
		newHighIndex = Partition(list, firstHighIndex, newHighIndex);
		while ( newHighIndex  > firstHighIndex){
			newHighIndex = Partition(list, firstHighIndex, newHighIndex);
		}
	*/
   		sortquick(low, high);
		return list;

	}

	private void sortquick(int low, int high){
		if (low >= high) {
			return;
		 }
		int lowEndIndex = Partition(low, high);
		sortquick(low, lowEndIndex-1);
		sortquick(lowEndIndex + 1, high);


	}
	private int Partition(int low, int high){
		
			int middle = (low + high) / 2;
			list.swap(high, middle);
			//int pivot = high;
			int storeIndex = low;
			for (int index = low; index<=high-1; index++) {
			  	if (list.compare(index, high, comparator) <= 0){
					
					list.swap(index, storeIndex);
					storeIndex++;

		  		} 
			}
			list.swap(storeIndex, high);
			return storeIndex;
		  }
		  
	
}
