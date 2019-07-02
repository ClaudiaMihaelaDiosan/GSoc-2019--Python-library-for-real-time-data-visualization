package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Connectivity;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.R;

public class ConnectManager {

    static String getConnectivityStatusString(Context context) {
        String status;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = context.getResources().getString(R.string.wifi_connected);
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = context.getResources().getString(R.string.mobile_internet);
                return status;
            }
        } else {
            status = context.getResources().getString(R.string.internet_not_available);
            return status;
        }
        return "";
    }
}

