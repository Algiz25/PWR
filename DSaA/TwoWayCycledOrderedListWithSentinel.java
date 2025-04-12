import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

	private class Element implements Comparable<Element>{
		public Element(E e) {
			object = e;
		}
		public Element(E e, Element next, Element prev) {
			object = e;
			this.next = next;
			this.prev = prev;
		}
		// add element e after this
		public void addAfter(Element elem) {
			if (elem.next != null) {
				elem.prev.next = elem.next;
			}
			if (elem.prev != null) {
				elem.next.prev = elem.prev;
			}

			elem.next = this.next;
			elem.prev = this;

			this.next.prev = elem;
			this.next = elem;
		}
		// assert it is NOT a sentinel
		public void remove() {
			if (this != sentinel){
				this.prev.next = this.next;
				this.next.prev = this.prev;

				this.next = null;
				this.prev = null;
			}
		}

		@Override
		public String toString() {
			return object.toString();
		}
		E object;
		Element next=null;
		Element prev=null;

		@Override
		public int compareTo(Element o) {
			if (this.object instanceof Comparable){
				return ((Comparable)this.object).compareTo(o.object);
			}
			else {
				throw new IllegalArgumentException("Object is not comparable");
			}
			//return object.toString().split("\\(")[0].compareTo(o.toString().split("\\(")[0]);
		}
	}


	Element sentinel;
	int size = 0;

	private class InnerIterator implements Iterator<E>{
		Element current;

		public InnerIterator() {
			current = sentinel;
		}
		@Override
		public boolean hasNext() {
			return current.next != sentinel;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current = current.next;
			return current.object;
		}
	}

	private class InnerListIterator implements ListIterator<E>{
		Element current;

		public InnerListIterator() {
			current = sentinel;
		}
		@Override
		public boolean hasNext() {
            return current.next != sentinel;
        }

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current = current.next;
			return current.object;
		}
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean hasPrevious() {
			return current.prev != sentinel;
		}
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			current = current.prev;
			return current.object;
		}
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
	public TwoWayCycledOrderedListWithSentinel() {
		sentinel = new Element(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		Element elem = new Element(e);

//		String elementRef = elem.object.toString().split("\\(")[0];
		Element node = sentinel;
		while (node.next != sentinel) {
//			String nodeRef = node.next.object.toString().split("\\(")[0];
			int compare = elem.compareTo(node.next);
			//int compare = elem.toString().compareTo(node.next.toString());
			if (compare < 0) {
				node.addAfter(elem);
				size++;
				return true;
			}
			node = node.next;
		}
		node.addAfter(elem);
		size++;
		return true;
	}

	private Element getElement(int index) {
//		if (index > size-1){
//			return null;
//		}
//
//		Element node = sentinel.next;
//		for (int i = 0; i < index; i++) {
//			node = node.next;
//		}
//		return node;
		return null;
	}

	private Element getElement(E obj) {
		//TODO
		return null;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	@Override
	public boolean contains(E element) {
//		Element node = sentinel;
//		while (node.next != sentinel) {
//			if (node.next.toString().equals(element.toString())) {
//				return true;
//			}
//		}
//		return false;
		return indexOf(element) != -1;
	}

	@Override
	public E get(int index) throws NoSuchElementException {
		if (index > size-1 || index < 0) {
			throw new NoSuchElementException();
		}

		Element node = sentinel.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		Element node = sentinel;
		Element elem = new Element(element);
		int i = 0;
		while (i < size) {
//			String nextNodeRef = node.next.object.toString().split("\\(")[0];
//			String elementRef = element.toString().split("\\(")[0];


//			System.out.println("n: "+nextNodeRef);
//			System.out.println("e: "+elementRef);
//			System.out.println(nextNodeRef.equals(elementRef));

			if(node.next.compareTo(elem) == 0){

			}

//			if (nextNodeRef.equals(elementRef)) {
//				return i;
//			}
			node = node.next;
			i++;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
		if (index > size-1 || index < 0){
			throw new NoSuchElementException();
		}

		Element node = sentinel.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;

		size--;
		return node.object;
	}

	@Override
	public boolean remove(E e) {
		Element node = sentinel.next;
		Element elem = new Element(e);
		while (node!= sentinel) {
//			String ref = node.toString().split("\\(")[0];
//			String eRef = e.toString().split("\\(")[0];
			if (node.compareTo(elem) == 0) {
				node.prev.next = node.next;
				node.next.prev = node.prev;

				size--;
				return true;
			}
			node = node.next;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	//@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
		if (this == other || other.isEmpty()){return;}

		if (isEmpty()){
			sentinel.next = other.sentinel.next;
			other.sentinel.next.prev = sentinel;

			sentinel.prev = other.sentinel.prev;
			other.sentinel.prev.next = sentinel;

			size = other.size;
			other.clear();
		}
		else{
			Element thisNode = sentinel.next;
			Element otherNode = other.sentinel.next;

			Element newSentinel = new Element(null);
			newSentinel.next = newSentinel;
			newSentinel.prev = newSentinel;

			Element newNode = newSentinel;

			int numOfElements = size + other.size;
			int numOfAddedElements = 0;

			Element toBeAdded;

			while (numOfAddedElements < numOfElements) {
				if (thisNode == sentinel) {
					toBeAdded = otherNode;
					otherNode = otherNode.next;

					newNode.addAfter(toBeAdded);
					newNode = newNode.next;

					numOfAddedElements++;
					continue;
				}
				if (otherNode == other.sentinel) {
					toBeAdded = thisNode;
					thisNode = thisNode.next;

					newNode.addAfter(toBeAdded);
					newNode = newNode.next;

					numOfAddedElements++;
					continue;
				}

				if (thisNode.compareTo(otherNode) <= 0){
					toBeAdded = thisNode;
					thisNode = thisNode.next;

					newNode.addAfter(toBeAdded);
					newNode = newNode.next;

					numOfAddedElements++;
				}
				else{
					toBeAdded = otherNode;
					otherNode = otherNode.next;

					newNode.addAfter(toBeAdded);
					newNode = newNode.next;

					numOfAddedElements++;
				}
			}

			size = numOfAddedElements;
			this.sentinel = newSentinel;
			other.clear();
		}

	}
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {
		Element node = sentinel.next;
		Element elem = new Element(e);
		int sizeForFor = size;
		for (int i = 0; i < sizeForFor; i++) {
			String reference = node.toString().split("\\(")[0];
			String eReference = e.toString().split("\\(")[0];
//			System.out.println(reference + "  "+ eReference);
			if (node.compareTo(elem) == 0) {
				node.prev.next = node.next;
				node.next.prev = node.prev;
				size--;
			}
			node = node.next;
		}
	}

	public int findLongestSequence(){
		if (isEmpty()){
			return 0;
		}

		int longestSequence = 1;
		int currentSequence = 1;

		Element node = sentinel.next;
		while (node.next != sentinel) {
			if (node.compareTo(node.next) == 0) {
				currentSequence++;
			}
			else{
				currentSequence = 1;
			}

			if (currentSequence > longestSequence) {
				longestSequence = currentSequence;
			}

			node = node.next;
		}
		return longestSequence;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		if(!isEmpty()) {
			Element node = sentinel;
			//sb.append('\n');
			while (node.next != sentinel) {
				if (i % 10 == 0){
					sb.append('\n');
				}
				if (i % 10 != 0) {
					sb.append(' ');
				}
				sb.append(node.next.toString());

				node = node.next;
				i ++;
			}
		}
		return sb.toString();
	}

	public String toStringReverse(){
		StringBuilder sb = new StringBuilder();
		int i = 0;
		if(!isEmpty()) {
			Element node = sentinel;
			//sb.append('\n');
			while (node.prev != sentinel) {
				if (i % 10 == 0){
					sb.append('\n');
				}
				if (i % 10 != 0) {
					sb.append(' ');
				}
				sb.append(node.prev.toString());

				node = node.prev;
				i ++;
			}
		}
		return sb.toString();

//		StringBuilder sb = new StringBuilder();
//		if (!isEmpty()){
//			Element node = sentinel;
//			while (node.prev != sentinel){
//				sb.append('\n');
//				sb.append(node.prev.toString());
//				node = node.prev;
//			}
//		}
//		return sb.toString();
	}
}

