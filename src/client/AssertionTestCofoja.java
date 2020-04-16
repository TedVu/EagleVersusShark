package client;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

// Design by Contract using Google Cofoja (Contracts for Java) library
// Caspar Ryan

@Invariant("count != 0")
public class AssertionTestCofoja {
	public static void main(String args[]) {
		// change param to null to break pre-condition
		new AssertionTestCofoja().someMethod(null);
	}

	@SuppressWarnings("unused")
	private int count = 0;

	public AssertionTestCofoja() {
		// comment increment to break invariant
		count++;
	}

	// pre: s !=null
	// post: s=s+"test"
	@Requires("s != null")
	@Ensures("s.equals(old(s) + \"test\")")
	// 'old' is a special function used by Cofoja to specify values at entry
	public String someMethod(String s) {
		s = s.concat("test");

		// uncomment to break post-condition
		// s = s.concat("test");

		return s;
	}
}
