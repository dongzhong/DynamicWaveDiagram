package dongzhong.dynamicwavediagram;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dongzhong on 2017/12/20.
 */

public class DynamicWaveDiagram extends View {
    private int updateTime = 500;
    private int pointNum = 100;
    private float ceilValue = 10.0f;
    private float floorValue = -10.0f;

    private int left = 0;
    private int right = 0;
    private int top = 0;
    private int bottom = 0;

    private Timer drawTimer = new Timer();

    /********************** Constructor **********************/
    public DynamicWaveDiagram(Context context) {
        super(context);
        init();
    }

    public DynamicWaveDiagram(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DynamicWaveDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**********************************************************/


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        if (getWidth() >= paddingLeft + paddingRight) {
            left = paddingLeft;
            right = getWidth() - paddingRight;
        }
        else {
            left = getWidth() / 2;
            right = getWidth() / 2;
        }
        if (getHeight() >= paddingTop + paddingBottom) {
            top = paddingTop;
            bottom = getHeight() - paddingBottom;
        }
        else {
            top = getHeight() / 2;
            bottom = getHeight() / 2;
        }
    }

    private void init() {
        if (drawTimer != null) {
            drawTimer.cancel();
        }
        drawTimer = new Timer();
        drawTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateDate();
            }
        }, 0, updateTime);
    }

    /**
     * 更新数据
     */
    private void updateDate() {

        invalidate();
    }

    /**
     * 绘制动态波浪图
     * @param canvas
     */
    private void drawDiagram(Canvas canvas) {


    }
}
