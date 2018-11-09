package com.example.topcontrol;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 时间：2018/9/20 10:00
 * 姓名：韩晓康
 * 功能：顶部切换按钮  项目需要  如不能满足需要  可自行扩展  最多显示3个  最少两个
 */
public class TopControl extends LinearLayout {
    private TextView left;
    private TextView leftCenter;
    private TextView rightCenter;
    private TextView right;
    private ItemClick itemClick;//点击回调
    private int hide;//控制显示个数  2显示左右  3显示左中右 4显示4个
    private int[] text;//控制显示个数
    private Context context;
    private int bg_select;
    private int bg_no_select;
    private int stroke_color;
    private int stork_with;
    private int stork_radius;
    private int text_select;
    private int text_no_select;
    private float text_size;
    private String left_text;
    private String left_center_text;
    private String right_center_text;
    private String right_text;
    private LinearLayout llBg;
    private int text_stroke_color;
    private int bg_color;

    public TopControl(Context context) {
        this(context, null);
    }

    public TopControl(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TopControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.top_control, this, true);
        left = (TextView) view.findViewById(R.id.tv_control_left);
        llBg = (LinearLayout) view.findViewById(R.id.ll_bg);
        left.setSelected(true);
        leftCenter = (TextView) view.findViewById(R.id.tv_control_left_center);
        rightCenter = (TextView) view.findViewById(R.id.tv_control_right_center);
        right = (TextView) view.findViewById(R.id.tv_control_right);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.top_control);
        if (array != null) {
            bg_select = array.getColor(R.styleable.top_control_select, ContextCompat.getColor(context, R.color.bg_select));
            bg_no_select = array.getColor(R.styleable.top_control_select, ContextCompat.getColor(context, R.color.bg_no_select));
            stroke_color = array.getColor(R.styleable.top_control_stroke_color, ContextCompat.getColor(context, R.color.stroke_color));
            text_select = array.getColor(R.styleable.top_control_text_select, ContextCompat.getColor(context, R.color.text_select));
            text_no_select = array.getColor(R.styleable.top_control_text_no_select, ContextCompat.getColor(context, R.color.text_no_select));
            text_stroke_color = array.getColor(R.styleable.top_control_text_stroke_color, ContextCompat.getColor(context, R.color.text_stroke_color));
            bg_color = array.getColor(R.styleable.top_control_bg_color, ContextCompat.getColor(context, R.color.bg_color));
            stork_with = array.getInteger(R.styleable.top_control_stroke_with, 2);
            stork_radius = array.getInteger(R.styleable.top_control_stroke_radius, 8);
            text_size = array.getDimension(R.styleable.top_control_text_size, 14);
            left_text = array.getString(R.styleable.top_control_left_text);
            left_center_text = array.getString(R.styleable.top_control_left_center_text);
            right_center_text = array.getString(R.styleable.top_control_right_center_text);
            right_text = array.getString(R.styleable.top_control_right_text);
            hide = array.getInteger(R.styleable.top_control_hide, 3);
        }
        array.recycle();
        init();
        onClick();
    }

    /**
     * 初始化
     */
    private void init() {
        hide(hide);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            llBg.setBackground(getDrawable(3, bg_color, stroke_color, 2, stork_radius));
            left.setBackground(initStateListDrawable(1));
            right.setBackground(initStateListDrawable(2));
            leftCenter.setBackground(initStateListDrawable(0));
            rightCenter.setBackground(initStateListDrawable(0));
        } else {
            llBg.setBackground(getDrawable(3, bg_select, stroke_color, 2, stork_radius));
            left.setBackgroundDrawable(initStateListDrawable(1));
            right.setBackgroundDrawable(initStateListDrawable(2));
            leftCenter.setBackgroundDrawable(initStateListDrawable(0));
            rightCenter.setBackgroundDrawable(initStateListDrawable(0));
        }
        left.setTextColor(colorStateList(text_select, text_select, text_no_select));
        right.setTextColor(colorStateList(text_select, text_select, text_no_select));
        leftCenter.setTextColor(colorStateList(text_select, text_select, text_no_select));
        rightCenter.setTextColor(colorStateList(text_select, text_select, text_no_select));

        left.setTextSize(text_size);
        right.setTextSize(text_size);
        leftCenter.setTextSize(text_size);
        rightCenter.setTextSize(text_size);

        left.setText(left_text);
        leftCenter.setText(left_center_text);
        rightCenter.setText(right_center_text);
        right.setText(right_text);
    }

    /**
     * 显示按钮个数
     *
     * @param hide
     */
    private void hide(int hide) {
        if (hide == 2) {
            leftCenter.setVisibility(GONE);
            rightCenter.setVisibility(GONE);
        } else if (hide == 3) {
            rightCenter.setVisibility(GONE);
            leftCenter.setVisibility(VISIBLE);
        } else if (hide == 4) {
            rightCenter.setVisibility(VISIBLE);
            leftCenter.setVisibility(VISIBLE);
        }
    }


    private void onClick() {
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorSelect();
                left.setSelected(true);
                if (itemClick != null) {
                    itemClick.itemClick(left, 0);
                }
            }
        });
        leftCenter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorSelect();
                leftCenter.setSelected(true);
                if (itemClick != null) {
                    itemClick.itemClick(leftCenter, 1);
                }
            }
        });
        rightCenter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorSelect();
                rightCenter.setSelected(true);
                if (itemClick != null) {
                    itemClick.itemClick(rightCenter, 2);
                }
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorSelect();
                right.setSelected(true);
                if (itemClick != null) {
                    itemClick.itemClick(right, 3);
                }
            }
        });
    }

    /**
     * 设置选择颜色
     */
    private void setColorSelect() {
        left.setSelected(false);
        leftCenter.setSelected(false);
        rightCenter.setSelected(false);
        right.setSelected(false);
    }

    /**
     * 对TextView设置不同状态时其文字颜色。
     *
     * @return
     */
    private ColorStateList colorStateList(int selected, int pressed, int normal) {
        int[] colors = new int[]{selected, pressed, normal};
        int[][] states = new int[3][];
        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{android.R.attr.state_pressed};
        states[2] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    /**
     * 产生shape类型的drawable
     * orientation 1 左边 2右边
     *
     * @param solidColor
     * @param strokeColor
     * @param strokeWidth
     * @param radius
     * @return
     */
    public static GradientDrawable getDrawable(int orientation, int solidColor, int strokeColor, int strokeWidth, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(solidColor);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadius(radius);
        //分别表示 左上 右上 右下 左下
        if (orientation == 1) {
            drawable.setCornerRadii(new float[]{radius, radius, 0f, 0f, 0f, 0f, radius, radius});
        } else if (orientation == 2) {
            drawable.setCornerRadii(new float[]{0f, 0f, radius, radius, radius, radius, 0f, 0f});
        } else if (orientation == 3) {
            drawable.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        } else {
            drawable.setCornerRadii(new float[]{0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f});
        }
        return drawable;
    }

    /**
     * 选择器
     * orientation 1 左边 2右边
     *
     * @return
     */
    private StateListDrawable initStateListDrawable(int orientation) {
        //初始化一个空对象
        StateListDrawable btState = new StateListDrawable();
        btState.addState(new int[]{android.R.attr.state_selected},
                getDrawable(orientation, bg_select, text_stroke_color, stork_with + 1, stork_radius));
        btState.addState(new int[]{},
                getDrawable(orientation, bg_no_select, text_stroke_color, stork_with + 1, stork_radius));
        return btState;
    }

    /**
     * 设置监听
     * @param itemClick
     */
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }
    /**
     * 监听接口
     */
    public interface ItemClick {
        void itemClick(View view, int i);
    }

    /**
     * 作者  韩晓康
     * 时间  2018/9/20 10:53
     * 描述  选择第几个  选中
     * 当显示3个时候choice 是0 1 3
     * 当显示2个时候choice 是0  3
     * 当显示4个时候choice 是0  1  2  3
     */
    public void setChoice(int choice) {
        setColorSelect();
        switch (choice) {
            case 0://左
                left.setSelected(true);
                break;
            case 1://左右
                leftCenter.setSelected(true);
                break;
            case 2://左中
                rightCenter.setSelected(true);
                break;
            case 3://右
                right.setSelected(true);
                break;
        }
    }
    /**
     * 设置显示文字
     *
     * @param text
     */
    public void setText(int[] text) {
        this.text = text;
        if (hide == 2) {
            if (text != null && text.length > 0) {
                left.setText(context.getString(text[0]));
                right.setText(context.getString(text[1]));
            }
        } else if (hide == 3) {
            if (text != null && text.length > 0) {
                left.setText(context.getString(text[0]));
                leftCenter.setText(context.getString(text[1]));
                right.setText(context.getString(text[2]));
            }
        } else {
            if (text != null && text.length > 0) {
                left.setText(context.getString(text[0]));
                leftCenter.setText(context.getString(text[1]));
                rightCenter.setText(context.getString(text[2]));
                right.setText(context.getString(text[3]));
            }

        }
    }
    /**
     * 作者  韩晓康
     * 时间  2018/9/20 10:53
     * 描述  设置显示个数
     */
    public void setHide(int hide) {
        this.hide = hide;
        invalidate();
    }
}
