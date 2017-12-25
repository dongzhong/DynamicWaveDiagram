package dongzhong.dynamicwavediagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import dongzhong.dynamicwavediagram.drawingconfig.DrawingConfig;
import dongzhong.dynamicwavediagram.util.Util;

/**
 * Created by dongzhong on 2017/12/20.
 */

public class DynamicWaveDiagram extends View {
    private final String TAG = "DynamicWaveDiagram";

    private int updateTime = 100;
    private int pointNum = 100;
    private Number baseValue;
    private Number ceilValue;
    private Number floorValue;
    private float baseDrawingCoordinate;
    private float ceilDrawingCoordinate;
    private float floorDrawingCoordinate;

    int paddingLeft;
    int paddingRight;
    int paddingTop;
    int paddingBottom;
    private DrawingRect drawingRect;

    private DrawingConfig drawingConfig = new DrawingConfig();

    private Queue<Number> drawingData;
    private Queue<Number> cacheData;
    private Queue<Number> historyData;

    private Timer drawTimer = new Timer();

    // 状态参数
    private boolean isDrawingWave = false; // 是否开始绘制波浪图

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

    private void init() {
        baseValue = 0.0f;
        ceilValue = 1.0f;
        floorValue = -1.0f;

        historyData = new LinkedList<>();
        drawingData = new LinkedList<>();
        cacheData = new LinkedList<>();
        for (int i = 0; i < pointNum; i++) {
            drawingData.offer(baseValue);
        }
    }

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

    private void calculateBaseAndLimit() {
        Number max = Util.max(baseValue, ceilValue, floorValue);
        Number min = Util.min(baseValue, ceilValue, floorValue);
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
        cacheData.offer(data);
    }

    /**
     * 更新数据
     */
    private void updateDate() {
        // TODO: 数据前移
        Number latestHistoryData = drawingData.poll();
        latestHistoryData = latestHistoryData == null
                ? baseValue : latestHistoryData;
        historyData.offer(latestHistoryData);

        Number newestDrawingData = cacheData.poll();
        newestDrawingData = newestDrawingData == null
                ? baseValue : newestDrawingData;
        drawingData.offer(newestDrawingData);

        Log.d(TAG, "drawingData的数据个数：" + drawingData.size());

        //invalidate();
        postInvalidate();
    }

    /**
     * 绘制动态波浪图
     * @param canvas
     */
    private void drawDiagram(Canvas canvas, DrawingRect drawingRect) {
        drawBackgroundAndLimitLine(canvas);

        if (isDrawingWave) { // 开始绘制
        }
        else { // 不绘制
            Paint paintWave = new Paint();
            paintWave.setColor(drawingConfig.getWaveColor());
            canvas.drawLine(drawingRect.left, (drawingRect.top + drawingRect.bottom) / 2,
                    drawingRect.right, (drawingRect.top + drawingRect.bottom) / 2, paintWave);
        }
    }

    /**
     * 绘制背景及上下限
     *
     * @param canvas
     */
    private void drawBackgroundAndLimitLine(Canvas canvas) {
        Paint paintBackground = new Paint();
        paintBackground.setColor(drawingConfig.getBackGroundColor());
        canvas.drawRect(drawingRect.getLeft(), drawingRect.getTop(),
                drawingRect.getRight(), drawingRect.getBottom(),
                paintBackground);

        DashPathEffect dashPathEffect = new DashPathEffect(new float[] {5, 5}, 0);
        Paint limitLinePaint = new Paint();
        limitLinePaint.setColor(drawingConfig.getLimitLineColor());
        limitLinePaint.setStyle(Paint.Style.STROKE);
        limitLinePaint.setPathEffect(dashPathEffect);
        Path dashPath = new Path();
        dashPath.moveTo(drawingRect.getLeft(), (drawingRect.getBottom() - drawingRect.getTop()) / 5);
        dashPath.lineTo(drawingRect.getRight(), (drawingRect.getBottom() - drawingRect.getTop()) / 5);
        dashPath.moveTo(drawingRect.getLeft(), (drawingRect.getBottom() - drawingRect.getTop()) * 4 / 5);
        dashPath.lineTo(drawingRect.getRight(), (drawingRect.getBottom() - drawingRect.getTop()) * 4 / 5);
        canvas.drawPath(dashPath, limitLinePaint);
    }

    /******************* Setter and Getter *****************/
    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;

        if (isDrawingWave) {
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
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        this.pointNum = pointNum;
    }

    public Number getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(Number baseValue) {
        this.baseValue = baseValue;
    }

    public Number getCeilValue() {
        return ceilValue;
    }

    public void setCeilValue(Number ceilValue) {
        this.ceilValue = ceilValue;
    }

    public Number getFloorValue() {
        return floorValue;
    }

    public void setFloorValue(Number floorValue) {
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
