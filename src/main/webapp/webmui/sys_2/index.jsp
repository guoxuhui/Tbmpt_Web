<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta name="format-detection" content="telephone=no">
        <meta charset="UTF-8">

        <meta name="description" content="Violate Responsive Admin Template">
        <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

        <title>推进油缸</title>
            
        <!-- CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/animate.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/form.css" rel="stylesheet">
        <link href="css/calendar.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/icons.css" rel="stylesheet">
        <link href="css/generics.css" rel="stylesheet">
        
        <style type="text/css">
			.red_color{background-color:red}
			.green_color{background-color:#73BF00}
		</style> 
    </head>
    <body id="skin-blur-violate">

        <header id="header" class="media">
            <a class="logo pull-left" href="index.html">
                   <img src="img/logo.png" alt=""/>
            </a>
            
            <div class="media-body">
                <div class="media" id="top-menu">

                    <div class="media-body">
                            <div class="header-m1">
                            <div class="clearfix"></div>
                            <div class="attrs">项目部：<span id="xmMc"></span></div>
                            <div class="block attrs">区间：<span id="qjxl"></span></div>
                            <div class="block attrs">盾构机：<span id="dgjMc"></span></div>
                            </div>
                            <div class="header-m2">
                            <div class="clearfix"></div>
                            <div class="attrs">项目经理：<span id="fzr"></span></div>
                            <div class="block attrs">操作手：<span id="baibsj"></span></div>
                            <div class="block attrs">操作手：<span id="yebsj"></span></div>
                            </div>
                            <div class="header-m3">
                            <div class="clearfix"></div>
                            <div class="m-a">环号<br><big style="font-size:32px"  id="huanhao">0</big></div>
                            </div>
                            <div class="header-m4">
                            <div class="clearfix"></div>
                            <div class="attrs">区间总长：<span id="qjzc"></span></div>
                            <div class="block attrs">主要地质：<span id="zydz"></span></div>
                            <div class="block attrs">始发日期：<span id="kgrq"></span></div>
                            </div>
                            <div class="header-m5">
	                            <div class="clearfix"></div>
	                            <div class="attrs"  id="status_j"  style="text-align:center">掘进中</div>
	                            <div class="block attrs"  id="status_p"  style="text-align:center">拼装中</div>
	                            <div class="block attrs"  id="status_t"  style="text-align:center">停机中</div>
                            </div>
                            <div class="header-m6">
                            <div class="clearfix"></div>
                            <div class="attrs" style="text-align:center"> <a href='#' id="baojing">【报警（<span id="baojingCount">2</span>）】</a></div>
                            <div class="block attrs" style="text-align:center"  id="dataConnect">【数据连接中...】</div>
                            <div class="block attrs" style="text-align:center">
                            
                                 <div id="time">
                                      <span id="hours"></span>
                                      :
                                      <span id="min"></span>
                                      :
                                      <span id="sec"></span>
                                 </div>
                            
                            </div>
                            </div>
                            <!-- 链接按钮 -->
                            <div class="yl-an" style="z-index:1000"  >
                                <button class="btn btn-primary"  id="goBack">返回主界面</button>
                            </div>
                            
                    </div>
                    
                </div>
            </div>
        </header>
        
        <div class="clearfix"></div>
        
        <section id="main" class="p-relative" role="main">
            
            <!-- Sidebar -->
            
        
            <!-- Content -->
            <section id="content" class="container">
            
                
                <!-- Main Widgets -->
               
                <div class="block-area">
                    <div class="row">
                        
                        <!-- col-md-8开始 -->
                        <div class="col-md-8">
                            
                            <!-- 推进系统 -->
                            <div class="tile">
                            
                                <div class="p-10">
                                    
                                     
                                       <div class="dgj1">
                                        
                                           <div class="dgj-l">
                                           
                                               <h2 class="tile-title">推进系统</h2>
                                               
                                               <img src="img/dgj-l-c.png"  id="xuanzhuan1" alt=""/ class="img1">
                                               <img src="img/dgj-l.png" alt=""/ class="img2">
                                               
                                               
                                               <div class="chart-info-t">
                                                  <ul style="list-style:none;">
                                                     <li class="m-b-10">
                                                     <span class="bk_blue1">刀盘泵压力</span>
                                                     <span class="bk_blue">
                                                     <span id="TY_TJXT_0006">0</span><span class="font_color">bar</span>
                                                     </span>
                                                     </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-info-t-r">
                                                   <ul style="list-style:none;">
                                                     <li class="m-b-10">
                                                        <div class="bk_blue1">刀盘转速</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TJXT_0004">0</span><span class="font_color">bar</span>
                                                        </div>
                                                     </li>
                                                     <li class="m-b-10">
                                                         <div class="bk_blue1">刀盘扭矩</div>
                                                         <div class="bk_blue">
                                                         <span id="TY_TJXT_0005">0</span><span class="font_color">bar</span>
                                                        </div>
                                                     </li>
                                                     <li class="m-b-10">
                                                         <div class="bk_blue1">刀盘压力</div>
                                                         <div class="bk_blue">
                                                        <span id="TY_TJXT_0006">0</span><span class="font_color">bar</span>
                                                        </div>
                                                     </li>
                                                    </ul>
                                               </div>
                                               <div class="chart-info">
                                                   <ul style="list-style:none;">
                                                     <li class="m-b-10">
                                                         <div class="bk_blue1">D组推进行程</div>
                                                         <div class="bk_blue">
                                                        <span id="TY_TJXT_0015">0</span><span class="font_color">bar</span>
                                                        </div>
                                                     </li>
                                                     <li class="m-b-10">
                                                         <div class="bk_blue1">D组推进压力</div>
                                                         <div class="bk_blue">
                                                        <span id="TY_TJXT_0011">0</span><span class="font_color">bar</span>
                                                        </div>
                                                     </li>
                                                     <li class="m-b-10">
                                                         <div class="bk_blue1">D组推进力</div>
                                                         <div class="bk_blue">
                                                         <span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                     </li>
                                                    </ul>
                                               </div>
                                               <div class="chart-info-l">
                                                    <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">C组推进行程</div>
                                                        <div class="bk_blue">
                                                         <span  id="TY_TJXT_0014">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">C组推进压力</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TJXT_0010">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                    </ul>
                                               </div>
                                               <div class="chart-info-c">
                                                   <ul style="list-style:none; text-align:right">
                                                        <li class="m-b-10">
                                                        滚动角
                                                        <span class="blue2">
                                                         <span id="TY_DXXT_0004">0</span><font class="font_color">mm/min</font>
                                                         </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        俯仰角
                                                        <span class="blue2">
                                                         <span id="TY_DXXT_0003">0</span><font class="font_color">mm/min</font>
                                                         </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        推进速度
                                                        <span class="blue2">
                                                         <span id="TY_TJXT_0002">0</span><font class="font_color">mm/min</font>
                                                         </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        推进压力
                                                        <span class="blue2">
                                                         <span id="TY_TJXT_0001">0</span><font class="font_color">mm/min</font>
                                                         </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        总推力
                                                        <span class="blue2">
                                                         <span id="TY_TJXT_0003">0</span><font class="font_color">mm/min</font>
                                                         </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        贯入度
                                                        <span class="blue2">
                                                         <span id="TY_TJXT_0007">0</span><font class="font_color">mm/min</font>
                                                         </span>
                                                        </li>
                                                   </ul>
                                               </div>
                                               <div class="chart-info-r">
                                                   <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">A组推进行程</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TJXT_0012">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">A组推进压力</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TJXT_0008">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                   </ul>
                                               </div>
                                               <div class="chart-info-b">
                                                   <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">B组推进行程</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TJXT_0013">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">B组推进压力</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TJXT_0009">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-info-l-b">
                                                  <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">HBW压力</div>
                                                        <div class="bk_blue">
                                                        <span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">内密封</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_YLJC_0001">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">外密封</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_YLJC_0002">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-info-r-b">
                                                  <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">EP2压力</div>
                                                        <div class="bk_blue">
                                                         <span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">内密封</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_YLJC_0003">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">外密封</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_YLJC_0004">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               
                                               <!--土压监测-->
                                               <div class="chart-t-zs-info">
                                                  <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">左上土压</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TCYL_0006">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-zx-info">
                                                  <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">左下土压</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TCYL_0006">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-ys-info">
                                                  <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">右上土压</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TCYL_0009">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-yz-info">
                                                  <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">右中土压</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TCYL_0010">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-yx-info">
                                                  <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">右下土压</div>
                                                        <div class="bk_blue">
                                                        <span id="TY_TCYL_0011">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-cc-info">
                                                   <ul style="list-style:none;">
                                                        <li class="m-b-10">
                                                        <div class="bk_blue1">前部土压</div>
                                                        <div class="bk_blue">
                                                        <span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                        </li>
                                                  </ul>
                                               </div>
                                               
                                           </div>
                                           
                                        </div>
                                      
                                </div>
                                
                            </div>
                            
                        </div>
                        <!-- col-md-8结束-->
                        
                        <!-- col-md-4开始-->
                        <div class="col-md-4">
                            
                            <!-- 推进系统 -->
                            <div class="tile">
                            
                                <div class="p-10">
                                    
                                     <div class="dgj2">
                                        
                                           
                                           
                                            <div class="dgj-r">
                                           
                                               <h2 class="tile-title">铰接系统</h2>
                                               
                                               <img src="img/dgj-r.png" alt=""/ class="img2">
                                               
                                               <!--铰接系统-->
                                               <div class="chart-j-t-info">
                                                   <ul style="list-style:none;">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">D组</div>
                                                       <div class="bk_blue">
                                                        <span id="TY_JJXT_0004">0</span><span class="font_color">bar</span>
                                                        </div>
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-b-info">
                                                  <ul style="list-style:none;">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">B组</div>
                                                       <div class="bk_blue">
                                                         <span id="TY_JJXT_0002">0</span><span class="font_color">bar</span>
                                                        </div>
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-l-info">
                                                  <ul style="list-style:none;">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">C组</div>
                                                       <div class="bk_blue">
                                                        <span id="TY_JJXT_0003">0</span><span class="font_color">bar</span>
                                                        </div>
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-c-info">
                                                  <ul style="list-style:none;">
                                                       <li class="m-b-10">
                                                       铰接
                                                       <span class="message yellow">
                                                       <span id="">0</span>
                                                       </span>
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-r-info">
                                                  <ul style="list-style:none;">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">A组</div>
                                                       <div class="bk_blue">
                                                       <span id="TY_JJXT_0001">0</span><span class="font_color">bar</span>
                                                        </div>
                                                       </li>
                                                  </ul>
                                               </div>
                                               
                                           </div>
                                            
                                           
                                            <div class="dgj-r-b">
                                                   
                                                   <div style="float:left">
                                                       <img src="img/lxj.png" class="lx_img">
                                                   </div>
                                                   
                                                   <div style="float:right">
                                                    <!--螺旋机-->
                                                       <div class="chart-lx-info">
                                                       <h2 class="tile-title">螺旋机</h2>
                                                       
                                                       <ul style="list-style:none;">
                                                          <li class="m-b-10">
                                                          <div class="bk_blue1">螺机转速</div>
                                                          <div class="bk_blue2">
                                                        	<span id="TY_LXJ_0001">0</span><span class="font_color">bar</span>
                                                        </div>
                                                          </li>
                                                          <li class="m-b-10">
                                                          <div class="bk_blue1">螺机压力</div>
                                                          <div class="bk_blue2">
                                                          <span id="TY_LXJ_0002">0</span><span class="font_color">bar</span>
                                                        </div>
                                                          </li>
                                                          <li class="m-b-10">
                                                          <div class="bk_blue1">螺机前部土压</div>
                                                          <div class="bk_blue2">
                                                         <span id="TY_LXJ_0005">0</span><span class="font_color">bar</span>
                                                        </div>
                                                          </li>
                                                         <li class="m-b-10">
                                                         <div class="bk_blue1">螺机后部土压</div>
                                                         <div class="bk_blue2">
                                                        	<span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                         </li>
                                                         <li class="m-b-10">
                                                         <div class="bk_blue1">螺机后闸门开口</div>
                                                         <div class="bk_blue2">
                                                          <span id="TY_LXJ_0008">0</span><span class="font_color">bar</span>
                                                        </div>
                                                         </li>
                                                         <li class="m-b-10">
                                                         <div class="bk_blue1">螺机扭矩</div>
                                                         <div class="bk_blue2">
                                                          <span id="TY_LXJ_0003">0</span><span class="font_color">bar</span>
                                                        </div>
                                                         </li>
                                                      </ul>


                                                       </div>
                                                   
                                               
                                                    <!--注浆-->
                                                       <div class="chart-lx-info">
                                                       <h2 class="tile-title">注浆</h2>
                                                       <ul style="list-style:none;">
                                                          <li class="m-b-10">
                                                          <div class="bk_blue1">螺机转速</div>
                                                          <div class="bk_blue2">
                                                        	<span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                          </li>
                                                          <li class="m-b-10">
                                                          <div class="bk_blue1">螺机压力</div>
                                                          <div class="bk_blue2">
                                                        	<span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                          </li>
                                                          <li class="m-b-10">
                                                          <div class="bk_blue1">螺机前部土压</div>
                                                          <div class="bk_blue2">
                                                        	<span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                          </li>
                                                         <li class="m-b-10">
                                                         <div class="bk_blue1">螺机后部土压</div>
                                                         <div class="bk_blue2">
                                                       	 <span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                         </li>
                                                         <li class="m-b-10">
                                                         <div class="bk_blue1">螺机后闸门开口</div>
                                                         <div class="bk_blue2">
                                                        	<span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                         </li>
                                                         <li class="m-b-10">
                                                         <div class="bk_blue1">螺机扭矩</div>
                                                         <div class="bk_blue2">
                                                      		 <span id="">0</span><span class="font_color">bar</span>
                                                        </div>
                                                         </li>
                                                      </ul>
                                                       </div>
                                                    
                                                   </div>
                                                   
                                            </div>
                                            
                                           
                                            
                                     </div>
                                      
                                </div>
                                
                            </div>
                            
                        </div>
                        <!-- col-md-4结束-->
                        
                        
                                    
                    </div>
                </div>
                
                
            </section>

            <!-- Older IE Message -->
            <!--[if lt IE 9]>
                <div class="ie-block">
                    <h1 class="Ops">Ooops!</h1>
                    <p>You are using an outdated version of Internet Explorer, upgrade to any of the following web browser in order to access the maximum functionality of this website. </p>
                    <ul class="browsers">
                        <li>
                            <a href="https://www.google.com/intl/en/chrome/browser/">
                                <img src="img/browsers/chrome.png" alt="">
                                <div>Google Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.mozilla.org/en-US/firefox/new/">
                                <img src="img/browsers/firefox.png" alt="">
                                <div>Mozilla Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.opera.com/computer/windows">
                                <img src="img/browsers/opera.png" alt="">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://safari.en.softonic.com/">
                                <img src="img/browsers/safari.png" alt="">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://windows.microsoft.com/en-us/internet-explorer/downloads/ie-10/worldwide-languages">
                                <img src="img/browsers/ie.png" alt="">
                                <div>Internet Explorer(New)</div>
                            </a>
                        </li>
                    </ul>
                    <p>Upgrade your browser for a Safer and Faster web experience. <br/>Thank you for your patience...</p>
                </div>   
            <![endif]-->
        </section>
        
        <!-- Javascript Libraries -->
        <!-- jQuery -->
        <script src="js/jquery.min.js"></script> <!-- jQuery Library -->
        <script src="js/jquery-ui.min.js"></script> <!-- jQuery UI -->
        <script src="js/jquery.easing.1.3.js"></script> <!-- jQuery Easing - Requirred for Lightbox + Pie Charts-->

        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Charts -->
        <script src="js/charts/jquery.flot.js"></script> <!-- Flot Main -->
        <script src="js/charts/jquery.flot.time.js"></script> <!-- Flot sub -->
        <script src="js/charts/jquery.flot.animator.min.js"></script> <!-- Flot sub -->
        <script src="js/charts/jquery.flot.resize.min.js"></script> <!-- Flot sub - for repaint when resizing the screen -->
        <script src="js/charts/jquery.flot.pie.min.js"></script> <!-- Flot Pie chart -->

        <script src="js/sparkline.min.js"></script> <!-- Sparkline - Tiny charts -->
        <script src="js/easypiechart.js"></script> <!-- EasyPieChart - Animated Pie Charts -->
        <script src="js/charts.js"></script> <!-- All the above chart related functions -->

        <!-- Map -->
        <script src="js/maps/jvectormap.min.js"></script> <!-- jVectorMap main library -->
        <script src="js/maps/usa.js"></script> <!-- USA Map for jVectorMap -->

        <!--  Form Related -->
        <script src="js/icheck.js"></script> <!-- Custom Checkbox + Radio -->

        <!-- UX -->
        <script src="js/scroll.min.js"></script> <!-- Custom Scrollbar -->

        <!-- Other -->
        <script src="js/calendar.min.js"></script> <!-- Calendar -->
        <script src="js/feeds.min.js"></script> <!-- News Feeds -->
        

        <!-- All JS functions -->
        <script src="js/functions.js"></script>
        
        
         <script type="text/javascript" src="../js/jzk.tbm.js"></script>
		<!-- All JS functions -->
		<script type="text/javascript">
			//获取项目信息
			var xiangmuId = getQueryString("xmId");
			var dgjId = getQueryString("tbmId");
			var xlId = getQueryString("xlId");
			var xianluId = getQueryString("xlId");
			$('#baojing').click(function(){
				location.href = '../sys_3/index.jsp?xmId='+xiangmuId+'&tbmId='+dgjId;
			});
		</script>
		<!-- 加载数据所需js -->
	    <script src="../layui/layui.js" type="text/javascript"></script>
	    <script src="../js/jzk.tbm.js"></script>
	     <script src="../jqjm/js/jquery.rotate.min.js"></script>
	  	<script type="text/javascript">
			layui.use([ 'layer' ], function() {
			});
			
			var beishu = 3 ;
			var angle = 0;
			setInterval(function(){
			      angle+=0.006 * beishu;
			     $("#xuanzhuan1").rotate(angle);
			},1);
		</script>
	    <script src="../js/zixitong.common.js"></script>
        
        
    </body>
</html>
    