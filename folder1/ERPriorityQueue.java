import java.util.ArrayList;
import java.util.HashMap;

public class ERPriorityQueue{

	public ArrayList<Patient>  patients;
	public HashMap<String,Integer>  nameToIndex;

	public ERPriorityQueue(){

		//  use a dummy node so that indexing starts at 1, not 0

		patients = new ArrayList<Patient>();
		patients.add( new Patient("dummy", 0.0) );

		nameToIndex  = new HashMap<String,Integer>();
	}

	private int parent(int i){
		return i/2;
	}

	private int leftChild(int i){
		return 2*i;
	}

	private int rightChild(int i){
		return 2*i+1;
	}

	/*
    TODO: OPTIONAL
    TODO: Additional helper methods such as isLeaf(int i), isEmpty(), swap(int i, int j) could be useful for this assignment
	 */

	public void upHeap(int i){
		while (i > 1 && patients.get(parent(i)).getPriority()>patients.get(i).getPriority()){
			swap(i, parent(i));
			i = parent(i);
		}
	}

	public void swap(int index1 ,int index2) {
		Patient temp = patients.get(index1);
		patients.set(index1, patients.get(index2));
		patients.set(index2, temp);
		nameToIndex.put(patients.get(index1).getName(), Integer.valueOf(index1));
		nameToIndex.put(patients.get(index2).getName(), Integer.valueOf(index2));
		
	}

	public void downHeap(int i){
		while (!isLeaf(i)&& patients.get(getMinChild(i)).getPriority()<patients.get(i).getPriority()){
			swap(i, getMinChild(i));
			i = getMinChild(i);
		}
	}
	
	public int getMinChild(int i) {//only called if !isLeaf(i)
		if (rightChild(i)>patients.size()) {
			return leftChild(i);
		} else {
			if (patients.get(leftChild(i)).getPriority()<patients.get(rightChild(i)).getPriority()) {
				return leftChild(i);
			} else {
				return rightChild(i);
			}
		}
	}

	public boolean isLeaf(int i) {
		if (patients !=null&& patients.size()>0) 
			return leftChild(i)>patients.size();
		return false;
	}
	//public int getLevel(int i) {
	//	return (int)(Math.log10(i)/Math.log10(2));
	//}
	public boolean contains(String name){
		return nameToIndex.get(name)!=null;
	}

	public double getPriority(String name){
		if (contains(name))
			return patients.get(Integer.parseInt(nameToIndex.get(name).toString())).getPriority();
		return -1.0;
	}

	public double getMinPriority(){
		if (patients!=null && patients.size()>1)
			return patients.get(1).getPriority();
		return -1;
	}

	public String removeMin(){
		if (patients.size()>1) {
			String temp = patients.get(1).getName();
			nameToIndex.put(patients.get(1).getName(), null);
			patients.set(1, patients.remove(patients.size()-1));
			nameToIndex.put(patients.get(1).getName(), Integer.valueOf(1));
			downHeap(1);
			return temp;
		}
		return null;
	}
	
	public String removeMax(){
		if (patients!=null && patients.size()>1) {
			String temp = patients.get(patients.size()-1).getName();
			nameToIndex.put(patients.get(patients.size()-1).getName(), null);
			patients.remove(patients.size()-1);
			return temp;
		}
		return null;
	}

	public String peekMin(){
		if (patients!=null && patients.size()>1)
			return patients.get(1).getName();
		return null;
	}

	/*
	 * There are two add methods.  The first assumes a specific priority.
	 * The second gives a default priority of Double.POSITIVE_INFINITY
	 *
	 * If the name is already there, then return false.
	 */

	public boolean  add(String name, double priority){//add starting at index 1
		if (contains(name))
			return false;
		patients.add(new Patient(name, priority));
		nameToIndex.put(name, Integer.valueOf(patients.size()-1));
		upHeap(patients.size()-1);
		return true;
	}

	public boolean  add(String name){
		return add(name, Double.POSITIVE_INFINITY);
	}

	public boolean remove(String name){
		if (!contains(name))
			return false;
		int index = Integer.parseInt(nameToIndex.get(name).toString());
		nameToIndex.put(patients.get(index).getName(), null);
		patients.set(index, patients.remove(patients.size()-1));
		nameToIndex.put(patients.get(index).getName(), Integer.valueOf(index));
		downHeap(index);
		return true;
	}

	/*
	 *   If new priority is different from the current priority then change the priority
	 *   (and possibly modify the heap).
	 *   If the name is not there, return false
	 */

	public boolean changePriority(String name, double priority){
		if (!contains(name))
			return false;
		int index = Integer.parseInt(nameToIndex.get(name).toString());
		patients.get(index).setPriority(priority);
		if (index==1) {
			if (patients.size()>2) {
				if (patients.get(1).getPriority()>patients.get(getMinChild(1)).getPriority()) {
					downHeap(1);
				}
			}
		} else {
			if (patients.get(index).getPriority()<patients.get(parent(index)).getPriority()) {
				upHeap(index);
				downHeap(index);
			} else {
				downHeap(index);
			}
		}
		return true;
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
		ArrayList<Patient> result = new ArrayList<Patient>();
		if (patients.size()<2)
			return result;
		while (patients.size()>1 && patients.get(1).getPriority()<=threshold) {
			result.add(patients.get(1));
			removeMin();
		}
		return result;
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
		ArrayList<Patient> result = new ArrayList<Patient>();
		while (patients.size()>1 && patients.get(patients.size()-1).getPriority()>=threshold) {
			result.add(patients.get(patients.size()-1));
			removeMax();
		}
		return result;
	}



	static class Patient{
		private String name;
		private double priority;

		Patient(String name,  double priority){
			this.name = name;
			this.priority = priority;
		}

		Patient(Patient otherPatient){
			this.name = otherPatient.name;
			this.priority = otherPatient.priority;
		}

		double getPriority() {
			return this.priority;
		}

		void setPriority(double priority) {
			this.priority = priority;
		}

		String getName() {
			return this.name;
		}

		@Override
		public String toString(){
			return this.name + " - " + this.priority;
		}

		public boolean equals(Object obj){
			if (!(obj instanceof  ERPriorityQueue.Patient)) return false;
			Patient otherPatient = (Patient) obj;
			return this.name.equals(otherPatient.name) && this.priority == otherPatient.priority;
		}

	}
}
