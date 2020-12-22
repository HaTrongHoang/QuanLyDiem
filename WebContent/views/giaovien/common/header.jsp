<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- HEADER MOBILE-->
<header class="header-mobile d-block d-lg-none">
	<div class="header-mobile__bar">
		<div class="container-fluid">
			<div class="header-mobile-inner">
				<a class="logo" href="/QuanLyDiem/admin/home"> <img
					src="/QuanLyDiem/images/logo.png" alt="QuanLyDiem" />
				</a>
				<button class="hamburger hamburger--slider" type="button">
					<span class="hamburger-box"> <span class="hamburger-inner"></span>
					</span>
				</button>
			</div>
		</div>
	</div>

	<nav class="navbar-mobile">
		<div class="container-fluid">
			<ul class="navbar-mobile__list list-unstyled">
				<!--  <li class="has-sub"><a class="js-arrow" href="#"> <i
						class="fas fa-tachometer-alt"></i>Dashboard
				</a>
					<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
						<li><a href="index.html">Dashboard 1</a></li>
						<li><a href="index2.html">Dashboard 2</a></li>
						<li><a href="index3.html">Dashboard 3</a></li>
						<li><a href="index4.html">Dashboard 4</a></li>
					</ul></li>
				<li><a href="chart.html"> <i class="fas fa-chart-bar"></i>Charts
				</a></li>-->
				<li><i class="zmdi zmdi-account-calendar"></i>Quản lý tài khoản













				
				<li><a href="/QuanLyDiem/admin/feedback?status=1">Giáo Viên</a></li>
				<li><a href="/QuanLyDiem/admin/feedback?status=2"">Sinh
						Viên</a></li>
				</li>

				<li><a href="/QuanLyDiem/admin/news"> <i
						class="zmdi zmdi-account-calendar"></i>Tin tức
				</a></li>
				<li class="has-sub"><a class="js-arrow" href="#"> <i
						class="fa fa-book" aria-hidden="true"></i> </i>Sổ tay hoa lan
				</a>
					<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
						<c:forEach items="${categoryList }" var="category">
							<li><a
								href="/QuanLyDiem/admin/tutorial?category_id=${category.id_category }">${category.name }</a></li>
						</c:forEach>
					</ul></li>
				<li><a href="/QuanLyDiem/admin/category"> <i
						class="fa fa-bars" aria-hidden="true"></i> Quản lý danh mục
				</a></li>
				<li class="has-sub"><a class="js-arrow" href="#"> <i
						class="far fa-envelope"></i>Quản lý phản hồi

				</a>
					<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
						<li><a href="/QuanLyDiem/admin/feedback?status=1">Chưa xử
								lý</a></li>
						<li><a href="/QuanLyDiem/admin/feedback?status=2"">Đã xử
								lý</a></li>
					</ul></li>
				<!-- <li class="has-sub"><a class="js-arrow" href="#"> <i
						class="fas fa-copy"></i>Pages
				</a>
					<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
						<li><a href="login.html">Login</a></li>
						<li><a href="register.html">Register</a></li>
						<li><a href="forget-pass.html">Forget Password</a></li>
					</ul></li>
				<li class="has-sub"><a class="js-arrow" href="#"> <i
						class="fas fa-desktop"></i>UI Elements
				</a>
					<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
						<li><a href="button.html">Button</a></li>
						<li><a href="badge.html">Badges</a></li>
						<li><a href="tab.html">Tabs</a></li>
						<li><a href="card.html">Cards</a></li>
						<li><a href="alert.html">Alerts</a></li>
						<li><a href="progress-bar.html">Progress Bars</a></li>
						<li><a href="modal.html">Modals</a></li>
						<li><a href="switch.html">Switchs</a></li>
						<li><a href="grid.html">Grids</a></li>
						<li><a href="fontawesome.html">Fontawesome Icon</a></li>
						<li><a href="typo.html">Typography</a></li>
					</ul></li>-->
			</ul>
		</div>
	</nav>
</header>
<!-- END HEADER MOBILE-->

<!-- MENU SIDEBAR-->
<aside class="menu-sidebar d-none d-lg-block">
	<div class="logo">
		<a href="/QuanLyDiem/giaovien/tai-khoan-gv"> <img
			src="/QuanLyDiem/images/logo.png" alt="QuanLyDiem" />
		</a>
	</div>
	<div class="menu-sidebar__content js-scrollbar1">
		<nav class="navbar-sidebar">
			<ul class="list-unstyled navbar__list">
				<li class="has-sub"><a class="js-arrow" href="#"> <i
						class="fas fa-user"></i></i>Quản lý tài khoản

				</a>
					<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
						<li><a href="/QuanLyDiem/giaovien/tai-khoan-gv">Giáo Viên</a></li>
						<li><a href="/QuanLyDiem/giaovien/taikhoan-sv">Sinh Viên</a></li>
					</ul></li>
				<li><a href="/QuanLyDiem/giaovien/phanmon"> <i
						class="far fa-address-book"></i>Phân Môn
				</a></li>
				<li><a href="/QuanLyDiem/giaovien/lop"><i
						class="fas fa-book"></i>Lớp học </a></li>
				<li><a href="/QuanLyDiem/giaovien/diem"> <i
						class="fa fa-bars" aria-hidden="true"></i> Quản lý điểm
				</a></li>
				<li><a href="/QuanLyDiem/giaovien/yeucau"> <i
						class="far fa-envelope"></i>Yêu cầu thay đổi
				</a></li>
				<c:if test="${loginAdmin.role eq 0 }">
					<li class="has-sub"><a class="js-arrow" href="#"> <i
							class="fas fa-cog"></i>Cài đặt
					</a>
						<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
							<li><a href="/QuanLyDiem/giaovien/hocky">Học kỳ</a></li>
						</ul>
						<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
							<li><a href="/QuanLyDiem/giaovien/nhapxuat">Nhập/Xuất</a></li>
						</ul></li>
				</c:if>

			</ul>
		</nav>
	</div>
</aside>
<!-- END MENU SIDEBAR-->

<!-- PAGE CONTAINER-->
<div class="page-container">
	<!-- HEADER DESKTOP-->
	<header class="header-desktop">
		<div class="section__content section__content--p30">
			<div class="container-fluid">
				<div class="header-wrap">
					<form class="form-header" action="" method="POST"></form>
					<div class="header-button ">
						<div class="account-wrap">
							<div class="account-item clearfix js-item-menu">
								<div>
									<div class="image">
										<img
											src="${pageContext.request.contextPath }/upload/giaovien/${sessionScope.loginGiaoVien.img}"
											alt="${sessionScope.loginGiaoVien.hoten}" />
									</div>
									<label> ${sessionScope.loginGiaoVien.hoten}</label>
								</div>
								<div class="account-dropdown js-dropdown">
									<div class="info clearfix">
										<!--  <div class="content">
											<h5 class="name">
												<a href="#"></a>
											</h5>
										</div>-->
									</div>
									<div class="account-dropdown__body">
										<div class="account-dropdown__item">
											<a href="/QuanLyDiem/giaovien/taikhoan"> <i
												class="zmdi zmdi-account"></i>Tài khoản
											</a>
										</div>
									</div>
									<div class="account-dropdown__footer">
										<a href="/QuanLyDiem/logout"> <i class="zmdi zmdi-power"></i>Logout
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- HEADER DESKTOP-->