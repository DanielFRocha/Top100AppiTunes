package br.com.brestate.top100appitunes.view.activity;


import br.com.brestate.top100appitunes.R;
import br.com.brestate.top100appitunes.modelo.TopApp;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AppArrayAdapter extends ArrayAdapter<TopApp> {

	private Context context;
	private TopApp[] values;

	public AppArrayAdapter(Context context, int resource, TopApp[] values) {
		super(context, resource, values);
		this.context = context;
		this.values = values;
	}


	public int getCount(){
		return values.length;
	}

	public TopApp getItem(int position){
		return values[position];
	}

	public long getItemId(int position){
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService("layout_inflater");
			v = inflater.inflate(R.layout.list_v, null);
		}
		//Buscando na list_v 
		TextView lbl_name = (TextView) v.findViewById(R.id.name);
		lbl_name.setTextColor(Color.BLACK);
		TextView lbl_price = (TextView) v.findViewById(R.id.title);
		lbl_price.setTextColor(Color.BLACK);

		//Preenchendo com os dados referentes ao nome 
		if(values[position] != null){
			lbl_name.setText((position+1) + " - " + values[position].getName()); 
			lbl_price.setText(("Preço: U$$ ") + values[position].getPrice());
		}
				
		else{
			lbl_name.setText("-");
			lbl_price.setText("Não há preço informado !");
		}
		return v;

	}

	@Override
	public View getDropDownView(int position, View convertView,
			ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService("layout_inflater");
			v = inflater.inflate(R.layout.list_v, null);
		}
		TextView lbl = (TextView) v.findViewById(R.id.name);
		lbl.setTextColor(Color.BLACK);

		if(values[position] != null)
			lbl.setText(values[position].getName());
		else
			lbl.setText("-");

		return v;
	}
}
