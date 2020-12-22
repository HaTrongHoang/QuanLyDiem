<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- HEADER MOBILE-->

<!-- END HEADER MOBILE-->

<!-- MENU SIDEBAR-->


<!-- END MENU SIDEBAR-->

<!-- PAGE CONTAINER-->
<div class="">
	<!-- HEADER DESKTOP-->
	<header class="header-desktop">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 logo" >
					<a href="/QuanLyDiem/sinhvien/diem"> <img  style="height: 75px"
						src="/QuanLyDiem/images/logo.png" alt="QuanLyDiem" />
					</a>
				</div>
				<div class="col-lg-2 header-button ">
					<div class="account-wrap">
						<div class="account-item clearfix js-item-menu">
							<div>
								<div class="image">
									<img
										src="${pageContext.request.contextPath }/upload/sinhvien/${sessionScope.loginSinhVien.img}"
										alt="${sessionScope.loginSinhVien.hoten}" />
								</div>
								<label> ${sessionScope.loginSinhVien.hoten}</label>
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
										<a href="/QuanLyDiem/sinhvien/taikhoan"> <i
											class="zmdi zmdi-account"></i>Tài khoản
										</a>
									</div>
								</div>
								<div class="account-dropdown__footer">
									<a href="/QuanLyDiem/logout-sv"> <i class="zmdi zmdi-power"></i>Logout
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- HEADER DESKTOP-->