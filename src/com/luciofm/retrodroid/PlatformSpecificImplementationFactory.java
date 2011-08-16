/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.luciofm.retrodroid;

import android.content.Context;
import android.location.LocationManager;

import com.luciofm.retrodroid.development.HoneycombStrictMode;
import com.luciofm.retrodroid.development.LegacyStrictMode;
import com.luciofm.retrodroid.development.base.IStrictMode;
import com.luciofm.retrodroid.location.FroyoLocationUpdateRequester;
import com.luciofm.retrodroid.location.GingerbreadLastLocationFinder;
import com.luciofm.retrodroid.location.GingerbreadLocationUpdateRequester;
import com.luciofm.retrodroid.location.LegacyLastLocationFinder;
import com.luciofm.retrodroid.location.LegacyLocationUpdateRequester;
import com.luciofm.retrodroid.location.base.ILastLocationFinder;
import com.luciofm.retrodroid.location.base.LocationUpdateRequester;
import com.luciofm.retrodroid.prefs.FroyoSharedPreferenceSaver;
import com.luciofm.retrodroid.prefs.GingerbreadSharedPreferenceSaver;
import com.luciofm.retrodroid.prefs.LegacySharedPreferenceSaver;
import com.luciofm.retrodroid.prefs.base.SharedPreferenceSaver;

/**
 * Factory class to create the correct instances
 * of a variety of classes with platform specific
 * implementations.
 * 
 */
public class PlatformSpecificImplementationFactory {    
	
  /**
   * Create a new LastLocationFinder instance
   * @param context Context
   * @return LastLocationFinder
   */
  public static ILastLocationFinder getLastLocationFinder(Context context) {
    return Constants.SUPPORTS_GINGERBREAD ? new GingerbreadLastLocationFinder(context) : new LegacyLastLocationFinder(context);
  }
  
  /**
   * Create a new StrictMode instance.
   * @return StrictMode
   */
  public static IStrictMode getStrictMode() {
	if (Constants.SUPPORTS_HONEYCOMB)
      return new HoneycombStrictMode();
	else if (Constants.SUPPORTS_GINGERBREAD)
      return new LegacyStrictMode(); 
	else
      return null;
  }
  
  /**
   * Create a new LocationUpdateRequester
   * @param locationManager Location Manager
   * @return LocationUpdateRequester
   */
  public static LocationUpdateRequester getLocationUpdateRequester(LocationManager locationManager, Context context) {
    return Constants.SUPPORTS_GINGERBREAD ? new GingerbreadLocationUpdateRequester(locationManager, context) : Constants.SUPPORTS_FROYO ? 
    	new FroyoLocationUpdateRequester(locationManager, context) : new LegacyLocationUpdateRequester(locationManager, context);    
  }
  
  /**
   * Create a new SharedPreferenceSaver
   * @param context Context
   * @return SharedPreferenceSaver
   */
  public static SharedPreferenceSaver getSharedPreferenceSaver(Context context) {
    return  Constants.SUPPORTS_GINGERBREAD ? 
       new GingerbreadSharedPreferenceSaver(context) : 
       Constants.SUPPORTS_FROYO ? 
           new FroyoSharedPreferenceSaver(context) :
           new LegacySharedPreferenceSaver(context);
  }
}
