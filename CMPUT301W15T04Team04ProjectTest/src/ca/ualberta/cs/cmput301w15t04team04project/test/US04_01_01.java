package ca.ualberta.cs.cmput301w15t04team04project.test;

import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301w15t04team04project.EditItemActivity;
import ca.ualberta.cs.cmput301w15t04team04project.controller.ItemEditController;
import ca.ualberta.cs.cmput301w15t04team04project.models.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.models.Currency;
import ca.ualberta.cs.cmput301w15t04team04project.models.Item;
import junit.framework.TestCase;

public class US04_01_01 extends ActivityInstrumentationTestCase2<EditItemActivity> {
	public EditItemActivity thisActivity;
	public ItemEditController controller;
	
	public US04_01_01() {
		super(EditItemActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		//thisActivity = (EditItemActivity) getActivity();
		controller = new ItemEditController();
	}

	public void test(){
		Claim claim = controller.getClaim();

		Item itemA = new Item("testAItem");

		Date Adate = new Date();
		Adate.setYear(2013);
		Date Bdate = new Date();
		Bdate.setYear(2014);
		Date Cdate = new Date();
		Cdate.setYear(2015);
		
		itemA.setItemDate(Adate);
		itemA.setItemCategory("Cate1");
		
		Currency itemCurrency = new Currency("CAD", 80);
		
		itemA.setItemCurrency(itemCurrency);
		itemA.setItemDescription("testDescription");
		
		controller.addItem(itemA);
		
		
		Item itemB = new Item("testAItem");


		
		itemB.setItemDate(Bdate);
		itemB.setItemCategory("Cate1");
		
		Currency itemCurrencyB = new Currency("USD", 80);
		
		itemB.setItemCurrency(itemCurrencyB);
		itemB.setItemDescription("testDescription");
		
		controller.addItem(itemB);
		
		assertEquals("itemA's itemname is add", "testAItem", claim.getItems().get(0).getItemName());
		

	}
}