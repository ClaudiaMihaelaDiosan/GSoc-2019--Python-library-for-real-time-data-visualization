package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConnectivityReceiver extends BroadcastReceiver {
    public static ConnectivityReceiverListener connectivityReceiverListener;
    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = ConnectManager.getConnectivityStatusString(context);
        if(status.isEmpty()) {
            status="No Internet Connection";
        }

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(status);
        }
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(String status);
    }
}
