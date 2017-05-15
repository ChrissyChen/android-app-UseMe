package project.csc780.xinlu.useme;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Xinlu on 5/15/17.
 */

public class ScreenUtil {

    // get the width and height of the screen
    public static int[] getScreenWidthAndHeight(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int[] heightAndWeight = new int[] { width, height };
        return heightAndWeight;
    }


    public static int getScreenWidth(Context context) {
        return getScreenWidthAndHeight(context)[0];
    }

    public static int getScreenHeight(Context context) {
        return getScreenWidthAndHeight(context)[1];
    }
}
