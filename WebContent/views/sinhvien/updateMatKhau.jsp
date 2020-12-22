<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Sửa thông tin</title>

<jsp:include page="/views/giaovien/common/diem.jsp"></jsp:include>
</head>

<body>
	<div class="page-wrapper">
		<!-- HEADER -->
		<jsp:include page="/views/sinhvien/common/headerDiem.jsp"></jsp:include>
		<!-- MAIN CONTENT-->
		<div class="main-content">
			<div class="section__content section__content--p30">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-11">
							<div class="card">
								<c:choose>
									<c:when test="${param.mess eq 'success' }">
										<div
											class="sufee-alert alert with-close alert-success alert-dismissible fade show">
											<span class="badge badge-pill badge-success">Success</span>
											Đổi thành công
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
									</c:when>
									<c:when test="${param.mess eq 'passM' }">
										<div
											class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
											<span class="badge badge-pill badge-danger">error</span> Mật
											khẩu mới không khớp!
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
									</c:when>
									<c:when test="${param.mess eq 'passC' }">
										<div
											class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
											<span class="badge badge-pill badge-danger">error</span> Mật
											khẩu hiện tại không đúng!
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
									</c:when>
								</c:choose>
								<div class="card-header">
									<strong>Sửa thông tin</strong>
								</div>
								<div class="card-body card-block">
									<form action="/QuanLyDiem/sinhvien/taikhoan/doipass"
										method="post" class="form-horizontal" accept-charset="UTF-8">
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="email-input" class=" form-control-label">Mật
													khẩu hiện tại</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="password" id="password-input" name="passwordCu"
													placeholder="Password" class="form-control">
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="email-input" class=" form-control-label">Mật
													khẩu mới</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="password" id="password-input"
													name="passwordMoi" placeholder="Password"
													class="form-control">
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="email-input" class=" form-control-label">Nhập
													lại mật khẩu mới</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="password" id="password-input"
													name="passwordMoiNhapLai" placeholder="Password"
													class="form-control">
											</div>
										</div>
										<div class="card-footer">
											<button type="submit" class="btn btn-primary btn-sm">
												<i class="fa fa-dot-circle-o"></i> Submit
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	</div>

	<jsp:include page="/views/giaovien/common/scriptJS.jsp"></jsp:include>
</body>

</html>
<!-- end document-->
