package ru.mirea.safronov_alexandr.mireaproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapFragment extends Fragment {
    private GoogleMap mMap;
    private ArrayList<MarkerOptions> markers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng mirea = new LatLng(55.670005, 37.479894);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(mirea).zoom(10).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                LatLng stromynka = new LatLng(55.79354087462711, 37.70127863791349);
                LatLng vernadski78 = new LatLng(55.66998101749648, 37.480306950144964);
                LatLng vernadski86 = new LatLng(55.66176753576964, 37.477711821815134);
                LatLng pirogova = new LatLng(55.73170218484044, 37.57470042792565);
                LatLng college = new LatLng(55.72455564763367, 37.631818935527185);
                LatLng vyc = new LatLng(55.72879012457672, 37.573178635527185);

                addNewMarker("РТУ МИРЭА, ул. Стромынка 20, год создания - 1787", "" +
                        "координаты (55.79354087462711, 37.70127863791349)", stromynka);
                addNewMarker("РТУ МИРЭА, пр. Вернадского 78, год создания - 1947", "" +
                        "координаты (55.66998101749648, 37.480306950144964)", vernadski78);
                addNewMarker("РТУ МИРЭА, пр. Вернадского 86, год создания - 1900", "" +
                        "координаты (55.66176753576964, 37.477711821815134)", vernadski86);
                addNewMarker("МТУ МИРЭА, Малая Пироговская ул. 1с5, год создания - 1933", "" +
                        "координаты (55.73170218484044, 37.57470042792565)", pirogova);
                addNewMarker("Колледж МИРЭА, 1-й Щипковский пер 23, год создания - 1947", "" +
                        "координаты (55.72455564763367, 37.631818935527185)", college);
                addNewMarker("ВУЦ МИРЭА, ул. Усачева, 7/1, год создания - 1969", "" +
                        "координаты (55.72879012457672, 37.573178635527185)", vyc);


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        addNewMarker("Где я?", "Новое место", latLng);
                    }
                });
            }
        });


        return view;
    }
    private void addNewMarker(String title, String snippet, LatLng latLng){
        MarkerOptions marker = new MarkerOptions().title(title)
                .snippet(snippet).position(latLng);
        mMap.addMarker(marker);

        markers.add(marker);
    }
}