import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		String specialWord = "link=";
		int specialWordLen = specialWord.length();
//		String[] words = scan.nextLine().split(" ");
//		for (String word : words) {
//			link.add(new Link(word));
//		}

		while (true) {
			String text = scan.nextLine();
			if (text.equals("eod")) {
				break;
			}
			String[] words = text.split(" ");
			for (String word : words) {
				if (word.length() > specialWordLen) {
					word = word.toLowerCase();
					String keyWordSubstring = word.substring(0, specialWordLen);
					String linkContent = word.substring(specialWordLen);
					String[] idAndWeight = linkContent.split("\\(");


					if (keyWordSubstring.equals(specialWord)) {
						if (isCorrectId(idAndWeight[0])) {
							Link newLink = createLink(linkContent);
							if (newLink != null) {
								link.add(newLink);
							}
						}
					}
				}
			}
		}
	}

	public static boolean isCorrectId(String id) {
		id = id.toLowerCase();
		char ch = id.charAt(0);
		if ((ch >= 'a' && ch <= 'z')) {
			for (int i = 1; i < id.length(); i++) {
				ch = id.charAt(i);
				if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '_'){
					continue;
				}
				else{
					return false;
				}
			}
		}
		else {
			return false;
		}
		return true;
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static Link createLink(String link) {
		StringBuilder linkRef = new StringBuilder();
		StringBuilder linkWeight = new StringBuilder();
		boolean isInParenthesis = false;
		boolean isFinished = false;

		for (int i = 0; i < link.length(); i++) {
			if (isFinished){
				//probably is a wrong link because has something after parenthesis
				//System.out.println("Error: invalid thing after parenthesis");
				return null;
			}
			char ch = link.charAt(i);
			if (ch == '(') {
				isInParenthesis = true;
			}
			else if (isInParenthesis) {
				if ((ch >= '0' && ch <= '9')){
					linkWeight.append(ch);
				}
				else if (ch == ')'){
					isFinished = true;
				}
				else{
					//wrong because something other than a number is inside of parenthesis
					//System.out.println("Error: invalid char inside parenthesis");
					return null;
				}
			}
			else if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '_') {
				linkRef.append(ch);
			}
		}

		if (isInParenthesis && !isFinished){
			return null;
		}

		if (linkWeight.toString().equals("")){
			linkWeight.append('1');
		}
		Link newLink = new Link(linkRef.toString(),Integer.valueOf(linkWeight.toString()));
		return newLink;
	}

	public int[] getWeights(){
		int[] weights = new int[link.size()];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = link.get(i).weight;
		}
		return weights;
	}

	public void showArray(int[] weights){
		StringBuilder sb = new StringBuilder();
		if (weights.length == 0){return;}

		for (int i = 0; i < weights.length; i++) {
			if (i != 0){sb.append(" ");}
			sb.append(weights[i]);
		}
		System.out.println(sb.toString());
	}

	public void insertSort(int[] weights){
		for (int i = weights.length - 1; i >= 0; i--) {
			for (int j = i + 1; j < weights.length; j++) {
				if (weights[j-1] > weights[j]){
					int temp = weights[j];
					weights[j] = weights[j-1];
					weights[j-1] = temp;
				}
				else{break;}
			}
			showArray(weights);
		}
	}

	public void bubbleSort(int[] weights){
		showArray(weights);

		for (int i = weights.length-1; i > 0; i--) {
			for (int j = weights.length-1; j > 0; j--) {
				if (weights[j-1] > weights[j]){
					int temp = weights[j-1];
					weights[j-1] = weights[j];
					weights[j] = temp;
				}
			}
			showArray(weights);
		}
	}

	public void selectSort(int[] weights){
		showArray(weights);

		for (int i = weights.length-1; i > 0; i--) {
			int biggestIndex = i;
			for (int j = i -1; j >= 0; j--) {
				if (weights[j] > weights[biggestIndex]){
					biggestIndex = j;
				}
			}

			if (biggestIndex != i) {
				int temp = weights[biggestIndex];
				weights[biggestIndex] = weights[i];
				weights[i] = temp;
			}
			showArray(weights);
		}
	}

	private void merge(int[] arr, int left, int mid, int right) {
		int nLeft = mid - left + 1;
		int nRight = right - mid;

		int[] tempArr1 = new int[nLeft];
		int[] tempArr2 = new int[nRight];

		for (int i = 0; i < nLeft; i++) {
			tempArr1[i] = arr[left + i];
		}
		for (int i = 0; i < nRight; i++) {
			tempArr2[i] = arr[mid + 1 + i];
		}

		int i = 0;
		int j = 0;
		int k = left;
		while (i < nLeft && j < nRight) {
			if (tempArr1[i] < tempArr2[j]) {
				arr[k] = tempArr1[i];
				i++;
			}
			else {
				arr[k] = tempArr2[j];
				j++;
			}
			k++;
		}

		while (i < nLeft) {
			arr[k] = tempArr1[i];
			i++;
			k++;
		}

		while (j < nRight) {
			arr[k] = tempArr2[j];
			j++;
			k++;
		}
	}

	public void iterativeMergeSort(int[] weights){
		int n = weights.length;
		showArray(weights);

		for (int currSize = 1; currSize <= n - 1; currSize = 2 * currSize) {

			for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {

				int mid = Math.min(leftStart + currSize - 1, n - 1);
				int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);

				merge(weights, leftStart, mid, rightEnd);
			}
			showArray(weights);
		}
	}

	public int[] countSort(int[] weights, int whichDigit){
		int[] outputArr = new int[weights.length];

		int[] digits = new int[weights.length];
		for (int i = 0; i < weights.length; i++) {
			String curr = String.valueOf(weights[i]);
			if (curr.length() - whichDigit < 0) {
				digits[i] = 0;
			}
			else {
				digits[i] = curr.charAt(curr.length() - whichDigit) - '0';
			}
		}

		int max = digits[0];
		for (int i = 1; i < weights.length; i++) {
			if (max < digits[i]) {
				max = digits[i];
			}
		}

		int[] countArr = new int[max + 1];
		for (int i = 0; i < digits.length; i++) {
			countArr[digits[i]]++;
		}

		for (int i = 1; i < countArr.length; i++) {
			countArr[i] = countArr[i - 1] + countArr[i];
		}

		for (int i = weights.length - 1; i >= 0; i--) {
			outputArr[countArr[digits[i]]-1] = weights[i];
			countArr[digits[i]]--;
		}

		return outputArr;
	}

	public void radixSort(int[] weights){
		showArray(weights);
		for (int i = 1; i < 4; i++){
			weights = countSort(weights,i);
			showArray(weights);
		}
	}

	@Override
	public String toString() {
		String retStr="Document: " + name + link.toString();

		return retStr;
	}

	public String toStringReverse() {
//		String retStr="Document: "+name;
////		ListIterator<Link> iter=link.listIterator();
////		while(iter.hasNext())
////			iter.next();
////		//TODO
////		while(iter.hasPrevious()){
////		}

		String retStr = "Document: " + name + link.toStringReverse();
		return retStr;
	}
}

