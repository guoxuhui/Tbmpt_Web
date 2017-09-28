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

        <title>盾构机综合监测界面</title>
            
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
        	
        	/** 推进系统 宽度 **/
        	.box_tuijin_width{width:65px;display:inline-block}
        	
        	.box_paomo_width{display: inline-block;width: 70px;}
        	.box_paomo_width1{display: inline-block;width: 82px;text-align: right;}
        	.c_r{text-align: right;}
        	.m_l_5{margin-left: 5px}
        	
        	.value_color{color:green}
        </style>
        <script>
        	var xianluMc = '';
        	var projectRoleId = '<%=request.getParameter("role").toString()%>';
        </script>
        
    </head>
    <body id="skin-blur-violate">

        <header id="header" class="media">
            <a class="logo pull-left" href="index.jsp">
                   <img src="img/logo.png" alt=""/>
            </a>
            
            <div class="media-body">
                <div class="media" id="top-menu">

                    <div class="media-body">
                            <div class="header-m1">
                            <div class="clearfix"></div>
                            <div class="attrs">项目名称：<span id="xmMc" class="value_color"></span></div>
                            <div class="block attrs">区间线路：<span id="dtxl" class="value_color"></span></div>
                            <div class="block attrs">盾构机：<span id="dgjMc" class="value_color"></span></div>
                            </div>
                            <div class="header-m2">
                            <div class="clearfix"></div>
                            <div class="attrs">项目经理：<span id="fzr" class="value_color"></span></div>
                            <div class="block attrs">操作手:<span id="dgjczs" class="value_color"></span></div>
							<div class="block attrs">始发日期：<span id="kgrq" class="value_color"></span></div>
                            </div>
                            <div class="header-m3">
                            	<div class="clearfix"></div>
                            	<div class="m-a">环号<br><big style="font-size:32px" id="huanhao">0</big></div>
                            </div>
                            <div class="header-m4">
                            <div class="clearfix"></div>
                            <div class="attrs">区间总长：<span id="qjzc" class="value_color">0</span>M</div>
                            <div class="block attrs">主要地质：<span id="dqdz" class="value_color"></span></div>
                            <div class="block attrs"><span></span></div>
                            </div>
                            <div class="header-m3">
                            	<div class="clearfix"></div>
                            	<div class="m-a">状态<br><big style="font-size:20px" id="zhuangtai">掘进中</big></div>
                            	<!-- 
                            	<div class="clearfix"></div>
                            	<div class="attrs " id="status_j" style="text-align:center">掘进中</div>
                            	<div class="block attrs " id="status_p"  style="text-align:center">拼装中</div>
                            	<div class="block attrs " id="status_t"  style="text-align:center">停机中</div>
                            	 -->
                            </div>
                            <div class="header-m6">
                            <div class="clearfix"></div>
                            <div class="attrs" style="text-align:center"><a href='#' id="baojing">【报警（<span id="baojingCount">2</span>）】</a></div>
                            <div class="block attrs" style="text-align:center" id="dataConnect">【数据连接中...】</div>
                            <div class="block attrs" style="text-align:center">
                            
                                 <div id="time" style="color:#fff">
                                      <span id="hours"></span>
                                      :
                                      <span id="min"></span>
                                      :
                                      <span id="sec"></span>
                                 </div>
                            
                            </div>
                            </div>
                            <!-- 链接按钮 -->
		                    <div class="yl-an" style="z-index:1000">
		                        <button class="btn btn-primary"  onclick="window.history.back();">返回</button>
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
                        <div class="col-md-8">
                            
                            <!-- USA Map -->
                            <div class="tile">
                            
                                <div class="p-10">
                                    
                                     <div class="dgj">
                                       
                                           <div class="dgj-l">
                                           
                                               <h2 class="tile-title">推进系统</h2>
                                               <img src="img/dgj-l.png" alt="" />
                                               <img src="img/dgj-l-c.png" alt=""/ style="position:absolute;top:33px;left:0;" id="xuanzhuan1">
                                               
                                               <div class="chart-info">
                                                   <ul class="list-unstyled">
                                                     <li class="m-b-10">
                                                         D组
                                                         <span class="bk_pm_blue3">
                                                         	<span id="TY_TJXT_0015" class="value_color">	
                                                         	0.0
                                                         	</span>
                                                         	<font class="font_color">mm</font>
                                                         </span>        
                                                     </li>
                                                     <li class="m-b-10">
                                                     	 <span class="bk_pm_blue3">                                                       
                                                         <span id="TY_TJXT_0011" class="value_color">
                                                         0.0
                                                         </span>
                                                         <font class="font_color">bar</font>
                                                         </span>
                                                     </li>
                                                    </ul>
                                               </div>
                                               <div class="chart-info-l">
                                                    <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                        C组
                                                        <span class="bk_pm_blue3">
                                                        <span id="TY_TJXT_0014" class="value_color">
                                                        0.0
                                                        </span>
                                                        <font color="font_color" style="color:#ccc">mm</font>
                                                        </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <span class="bk_pm_blue3">
                                                        <span id="TY_TJXT_0010" class="value_color">
                                                        0.0
                                                        </span>
                                                        <font class="font_color">bar</font>
                                                        </span>
                                                        </li>
                                                    </ul>
                                               </div>
                                               <div class="chart-info-c">
                                                   <ul class="list-unstyled" style="background:rgba(5,5,5,.4);padding:5px;">
                                                        <li class="m-b-10">
                                                        <span class="blue1">推进速度</span>	
                                                        <span class="blue2">			                                                        
	                                                        <span id="TY_TJXT_0002" class="value_color">0.0 </span>
	                                                        <font class="font_color">mm/min</font>
                                                        </span>                                                       
                                                        </li>
                                                        <li class="m-b-10">
                                                        <span class="blue1">推进压力 </span>
                                                        <span class="blue2">
	                                                        <span  id="TY_TJXT_0001" class="value_color">0.0 </span>
	                                                        <font class="font_color">bar</font>
                                                        </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <span class="blue1">总推力</span>
                                                        <span class="blue2">
                                                        <span id="TY_TJXT_0003" class="value_color">0.0</span>
                                                        <font class="font_color">KN</font>
                                                        </span>
                                                        </li>
                                                        <li class="m-b-10" id="TY_TJXT_0005_tag" style="display:none;">
	                                                        <span class="blue1" > 刀盘扭矩</span>
	                                                        <span class="blue2">
	                                                        <span id="TY_TJXT_0005" class="value_color">0.0</span>
	                                                        <font class="font_color">kN.M</font> 
	                                                        </span>
                                                        </li>
                                                        <li class="m-b-10" id="TY_TJXT_0004_tag" style="display:none;">
	                                                        <span class="blue1"> 刀盘转速</span>
	                                                        <span class="blue2">
	                                                        <span id="TY_TJXT_0004" class="value_color">0.0</span>
	                                                        <font class="font_color">R/min</font>
	                                                        </span>
                                                        </li>
                                                        <li class="m-b-10" id="TY_TJXT_0007_tag" style="display:none;">
	                                                        <span class="blue1">贯入度</span>
	                                                        <span class="blue2">
	                                                        <span  id="TY_TJXT_0007" class="value_color">0.0</span>
	                                                        <font class="font_color">mm/rev</font>
	                                                        </span>
                                                        </li>
                                                   </ul>
                                               </div>
                                               <div class="chart-info-r">
                                                   <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                        A组
                                                        <span class="bk_pm_blue3 ">
                                                        <span  id="TY_TJXT_0012" class="value_color">
                                                        0.0
                                                        </span>
                                                        <font class="font_color">mm</font>
                                                        </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <span class="bk_pm_blue3">
                                                        <span  id="TY_TJXT_0008" class="value_color">
                                                        0.0
                                                        </span>
                                                        <font class="font_color">bar</font>
                                                        </span>
                                                        </li>
                                                   </ul>
                                               </div>
                                               <div class="chart-info-b">
                                                   <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                        B组
                                                        <span class="bk_pm_blue3">
                                                        <span  id="TY_TJXT_0013" class="value_color">
                                                        0.0
                                                        </span>
                                                        <font class="font_color">mm</font>
                                                        </span>
                                                        </li>
                                                        <li class="m-b-10">
                                                        <span class="bk_pm_blue3">
                                                        <span id="TY_TJXT_0009" class="value_color">
                                                        0.0
                                                        </span>
                                                        <font class="font_color">bar</font>
                                                        </span>
                                                        </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-info-t">
                                                  <ul class="list-unstyled">
                                                     <li class="m-b-10">
                                                     <div class="bk_blue1">刀盘泵<span class="huanhang"></span>压力</div>
                                                     <div class="bk_blue_tj">
														<span id="TY_TJXT_0006" class="value_color">0</span><span class="font_color">bar</span>
													 </div>
                                                     </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-info-l-b">
                                                  <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                              <div class="bk_blue1">设备桥<span class="huanhang"></span>拉力</div>
                                                              <div class="bk_blue_tj">
                                                              		<span id="TY_TJXT_0016" class="value_color">0.0</span><span class="font_color">kN</span>
                                                              </div>
                                                        </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-info-r-b">
                                                  <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                       	      <div class="bk_blue1">皮带机<span class="huanhang"></span>转速 </div>
                                                       	      <div class="bk_blue_tj">
                                                       	      		<span id="TY_TJXT_0017"  class="value_color">0.0</span> <span class="font_color">m/s</span>	
                                                       	      </div>	
                                                        </li>
                                                 </ul>
                                               </div>
                                               
                                               <!--土压监测-->
                                               <div class="chart-t-zs-info">
                                                  <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                        	<div class="bk_blue1">左上土压</div>
                                                        	<div class="bk_blue_tj">
                                                        		<span  id="TY_TCYL_0006" class="value_color">0.0</span><span class="font_color">bar</span>
                                                        	</div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-zx-info">
                                                  <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                       		<div class="bk_blue1"> 左下土压</div>
                                                       		<div class="bk_blue_tj">
                                                       			<span  id="TY_TCYL_0007" class="value_color">0.0</span><span class="font_color">bar</span>
                                                       		</div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-ys-info">
                                                  <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                        	<div class="bk_blue1">右上土压</div>
                                                        	<div  class="bk_blue_tj">
                                                        		<span id="TY_TCYL_0009" class="value_color"> 0.0</span><span class="font_color">bar</span> 
                                                        	</div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-yz-info">
                                                  <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                        	<div class="bk_blue1">右中土压</div>
                                                        	<div class="bk_blue_tj">
                                                        		<span id="TY_TCYL_0010" class="value_color">0.0</span><span class="font_color">bar</span>
                                                        	</div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               <div class="chart-t-yx-info">
                                                  <ul class="list-unstyled">
                                                        <li class="m-b-10">
                                                        	<div class="bk_blue1">右下土压</div>
                                                        	<div  class="bk_blue_tj">
                                                        		 <span id="TY_TCYL_0011" class="value_color">0.0</span><span class="font_color">bar</span>
                                                        	</div>
                                                        </li>
                                                 </ul>
                                               </div>
                                               
                                           </div>
                                           
                                           <div class="dgj-r">
                                               <h2 class="tile-title">铰接系统</h2>
                                               <img src="img/dgj-r.png" alt=""/>
                                               <!--铰接系统-->
                                               <div class="chart-j-t-info">
                                                   <ul class="list-unstyled">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">D组</div>
                                                       <div class="bk_blue" style="width:64px;display: -webkit-inline-box">
                                                       		<span id="TY_JJXT_0004" class="value_color">0.0</span><span class="font_color">mm</span> 
                                                       </div>
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-b-info">
                                                  <ul class="list-unstyled">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">B组</div>
                                                       <div class="bk_blue" style="width:64px;display: -webkit-inline-box">
	                                                       <span id="TY_JJXT_0002" class="value_color">
	                                                       0.0
	                                                       </span><span class="font_color">mm</span>
                                                       </div>
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-l-info">
                                                  <ul class="list-unstyled">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">C组</div>
                                                       <div class="bk_blue" style="width:64px;display: -webkit-inline-box">
	                                                       <span id="TY_JJXT_0003" class="value_color">
	                                                        0.0
	                                                       </span>
	                                                       <span class="font_color">mm</span>
                                                       </div>
                                                       
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-c-info">
                                                  <ul class="list-unstyled">
                                                       <li class="m-b-10">
                                                       <span class="bk_blue1">铰接压力</span>
                                                       <span class="bk_blue box_paomo_width c_r">
	                                                       <span id="" class="value_color">
	                                                       0.0
	                                                       </span>
	                                                       <font class="font_color">bar
	                                                       </font>
                                                       </span>
                                                       </li>
                                                  </ul>
                                               </div>
                                               <div class="chart-j-r-info">
                                                  <ul class="list-unstyled">
                                                       <li class="m-b-10">
                                                       <div class="bk_blue1">A组</div>
                                                       <div class="bk_blue" style="width:64px;display: -webkit-inline-box">
		                                                       <span id="TY_JJXT_0001" class="value_color">
		                                                       0.0
		                                                       </span>
		                                                       <span class="font_color">mm</span>
                                                       </div>
                                                       </li>
                                                  </ul>
                                               </div>
                                               
                                           </div>
                                           
                                           <div class="dgj-r-b">
                                               
                                               
                                               <!--螺旋机-->
                                                    <div class="chart-lx-info">
                                                       <h2 class="tile-title">螺旋机</h2>
                                                       <ul class="list-unstyled">
                                                          <li class="m-b-10">
                                                         		<div class="bk_blue1 c_r" style="width:90px"> 螺机转速</div>
                                                         		<div class="bk_blue_lx">
			                                                          <span id="TY_LXJ_0001" class="value_color">
			                                                          0.0
			                                                          </span>
			                                                          <span class="font_color">rpm</span>
                                                         		</div>	
                                                         	       
                                                          </li>
                                                          <li class="m-b-10">
                                                        		  <div class="bk_blue1 c_r" style="width:90px">螺机压力</div>
                                                        		  <div class="bk_blue_lx">
			                                                          <span id="TY_LXJ_0002" class="value_color">
			                                                          0.0
			                                                          </span>
			                                                          <span class="font_color">bar</span>
                                                        		  </div>
                                                          </li>
                                                          <li class="m-b-10">
                                                         	 <div class="bk_blue1 c_r" style="width:90px">螺机前部土压</div>
                                                         	 <div class="bk_blue_lx">
		                                                          <span  id="TY_LXJ_0005" class="value_color">
		                                                          0.0
		                                                          </span>
		                                                          <span class="font_color">bar</span>
                                                         	 </div>
                                                          </li>
                                                         <li class="m-b-10">
                                                         		<div class="bk_blue1 c_r" style="width:90px">螺机后部土压</div>
                                                         		<div class="bk_blue_lx">
			                                                         <span id="TY_LXJ_0006" class="value_color">
			                                                         0.0
			                                                         </span>
			                                                         <span class="font_color">bar</span>
                                                         		</div>
                                                         		
                                                         </li>
                                                         <li class="m-b-10">
                                                         		<div class="bk_blue1 c_r" style="width:90px">螺机后闸门开口</div>
                                                         		<div class="bk_blue_lx">
				                                                         <span id="TY_LXJ_0008" class="value_color">
				                                                         0.0
				                                                         </span>
				                                                         <span class="font_color">mm</span>
		                                                         </div>
                                                         </li>
                                                         <li class="m-b-10">
                                                         		<div class="bk_blue1 c_r" style="width:90px">螺机扭矩</div>
                                                         		<div class="bk_blue_lx">
			                                                         <span  id="TY_LXJ_0003" class="value_color">
			                                                         0.0
			                                                         </span>
			                                                         <span class="font_color">kK.M</span>
                                                         		</div>
                                                         </li>
                                                      </ul>
                                                  </div>
                                              </div>
                                              
                                     </div>
                                      
                                </div>
                                
                            </div>

                            <!-- Dynamic Chart柱状图 -->
                            <div class="tile">
                                <h2 class="tile-title">泡沫系统</h2>
                                
                                <div class="p-10">
                                    
                                    <div class="main-chart-pmxt">
                                        <p class="pm"><img src="img/pm-0.png" alt=""/></p>
                                        <p class="pm1"><img src="img/pm-1.png" alt=""/></p>
                                        <p class="pm1"><img src="img/pm-1.png" alt=""/></p>
                                        <p class="pm2"><img src="img/pm-2.png" alt=""/></p>
                                    </div>
                                    
                                    <div class="dd">
                                        <h2 style=" width:100px; text-align:center;font-size:16px;">盾尾油脂</h2>
                                    </div>         
                                                                          
                                    <div class="chart-pm-1-info">
                                        <h2>前部</h2>
                                        <ul class="list-unstyled">
                                            <li class="m-b-10">
                                                1&nbsp;号
                                               	<span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0001" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                2号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0002" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                3号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0003" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                4号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0004" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                               5号
                                               <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0005" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                6号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0006" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                        </ul>
                                      </div>
                                      
                                      <div class="chart-pm-2-info">
                                        <h2>尾部</h2>
                                        <ul class="list-unstyled">
                                            <li class="m-b-10">
                                                1&nbsp;号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0007" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                2号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0008" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                3号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0009" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                4号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0010" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                5号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0011" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10">
                                                6号
                                                <span class="bk_pm_blue box_paomo_width"><span id="TY_DWYZ_0012" class="value_color">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                        </ul>
                                      </div>
                                      
                                      <div class="chart-pm-3-info">
                                      		<ul class="list-unstyled">
                                            <li class="m-b-10 w1">
                                                泡沫压力<br>
                                                <span class="bk_pm_blue box_paomo_width">
                                                	<span id="TY_PMXT_0001" class="value_color">0.0</span>
                                                	<font class="font_color">bar</font>
                                                </span>
                                            </li>
                                            <li class="m-b-10 w2">  
                                              	<span class="no-dw w">空气流量</span>
                                                <span class="message box_paomo_width1" style="text-align: right;">
                                                	<span  id="TY_PMXT_0002" class="value_color">0.0</span>
                                                	<span class="font_color">L/min</span>
                                                </span>
                                            </li>
                                            <li class="m-b-10 w3">
                                             	<span class="no-dw w">泡沫混合液</span>
                                                <span class="message box_paomo_width1" style="text-align: right;">
                                                	<span id="TY_PMXT_0003"  class="value_color">0.0</span>
                                               		<span class="font_color">L/min</span>
                                                </span>
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled wx">
                                            <li class="m-b-10 w1">
                                                泡沫压力<br>
                                                <span class="bk_pm_blue box_paomo_width">
                                                	<span id="TY_PMXT_0004" class="value_color">0.0</span>
                                                	<font class="font_color">bar</font>
                                                </span>
                                            </li>
                                            <li class="m-b-10 w2">
                                             	<span class="no-dw w">空气流量</span>
                                                <span class="message box_paomo_width1" style="text-align: right;">
                                                	<span id="TY_PMXT_0005" class="value_color">0.0</span>
                                                	<span class="font_color">L/min</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w3">
                                            	<span class="no-dw w">泡沫混合液</span>							
                                                <span class="message box_paomo_width1" style="text-align: right;">
                                                	<span  id="TY_PMXT_0006" class="value_color">0.0</span>
                                                	<span class="font_color">L/min</span>
                                                </span>	
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled wx1">
                                            <li class="m-b-10 w1">
                                              	泡沫压力<br>
                                                <span class="bk_pm_blue box_paomo_width">
                                                	<span id="TY_PMXT_0007" class="value_color">0.0</span>
                                                	<font class="font_color">bar</font>
                                                </span>
                                            </li>
                                            <li class="m-b-10 w2">
                                             	<span class="no-dw w">空气流量</span>								
                                                <span class="message box_paomo_width1"  style="text-align: right;">
                                                	<span id="TY_PMXT_0008" class="value_color">0.0</span>
                                                	<span class="font_color">L/min</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w3">
                                                <span class="no-dw w">泡沫混合液</span>							
                                                <span class="message box_paomo_width1" style="text-align: right;">
                                                	<span  id="TY_PMXT_0009"  class="value_color">0.0</span>
                                                	<span class="font_color">L/min</span>
                                                </span>
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled wx2">
                                             <li class="m-b-10 w1">
                                              	泡沫压力<br>		
                                                <span class="bk_pm_blue box_paomo_width	">
                                                	<span id="TY_PMXT_0010" class="value_color">0.0</span>
                                                	<font class="font_color">bar</font>
                                                </span>
                                            </li>
                                            <li class="m-b-10 w2">
                                          	    <span class="no-dw w">空气流量</span>								
                                                <span class="message box_paomo_width1" style="text-align: right;">
                                                	<span id="TY_PMXT_0011" class="value_color">0.0</span>
                                                	<span class="font_color">L/min</span>
                                                </span>
                                            </li>
                                            <li class="m-b-10 w3">	
                                              	<span class="no-dw w">泡沫混合液</span>					
                                                <span class="message box_paomo_width1" style="text-align: right;">
                                                	<span id="TY_PMXT_0012" class="value_color">0.0</span>
                                                	<span class="font_color">L/min</span>
                                                </span>
                                            </li>
                                        </ul>
                                        <!-- <ul class="list-unstyled">
                                            <li class="m-b-10 w1">
                                                                                                                泡沫压力<br>
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0001">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10 w2">  
                                              	空气流量
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0002">0.0</span><font  class="font_color">L/min</font></span>
                                            </li>
                                            <li class="m-b-10 w3">
                                             	泡沫混合液
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0003">0.0</span><font  class="font_color">L/min</font></span>
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled wx">
                                            <li class="m-b-10 w1">
                                                                                                                 泡沫压力<br>
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0004">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10 w2">
                                             	空气流量
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0005">0.0</span><font  class="font_color">L/min</font></span>
                                            </li>
                                            <li class="m-b-10 w3">
                                            	泡沫混合液
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0006">0.0</span><font  class="font_color">L/min</font></span>
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled wx1">
                                            <li class="m-b-10 w1">
                                              	泡沫压力<br>
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0007">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10 w2">
                                             	空气流量
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0008">0.0</span><font class="font_color">L/min</font></span>
                                            </li>
                                            <li class="m-b-10 w3">
                                                                                                                泡沫混合液
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0009">0.0</span><font class="font_color">L/min</font></span>
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled wx2">
                                            <li class="m-b-10 w1">
                                              	泡沫压力<br>		
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0010">0.0</span><font class="font_color">bar</font></span>
                                            </li>
                                            <li class="m-b-10 w2">
                                          	        空气流量
                                               <span class="bk_pm_blue"><span id="TY_PMXT_0011">0.0</span><font class="font_color">L/min</font></span>
                                            </li>
                                            <li class="m-b-10 w3">	
                                              	泡沫混合液
                                                <span class="bk_pm_blue"><span id="TY_PMXT_0012">0.0</span><font class="font_color">L/min</font></span>
                                            </li>
                                        </ul> -->
                                      </div>
                                      
                                      <div class="chart-pm-4-info">
                                        <ul class="list-unstyled">
                                            <li class="m-b-10 w2">
                                            	原液流量<br>
                                               <span style="width:65px;display: -webkit-inline-box;" class="bk_pm_blue"><span id="TY_PMXT_0013" class="value_color">0.0</span><font class="font_color">L/min</font></span>
                                            </li>
                                            <br/>
                                            <li class="m-b-10 w3">
                                            	工业水流量<br>
                                               	<span style="width:65px;display: -webkit-inline-box;" class="bk_pm_blue"><span id="TY_PMXT_0014" class="value_color">0.0</span><font class="font_color">L/min</font></span>
                                            </li>
                                        </ul>
                                      </div>

                                </div>
                            </div>
                            
                            <div class="clearfix"></div>
                        </div>
                        
                        <div class="col-md-4">
                        
                            <!-- Main Chart 折线图 -->
                            <div class="tile">
                                <h2 class="tile-title" style="text-align:left">导向系统</h2>
                                
                                <div class="p-10">
                                	
                                	<div class="dx">
                                    <div class="dx_box"> 
                                    <div class="chart-dx-1-info">
                                        <ul class="list-unstyled">
                                            <li class="dx_box1" style="text-align: center;width:60%">
                                                                                                                 转动角<br>
                                                <span class="bk_pm_blue1">
                                                	<span id="TY_DXXT_0004" class="value_color">0.0</span><span class="font_color">mm/m</span>
                                                </span>
                                            </li>
                                            <li class="dx_box2" style="text-align: center;width:60%">
                                                                                                                 俯仰角
                                                <span class="bk_pm_blue1">
                                                	<span id="TY_DXXT_0003" class="value_color">0.0</span><span class="font_color">mm/m</span>
                                                </span>
                                            </li>
                                        </ul>
                                    </div>
                                	
                                	<canvas id="myCanvas1" width="360" height="360"></canvas>
                                	<script>
                                	function  drawCoordinate(width,height){
                                		var canvas = document.getElementById("myCanvas");
										var context = canvas.getContext("2d");
										
										context.clearRect(0,0,360,360);
										
										var xCells = 50,//x轴显示几个单元格
										yCells = 50,//y轴显示几个单元格
										xWidth = 260,//x轴显示宽度
										yWidth = 260,//y轴显示宽度
										stepX = parseInt(xWidth/xCells), //步长
										stepY = parseInt(yWidth/yCells), //步长
										startX = 0,//初始坐标点
										startY = 0,//初始坐标点
										xSpacing = 15,//x轴距容器底部边距
										ySpacing = 13,//y轴距容器左侧边距
										xScaleNum = -5,//x轴刻度开始
										yScaleNum = 5,//x轴刻度开始
										xLineBegin = 55,//x轴线开始坐标(xLineBegin,height-xSpacing)
										yLineBegin = 54;//y轴线开始坐标(ySpacing,yLineBegin)
										//x轴坐标线
										context.beginPath();
										context.strokeStyle='#ccc';
										context.moveTo(xLineBegin,height-xSpacing);  //x开始坐标25
										context.lineTo(xLineBegin+xWidth,height-xSpacing);
										context.stroke();   
										//纵坐标轴
										for(i= 0 ;i < xCells ;i++){
											  if(i % 5 == 0){
												  context.moveTo(xLineBegin,height-xSpacing+5);
												  context.lineTo(xLineBegin,height-xSpacing-5);
												  //横坐标的数字
												  context.fillStyle='#ccc';
												  context.font = "12px Microsoft Yahei";
												  context.fillText(xScaleNum, xLineBegin-2,height-xSpacing+15);
												  xScaleNum = xScaleNum + 1;
											  }else{
												 context.moveTo(xLineBegin,height-xSpacing);
												 context.lineTo(xLineBegin,height-xSpacing-3);
											  }
											  context.stroke();   
											  xLineBegin = xLineBegin + stepX;
										 }
										 //横坐标轴
										 context.beginPath();
										 context.strokeStyle='#ccc';
										 context.moveTo(ySpacing,yLineBegin);
										 context.lineTo(ySpacing,yLineBegin+yWidth);
										 context.stroke();   
										 for(i= 0 ;i < yCells;i++){
											  if(i % 5 == 0){
												 context.moveTo(ySpacing-5,yLineBegin);
												 context.lineTo(ySpacing+5,yLineBegin);
												 //横坐标的数字
												 context.fillStyle='#ccc';
												 context.font = "12px Microsoft Yahei";
												 context.fillText(yScaleNum, ySpacing-12,yLineBegin+5);
												 yScaleNum = yScaleNum - 1;
											  }else{
												  context.moveTo(ySpacing,yLineBegin);
												  context.lineTo(ySpacing+5,yLineBegin);
											  }
											  context.stroke();   
											  yLineBegin = yLineBegin + stepY;
										 }
										 
										 //------------------------------------------------------------------------------------------------------------------
										 
										 //var c=document.getElementById("myCanvas");
                                         //var ctx=c.getContext("2d");
                                          //圆圈范围
										 var x = 160;
									 	 var r = 140;
										 for(var n=0;n<9;n++){
											 //cir(180+n*10,180,160-n*10,1-n*0.1);
											 cir(x,180,r,1-n*0.05,context);
											 x = x + 15;
											 r = r - (8-n*0.7)*2;
										 }
										 function cir(x,y,r,a){
											 context.beginPath();
											 context.arc(x,y,r,0,2*Math.PI);
											 context.strokeStyle='#ccc';
											 context.globalAlpha=a;
											 context.stroke();
											 context.closePath();
										 }
										 var pingyi = 20;
										 var centerX = width/2,centerY = height/2;
										 // 绘制横线
										 context.beginPath();
										 context.lineWidth="1";
										 context.strokeStyle='#ccc';
										 context.globalAlpha=0.8;
										 context.moveTo(10,180);
										 context.lineTo(340,180);
										 context.stroke(); // 进行绘制
                                         // 显示不同的单位值
										 // 绘制竖线
										 context.beginPath();
										 context.lineWidth="1";
										 context.strokeStyle='#ccc';
										 context.globalAlpha=0.5;
										 context.moveTo(180,20);
										 context.lineTo(180,350);
										 context.stroke(); // 进行绘制
										 // 显示不同的单位值
										 /* context.font="12px Microsoft Yahei";
										 context.fillStyle="#ccc";
										 context.globalAlpha=0.5;
										 context.textAlign="200";
										 context.fillText("220",190,15);
										 context.textAlign="-220";
										 context.fillText("-220",190,335);
										 
										 context.font="12px Microsoft Yahei";
										 context.fillStyle="#ccc";
										 context.globalAlpha=0.5;
										 context.textAlign="220";
										 context.fillText("220",322,170);
										 context.textAlign="-220";
										 context.fillText("-220",20,170);
										  */
										 //纵轴线坐标
                                         context.textAlign="100";context.fillText("100",  centerX+5,centerY -100);
                                         context.textAlign="50";context.fillText("50",    centerX+5,centerY-50);
                                         context.textAlign="-50";context.fillText("-50",  centerX+5,centerY+50);
                                         context.textAlign="-100";context.fillText("-100",centerX+5,centerY+100);
                                         context.stroke(); // 进行context
                                         //横轴县坐标
                                         context.textAlign="-100";context.fillText("-100",centerX-100,centerY-5);
                                         context.textAlign="-50";context.fillText("-50",centerX-50,centerY-5);
                                         context.textAlign="50";context.fillText("50",centerX+50,centerY-5);
                                         context.textAlign="100";context.fillText("100",centerX+100,centerY-5);
                                         context.stroke(); // 进行绘制
                                         
                                         //画正方形区域范围
                                         context.beginPath();   // 50 
                                         context.strokeStyle='#fff';
                                         context.moveTo(centerX-50,centerY-50);
                                         context.lineTo(centerX+50,centerY-50);
                                         context.lineTo(centerX+50,centerY+50);
                                         context.lineTo(centerX-50,centerY+50);
                                         context.lineTo(centerX-50,centerY-50);
                                         context.stroke(); 
										 
                                         context.beginPath();  //100
                                         context.strokeStyle='#fff';
                                         context.moveTo(centerX-100,centerY-100);
                                         context.lineTo(centerX+100,centerY-100);
                                         context.lineTo(centerX+100,centerY+100);
                                         context.lineTo(centerX-100,centerY+100);
                                         context.lineTo(centerX-100,centerY-100);
                                         context.stroke(); 
										 
										 
										 
                                	}
                               		window.onload = function(){
												//横线与竖线的间隔距离
												drawCoordinate(360,360);
									};

									   </script>
                                    <canvas id="myCanvas" width="360" height="360" class="myCanvas"></canvas>
                                      </div>
                                      <!--推进环数-->
                                      <div class="chart-dx-0-info">
                                        <ul class="list-unstyled">
                                            <li class="m-b-10 c_r">
                                               	当天环数
                                               	<span class="bk_pm_blue1 m_l_5" style="width:45%">
                                                	<span id="TY_DXXT_0001" class="value_color">0</span><span class="font_color">环</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 c_r">
                                             	里程
                                             	<span class="bk_pm_blue1 m_l_5" style="width:45%">
                                                	<span  class="value_color">0.0</span><span class="font_color">m</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 c_r">
                                              	已掘进
                                              	<span class="bk_pm_blue1 m_l_5" style="width:45%">
                                                	<span  class="value_color">0.0</span><span class="font_color">m</span>
                                                </span>	
                                            </li>
                                        </ul>
                                      </div>
                                      <div class="chart-dx-2-info">
                                        <h2>水平偏差</h2>
                                        <ul class="list-unstyled">
                                            <li class="m-b-10 c_r">
                                               	切口
                                               	<span class="bk_pm_blue1 m_l_5" style="width:60%">
                                                	<span id="TY_DXXT_0007" class="value_color">0.0</span><span class="font_color">mm</span>
                                                </span>	
                                            </li>
                                            <!-- 
                                            <li class="m-b-10">
                                                中点
                                                <span class="message yellow">0m</span>
                                            </li>
                                             -->
                                            <li class="m-b-10 c_r">
                                            	盾尾
                                            	<span class="bk_pm_blue1 m_l_5" style="width:60%">
                                                	<span id="TY_DXXT_0008" class="value_color">0.0</span><span class="font_color">mm</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 c_r">
                                              	趋向
                                              	<span class="bk_pm_blue1 m_l_5" style="width:60%">
                                                	<span id="TY_DXXT_0010" class="value_color">0.0</span><span class="font_color">mm/m</span>
                                                </span>	
                                            </li>
                                        </ul>
                                      </div>
                                      <div class="chart-dx-3-info">
                                        <h2>垂直偏差</h2>
                                        <ul class="list-unstyled">
                                            <li class="m-b-10 c_r">
                                           	          切口
                                           	    <span class="bk_pm_blue1 m_l_5" style="width:60%">
                                                	<span id="TY_DXXT_0005" class="value_color">0.0</span><span class="font_color">mm</span>
                                                </span>	
                                            </li>
                                            <!-- 
                                            <li class="m-b-10">
                                                中点
                                                <span class="message yellow">0m</span>
                                            </li>
                                             -->
                                            <li class="m-b-10 c_r">
                                                	盾尾
                                                <span class="bk_pm_blue1 m_l_5" style="width:60%">
                                                	<span id="TY_DXXT_0006" class="value_color">0.0</span><span class="font_color">mm</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 c_r">
                                              	趋向
                                              	<span class="bk_pm_blue1 m_l_5" style="width:60%">	
                                                	<span id="TY_DXXT_0009" class="value_color">0.0</span><span class="font_color">mm/m</span>
                                                </span>	
                                            </li>
                                        </ul>
                                      </div>
                                      
                                      </div>
                                </div>  
                            </div>
    
                            <!-- Dynamic Chart走动的曲线图 -->
                            <div class="tile">

                                <div class="p-10">
                                    
                                    
                                     
                                     <div class="chart-zj-info">
                                        <h2>注浆监测</h2>
                                        <ul class="list-unstyled">
                                            <li class="m-b-10 w1">
                                                <span class="message no-dw">１号压力</span>
                                                <span class="bk_pm_blue2">
                                                <span id="TY_ZJXT_0001"  class="value_color">0.0</span><span class="font_color">bar</span>
                                                </span>
                                            </li>
                                            <li class="m-b-10 w2">
                                                <span  class="message no-dw">１号累计量</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="TY_ZJXT_0005" class="value_color">0.0</span><span class="font_color">Mp</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w1">
                                                <span class="message no-dw">２号压力</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="TY_ZJXT_0002" class="value_color">0.0</span><span class="font_color">bar</span>
                                                 </span>	
                                            </li>
                                            <li class="m-b-10 w2">
                                                <span class="message no-dw">２号累计量</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="TY_ZJXT_0006" class="value_color">0.0</span><span class="font_color">Mp</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w1">
                                                <span class="message no-dw">３号压力</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="TY_ZJXT_0003" class="value_color">0.0</span><span class="font_color">bar</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w2">
                                                <span class="message no-dw">３号累计量</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="TY_ZJXT_0007" class="value_color">0.0</span><span class="font_color">Mp</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w1">
                                                <span class="message no-dw">４号压力</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="TY_ZJXT_0004" class="value_color">0.0</span><span class="font_color">bar</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w2">
                                                <span class="message no-dw">４号累计量</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="TY_ZJXT_0008" class="value_color">0.0</span><span class="font_color">Mp</span>
                                                </span>	
                                            </li>
                                            <li class="m-b-10 w1">
                                                
                                            </li>
                                            <li class="m-b-10 w2">
                                                <span class="message no-dw">合计累计量</span>
                                                <span class="bk_pm_blue2">
                                                	<span id="leijiliang" class="value_color">0.0</span><span class="font_color">Mp</span>
                                                </span>	
                                            </li>
                                            <!-- <li class="m-b-10 w1" style="width:100%;">
                                                <span class="message no-dw">合计累计量</span>
                                                <div class="w5 bk_blue">
                                                	<span id="leijiliang">000.00</span><span class="font_color">mp</span>
                                                </div>
                                                <script>
                                                	
                                                </script>
                                            </li> -->
                                        </ul>
                                      </div>
                                     
                                     <div class="chart-zj-r">
                                           <h2>温度监测</h2>
                                           <div class="p-10">
                                               <div class="pie-chart-tiny1" data-percent="0" id="TY_WDJC_0003" style="visibility: hidden">
                                                   <span class="percent1"></span>
                                                   <span class="pie-title1">刀盘泄露油<i class="m-l-5 fa fa-retweet"></i></span>
                                               </div>
                                               <div class="pie-chart-tiny1" data-percent="0"  id="TY_WDJC_0002" style="visibility: hidden">
                                                   <span class="percent1"></span>
                                                   <span class="pie-title1">齿轮油箱<i class="m-l-5 fa fa-retweet"></i></span>
                                               </div>
                                               <div class="pie-chart-tiny1" data-percent="0" id="TY_WDJC_0001" style="visibility: hidden">
                                                   <span class="percent1"></span>
                                                   <span class="pie-title1">液压油箱<i class="m-l-5 fa fa-retweet"></i></span>
                                               </div>
                                               <div class="pie-chart-tiny1" data-percent="0" id="TY_WDJC_0004" style="visibility: hidden">
                                                   <span class="percent1"></span>
                                                   <span class="pie-title1">螺机泄露油<i class="m-l-5 fa fa-retweet"></i></span>
                                               </div>
                                           </div>
                                     </div>
                                     
                                    <hr class="whiter" style="margin:10px 0 0 0;"/>
                                     
                                    <!-- 压力测试 -->
                                    <div class="text-center">
                                           
                                           <div class="p-10">
                                               
                                               <div class="yljc">
                                               <h2>压力监测</h2>
                                               <div class="m-b-10 yl-w">
                                                   HBW内密封压力 - <span id="TY_YLJC_0001" class="value_color">0.0</span><font class="font_color">bar</font>
                                                   <div class="progress progress-striped progress-alt">
                                                       <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                                                   </div>
                                               </div> 
                                               <div class="m-b-10 yl-w">
                                                   HBW外密封压力 - <span id="TY_YLJC_0002" class="value_color">0.0</span><font class="font_color">bar</font>
                                                   <div class="progress progress-striped progress-alt">
                                                       <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                                                   </div>
                                               </div>
                                               <div class="m-b-10 yl-w">
                                                   EP2内密封压力 - <span id="TY_YLJC_0003" class="value_color">0.0</span><font class="font_color">bar</font>
                                                   <div class="progress progress-striped progress-alt">
                                                       <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="33" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                                                   </div>
                                               </div>
                                               <div class="m-b-10 yl-w">
                                                   EP2外密封压力 -  <span id="TY_YLJC_0004" class="value_color">0.0</span><font class="font_color">bar</font>
                                                   <div class="progress progress-striped progress-alt">
                                                       <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                                                   </div>
                                               </div>
                                               <div class="m-b-10 yl-w">
                                                   	刀盘磨损监测 - <span id="TY_YLJC_0005" class="value_color">0.0</span><font class="font_color">bar</font>
                                                   <div class="progress progress-striped progress-alt">
                                                       <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="99" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                                                   </div>
                                               </div>
                                               <div class="m-b-10 yl-w">
                                                   	齿轮油压力 - <span id="TY_YLJC_0006" class="value_color">0.0</span><font class="font_color">bar</font>
                                                   <div class="progress progress-striped progress-alt">
                                                       <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="33" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                                                   </div>
                                               </div>
                                               </div>
                                               
                                           </div>
                                    </div>
                                    
                                    <!-- 链接按钮 -->
                                    <div class="yl-an"><!-- class="btn btn-primary" -->
                                           <button  class="btn" onclick="location.href='../sys_1/index.jsp?xmId='+xiangmuId+'&tbmId='+tbmId+'&xlId='+xianluId+'&role='+projectRoleId" >导向系统</button>
                                           <button  class="btn" onclick="location.href='../sys_2/index.jsp?xmId='+xiangmuId+'&tbmId='+tbmId+'&xlId='+xianluId+'&role='+projectRoleId" >推进系统</button>
                                           <button  class="btn" onclick="location.href='../sys_3/index.jsp?xmId='+xiangmuId+'&tbmId='+tbmId+'&xlId='+xianluId+'&role='+projectRoleId" >报警系统</button>
                                           <button  class="btn" onclick="location.href='../sys_4/index.jsp?xmId='+xiangmuId+'&tbmId='+tbmId+'&xlId='+xianluId+'&role='+projectRoleId" >相关参数</button>
                                    </div>
                                     
                                    
                                </div>
                            </div>
                                                            
                                
                            </div>
                        </div>
                        <div class="clearfix"></div>
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
        
        
        <!-- 加载数据所需js -->
        <script src="../layui/layui.js" type="text/javascript"></script>
        <script src="../js/jzk.tbm.js"></script>
        <script src="js/jquery.rotate.min.js"></script>
        <script type="text/javascript">
        	var xiangmuId = getQueryString("xmId");
        	var tbmId = getQueryString("tbmId");//'20150171';//
        	var xianluId = getQueryString("xlId");//'20150171';//
        </script>
        <script type="text/javascript" src="js/wuxi.canshu.js"></script>
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
    </body>
</html>
