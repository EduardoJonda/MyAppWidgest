package widgetproviders;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.jonda.seichi.c15.myappwidgest.ApiService;
import com.android.jonda.seichi.c15.myappwidgest.ApiServiceGenerator;
import com.android.jonda.seichi.c15.myappwidgest.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String TAG = NewAppWidget.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "Calling onUpdate...");

        for (int i = 0; i < appWidgetIds.length; i++) {
            Log.d(TAG, "appWidgetIds: " + appWidgetIds[i]);

            ApiService service = ApiServiceGenerator.createService(ApiService.class);

            initialize();

            String message = "Hola mundo del RAP!";
            String message2 ="obejto 2";
            String message3 ="obejto 3";
            String message4 ="obejto 4";
            String message5 ="obejto 5";

            // Set layout
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
            remoteViews.setTextViewText(R.id.text_widget, message);
            remoteViews.setTextViewText(R.id.text_widget2, message2);
            remoteViews.setTextViewText(R.id.text_widget3, message3);

            // Register an onClickListener on the image button
            Intent intent = new Intent(context, NewAppWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.button_widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Usuario>> call = service.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {


            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {


                int statusCode = response.code();
                Log.d(TAG, "HTTP status code: " + statusCode);

                if (response.isSuccessful()) {

                    List<Usuario> productos = response.body();
                    Log.d(TAG, "productos: " + productos);

                    //ProductosAdapter adapter = (ProductosAdapter) productosList.getAdapter();
                    //adapter.setProductos(productos);


                } else {
                    try {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        throw new Exception("Error en el servicio");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
               // Toast.makeText(NewAppWidget.this, t.getMessage(), LENGTH_LONG).show();

            }
        });
}
}