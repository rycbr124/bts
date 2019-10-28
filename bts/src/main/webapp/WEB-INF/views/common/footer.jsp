<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>

ul{
list-style: none;
}

#footer {width: 100%;padding: 0 0 60px;position:relative; background: #fff;z-index:100;}
.footer_inner {max-width: 1200px;margin: 0 auto;position: relative;}
/*.footer_logo {position:absolute;right: 70px;width: 51px;height: 81px;} */

/* top */
/*.footer_top {padding: 73px 0 28px;margin-bottom:40px;border-bottom:1px solid #333333;position:relative;z-index:10;} */
.footer_cs_title { margin-bottom:10px; font-size:16px; font-weight:600; color: #333333; letter-spacing:-0.025em;}
.footer_cs_content .footer_cs_tel {display:inline-block;vertical-align:middle;}
.footer_cs_content .footer_cs_tel a {font-size:40px;font-weight:600;font-family: 'Binggrae', sans-serif;color: #333333;letter-spacing: -0.025em;}
.footer_cs_content .footer_cs_time {margin-left:20px;font-size:12px;font-weight:600;line-height: 1.8;color: #333333;display:inline-block;vertical-align:middle;}

/* menu */
.footer_menu > li {float: left; margin-right:5px;}
.footer_menu > li:last-child { margin-right:0;}
.footer_menu > li > a {display: block;width: 89px;height: 30px;line-height: 30px;text-align: center;position: relative;font-size: 12px;font-weight: 600;color: #FFFFFF;-webkit-transition: all 300ms;transition: all 300ms;}
.footer_menu > li.footer_menu_notice > a, .footer_menu > li.footer_menu_faq > a { background: #333333; border-radius:10px 10px 10px 0;}
.footer_menu > li.footer_menu_event > a { background: #333333;  border-radius:10px 10px 10px 0;}
.footer_menu > li.footer_menu_terms > a { background:#333333; border-radius:10px 10px 0 10px;}
.footer_menu > li.footer_menu_privacy > a {background:#333333;border-radius:10px 10px 0 10px;width: 128px;}
html:not(.mobile) .footer_menu > li.footer_menu_notice > a:hover, html:not(.mobile) .footer_menu > li.footer_menu_faq > a:hover { background:#d91114}
html:not(.mobile) .footer_menu > li.footer_menu_event > a:hover { background:#d91114}
html:not(.mobile) .footer_menu > li.footer_menu_terms > a:hover { background:#d91114}
html:not(.mobile) .footer_menu > li.footer_menu_privacy > a:hover { background:#d91114}

/* info */
.footer_info {clear:both; margin-bottom: 20px;}
.footer_info ul {padding: 38px 0 27px;overflow:hidden;}
.footer_info ul li  {margin-right:20px;margin-bottom:6px;font-size:14px;line-height: 1.4;color:#888;float:left;}
.footer_info ul li:first-child { display:block; float:none;}
.footer_info ul li span {padding-right: 16px;position:relative;}
.footer_info ul li span:after {width:1px;height: 13px;background: #ed4043;content:'';display:block;-webkit-transform: rotateZ(30deg);-ms-transform: rotate(30deg);transform: rotateZ(30deg);position:absolute;top:4px;right: 7px;}
.footer_info ul li  a { color:#888;}

</style>
<meta charset="UTF-8">
<title>하단</title>
</head>
<body>
	    <div class="footer_inner">
			<!--  <a href="#" id="go_top" class="go_top"><span lang="en">TOP</span></a>-->

			<div class="footer_top">
				<div class="footer_cs">
					<p class="footer_cs_title">Best Travel Seoul</p>
					<div class="footer_cs_content">
						 <p class="footer_cs_tel"><a href="#">1234-5678</a></p>
						 <ul class="footer_cs_time">
							 <li>이용시간 평일 09:00~18:00</li>
							 <li>점심시간 12:00~13:00</li>
						 </ul>
					</div><!-- .footer_cs_content -->
				</div><!-- .footer_cs -->

							</div><!-- .footer_top -->
			
			<ul class="footer_menu">
				<li class="footer_menu_notice"><a href="#">공지사항</a></li>
				<li class="footer_menu_event"><a href="#">이벤트</a></li>
				<li class="footer_menu_faq"><a href="#">FAQ</a></li>
				<li class="footer_menu_terms"><a href="#">이용약관</a></li>
				<li class="footer_menu_privacy"><a href="#">개인정보처리방침</a></li>
			</ul>

			<!--  <p class="footer_logo"><a href="#"><img src="test04/BTS_logo_black_all.png" alt="BTS" /></a></p>-->

			<div class="footer_info">
				<ul>
					<li>Best Travel Seoul</li>
				    <li class="name"><span>대표이사</span>이주희</li>
					<li><span>주소</span>서울특별시 서초구 서초동</li>
					<li class="sub_name"><span>직원</span>안밀령 이재홍 안은영 원종평</li>
				</ul>
				<div lang="en" class="copyright"> <span>COPYRIGHT 2019 BTS Corp. ALL RIGHTS RESERVED.</span></div>
			</div><!-- .footer_info -->
        </div> <!-- .footer_inner -->
</body>
</html>