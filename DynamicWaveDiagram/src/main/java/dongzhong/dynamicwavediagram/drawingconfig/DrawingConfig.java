package dongzhong.dynamicwavediagram.drawingconfig;

import android.graphics.Color;

/**
 * Created by admin on 2017/12/22.
 */

public class DrawingConfig {
    private int waveColor = Color.BLACK;
    private int backGroundColor = Color.CYAN;
    private int limitLineColor = Color.BLACK;

    public DrawingConfig() {

    }

    public int getWaveColor() {
        return waveColor;
    }

    public void setWaveColor(int waveColor) {
        this.waveColor = waveColor;
    }

    public int getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public int getLimitLineColor() {
        return limitLineColor;
    }

    public void setLimitLineColor(int limitLineColor) {
        this.limitLineColor = limitLineColor;
    }
}
