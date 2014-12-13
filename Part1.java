import java.util.ArrayList;

public class Part1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ComparableList c = new ComparableList();
		String obj[] = { "345", "1", "2", "3" };
		c.addFromArray(obj);

		A a1 = new A(6);
		A a2 = new A(7);

		B b1 = new B(2, 4);
		B b2 = new B(3, 5);

		System.out.println(a1.compareTo(b1)); // returns 0, since 6 = (2+4)
		System.out.println(a1.compareTo(b2)); // returns -1, since 6 < (3+5)
		System.out.println(b1.compareTo(a1)); // returns 0, since (2+4) = 6
		System.out.println(b2.compareTo(a1)); // returns 1, since (3+5) > 6
		System.out.println(b1.compareTo(b2)); // returns -1, since (2+4) < (3+5)
		//
		test();
	}

	public static void addToCList(A z, ComparableList<?> a) {
		a.add(z);
	}

	public static void addToCList(B z, ComparableList<?> a) {
		a.add(z);
	}

	public static void test() {
		ComparableList<A> c1 = new ComparableList<A>();
		ComparableList<A> c2 = new ComparableList<A>();

		for (int i = 0; i < 10; i++) {
			addToCList(new A(i), c1);
			addToCList(new A(i), c2);
		}

		addToCList(new A(12), c1);
		addToCList(new B(6, 6), c2);

		addToCList(new B(7, 11), c1);
		addToCList(new A(13), c2);

		System.out.print("c1: ");
		System.out.println(c1);

		System.out.print("c2: ");
		System.out.println(c2);

		switch (c1.compareTo(c2)) {
		case -1:
			System.out.println("c1 < c2");
			break;
		case 0:
			System.out.println("c1 = c2");
			break;
		case 1:
			System.out.println("c1 > c2");
			break;
		default:
			System.out.println("Uh Oh");
			break;
		}

	}
}

@SuppressWarnings("rawtypes")
class ComparableList<T extends ComparableList> extends ArrayList implements
		Comparable {

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object o) {
		int returnVal = 0;

		int firstSize = this.size();
		int secondSize = ((T) o).size();

		for (int i = 0; i < (firstSize < secondSize ? firstSize : secondSize); i++) {
			if (((T) (this.get(i))).compareTo(((T) o).get(i)) < 0) {
				returnVal = -1;
				break;
			} else if (((T) (this.get(i))).compareTo(((T) o).get(i)) > 0) {
				returnVal = 1;
				break;
			}
		}

		if (returnVal == 0 && firstSize < secondSize)
			return -1;
		else if (returnVal == 0 && firstSize > secondSize)
			return 1;

		return returnVal;

	}

	@SuppressWarnings("unchecked")
	public void addFromArray(Object[] O) {
		for (int i = 0; i < O.length; i++) {
			this.add(O[i]);
		}
	}

}

@SuppressWarnings("rawtypes")
class A extends ComparableList {

	int x;

	A() {

	}

	A(int x) {
		this.x = x;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass() == B.class) {
			return (new B(this.x, 0)).compareTo((B) o);
		}

		if (this.x < ((A) o).x)
			return -1;
		else if (this.x == ((A) o).x)
			return 0;
		return 1;
	}

	@Override
	public String toString() {

		return "A<" + this.x + ">";
	}

}

@SuppressWarnings("rawtypes")
class B extends A {

	int x;
	int y;

	B(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass() == A.class) {
			return (new A(this.x + this.y)).compareTo((A) o);
		}

		if (this.x + this.y < ((B) o).x + ((B) o).y)
			return -1;
		else if (this.x + this.y == ((B) o).x + ((B) o).y)
			return 0;
		return 1;

	}

	@Override
	public String toString() {

		return "B<" + this.x + "," + this.y + ">";
	}

}