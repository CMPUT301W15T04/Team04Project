package ca.ualberta.cs.cmput301w15t04team04project.test;

import ca.ualberta.cs.cmput301w15t04team04project.OneClaimActivity;
import ca.ualberta.cs.cmput301w15t04team04project.controller.ItemEditController;
import ca.ualberta.cs.cmput301w15t04team04project.models.Item;
import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;

public class US06_04_01 extends ActivityInstrumentationTestCase2<OneClaimActivity> {
	public OneClaimActivity thisActivity;
	public ItemEditController controller;
	
	public US06_04_01() {
		super(OneClaimActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		//thisActivity = (OneClaimActivity) getActivity();
		controller = new ItemEditController();
	}

	public void test(){
		Bitmap bitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_4444);
		Item item = new Item("test");
		item.setReceipBitmap(bitmap);
		controller.addItem(item);
		
		assertFalse("Not null", item.getReceipt().isEmpty());
		assertTrue("Bitmap size is not larger than 65536", item.getReceipBitmap().getByteCount() < 65536);

		Bitmap newBitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
		item.setReceipBitmap(newBitmap);
		controller.addItem(item);
		assertFalse("Not null", item.getReceipt().isEmpty());
		//assertEquals("equal",newBitmap, item.getReceipBitmap());
		assertFalse("Edit already", item.getReceipBitmap().equals(bitmap));
		
		assertTrue("Bitmap size is not larger than 65536", item.getReceipBitmap().getByteCount()<65536);

	}

}
