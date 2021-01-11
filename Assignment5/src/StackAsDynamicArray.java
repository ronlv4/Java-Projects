/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.NoSuchElementException;

public class StackAsDynamicArray<T> implements Stack<T>{

	private List<T> array;
	
	public StackAsDynamicArray() {
		this.array = new DynamicArray<T>();
	}
	
	public boolean isEmpty() {
		return array.isEmpty();
	}
	
	public T pop(){
		if(isEmpty())
			throw new NoSuchElementException();
		return array.remove(array.size()-1);
	}
		
	public void push(T element){
		array.add(element);		
	}
	
	public T peek() {
		if(isEmpty())
			throw new NoSuchElementException();
		return array.get(array.size() - 1);
	}
}
