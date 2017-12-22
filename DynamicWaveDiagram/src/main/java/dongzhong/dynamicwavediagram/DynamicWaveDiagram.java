package dongzhong.dynamicwavediagram;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import dongzhong.dynamicwavediagram.drawingconfig.DrawingConfig;

/**
 * Created by dongzhong on 2017/12/20.
 */

public class DynamicWaveDiagram extends View {
    private int updateTime = 500;
    private int pointNum = 100;
    private float ceilValue = 10.0f;
    private float floorValue = -10.0f;

    int paddingLeft = getPaddingLeft();
    int paddingRight = getPaddingRight();
    int paddingTop = getPaddingTop();
    int paddingBottom = getPaddingBottom();
    private DrawingRect drawingRect;

    private DrawingConfig drawingConfig = new DrawingConfig();

    private Queue<Number> drawingData;
    private Queue<Number> cacheData;

    private Timer drawTimer = new Timer();

    // 状态参数
    private boolean isDrawingWave = false; // 是否开始绘制波浪图

    /********************** Constructor **********************/
    public DynamicWaveDiagram(Context context) {
        super(context);
    }

    public DynamicWaveDiagram(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicWaveDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**********************************************************/


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // TODO: 处理布局
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

        calculateDrawingRect();
        drawDiagram(canvas, drawingRect);
    }

    /**
     * 根据padding计算绘制范围
     */
    private void calculateDrawingRect() {
        int left, right, top, bottom;
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
        drawingRect = new DrawingRect(left, right, top, bottom);
    }

    /**
     * 开始绘制
     */
    public void start() {
        if (drawingData == null || cacheData == null) {
            return;
        }

        isDrawingWave = true;
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
     * 写入一个数据
     *
     * @param data  继承自Number
     */
    public void writeData(Number data) {
        // TODO: 写入数据的逻辑
    }

    /**
     * 更新数据
     */
    private void updateDate() {
        // TODO: 数据前移

        invalidate();
    }

    /**
     * 绘制动态波浪图
     * @param canvas
     */
    private void drawDiagram(Canvas canvas, DrawingRect drawingRect) {
        if (isDrawingWave) { // 开始绘制

        }
        else { // 不绘制

        }
    }

    /******************* Setter and Getter *****************/
    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        this.pointNum = pointNum;
    }

    public float getCeilValue() {
        return ceilValue;
    }

    public void setCeilValue(float ceilValue) {
        this.ceilValue = ceilValue;
    }

    public float getFloorValue() {
        return floorValue;
    }

    public void setFloorValue(float floorValue) {
        this.floorValue = floorValue;
    }
    /*******************************************************/

    /*********************** 内部类 ************************/
    class DrawingRect {
        private int left;
        private int right;
        private int top;
        private int bottom;

        public DrawingRect(int left, int right, int top, int bottom) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }
    }

    /*******************************************************/
}
