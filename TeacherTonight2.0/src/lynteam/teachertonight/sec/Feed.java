package lynteam.teachertonight.sec;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.widget.Toast;

public class Feed extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getPackageManager().getLaunchIntentForPackage("start.FoodTime");
        if(intent!=null){
        	startActivity(intent);
        }
        
        else{
        	Toast.makeText(getApplicationContext(), R.string.no_install, Toast.LENGTH_LONG).show();
        	String url = "market://details?id=start.FoodTime";
        	Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
        	marketLaunch.setData(Uri.parse(url));
        	startActivity(marketLaunch);
        }
        
        finish();
    }
}