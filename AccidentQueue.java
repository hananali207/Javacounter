package accidentpack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class AccidentQueue implements Queue<Report>{
	int head;
	int tail;
	Report[] elements;

	public AccidentQueue(int initialSize) {
		this.head = 0;
		this.tail = head;
		this.elements = new Report[initialSize];
		
	}
	
	@Override
	public int size() {
		return (tail-head) & (elements.length - 1);
	}

	@Override
	public boolean isEmpty() {
		return head == tail;
	}

	@Override
	public boolean contains(Object o) {
		
		return false;
	}

	public Report peekLast() {
		if(!isEmpty()) {
			return elements[tail - 1];
		}
		return null;
	}
	
	@Override
	public Iterator<Report> iterator() {
		
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		if(!isEmpty()) {
			elements[head] = null;
			head+=1;
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Report> c) {
		ArrayList arr = (ArrayList) c;
		for(int i = 0; i < c.size(); i++) {
			this.add((Report) arr.get(i));
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean add(Report e) {
		if(tail == elements.length - 1) {
			tail = 0;
			if(tail == head) {
				resize(this.size() * 2);
			}
		}
		elements[tail] = e;
		tail += 1;
		return true;
	}

	@Override
	public boolean offer(Report e) {
		if(tail == elements.length - 1) {
			tail = 0;
			if(tail == head) {
				resize(elements.length * 2);
			}
		}
		
		this.elements[tail] = e;
		this.tail += 1;
		return true;
	}

	@Override
	public Report remove() {
		if(!isEmpty()) {
			Report removedR = elements[head];
			elements[head] = null;
			head+=1;
			return removedR;
		}
		return null;
	}

	@Override
	public Report poll() {
		if(!isEmpty()) {
			Report removedR = elements[head];
			elements[head] = null;
			head+=1;
			return removedR;
		}
		return null;
	}

	@Override
	public Report element() {
		if(!isEmpty()) {
			return elements[head];
		}
		return null;
	}

	@Override
	public Report peek() {
		// TODO Auto-generated method stub
		if(!isEmpty()) {
			return elements[head];
		}
		return null;
	}
	
	public void resize(int newSize) {
		Report[] tempArr = (Report[]) new Report[newSize];
		int current = head;
		int currentSize = this.size();
		for(int i = 0; i < currentSize; i++) {
			tempArr[i] = elements[current];
			current = (current + 1) % currentSize;
		}
		elements = tempArr;
		head = 0;
		tail = currentSize;
		
	}

}
