/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.android.internal.statusbar;

import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.util.Log;

public class ThemeAccentUtils {

    public static final String TAG = "ThemeAccentUtils";

    // Stock dark theme package
    private static final String STOCK_DARK_THEME = "com.android.systemui.theme.dark";

    // Dark themes
    private static final String[] DARK_THEMES = {
        "com.android.system.theme.dark", // 0
        "com.android.systemui.theme.custom.dark", // 1
        "com.android.settings.theme.dark", // 2
        "com.android.settings.intelligence.theme.dark", // 3
        "com.android.gboard.theme.dark", // 4
        "org.lineageos.updater.theme.dark", // 5
        "com.android.wellbeing.theme.dark", // 6
        "com.google.intelligence.sense.theme.dark", // 7
    };

    // Black themes
    private static final String[] BLACK_THEMES = {
        "com.android.system.theme.black", // 0
        "com.android.systemui.theme.custom.black", // 1
        "com.android.settings.theme.black", // 2
        "com.android.settings.intelligence.theme.black", // 3
        "com.android.gboard.theme.black", // 4
        "org.lineageos.updater.theme.black", // 5
        "com.android.wellbeing.theme.black", // 6
        "com.google.intelligence.sense.theme.black", // 7
    };

    // BlueNight themes
    private static final String[] BLUENIGHT_THEMES = {
        "com.android.system.theme.bluenight", // 0
        "com.android.systemui.theme.custom.bluenight", // 1
        "com.android.settings.theme.bluenight", // 2
        "com.android.settings.intelligence.theme.bluenight", // 3
        "com.android.gboard.theme.bluenight", // 4
        "org.lineageos.updater.theme.bluenight", // 5
        "com.android.wellbeing.theme.bluenight", // 6
        "com.google.intelligence.sense.theme.bluenight", // 7
    };

    // BlackSupreme themes
    private static final String[] BLACKSUPREME_THEMES = {
        "com.android.system.theme.blacksupreme", // 0
        "com.android.systemui.theme.custom.blacksupreme", // 1
        "com.android.settings.theme.blacksupreme", // 2
        "com.android.settings.intelligence.theme.blacksupreme", // 3
        "com.android.gboard.theme.blacksupreme", // 4
        "org.lineageos.updater.theme.blacksupreme", // 5
        "com.android.wellbeing.theme.blacksupreme", // 6
        "com.google.intelligence.sense.theme.blacksupreme", // 7
    };

    private static final String[] LIGHT_THEMES = {
        "com.android.wellbeing.theme.light", // 0
        "com.android.gboard.theme.light", // 1
        "com.google.intelligence.sense.theme.light", // 2
    };

    // Accents
    private static final String[] ACCENTS = {
        "default_accent", // 0
        "com.accents.red", // 1
        "com.accents.pink", // 2
        "com.accents.purple", // 3
        "com.accents.deeppurple", // 4
        "com.accents.indigo", // 5
        "com.accents.blue", // 6
        "com.accents.lightblue", // 7
        "com.accents.cyan", // 8
        "com.accents.teal", // 9
        "com.accents.green", // 10
        "com.accents.lightgreen", // 11
        "com.accents.lime", // 12
        "com.accents.yellow", // 13
        "com.accents.amber", // 14
        "com.accents.orange", // 15
        "com.accents.deeporange", // 16
        "com.accents.brown", // 17
        "com.accents.grey", // 18
        "com.accents.bluegrey", // 19
        "com.accents.black", // 20
        "com.accents.white", // 21
        "com.accents.userone", // 22
        "com.accents.usertwo", // 23
        "com.accents.userthree", // 24
        "com.accents.userfour", // 25
        "com.accents.userfive", // 26
        "com.accents.usersix", // 27
        "com.accents.userseven", // 28
        "com.accents.nblue", // 29
        "com.accents.nbrown", // 30
        "com.accents.ngreen", // 31
        "com.accents.norange", // 32
        "com.accents.npink", // 33
        "com.accents.npurple", // 34
        "com.accents.nred", // 35
    };

    private static final String[] QS_TILE_THEMES = {
        "default", // 0
        "com.android.systemui.qstile.square", // 1
        "com.android.systemui.qstile.roundedsquare", // 2
        "com.android.systemui.qstile.squircle", // 3
        "com.android.systemui.qstile.teardrop", // 4
        "com.android.systemui.qstile.doublecircle", // 5
    };

    // Switch themes
    private static final String[] SWITCH_THEMES = {
        "default", // 0
        "com.android.system.switch.md2", // 1
        "com.android.system.switch.oneplus", // 2
        "com.android.system.switch.telegram", // 3
    };

    // Unloads the stock dark theme
    public static void unloadStockDarkTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(STOCK_DARK_THEME,
                    userId);
            if (themeInfo != null && themeInfo.isEnabled()) {
                om.setEnabled(STOCK_DARK_THEME,
                        false /*disable*/, userId);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // Check for the dark system theme
    public static boolean isUsingDarkTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(DARK_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    // Check for the black system theme
    public static boolean isUsingBlackTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(BLACK_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    // Check for the bluenight system theme
    public static boolean isUsingBlueNightTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(BLUENIGHT_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    // Check for the blacksupreme system theme
    public static boolean isUsingBlackSupremeTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(BLACKSUPREME_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    // Set light / dark theme
    public static void setLightDarkTheme(IOverlayManager om, int userId, boolean useDarkTheme) {
        for (String theme : DARK_THEMES) {
            try {
                om.setEnabled(theme,
                        useDarkTheme, userId);
                if (useDarkTheme) {
                    unloadStockDarkTheme(om, userId);
                }
            } catch (RemoteException e) {
            }
        }
        for (String theme : LIGHT_THEMES) {
            try {
                om.setEnabled(theme,
                        !useDarkTheme, userId);
            } catch (RemoteException e) {
            }
        }
        unfuckBlackWhiteAccent(om, userId);
    }

    // Set light / black theme
    public static void setLightBlackTheme(IOverlayManager om, int userId, boolean useBlackTheme) {
        for (String theme : BLACK_THEMES) {
            try {
                om.setEnabled(theme,
                        useBlackTheme, userId);
            } catch (RemoteException e) {
            }
        }
        for (String theme : LIGHT_THEMES) {
            try {
                om.setEnabled(theme,
                        !useBlackTheme, userId);
            } catch (RemoteException e) {
            }
        }
        unfuckBlackWhiteAccent(om, userId);
    }

    // Set light / bluenight theme
    public static void setLightBlueNightTheme(IOverlayManager om, int userId, boolean useBlueNightTheme) {
        for (String theme : BLUENIGHT_THEMES) {
            try {
                om.setEnabled(theme,
                        useBlueNightTheme, userId);
            } catch (RemoteException e) {
            }
        }
        for (String theme : LIGHT_THEMES) {
            try {
                om.setEnabled(theme,
                        !useBlueNightTheme, userId);
            } catch (RemoteException e) {
            }
        }
        unfuckBlackWhiteAccent(om, userId);
    }

    // Set light / blacksupreme theme
    public static void setLightBlackSupremeTheme(IOverlayManager om, int userId, boolean useBlackSupremeTheme) {
        for (String theme : BLACKSUPREME_THEMES) {
            try {
                om.setEnabled(theme,
                        useBlackSupremeTheme, userId);
            } catch (RemoteException e) {
            }
        }
        for (String theme : LIGHT_THEMES) {
            try {
                om.setEnabled(theme,
                        !useBlackSupremeTheme, userId);
            } catch (RemoteException e) {
            }
        }
        unfuckBlackWhiteAccent(om, userId);
    }

    // Check for black and white accent overlays
    public static void unfuckBlackWhiteAccent(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            if (isUsingDarkTheme(om, userId)) {
                themeInfo = om.getOverlayInfo(ACCENTS[20],
                        userId);
                if (themeInfo != null && themeInfo.isEnabled()) {
                    om.setEnabled(ACCENTS[20],
                            false /*disable*/, userId);
                    om.setEnabled(ACCENTS[21],
                            true, userId);
                }
            } else {
                themeInfo = om.getOverlayInfo(ACCENTS[21],
                        userId);
                if (themeInfo != null && themeInfo.isEnabled()) {
                    om.setEnabled(ACCENTS[21],
                            false /*disable*/, userId);
                    om.setEnabled(ACCENTS[20],
                            true, userId);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // Check for any accent overlay
    public static boolean isUsingAccent(IOverlayManager om, int userId, int accent) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(ACCENTS[accent],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    // Switches theme accent from one to another or back to stock
    public static void updateAccents(IOverlayManager om, int userId, int accentSetting) {
        if (accentSetting == 0) {
            unloadAccents(om, userId);
        } else if (accentSetting < 20) {
            try {
                om.setEnabled(ACCENTS[accentSetting],
                        true, userId);
            } catch (RemoteException e) {
            }
        } else if (accentSetting > 21) {
            try {
                om.setEnabled(ACCENTS[accentSetting],
                        true, userId);
            } catch (RemoteException e) {
            }
        } else if (accentSetting == 20) {
            try {
                // If using a dark theme we use the white accent, otherwise use the black accent
                if (isUsingDarkTheme(om, userId)) {
                    om.setEnabled(ACCENTS[21],
                            true, userId);
                } else {
                    om.setEnabled(ACCENTS[20],
                            true, userId);
                }
            } catch (RemoteException e) {
            }
        }
    }

    // Unload all the theme accents
    public static void unloadAccents(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 1; i < ACCENTS.length; i++) {
            String accent = ACCENTS[i];
            try {
                om.setEnabled(accent,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Switches qs tile style to user selected.
    public static void updateTileStyle(IOverlayManager om, int userId, int qsTileStyle) {
        if (qsTileStyle == 0) {
            unlockQsTileStyles(om, userId);
        } else {
            try {
                om.setEnabled(QS_TILE_THEMES[qsTileStyle],
                        true, userId);
            } catch (RemoteException e) {
            }
        }
    }

    // Unload all the qs tile styles
    public static void unlockQsTileStyles(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 1; i < QS_TILE_THEMES.length; i++) {
            String qstiletheme = QS_TILE_THEMES[i];
            try {
                om.setEnabled(qstiletheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Check for any QS tile styles overlay
    public static boolean isUsingQsTileStyles(IOverlayManager om, int userId, int qsstyle) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(QS_TILE_THEMES[qsstyle],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    // Switches switch style to user selected.
    public static void updateSwitchStyle(IOverlayManager om, int userId, int switchStyle) {
        if (switchStyle == 0) {
            unlockSwitchStyles(om, userId);
        } else {
            try {
                om.setEnabled(SWITCH_THEMES[switchStyle],
                        true, userId);
            } catch (RemoteException e) {
            }
        }
    }

    // Unload all the switch styles
    public static void unlockSwitchStyles(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 1; i < SWITCH_THEMES.length; i++) {
            String switchtheme = SWITCH_THEMES[i];
            try {
                om.setEnabled(switchtheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Check for any switch styles overlay
    public static boolean isUsingSwitchStyles(IOverlayManager om, int userId, int switchstyle) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(SWITCH_THEMES[switchstyle],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }
}
