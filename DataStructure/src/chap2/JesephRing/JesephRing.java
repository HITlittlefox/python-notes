package chap2.JesephRing;

public class JesephRing {
	Node head; // ????
	Node rear; // β???
	Node current; // ??????
	int size; // ??????

	JesephRing() { // ??????
		head = current = rear = null;
	}

	public void index(int i) throws Exception { // ??λ
		// ??λ??????current????i?????
		if (i < -1 || i > size - 1) {
			throw new Exception("????????");
		}
		if (i == -1)
			return;
		current = head;
		int j = 0;
		while ((current != null) && j < i) {
			current = current.next;
			j++;
		}
	}

	public void insert(int i, Node obj) throws Exception { // ????
		// ???i????????????obj
		if (i < 0 || i > size) {
			throw new Exception("????????");
		}

		index(i - 1); // current??λ??i - 1
		if (size == 0) {
			head = current = rear = obj; // ?????β????????
		}

		// ???????????
		obj.next = current.next;
		current.next = obj;

		if (current.equlas(rear)) {
			obj.next = head; // ???????????
			rear = obj; // ??β???
		} else
			rear.next = head; // ???????????

		size++;
	}

	public void delete(int m) throws Exception { // ???
		// ??m???????????????????????
		int i;
		current = head;
		while (size > 0) {
			for (i = 1; i < m; i++) { // ????m??
				current = current.next;
			}
			System.out.print(current.next.code + "   ");// ???????????

			current.next = current.next.next; // ??????????
			size--;
			current = current.next; // ?????????????????
		}
		head = current = rear = null;
	}

	public static void main(String[] args) throws Exception {
		int m = 2;
		JesephRing lin = new JesephRing();

		lin.insert(0, new Node(1, null));
		lin.insert(1, new Node(2, null));
		lin.insert(2, new Node(3, null));
		lin.insert(3, new Node(4, null));
		lin.insert(4, new Node(5, null));
		lin.insert(5, new Node(6, null));
		lin.insert(6, new Node(7, null));

		Node curr = lin.head;
		System.out.println("???????????????");
		for (int i = 0; i < lin.size * 2; i++) {
			System.out.print(curr.code + "   ");
			curr = curr.next;
		}
		System.out.println();

		System.out.println("???α??????????");
		lin.delete(m);
	}
}
