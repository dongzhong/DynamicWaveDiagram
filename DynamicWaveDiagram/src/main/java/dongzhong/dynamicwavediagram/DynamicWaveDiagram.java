package dongzhong.dynamicwavediagram;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dongzhong on 2017/12/20.
 */

public class DynamicWaveDiagram extends View {

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        int left, right, top, bottom;
        if (getWidth() >= paddingLeft + paddingBottom) {

        }
        else {

        }
        if (getHeight() >= paddingTop + paddingBottom) {

        }
        else {

        }
    }
}
