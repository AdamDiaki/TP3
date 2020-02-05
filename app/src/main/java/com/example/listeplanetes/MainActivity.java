package com.example.listeplanetes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> planetes;
    ListView listview;
    PlaneteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        installePlanetes();
        listview = (ListView) findViewById(R.id.listView);
        adapter = new PlaneteAdapter();
        listview.setAdapter(adapter);
    }

    private void installePlanetes() {
        planetes = new ArrayList<String>();
        planetes.add("Mercure");
        planetes.add("Venus");
        planetes.add("Terre");
        planetes.add("Mars");
        planetes.add("Jupiter");
        planetes.add("Saturne");
        planetes.add("Uranus");
        planetes.add("Neptune");
        planetes.add("Pluton");
    }

    class PlaneteAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return planetes.size();
        }

        @Override
        public Object getItem(int arg0) {
            return planetes.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)    MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = inflater.inflate(R.layout.listitem, null);
            }

            TextView nomPlanete = (TextView) itemView.findViewById(R.id.textView);
            final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            final Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);

            nomPlanete.setText(planetes.get(position));

            //  installer l'adaptateur pour la liste déroulante (spinner)
            String[] taillePlanetes = {"4900", "12000", "12800", "6800", "144000", "120000", "52000", "50000", "2300"};
            final ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, taillePlanetes);
            spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinadapter);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    CheckBox checkBox = (CheckBox) compoundButton.findViewById(R.id.checkbox);
                    spinner.setEnabled(!checkBox.isChecked());
                    spinadapter.notifyDataSetChanged();
                }
            });

            return itemView;
        }
    }
}