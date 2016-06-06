
/*
   Copyright [2016] [Mohit Rakhra]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package autohoverbutton.rakhra.com.libautohover;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Mohit Rakhra on 6/6/16.
 */
public class AutoHoverButton extends FrameLayout {

    private final AttributeSet attrs;

    private LinearLayout container;
    private ImageView mImageView;
    private TextView mTextView;

    private final  int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#5D4C46");
    private final int SELECTOR_BACKGROUND_COLOR = Color.parseColor(("#E45641"));
    private final  int DEFAULT_TEXT_COLOR = Color.parseColor("#ffffff");
    private final int SELECTOR_TEXT_COLOR = Color.parseColor(("#000000"));
    private final int DEFAULT_MARGIN = 4;
    private final int DEFAULT_TEXT_SIZE = 10;

    private int atb_margin;
    private String atb_bg_Normal;
    private String atb_bg_Selected;
    private String atb_txtcolor_Normal;
    private String atb_txtcolor_Selected;
    private String atb_text;
    private Drawable atb_drawable_normal;
    private Drawable atb_drawable_selected;
    private int atb_txt_size;;


    private enum animation_Type {Fade_In,Fade_Out };

    /**
     * Simple constructor to use when creating a SwipeRefreshLayout from code.
     */
    public AutoHoverButton(Context context) {
        this(context, null);

        init_AnimatedTabBar(context);
    }

    /**
     * Constructor that is called when inflating SwipeRefreshLayout from XML.
     */
    public AutoHoverButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.attrs = attrs;

        init_AnimatedTabBar(context);
    }


    public void init_AnimatedTabBar(Context context){

        final Resources res = getResources();

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AnimatedTabBar);

        atb_drawable_normal = a.getDrawable(R.styleable.AnimatedTabBar_atb_drawable_normal);
        atb_drawable_selected = a.getDrawable(R.styleable.AnimatedTabBar_atb_drawable_selector);
        atb_bg_Normal = a.getString(R.styleable.AnimatedTabBar_atb_background_normal);
        atb_bg_Selected = a.getString(R.styleable.AnimatedTabBar_atb_background_selector);
        atb_txtcolor_Normal = a.getString(R.styleable.AnimatedTabBar_atb_textColor_normal);
        atb_txtcolor_Selected = a.getString(R.styleable.AnimatedTabBar_atb_textColor_selector);
        atb_margin = (int) a.getDimension(R.styleable.AnimatedTabBar_atb_margin, TypedValue.COMPLEX_UNIT_DIP);
        atb_txt_size =  a.getInt(R.styleable.AnimatedTabBar_atb_textSize, DEFAULT_TEXT_SIZE);
        atb_text = a.getString(R.styleable.AnimatedTabBar_atb_text);

        // Action Layout
        container = new LinearLayout(context);
        container.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT, CENTER_VERTICAL));
        setBackgroundColor(Color.parseColor(getBackgroundNormal()));
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(CENTER_VERTICAL | CENTER_HORIZONTAL);

        mImageView = new ImageView(context);
        mImageView.setImageDrawable(getDrawable_Normal());
        LayoutParams param = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mImageView.setLayoutParams(param);

        mTextView = new TextView(context);
        mTextView.setText(atb_text);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, atb_txt_size);
        mTextView.setTextColor(Color.parseColor(getTextColor_Normal()));
        LayoutParams paramText = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mTextView.setPadding(0,atb_margin,0,0);
        mTextView.setLayoutParams(paramText);

        container.addView(mImageView);
        container.addView(mTextView);
        this.addView(container);

        a.recycle();
    }


    public void makeSelectable(){
        container.setBackgroundColor(Color.parseColor(getBackgroundSelected()));;
        mTextView.setTextColor(Color.parseColor(getTextColor_Selected()));
        mImageView.setImageDrawable(getDrawable_Selected());
        postInvalidate();
    }

    public void makeUnSelectable(){
        container.setBackgroundColor(Color.parseColor(getBackgroundNormal()));
        mTextView.setTextColor(Color.parseColor(getTextColor_Normal()));
        mImageView.setImageDrawable(getDrawable_Normal());
        postInvalidate();
    }

    /**
     * Method to set the normal state of background color
     * @param color
     */
    public void setBackgroundNormal(String color){
        atb_bg_Normal = color;
        postInvalidate();
    }

    /**
     * Returns the present normal color of background
     * @return
     */
    public String getBackgroundNormal(){
        return atb_bg_Normal;
    }

    /**
     * Method to set the selected state of background color
     * @param Color
     */
    public void setBackgroundSelected(String Color){
        atb_bg_Selected = Color;
        postInvalidate();
    }

    /**
     * Returns the present selected color of background
     * @return
     */
    public String getBackgroundSelected(){
        return atb_bg_Selected;
    }

    /**
     * Method to set the normal state of drawable
     * @param drawable
     */
    public void setDrawable_Normal(Drawable drawable){
        atb_drawable_normal = drawable;
    }

    /**
     * Returns the present normal state drawable
     * @return
     */
    public Drawable getDrawable_Normal(){
        return atb_drawable_normal;
    }

    /**
     * Method to set the selected state of drawable
     * @param drawable
     */
    public void setDrawable_Selected(Drawable drawable){
        atb_drawable_selected = drawable;
    }

    /**
     * Returns the present selected state drawable
     * @return
     */
    public Drawable getDrawable_Selected(){
        return atb_drawable_selected;
    }

    /**
     * Method to set the normal state text Color
     * @param color
     */
    public void setTextColor_Normal(String color){
        atb_txtcolor_Normal = color;
        postInvalidate();
    }

    /**
     * Returns the present normal state of text color
     * @return
     */
    public String getTextColor_Normal(){
        return atb_txtcolor_Normal;
    }

    /**
     * Method to set the selected state text Color
     * @param color
     */
    public void setTextColor_Selected(String color){
        atb_txtcolor_Selected = color;
        postInvalidate();
    }

    /**
     * Returns the present selected state of text color
     * @return
     */
    public String getTextColor_Selected(){
        return atb_txtcolor_Selected;
    }

    /**
     * Method to set the normal state of background Color
     * @param color
     */
    public void setbgColor_Normal(String color){
        atb_bg_Normal = color;
        postInvalidate();
    }

    /**
     * Returns the present normal state of background color
     * @return
     */
    public String getbgColor_Normal(){
        return atb_bg_Normal;
    }

    /**
     * Method to set the selected state of background color
     * @param color
     */
    public void setbgColor_Selected(String color){
        atb_bg_Selected = color;
        postInvalidate();
    }

    /**
     * Returns the present selected state of background color
     * @return
     */
    public String getbgColor_Selected(){
        return atb_bg_Selected;
    }

    /**
     * Method to set the margin between the image and the text
     * @param margin
     */
    public void setMargin(int margin){
        atb_margin = margin;
    }

    /**
     * Returns the margin between the image and text
     * @return
     */
    public int getMargin(){
        return atb_margin;
    }

}
