# 效果展示
![](https://github.com/JadeKkang/topcontroldemo/blob/master/image/topcontrol.gif)
# 使用
     1.在项目gradle中添加
        allprojects {
           repositories {
              ...<br> 
              maven { url 'https://jitpack.io' }
           }<br> 
         }<br> 
     2.添加依赖
       {
         implementation 'com.github.JadeKkang:topcontroldemo:v1.0'
       }
     3.xml中使用
           <--显示2个-->
       <com.example.topcontrol.TopControl
        android:layout_width="match_parent"
        android:layout_marginLeft="30dp"
        android:layout_marginTop="10dp"
        android:layout_marginRight="30dp"
        android:layout_height="wrap_content"
        app:hide="2" 
        app:left_text="左"
        app:right_text="右" 
        app:text_size="16sp"
        > 
       </com.example.topcontrol.TopControl> 
           <--显示3个--><br> 
       <com.example.topcontrol.TopControl
        android:id="@+id/top_control"
        android:layout_width="match_parent" 
        android:layout_marginLeft="30dp"> 
        android:layout_marginTop="10dp"
        android:layout_marginRight="30dp"
        android:layout_height="wrap_content"
        app:hide="3" 
        app:left_text="左"
        app:left_center_text="中"
        app:right_text="右"
        app:text_size="16sp"
        >
        </com.example.topcontrol.TopControl>
            <--显示4个-->
       <com.example.topcontrol.TopControl 
        android:layout_width="match_parent"
        android:layout_marginLeft="30dp"
        android:layout_marginTop="10dp" 
        android:layout_marginRight="30dp" 
        android:layout_height="wrap_content"
        app:hide="4"
        app:left_text="左" 
        app:left_center_text="左中"
        app:right_center_text="右中" 
        app:right_text="右"
        app:text_size="16sp" 
        >
        </com.example.topcontrol.TopControl>
# 自定义属性
| 属性 | 值 | 描述 | 
| ------------- |:-------------:| -----:| 
| select |#0082e0| 背景选中颜色 | 
| no_select | #ffffff | 背景未选中颜色 | 
| stroke_color |#0082e0| 背景边框线颜色 | 
| text_select | #0082e0 |文字选中颜色 | 
| text_no_select |#ffffff| 文字未选中颜色 | 
| stroke_radius | 16 | 背景边框线圆角 | 
| stroke_with |2| 背景边框宽度 | 
| left_text | "左" | 左边文字显示 | 
| left_center_text |"左中"| 靠近左边文字显示 | 
| right_center_text | "右中" | 靠近右边文字显示 | 
| right_text |"右" | 右边文字显示 | 
| hide | 3 | 个数| 
| text_size |16sp | 文字大小| 
# 预留方法

	1.setChoice(int choice)设置选择第几个

	2.setItemClick(ItemClick itemClick)设置监听回调

	3.setText(int[] text)设置文字显示
  
        4.setHide(int hide)设置显示个数
 
 #注意
 
        1.setChoice(int choice)当显示3个时候choice 是0 1 3;当显示2个时候choice 是0  3;当显示4个时候choice 是0  1  2  3
   
        2.void itemClick(View view, int i)监听回调 当显示3个时候i 是0 1 3;当显示2个时候i 是0  3;当显示4个时候i 是0  1  2  3
  
  
  
  
  
  
