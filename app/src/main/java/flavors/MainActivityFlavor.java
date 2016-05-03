package flavors;

import android.view.MenuItem;
import android.view.Menu;
import ricardo.colormatch.activities.MainActivity;



/** Class created by tvbarthel at https://github.com/tvbarthel/CameraColorPicker
 * The behavior of the {@link ricardo.colormatch.activities.MainActivity} specific to the foss product flavor.
 * This class should be present in all the product flavors.
 * @author tvbarthel
 * */


public class MainActivityFlavor {

    /**
     * A {@link ricardo.colormatch.activities.MainActivity}.
     */
    private MainActivity mMainActivity;


    public MainActivityFlavor(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    /**
     * A flavor behavior for {@link android.app.Activity#onCreateOptionsMenu(android.view.Menu)}
     */
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Nothing to do here at the moment.
        return true;
    }

    /**
     * A flavor behavior for {@link android.app.Activity#onCreateOptionsMenu(android.view.Menu)}.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Nothing to do here at the moment.
        return true;
    }
}
