package com.example.jaga.cafeteriasmapsdrawer.atividade;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jaga.cafeteriasmapsdrawer.R;
import com.example.jaga.cafeteriasmapsdrawer.beans.CafeteriaBean;
import com.example.jaga.cafeteriasmapsdrawer.dao.DataAccessObject;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class CoffeeShopMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String LOG_S = "SCRIPT";
    private GoogleMap mMap;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_coffee_shop_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        DataAccessObject dao = new DataAccessObject(getApplicationContext());
        //dao.seedTableCafeteria(dao.getWritableDatabase());
        ArrayList<CafeteriaBean> listaCafeterias = dao.selectCafeterias();

        if (listaCafeterias.isEmpty()) {
            dao.seedTableCafeteria();
            listaCafeterias = dao.selectCafeterias();
        }

        for (CafeteriaBean cafeteria : listaCafeterias) {
            LatLng localCafeteria = new LatLng(cafeteria.getLatitude(), cafeteria.getLongitude());
            createCustomMapMarkers(localCafeteria, cafeteria.getNome(), cafeteria.getDescricao());
        }

        configureCameraPosition();
        //LatLng fortaleza = new LatLng(-3.7903373, -38.6585574);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(fortaleza));
    }

    public void configureCameraPosition() {
        /*
		 * A posição do zoom varia de 2 a 21. Logo esses valores podem ser utilizados como parametrização.
		 * CameraPosition cameraPosition = new CameraPosition.Builder().target(lating).zoom(15).build();
		 * Rotação do Mapa exibido na tela. Não é rotação do device.
		 * Zoom In e Out
		 * Bearing (Rotação da tela - deve ser definida a angulação)
		 * Tilt (Inclinação da tela - deve ser definida a angulação de até 90º)
		 */
        LatLng fortaleza = new LatLng(-3.772099, -38.471791);
        //createCustomMapMarkers(fortaleza, "Fortaleza", "Marker em Fortaleza");
//        mMap.addMarker(new MarkerOptions().position(fortaleza).title("Fortaleza").snippet("Marker em Fortaleza"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(fortaleza)
                .zoom(12)
                .bearing(0)
                .build();

		/*
		 * Camera que referencia a visão do Mapa
		 */
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        mMap.animateCamera(cameraUpdate, 3000, new GoogleMap.CancelableCallback() {

            @Override
            public void onFinish() {
                Log.i(LOG_S, "Acionado o metodo: onFinish");
            }

            @Override
            public void onCancel() {
                Log.i(LOG_S, "Acionado o metodo: onCancel");
            }
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter(){

            // Altera somente o conteudo do InfoWindow
            @Override
            public View getInfoContents(Marker marker) {
                // TODO Auto-generated method stub
                String markerTextColor = "#ff0000";

                TextView textView = new TextView(CoffeeShopMapsActivity.this);
                textView.setText(Html.fromHtml("<b><font color= "
                        + markerTextColor + marker.getTitle() + "</font></b>"
                        + marker.getSnippet()));

                return textView;
            }


            // Altera os parametros do InfoWindow. Normalmente é utilizado para customizar os markers.
            // Esse método tem "preferencia" em relação ao anterior quanto a sua execução. Portanto
            // caso continue nulo, irá "passar" sobre código programado no método getInfoContents()
            @Override
            public View getInfoWindow(Marker marker) {
                // TODO Auto-generated method stub
                LinearLayout linearLayout = new LinearLayout(CoffeeShopMapsActivity.this);
                // Trabalha com a quantidade de pixels considerados na renderização
                linearLayout.setPadding(30, 30, 20, 20);
                linearLayout.setBackgroundColor(Color.LTGRAY);

                String markerTextColor = "#f5f5f5";
                TextView textView = new TextView(CoffeeShopMapsActivity.this);
                textView.setText(Html.fromHtml("<b><font color= "
                        + markerTextColor + marker.getTitle() + "</font></b>"
                        + marker.getSnippet()));
                linearLayout.addView(textView);

                Button button = new Button(CoffeeShopMapsActivity.this);
                button.setText("Botão do InfoWindow");
//              linearLayout.setBackgroundColor(Color.RED);
                linearLayout.addView(button);

                return null;
            }

        });

    }

    public void createCustomMapMarkers(LatLng latlng, String title, String snippet){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng).title(title).snippet(snippet);

        // Criar instancia do marcador blobal.
        mMap.addMarker(markerOptions);
    }
}
