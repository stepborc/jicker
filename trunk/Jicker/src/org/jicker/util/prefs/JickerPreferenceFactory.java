package org.jicker.util.prefs;

import java.io.File;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;
import org.jicker.util.prefs.JickerPreference;

public class JickerPreferenceFactory implements PreferencesFactory {
	  static {
		    // we create the target folder if needed
		    new File(JickerPreference.getPropertyFolder(true)).mkdirs();
		    new File(JickerPreference.getPropertyFolder(false)).mkdirs();
		  }
		  
		  public Preferences userRoot() {
		    return new JickerPreference("", false);
		  }

		  public Preferences systemRoot() {
		    return new JickerPreference("", true);
		  }
		}