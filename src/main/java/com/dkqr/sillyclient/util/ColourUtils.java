package com.dkqr.sillyclient.util;

import java.awt.*;

public class ColourUtils {
    static public int convertHSBToRGB(float h, float s, float b) {
        Color col = Color.getHSBColor(h /360, s, b);
        int red = col.getRed() << 16;
        int blue = col.getBlue() << 8;
        int green = col.getGreen();
        return red | blue | green;
    }
    static public int convertHSBToRGB(Color colour) {
        int red = colour.getRed() << 16;
        int blue = colour.getBlue() << 8;
        int green = colour.getGreen();
        int rgb = red | blue | green;
        return rgb;
    }
}
