# 项目一
# 中山大学智慧健康服务平台应用开发

## 基本的UI界面设计

---

## Week4

### 实验内容

实现一个Android应用，界面呈现如图中的效果。  
 ![preview](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/preview.jpg) 

#### 要求  
* 该界面为应用启动后看到的第一个界面。  
* 各控件的要求
   1. 标题字体大小**20sp**，与顶部距离**20dp**，居中；
   2. 图片与上下控件的间距均为**20dp**，居中；  
   3. 输入框整体距左右屏幕各间距**20dp**，内容（包括提示内容）如图所示，内容字体大小**18sp**；  
   4. 按钮与输入框间距**10dp**，文字大小**18sp**。按钮背景框左右边框与文字间距**10dp**，上下边框与文字间距**5dp**，圆角半径**180dp**，背景色为**#3F51B5**；  
   5. 四个单选按钮整体居中，与输入框间距10dp，字体大小**18sp**，各个单选按钮之间间距**10dp**，默认选中的按钮为第一个。

#### 使用的组件
TextView、EditText、ConstraintLayout、Button、ImageView、RadioGroup、RadioButton。 

---

---
## Week5
## 基础的事件处理
---

### 实验内容
实现一个Android应用，界面呈现如图中的效果。  

![preview](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/preview.jpg)  
#### 要求  
* 该界面为应用启动后看到的第一个界面。  
* 各控件处理的要求
   1. 点击搜索按钮：
      * 如果搜索内容为空，弹出Toast信息“**搜索内容不能为空**”。
      * 如果搜索内容为“Health”，根据选中的RadioButton项弹出如下对话框。  
![success](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/success.jpg)  
点击“确定”，弹出Toast信息——**对话框“确定”按钮被点击**。  
点击“取消”，弹出Toast 信息——**对话框“取消”按钮被点击**。  
否则弹出如下对话框，对话框点击效果同上。  
![fail](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/fail.jpg)  
   2. RadioButton选择项切换：选择项切换之后，弹出Toast信息“**XX被选中**”，例如从图片切换到视频，弹出Toast信息“**视频被选中**”  

---

---

## Week6
## Intent、Bundle的使用以及RecyclerView、ListView的应用
---

### 实验内容
本次实验模拟实现一个健康食品列表，有两个界面，第一个界面用于呈现食品列表 如下所示  
![img1](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/img1.jpg)  
数据在"manual/素材"目录下给出。  
点击右下方的悬浮按钮可以切换到收藏夹  
![img2](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/img2.jpg)   
上面两个列表点击任意一项后，可以看到详细的信息：  
![img3](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/img3.jpg) 

#### UI要求  
* 食品列表  
  ​    每一项为一个圆圈和一个名字，圆圈和名字都是垂直居中。圆圈内的内容是该食品的种类，内容要处于圆圈的中心，颜色为白色。食品名字为黑色，圆圈颜色自定义，只需能看见圆圈内的内容即可。
* 收藏夹  
  ​    与食品列表相似
* 食品详情界面  
   1. 界面顶部  
   ![img4](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/img4.jpg)  
   顶部占整个界面的1/3。每个食品详情的顶部颜色在数据中已给出。返回图标处于这块区域的左上角，食品名字处于左下角，星标处于右下角，边距可以自己设置。 **返回图标与名字左对齐，名字与星标底边对齐。** 建议用RelativeLayout实现，以熟悉RelativeLayout的使用。  
   2. 界面中部  
   ![img5](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/img5.jpg)  
   使用的黑色argb编码值为#D5000000，稍微偏灰色的“富含”“蛋白质”的argb编码值为#8A000000。"更多资料"一栏上方有一条分割线，argb编码值为#1E000000。右边收藏符号的左边也有一条分割线，要求与收藏符号高度一致，垂直居中。字体大小自定。"更多资料"下方分割线高度自定。这部分所有的分割线argb编码值都是#1E000000。  
   3. 界面底部  
   ![img6](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/img6.jpg)  
   使用的黑色argb编码值为#D5000000。  
* 标题栏  
  ​    两个界面的标题栏都需要去掉  

#### 功能要求
* 使用RecyclerView实现食品列表。点击某个食品会跳转到该食品的详情界面，呈现该食品的详细信息。长按列表中某个食品会删除该食品，并弹出Toast，提示 **"删除XX"** 。
* 点击右下方的FloatingActionButton，从食品列表切换到收藏夹或从收藏夹切换到食品列表，并且该按钮的图片作出相应改变。
* 使用ListView实现收藏夹。点击收藏夹的某个食品会跳转到食品详情界面，呈现该食品的详细信息。长按收藏夹中的某个食品会弹出对话框询问是否移出该食品，点击确定则移除该食品，点击取消则对话框消失。如长按“鸡蛋”，对话框内容如下图所示。  
![img7](https://gitee.com/code_sysu/PersonalProject1/raw/master/manual/images/img7.jpg)
* 商品详情界面中点击返回图标会返回上一层。点击星标会切换状态，如果原本是空心星星，则会变成实心星星；原本是实心星星，则会变成空心星星。点击收藏图表则将该食品添加到收藏夹并弹出Toast提示 **"已收藏"** 。

---

