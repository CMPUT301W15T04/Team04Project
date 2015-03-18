package ca.ualberta.cs.cmput301w15t04team04project.test;

import java.sql.Date;
import java.util.Calendar;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
import ca.ualberta.cs.cmput301w15t04team04project.FragmentProfile;
import ca.ualberta.cs.cmput301w15t04team04project.MainActivity;
import ca.ualberta.cs.cmput301w15t04team04project.OneClaimActivity;
import ca.ualberta.cs.cmput301w15t04team04project.CLmanager.MyLocalClaimListManager;
import ca.ualberta.cs.cmput301w15t04team04project.adapter.ClaimListAdapter;
import ca.ualberta.cs.cmput301w15t04team04project.adapter.ItemListAdapter;
import ca.ualberta.cs.cmput301w15t04team04project.controller.MyLocalClaimListController2;
import ca.ualberta.cs.cmput301w15t04team04project.controller.OneClaimController2;
import ca.ualberta.cs.cmput301w15t04team04project.models.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.models.Item;
import ca.ualberta.cs.cmput301w15t04team04project.models.User;

public class US08_04_01 extends ActivityInstrumentationTestCase2<OneClaimActivity> {
	private OneClaimActivity thisActivity;
	//private FragmentProfile profilefragment;
	private Claim claim ;
	private MyLocalClaimListController2 controller;
	private OneClaimController2 itemcontroller;
	private Item item;
	private Date date;
	private Calendar calender = Calendar.getInstance();;
	private User approver;
	private ItemListAdapter itemListAdapter;
	private MyLocalClaimListManager manager;
	
	public US08_04_01() {
		super(OneClaimActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		thisActivity = (OneClaimActivity) getActivity();
		manager = new MyLocalClaimListManager();
		claim = manager.loadClaimList(getActivity()).getClaimArrayList().get(0);
		controller = new MyLocalClaimListController2(manager.loadClaimList(getActivity()));
	}
	
	public void testPreConditions(){
        assertNotNull(thisActivity);
        
        claim.setStatus("Submitted");
        controller.addClaim(claim);
        item = new Item("AItem");
        claim.addItem(item);
        calender.set(2005, 1,
				12);
        date = (Date) calender.getTime();
        item.setDate(date);
        String description = "testdescription";
        item.setDescription(description);
        approver = new User("approver");
	}
	
	protected void AllItemDetailofSubmittedClaim(){
		itemListAdapter = new ItemListAdapter(getActivity(), 0, claim.getItems());

		ListView itemListView = (ListView) getActivity().findViewById(ca.ualberta.cs.cmput301w15t04team04project.R.id.OneCaimItemListView); //listView

		View view = itemListAdapter.getView(0, null, null);
		ListView listview = (ListView) getActivity().findViewById(ca.ualberta.cs.cmput301w15t04team04project.R.id.OneCaimItemListView);  
		
		
		
		assertEquals("Name is equal", "approver", item.getItemName());
		assertTrue(item.getDate().equals(date));
		assertEquals("description", "testdescription", item.getDescription());
		
		
	}
	
}
