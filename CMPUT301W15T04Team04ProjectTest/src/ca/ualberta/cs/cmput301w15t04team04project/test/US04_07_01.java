package ca.ualberta.cs.cmput301w15t04team04project.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301w15t04team04project.EditItemActivity;
import ca.ualberta.cs.cmput301w15t04team04project.controller.ClaimEditController;
import ca.ualberta.cs.cmput301w15t04team04project.controller.ItemEditController;
import ca.ualberta.cs.cmput301w15t04team04project.models.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.models.Currency;
import ca.ualberta.cs.cmput301w15t04team04project.models.Destination;
import ca.ualberta.cs.cmput301w15t04team04project.models.Item;

public class US04_07_01 extends ActivityInstrumentationTestCase2<EditItemActivity> {
	public EditItemActivity thisActivity;
	public ItemEditController controller;
	public ClaimEditController controller2;
	public Item itemA;
	
	public US04_07_01() {
		super(EditItemActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		//thisActivity = (EditItemActivity) getActivity();
		controller = new ItemEditController();
		controller2 = new ClaimEditController();
	}

	public void addItemTest(){
		Claim claim = controller.getClaim();

		itemA = new Item("testAItem");

		Date Adate = new Date();
		Adate.setYear(2013);
		itemA.setItemDate(Adate);
		
		itemA.setItemCategory("Cate1");
		itemA.setComplete(false);
		
		Currency itemCurrency = new Currency("CAD", 80);
		
		itemA.setItemCurrency(itemCurrency);
		itemA.setItemDescription("testDescription");
		
		controller.addItem(itemA);
		
		assertEquals("itemA's item name is add", "testAItem", claim.getItems().get(0).getItemName());
		assertEquals("itemA's item date is add", 2013, claim.getItems().get(0).getItemDate().getYear());
		assertEquals("itemA's item category is add", "Cate1", claim.getItems().get(0).getItemCategory());
		assertEquals("itemA's item carency unit is add", "CAD", claim.getItems().get(0).getItemCurrency().getType());
		assertEquals("itemA's item carency is add", 80, claim.getItems().get(0).getItemCurrency().getAmount());
		assertEquals("itemA's item description is add", "testDescription", claim.getItems().get(0).getItemDescription());
	}
	
	public void testDelete() {
		Claim claim = controller.getClaim();
		claim.removeItem(itemA);
		assertNull(null);
	}
	
}