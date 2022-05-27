package ru.mirea.safronov_alexandr.yandexdriver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingSession;

import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;

import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DrivingSession.DrivingRouteListener,
        UserLocationObjectListener {
    private final String MAPKIT_API_KEY = "6c646bb9-a542-43ce-938c-4b354eb25c91";
    private final Point ROUTE_START_LOCATION = new Point(55.669978, 37.480143);
    private final Point ROUTE_END_LOCATION = new Point(55.752293, 37.619919);
    private final Point SCREEN_CENTER = new Point(
            (ROUTE_START_LOCATION.getLatitude() + ROUTE_END_LOCATION.getLatitude()) / 2,
            (ROUTE_START_LOCATION.getLongitude() + ROUTE_END_LOCATION.getLongitude()) /
                    2);
    private MapView mapView;
    private MapObjectCollection mapObjects;
    private DrivingRouter drivingRouter;
    private DrivingSession drivingSession;
    private int[] colors = {0xFFFF0000, 0xFF00FF00, 0x00FFBBBB, 0xFF0000FF};

    private PlacemarkMapObject mark;

    private UserLocationLayer userLocationLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        DirectionsFactory.initialize(this);

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        mapView = findViewById(R.id.mapView);
        mapView.getMap().move(new CameraPosition(SCREEN_CENTER, 10, 0, 0));


        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter();
        mapObjects = mapView.getMap().getMapObjects().addCollection();
        submitRequest();

        loadUserLocationLayer();

    }

    private void loadUserLocationLayer(){
        MapKit mapKit = MapKitFactory.getInstance();
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(true);
        userLocationLayer.setObjectListener(this);
    }

    private void submitRequest() {
        DrivingOptions options = new DrivingOptions();
        options.setAlternativeCount(3);
        ArrayList<RequestPoint> requestPoints = new ArrayList<>();
        requestPoints.add(new RequestPoint(
                ROUTE_START_LOCATION,
                RequestPointType.WAYPOINT,
                null));
        requestPoints.add(new RequestPoint(
                ROUTE_END_LOCATION,
                RequestPointType.WAYPOINT,
                null));
        drivingSession = drivingRouter.requestRoutes(requestPoints, options, this);
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void onDrivingRoutes(@NonNull List<DrivingRoute> list) {
        int color;
        for (int i = 0; i < list.size(); i++) {
            color = colors[i];
            mapObjects.addPolyline(list.get(i).getGeometry()).setStrokeColor(color);
        }
    }
    @Override
    public void onDrivingRoutesError(@NonNull Error error) {
        String errorMessage = "ошибка";
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObjectAdded(@NonNull UserLocationView userLocationView) {
        userLocationLayer.setAnchor(
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() *
                        0.5)),
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() *
                        0.83)));

        userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                this,android.R.drawable.star_big_on ));

        userLocationView.getPin().setIcon(ImageProvider.fromResource(
                this, android.R.drawable.ic_menu_mylocation));
        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE);
    }

    @Override
    public void onObjectRemoved(@NonNull UserLocationView userLocationView) {
    }

    @Override
    public void onObjectUpdated(@NonNull UserLocationView userLocationView,
                                @NonNull ObjectEvent objectEvent) {
    }
}