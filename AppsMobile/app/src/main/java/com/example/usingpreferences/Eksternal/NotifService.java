package com.example.usingpreferences.Eksternal;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Activity.MainActivity;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifService extends JobIntentService {

    private final int NOTIFICATION_ID = 1;
    private final int DELAY_INTERVAL = 60000;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private String lastKnownStateDitolak = "",lastKnownStateDiterima = "";

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, NotifService.class, 111, work);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        // Panggil metode untuk memantau perubahan dan menampilkan notifikasi
        monitorChangesAndNotify();
    }

    private void monitorChangesAndNotify() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkForUpdates();
                checkForUpdatesditolak();
                monitorChangesAndNotify(); // ini mngimplementasikan fungsi rekursif
            }
        }, DELAY_INTERVAL);
    }

    private void checkForUpdates() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ModelResponseAll> getLoginResponse = ardData.notifditerima(idUserShared);
        getLoginResponse.enqueue(new Callback<ModelResponseAll>() {
            @Override
            public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
                if (response.body().getKode() == 1) {
                    response.body().setKode(0);
                    String currentState = response.body().getPesan();
                    // Check if the current state is different from the last known state
                    if (!currentState.equals(lastKnownStateDiterima)) {
                        // Data has changed, show notification
                        showNotification();
                    }

                    // Update the last known state
                    lastKnownStateDiterima = currentState;
                } else if (response.body().getKode() == 0) {
                    // Handle other cases if needed
                } else {
                    // Handle other cases if needed
                }
            }

            @Override
            public void onFailure(Call<ModelResponseAll> call, Throwable t) {


            }

        });
    }
    private void checkForUpdatesditolak() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ModelResponseAll> getnotifrespon = ardData.notifditolak(idUserShared);
        getnotifrespon.enqueue(new Callback<ModelResponseAll>() {
            @Override
            public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
                if (response.body().getKode() == 1) {
                    String currentState = response.body().getPesan();
                    // Check if the current state is different from the last known state
                    if (!currentState.equals(lastKnownStateDitolak)) {
                        // Data has changed, show notification
                        showNotificationditolak();
                    }

                    // Update the last known state
                    lastKnownStateDitolak = currentState;
                } else if (response.body().getKode() == 0) {
                    // Handle other cases if needed
                } else {
                    // Handle other cases if needed
                }
            }

            @Override
            public void onFailure(Call<ModelResponseAll> call, Throwable t) {
                Toast.makeText(NotifService.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }
    private void showNotificationditolak() {
        Intent intent = new Intent(NotifService.this, MainActivity.class)
                .putExtra(MainActivity.FRAGMENT, R.layout.fragment_status)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifService.this, "fadil")
                .setSmallIcon(R.drawable.logodisporabudpar)
                .setContentTitle("NGANJUK ELOK")
                .setContentText("Mohon Maaf, Pengajuan Anda Tidak Diterima\nKlik Disini Untuk Melihat Status Pengajuan Anda")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotifService.this);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(222, builder.build());
    }

    private void showNotification() {
        Intent intent = new Intent(NotifService.this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifService.this, "fadil")
                .setSmallIcon(R.drawable.logodisporabudpar)
                .setContentTitle("NGANJUK ELOK")
                .setContentText("Status Pengajuan Anda Telah Diterima")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotifService.this);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(111, builder.build());
    }
}
