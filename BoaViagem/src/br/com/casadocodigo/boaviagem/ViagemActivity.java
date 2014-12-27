package br.com.casadocodigo.boaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ViagemActivity extends Activity {

	private int ano, mes, dia;
	private Button dataSaida;
	private Button dataChegada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viagem);
		Calendar calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		dataSaida = (Button) findViewById(R.id.dataSaida);
		dataSaida.setText(dia + "/" + (mes + 1) + "/" + ano);
		dataChegada = (Button) findViewById(R.id.dataChegada);
		dataChegada.setText(dia + "/" + (mes + 1) + "/" + ano);
	}

	public void selecionarData(View view) {
		int id = view.getId();
		if (R.id.dataChegada == id) {
			new DatePickerDialog(this, listenerChegada, ano, mes, dia).show();
		}
		if (R.id.dataSaida == id) {
			new DatePickerDialog(this, listenerSaida, ano, mes, dia).show();
		}
	}

	private OnDateSetListener listenerChegada = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			dataChegada.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};

	private OnDateSetListener listenerSaida = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			dataSaida.setText(dia + "/" + (mes + 1) + "/" + ano);
		}

	};

	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.viagem_menu, menu);
		return true;
	};

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.novo_gasto:
			startActivity(new Intent(this, GastoActivity.class));
			return true;
		case R.id.remover:
			// remover a viagem do banco de dados;
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}
}