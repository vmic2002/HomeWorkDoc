import java.util.Iterator;
//import java.util.NoSuchElementException;
import java.util.ArrayList;
// add your imports here

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
    // TODO: implement the iterator class here
    // add your own helper methods if necessary
    
    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
	ArrayList<TSTNode<T>> tstList;
	int index;
	TSTIterator(TST<T> t){
		tstList = t.getAsList();
		index = 0;
	}
    @Override
    public boolean hasNext() {
      return index<tstList.size()&&tstList.get(index)!=null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws NoSuchElementException
     *         if the iteration has no more elements
     */
    @Override
    public T next() {
        if(!hasNext())
        	return null;
        	//throw new NoSuchElementException();
        else {
        	T result = tstList.get(index).element;   
        	index++;
        	return result;
        }
     
    }
}