
public class BruteForceSearch {

	public int search(String pattern, String text) {
		int n = text.length();
		int m = pattern.length();
		int lengthDiff = n - m;
		boolean found;
		
		for (int k = 0; k < lengthDiff; k++) {
			found = true;
			for (int i = 0; i < m; i++) {
				if (pattern.charAt(i) != text.charAt(k+i)) {
					found = false;
					break;
				}
			}
			if (found) return k;
		}
		
		return -1;
	}
}
