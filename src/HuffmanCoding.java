import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	/**
	 * This would be a good place to compute and store the tree.
	 */
	
   public HuffmanCoding(String text) {
		Map<Character, Integer> table = new HashMap<Character, Integer>();
		for (char ch : text.toCharArray()) {
			if (!table.containsKey(ch)) {
				table.put(ch, 0);
			}
			table.put(ch, table.get(ch) + 1);
		}
		PriorityQueue<Node> queue = new PriorityQueue<Node>(table.size(), new NodeComparator());
		for (char c : table.keySet()) {
			Node n = new Node();
			n.alphabet = c;
			n.i = table.get(c);
			queue.add(n);
		}
		while (queue.size() >= 2) {
			Node first, second;
			first = queue.poll();
			second = queue.poll();
			Node n = new Node();
			n.leftNode = first;
			n.rightNode = second;
			n.i = first.i + second.i;
			queue.add(n);
		}
		Node root = queue.peek();
		addNewCode(root, "");

		for (Character c : encode.keySet()) {
			System.out.println("[" + c + "] is " + encode.get(c));
		}
	}

	public void addNewCode(Node n, String base) {
		if (n.alphabet != null) {
			encode.put(n.alphabet, base);
			decode.put(base, n.alphabet);
		} else {
			addNewCode(n.leftNode, base + "0");
			addNewCode(n.rightNode, base + "1");
		}
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should return
	 * the encoded text as a binary string, that is, a string containing only 1 and
	 * 0.
	 */
	public String encode(String text) {
		StringBuilder output = new StringBuilder();
		for (char c : text.toCharArray()) {
			output.append(encode.get(c));
		}
		return output.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree, and
	 * return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		StringBuilder output = new StringBuilder();
		String ch = "";
		for (char c : encoded.toCharArray()) {
			ch += c;
			if (decode.containsKey(ch)) {
				output.append(decode.get(ch));
				ch = "";
			}
		}
		
		return output.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print out
	 * the encoding tree.
	 */
	public String getInformation() {

		String string = "";
        for (Character ch : encode.keySet()) {
			string += "[" + ch + "] is " + encode.get(ch) + "\n";
		}
		return string;
	}
	class Node {
		int i = 0;
		Character alphabet = null;
		Node leftNode = null;
		Node rightNode = null;

		@Override
		public String toString() {
			return alphabet + "(" + i + ")";
		}
	}

	class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.i - o2.i;
		}
	}

	Map<Character, String> encode = new HashMap<Character, String>();
	Map<String, Character> decode = new HashMap<String, Character>();

}
