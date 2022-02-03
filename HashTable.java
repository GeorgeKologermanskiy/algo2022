import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HashTable {

    static class LinkedList {

        public LinkedList() {
            head = null;
        }

        public boolean contains(String o) {
            Node curr = head;
            while(curr != null) {
                if (curr.val.equals(o)) {
                    return true;
                }
                curr = curr.next;
            }
            return false;
        }

        public void add(String o) {
            Node curr = new Node(o);
            curr.next = head;

            head = curr;
        }

        public void remove(String o) {
            Node prev = null;
            Node curr = head;
            while(curr != null) {
                if (curr.val.equals(o)) {
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            if (curr == null) {
                return;
            }

            if (curr == head) {
                head = head.next;
                return;
            }

            prev.next = curr.next;
        }

        class Node {
            public Node() {
                this(null, null);
            }

            public Node(String val) {
                this(val, null);
            }

            public Node(String val, Node next) {
                this.val = val;
                this.next = next;
            }

            public String val;
            public Node next;
        };

        private Node head;
    };

    static class ChainedHashSet {

        public ChainedHashSet(int p, int M) {
            this.p = p;
            this.M = M;
            this.table = new LinkedList[M];
        }

        private int hash(String s) {
            long hashString = 0;
            long Mod = this.M;
            for (int i = 0; i < s.length(); ++i) {
                hashString = (hashString * p + (s.charAt(i) - 'a' + 1)) % Mod;
            }
            return (int)(hashString);
        }

        public boolean contains(String s) {
            int hashString = hash(s);
            if (table[hashString] == null) {
                return false;
            }
            return table[hashString].contains(s);
        }

        public void add(String s) {
            if (contains(s)) {
                return;
            }
            int hashString = hash(s);
            if (table[hashString] == null) {
                table[hashString] = new LinkedList();
            }
            table[hashString].add(s);
        }

        public void remove(String s) {
            if (!contains(s)) {
                return;
            }

            int hashString = hash(s);
            table[hashString].remove(s);
        }

        private final int p;
        private final int M;
        private final LinkedList[] table;
    };

    public static void main(String[] args) throws IOException {

        //Scanner in = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        ChainedHashSet all = new ChainedHashSet(29, (int)(1e6 + 1));
        //LinkedList all = new LinkedList();

        while(true) {
            String[] line = in.readLine().split(" ");
            if (line[0].charAt(0) == '#') {
                break;
            }
            if (line[0].charAt(0) == '+') {
                String str = line[1];
                all.add(str);
            }
            if (line[0].charAt(0) == '-') {
                String str = line[1];
                all.remove(str);
            }
            if (line[0].charAt(0) == '?') {
                String str = line[1];
                String answer = all.contains(str) ? "YES" : "NO";
                System.out.println(answer);
            }
        }
    }
}
