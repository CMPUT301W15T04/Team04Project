package ca.ualberta.cs.cmput301w15t04team04project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import ca.ualberta.cs.cmput301w15t04team04project.CLmanager.CLmanager;
import ca.ualberta.cs.cmput301w15t04team04project.CLmanager.MyLocalClaimListManager;
import ca.ualberta.cs.cmput301w15t04team04project.adapter.ItemListAdapter;
import ca.ualberta.cs.cmput301w15t04team04project.controller.OneClaimController2;
import ca.ualberta.cs.cmput301w15t04team04project.models.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.models.ClaimList;
import ca.ualberta.cs.cmput301w15t04team04project.models.Item;
import ca.ualberta.cs.cmput301w15t04team04project.models.Listener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
/**
 * The one Activity is to see the detail and the items of a certain claim
 * @author youdong
 * @author Weijie Sun
 * @version 1.0
 * @since 2015-03-11
 * @author Chenrui Lei
 * @author Yufei Zhang
 * @version 1.1
 * @since 2015-03-15
 */
public class OneClaimActivity extends Activity {
	private TextView itemName;
	private TextView amount;
	private TextView itemDate;
	private TextView category;
	private TextView descript;
	private OneClaimController2 controller;
	private TextView approverView;
	
	private TextView claimName;
	
	protected static boolean isClaimant = true;
	private OneClaimActivity thisActivity = this;

	private Claim claim;
	private ClaimList claimList;
	private ArrayList<Claim> claims;
	private int claimId;
	private int itemId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one_claim);
		approverView = (TextView) findViewById(R.id.testApproverTextView);
		Bundle extras = getIntent().getExtras();

		claimId = extras.getInt("myClaimId");
		claimList = MyLocalClaimListManager.loadClaimList(this);		
		claims = claimList.getClaimArrayList(); 
		claim = claims.get(claimId);

		final ArrayList<Item> items = claim.getItems();

		ListView itemlistview = (ListView) findViewById(R.id.OneCaimItemListView);
		controller = new OneClaimController2(claim);
		final ItemListAdapter itemAdapter = new ItemListAdapter(this,
				android.R.layout.simple_list_item_1, items);

		itemlistview.setAdapter(itemAdapter);
		itemAdapter.notifyDataSetChanged();

		checkUserType();
		controller.getClaim().addListener(new Listener() {
			@Override
			public void update() {
				// TODO Auto-generated method stub
				items.clear();
				Collection<Item> items = controller.getItem();
				items.addAll(items);
				itemAdapter.notifyDataSetChanged();
			}
		});

		itemlistview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, int position, long id) {
				// final int finalPosition = position;
				itemId = position;
				// Claim claim = list.get(finalPosition);
				AlertDialog.Builder adb = new AlertDialog.Builder(
						OneClaimActivity.this);
				// adb.setMessage(claim.getClaim()+" total cost \n"+claim.getAmount()+"\n From "+claim.getStartDate()+" to "+claim.getEndDate());
				adb.setCancelable(true);

				adb.setPositiveButton("Delete", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int position) {
						controller.deleteItem(itemId);
						// controller.deleteClaim(which);
						// manager.saveClaimList(controller.getcClaimList());
					}
				});

				adb.setNeutralButton("Edit", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(OneClaimActivity.this,
								"OCA ItemID = " + itemId, Toast.LENGTH_SHORT)
								.show();
						Intent myIntent = new Intent(OneClaimActivity.this,
								EditItemActivity.class);
						myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						myIntent.putExtra("myItemId", itemId);
						myIntent.putExtra("myClaimId", claimId);
						OneClaimActivity.this.startActivity(myIntent);

					}
				});

				adb.setNegativeButton("Cancel", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				adb.show();
				return true;
			}

		}

		);

		itemlistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				int itemPosition = position;
				Toast.makeText(OneClaimActivity.this, "Item " + id,
						Toast.LENGTH_SHORT).show();
				showItemDetailC(view, position);
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.one_claim, menu);
		return true;
	}

	/**
	 * @param item
	 */
	public void goToSearch(MenuItem item) {
		Intent intent = new Intent(OneClaimActivity.this, SearchActivity.class);
		startActivity(intent);
	}

	/**
	 * @param item
	 */
	public void goToEditItem(MenuItem item) {
		Intent intent = new Intent(OneClaimActivity.this,
				EditItemActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("myClaimId", claimId);
		startActivity(intent);
	}

	/**
	 * @param view
	 */
	public void showClaimDetailC(View view) {
		isClaimant = true;

		AlertDialog.Builder adb = new AlertDialog.Builder(OneClaimActivity.this);

		LayoutInflater factory = LayoutInflater.from(OneClaimActivity.this);
		View claimInfoCDialogView = factory.inflate(
				R.layout.activity_claim_detail, null);
		adb.setView(claimInfoCDialogView);


		claimName = (TextView) claimInfoCDialogView
				.findViewById(R.id.currentClaimNameCTextView);
		claimName.setText(claims.get(claimId).getClaim());
		
		
		adb.setNeutralButton("Submit", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(OneClaimActivity.this, "Clicked On Submit",
						Toast.LENGTH_SHORT).show();
				controller.submittedClaim(which);
				MyLocalClaimListManager.saveClaimList(getApplicationContext(), claimList);
				Intent intent = new Intent(OneClaimActivity.this,
						MyClaimActivity.class);
				startActivity(intent);
				/**
				 * You need to add code here to do the submit stuff Once the
				 * claimant click this, the claim will be submitted
				 **/
			}
		});

		adb.setNegativeButton("Cancel", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(OneClaimActivity.this, "Clicked On Confirm",
						Toast.LENGTH_SHORT).show();
				controller.confirmClaim(which);
				/**
				 * You need to add code here to do the confirm stuff Once the
				 * claimant click this, the claim is updated
				 **/
			}
		});

		adb.setCancelable(true);
		adb.show();
	}

	/**
	 * @param view
	 */
	public void showClaimDetailA(View view) {
		isClaimant = false;
		AlertDialog.Builder adb = new AlertDialog.Builder(OneClaimActivity.this);

		LayoutInflater factory = LayoutInflater.from(OneClaimActivity.this);
		View claimInfoCDialogView = factory.inflate(
				R.layout.activity_claim_detail_a, null);
		adb.setView(claimInfoCDialogView);

		adb.setNegativeButton("Return", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(OneClaimActivity.this, "Clicked On Return",
						Toast.LENGTH_SHORT).show();
				controller.returnClaim(which);
				/**
				 * You need to add code here to do the return stuff Once the
				 * approver click this, the claim will be returned
				 **/
			}
		});

		adb.setNeutralButton("Approve", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(OneClaimActivity.this, "Clicked On Approve",
						Toast.LENGTH_SHORT).show();
				controller.approveClaim(which);
				/**
				 * You need to add code here to do the approve stuff Once the
				 * approver click this, the claim will be approved
				 **/
			}
		});

		adb.setPositiveButton("Comment", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(OneClaimActivity.this, "Clicked On Comment",
						Toast.LENGTH_SHORT).show();
				/**
				 * You need to add code here to do the comment stuff Once the
				 * approver click this, some comments will be add However, I
				 * think we don't need this button Because whether we click on
				 * Approve or Return, a comment is needed to be add
				 **/
			}
		});

		adb.setCancelable(true);
		adb.show();
	}

	/**
	 * @param view
	 * @param id
	 */
	public void showItemDetailC(View view, int id) {

		isClaimant = true;
		AlertDialog.Builder adb = new AlertDialog.Builder(OneClaimActivity.this);
		LayoutInflater factory = LayoutInflater.from(OneClaimActivity.this);
		View itemInfoCDialogView = factory.inflate(
				R.layout.activity_item_detail, null);
		adb.setView(itemInfoCDialogView);

		itemName = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemNameTextView);
		amount = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemAmountTextView);
		itemDate = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemDateTextView);
		category = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemCateTextView);
		descript = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentDescripTextView);

		descript.setText(claim.getItems().get(id).getItemName());

		// amount.setText(claim.getItems().get(id).getCurrency().getType()+String.valueOf(claim.getItems().get(id).getCurrency().getAmount()));
		amount.setText(claim.getItems().get(id).getItemCurrency().toString());

		// Toast.makeText(this,
		// claim.getItems().get(id).getCurrency().getType(),
		// Toast.LENGTH_LONG).show();
		// amount.setText(claim.getItems().get(id).getCurrency().getType());
		// +String.valueOf(claim.getItems().get(id).getCurrency().getAmount())
		// amount.setText(String.valueOf(claim.getItems().get(id).getCurrency().getAmount()));

		Date idate = claim.getItems().get(id).getItemDate();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String dateOutput = df.format(idate);
		itemDate.setText(dateOutput);
		itemName.setText(claim.getItems().get(id).getItemName());
		category.setText(claim.getItems().get(id).getItemCategory());
		descript.setText(claim.getItems().get(id).getItemDescription());

		// set the Edit Button on the Dialog
		/*
		 * adb.setNeutralButton("Edit", new OnClickListener() { public void
		 * onClick(DialogInterface dialog, int which) {
		 * Toast.makeText(OneClaimActivity.this,
		 * "Clicked On Edit",Toast.LENGTH_SHORT).show(); Intent intent = new
		 * Intent(OneClaimActivity.this, EditItemActivity.class);
		 * startActivity(intent); } });
		 */

		adb.setCancelable(true);
		adb.show();
	}

	/**
	 * @param view
	 * @param id
	 */
	public void showItemDetailA(View view, int id) {
		isClaimant = false;
		AlertDialog.Builder adb = new AlertDialog.Builder(OneClaimActivity.this);

		LayoutInflater factory = LayoutInflater.from(OneClaimActivity.this);
		View itemInfoCDialogView = factory.inflate(
				R.layout.activity_item_detail_a, null);
		adb.setView(itemInfoCDialogView);

		itemName = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemNameTextView);
		amount = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemAmountTextView);
		itemDate = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemDateTextView);
		category = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentItemCateTextView);
		descript = (TextView) itemInfoCDialogView
				.findViewById(R.id.currentDescripTextView);

		itemName.setText(claim.getItems().get(id).getItemName());

		amount.setText(claim.getItems().get(id).getItemCurrency().getType()
				+ String.valueOf(claim.getItems().get(id).getItemCurrency()
						.getAmount()));

		Date idate = claim.getItems().get(id).getItemDate();
		DateFormat df = new SimpleDateFormat("MMM-dd-yyyy");
		String dateOutput = df.format(idate);
		itemDate.setText(dateOutput);

		category.setText(claim.getItems().get(id).getItemCategory());
		descript.setText(claim.getItems().get(id).getItemDescription());

		adb.setNeutralButton("Add Comment", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(OneClaimActivity.this, "Clicked On Add Comment",
						Toast.LENGTH_SHORT).show();
				/**
				 * You need to add code here to do the addComment stuff Once the
				 * approver click this, the comment will be add for the current
				 * claim
				 **/
			}
		});

		adb.setCancelable(true);
		adb.show();
	}

	/**
	 * 
	 */
	public void checkUserType() {
		if (isClaimant) {
			approverView.setVisibility(View.GONE);
		}

	}
}
