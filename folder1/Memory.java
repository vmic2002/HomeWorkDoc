

import java.util.LinkedList;
public class Memory{
	LinkedList<StringInterval> intervalList;
	char[] memoryArray;
	static int idCount;

	private class StringInterval{
		int id;
		int start;
		int length;
		StringInterval(int id, int start, int length){
			this.id = id;
			setStart(start);
			this.length = length;

		}

		void setStart(int start){
			this.start = start;
		}

	}


	public Memory(int length) {
		idCount = 0;
		intervalList = new LinkedList<StringInterval>();
		memoryArray = new char[length];
	}

	public static void main(String[] args) {
		System.out.println("Hello World");
		Memory memory = new Memory(0);
		//tester code below
		memory.put("kiwi");
		memory.put("pomegranate");
		memory.put("apple");
		memory.get(1);
		for (int i=0; i<memory.intervalList.size(); i++) {
			System.out.println(i+"<>"+ memory.get(i)+ "<>"+ memory.intervalList.get(i).start);
		}
		memory.remove("pomegranate");
		System.out.println("POMEGRANATE REMOVED");
		int i=0;
		for (StringInterval strInt:memory.intervalList) {
			System.out.println(i+"<>"+strInt.id+ "<>"+ strInt.start);
			i++;
		}
		memory.defragment();
		System.out.println("DEFRAGMENT");
		i=0;
		for (StringInterval strInt:memory.intervalList) {
			System.out.println(i+"<>"+strInt.id+ "<>"+ strInt.start);
			i++;
		}
		System.out.println(">>"+memory.get(10));
	}

	public String get(int id) {

		for (StringInterval s:intervalList) {

			if (s.id==id) {
				String result = "";
				for (int i = s.start; i<s.start+s.length;i++) {
					result+=memoryArray[i];
				}
				return  result;
			}

		}
		return null;//id is not valid
	}

	public int get(String s) {
		int counter;
		boolean areEqual = true;
		for (StringInterval strInt:intervalList) {
			counter= 0;
			if (s.length()!=strInt.length) {}else {
				int i = strInt.start;
				while (areEqual && i<memoryArray.length && counter<s.length()) {
					if (memoryArray[i] == s.charAt(counter)) {
						counter++;
						i++;
					}else {
						areEqual = false;

					}
				}
				if (areEqual) {
					return strInt.id;
				}
			}
		}
		return -1;//string s isn't found in memory
	}

	public String remove(int id) {

		String temp="";
		int counter = 0;
		for (StringInterval strInt: intervalList) {
			if (strInt.id==id) {
				for (int i=strInt.start; i<strInt.start+strInt.length; i++) {
					temp+=memoryArray[i];
				}
				intervalList.remove(counter);
				//idCount--;
				return temp;
			}
			counter++;

		}
		return null;//id isn't found in the list
	}

	public int remove(String s) {
		int temp = get(s);
		int counter = 0;
		for (StringInterval strInt: intervalList) {
			if (strInt.id==temp) {
				intervalList.remove(counter);
				return temp;
			}
			counter++;
		}
		return temp;
	}

	public void add(String s, int startIndex) {

		int temp = startIndex;

		if (s.length()<=memoryArray.length-startIndex) {
			for (int i=0; i<s.length();i++) {
				memoryArray[startIndex]= s.charAt(i);
				startIndex++;
			}
		}
		intervalList.add(new StringInterval(idCount,temp,s.length()));
		idCount++;

	}

	public void change(String s, int startIndex) {
		for (int i=0; i<s.length();i++) {
			memoryArray[startIndex]= s.charAt(i);
			startIndex++;
		}
	}


	public int put(String s) {
		if (s.length()>memoryArray.length)
			return -1;
		int counter = 0;
		int index2 = 0;
		int size = intervalList.size();
		boolean b = true;
		while(b&&index2<size) {
			StringInterval strInt = intervalList.get(index2);
			if (strInt.start==counter) {
				counter+=strInt.length;
			}else if (s.length()<=strInt.start-counter) {
				add(s, counter);
				return idCount-1;
			}
			index2++;
		}
		if(intervalList.size()==0) {
			add(s,0);
			return idCount-1;
		}
		StringInterval temp = intervalList.get(intervalList.size()-1);
		if (s.length()<=memoryArray.length-(temp.start+temp.length) + 1) {
			add(s,temp.start+temp.length);
			return idCount-1;
		}
		defragment();
		temp = intervalList.get(intervalList.size()-1);
		if (s.length()<=memoryArray.length-(temp.start+temp.length) + 1) {
			add(s,temp.start+temp.length);
			return idCount-1;
		}
		return -1;
	}

	public void defragment() {
		int counter = 0;
		for (StringInterval strInt:intervalList) {
			if (strInt.start==counter) {
				counter+=strInt.length;
			}else {
				String result = "";
				for (int i = strInt.start; i<strInt.length;i++) {
					result+=memoryArray[i];
				}
				change(result, counter);
				strInt.setStart(counter);
				counter += strInt.length;
			}
		}
	}
}