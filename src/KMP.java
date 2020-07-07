/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	private String pattern;
	private String text;

	public KMP(String pattern, String text) {
		this.pattern = pattern;
		this.text = text;
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		int n = text.length();
		int m = pattern.length();
		int[] matchTab = getMatchTable();

		int k = 0;
		int i = 0;

		while (k + i < n) {
			if (pattern.charAt(i) == text.charAt(k + i)) {
				i = i + 1;
				if (i == m) {
					return k;
				}
			} else if (matchTab[i] == -1) {
				k = k + i + 1;
				i = 0;
			} else {
				k = k + i - matchTab[i];
				i = matchTab[i];
			}
		}
		return -1;
	}

	private int[] getMatchTable() {
		int m = pattern.length();
		int[] matchTab = new int[m];
		int j = 0;
		int pos = 2;

		matchTab[0] = -1;
		matchTab[1] = 0;

		while (pos < m) {
			if (pattern.charAt(pos - 1) == pattern.charAt(j)) {
				matchTab[pos] = j + 1;
				pos = pos + 1;
				j = j + 1;
			} else if (j > 0) {
				j = matchTab[j];
			} else {
				matchTab[pos] = 0;
				pos = pos + 1;
			}
		}

		return matchTab;
	}
}
