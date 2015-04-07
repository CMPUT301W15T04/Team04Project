package ca.ualberta.cs.cmput301w15t04team04project.test;

import java.util.ArrayList;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import ca.ualberta.cs.cmput301w15t04team04project.FragmentMoments;
import ca.ualberta.cs.cmput301w15t04team04project.FragmentProfile;
import ca.ualberta.cs.cmput301w15t04team04project.MainActivity;
import ca.ualberta.cs.cmput301w15t04team04project.CLmanager.SignInManager;
import ca.ualberta.cs.cmput301w15t04team04project.controller.MyLocalClaimListController;
import ca.ualberta.cs.cmput301w15t04team04project.controller.ClaimEditController;
import ca.ualberta.cs.cmput301w15t04team04project.models.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.models.User;
import junit.framework.TestCase;
//resource from http://blog.denevell.org/android-testing-fragments.html
public class US08_03_01UITest extends ActivityInstrumentationTestCase2<MainActivity> {

	private ListView claimlistview;
    private MainActivity thisActivity;
	private FragmentProfile profilefragment;
	private FragmentMoments momentfragment;
    private ClaimEditController controller;
	private Claim Aclaim;
	private Claim Bclaim;
	private Claim Cclaim;
	private User claimiant;
	private SignInManager manager;
	
	public US08_03_01UITest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		claimiant = new User("testclaimiant");

		SignInManager.saveInFile(getActivity(), claimiant);

		thisActivity = (MainActivity) getActivity();
		momentfragment = (FragmentMoments) thisActivity.fragments.get(1);
		Claim Aclaim = new Claim("Aclaim");
		Aclaim.setStatus("Submitted");
		Aclaim.setClaimiant("testclaimiant");
		Claim Bclaim = new Claim("Bclaim");
		Bclaim.setStatus("Submitted");
		Aclaim.setClaimiant("testclaimiant");

		Claim Cclaim = new Claim("Cclaim");
		Cclaim.setStatus("Proceed");
		Aclaim.setClaimiant("testclaimiant");

		ArrayList<Claim> claims = new ArrayList<Claim>();

		claims.add(Aclaim);
		claims.add(Bclaim);
		claims.add(Cclaim);

		controller.addall(claims);
	}

/*	public void testPreConditions() {
        assertNotNull(thisActivity);
        assertNotNull(momentfragment);
        //claimiant = new User("testclaimiant");
 
    }*/
	
	protected void testAllClaimDetailsUItest(){
		ListView listView = (ListView) thisActivity.findViewById(ca.ualberta.cs.cmput301w15t04team04project.R.id.myClaimsListView); //listView

		assertEquals("index 0 equals", listView.getChildAt(0).equals(Aclaim));
		assertEquals("index 1 equals", listView.getChildAt(1).equals(Bclaim));
		
	}
	
/*	private Fragment startFragment(Fragment fragment) {
	      FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
	      transaction.add(R.id.activity_test_fragment_linearlayout, fragment, "tag");
	      transaction.commit();
	      getInstrumentation().waitForIdleSync();
	      Fragment frag = mActivity.getSupportFragmentManager().findFragmentByTag("tag");
	      return frag;
	    }*/
}
