package ca.ualberta.cs.cmput301w15t04team04project;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import android.content.Intent;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;

public class Item {
	protected String itemname;
	protected static Bitmap bitmap;
	protected Date startdate;
	protected String categary;
	protected String des;
	protected int amount;
	protected String unit;
	protected int flag;
	
	protected Uri imageFileUri;
	private Bitmap BMP;
	
	public Item(String itemname) {
		// TODO Auto-generated constructor stub
	}


    public void takeAPhoto() {
    	takeAPhoto();
    }

   

	public String getItemname() {
		return itemname;
	}




	public void setItemname(String itemname) {
		this.itemname = itemname;
	}




	public Date getStartdate() {
		return startdate;
	}




	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}




	public String getCategary() {
		return categary;
	}




	public void setCategary(String categary) {
		this.categary = categary;
	}




	public String getDes() {
		return des;
	}




	public void setDes(String des) {
		this.des = des;
	}




	public int getAmount() {
		return amount;
	}







	public String getUnit() {
		return unit;
	}




	public void setUnit(String unit) {
		this.unit = unit;
	}




	public void addFlag() {
		this.flag = 1;
		
	}




	public void removeFlag() {
		// TODO Auto-generated method stub
		this.flag = 0;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	@Override
	public String toString() {
		return "Item [itemname=" + itemname + "]";
	}


	public Bitmap getPhoto() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		File intentPicture = getPicturePath(intent);
		try {
			saveBMP(intentPicture, BMP);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return BMP;
	}


	private File getPicturePath(Intent intent) {
		// TODO Auto-generated method stub
		Uri uri = (Uri) intent.getExtras().get(MediaStore.EXTRA_OUTPUT);
		return new File(uri.getPath());
		
	}


	private Intent getIntent() {
		// TODO Auto-generated method stub
		return null;
	}


	public void deleteAPhoto() {
		// TODO Auto-generated method stub
		BMP = null;
	}


	public int getPhotoSize() {
		// TODO Auto-generated method stub
		//Bitmap bmp = BitmapFactory.decodeResource(getPhoto(), R.drawable.ic_launcher);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		BMP.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		return byteArray.length; // size
	}
//get from BogoPicGen
	private void saveBMP(File intentPicture, Bitmap BMP) throws IOException,FileNotFoundException {
		OutputStream out = new FileOutputStream(intentPicture);
		BMP.compress(Bitmap.CompressFormat.JPEG, 75, out);
		out.close();
	}

}
