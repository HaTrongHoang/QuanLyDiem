<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.hung.model.Diem"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="author" content="Hau Nguyen">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Tables</title>

<jsp:include page="/views/giaovien/common/linkCss.jsp"></jsp:include>

</head>

<body>
	</script>
	<div class="page-wrapper">
		<!-- header -->
		<jsp:include page="/views/giaovien/common/header.jsp"></jsp:include>
		<!--end header -->
		<!-- MAIN CONTENT-->
		<div class="main-content">
			<div class="section__content section__content--p30">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-12">
							<c:choose>
								<c:when test="${param.mess eq 'exist' }">
									<div
										class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
										<span class="badge badge-pill badge-danger">Erorr</span> Danh
										muc ton tai
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
								<c:when test="${param.mess eq 'success' }">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-success">Success</span>
										Them thanh cong
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
								<c:when test="${param.mess eq 'delete' }">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-success">Success</span>
										Xoa thanh cong
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
								<c:when test="${param.mess eq 'update' }">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-success">Success</span>
										Sua thanh cong
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
							</c:choose>
							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Xuất danh sách lớp
								</h3>
								<div class="card-body card-block">
									<form action="/QuanLyDiem/giaovien/dslop" method="post"
										class="form-horizontal">
										<div class="table-data__tool">
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Lớp
													Học</label> <select name="id_lop" id="select" class="form-control"
													required>
													<c:forEach items="${lopList }" var="lopList">
														<option value="${lopList.id_lop }">${lopList.tenlop }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Học
													Kỳ</label> <select name="id_hocky" id="select" class="form-control"
													required>
													<c:forEach items="${hockyList }" var="hockyList">
														<option value="${hockyList.id_hocky }">${hockyList.tenhocky}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col col-md-3"></div>
										</div>
										<div class="card-footer">
											<button type="submit" class="btn btn-primary btn-sm">
												<i class="fa fa-dot-circle-o"></i> Xuất
											</button>
										</div>
									</form>
								</div>
							</div>
							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Xuất danh sách thi
									lại
								</h3>
								<div class="card-body card-block">
									<form action="/QuanLyDiem/giaovien/exportThiLai" method="post"
										class="form-horizontal">
										<div class="table-data__tool">
											<div class="col col-md-5">
												<label for="select" class=" form-control-label">Môn
													học</label> <select name="id_mon" id="select" class="form-control"
													required>
													<c:forEach items="${monList }" var="monList">
														<option value="${monList.id_mon }">${monList.mamon}-${monList.tenmon }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="card-footer">
											<button type="submit" class="btn btn-primary btn-sm">
												<i class="fa fa-dot-circle-o"></i> Xuất
											</button>
										</div>
									</form>
								</div>
							</div>

							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Nhập điểm thi lần 1
								</h3>
								<div class="card-body card-block">
									<div class="table-data__tool">
										<div class="col col-md-10">
											<form class="form-header"
												action="/QuanLyDiem/giaovien/importl1" method="post"
												enctype="multipart/form-data">
												<input class="" type="file" name="file" />
												<button type="submit" class="btn btn-primary btn-sm">
													<i class="fa fa-dot-circle-o"></i> Nhập
												</button>

											</form>
										</div>
										<div class="col col-md-2">
											<a href="/QuanLyDiem/giaovien/exportmau">File mẫu</a>
										</div>
									</div>
								</div>
							</div>
							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Nhập điểm thi lần 2
								</h3>
								<div class="card-body card-block">
									<div class="table-data__tool">
										<div class="col col-md-10">
											<form class="form-header"
												action="/QuanLyDiem/giaovien/importl2" method="post"
												enctype="multipart/form-data">
												<input class="" type="file" name="file" />
												<button type="submit" class="btn btn-primary btn-sm">
													<i class="fa fa-dot-circle-o"></i> Nhập
												</button>

											</form>
										</div>
										<div class="col col-md-2">
											<a href="/QuanLyDiem/giaovien/exportmau">File mẫu</a>
										</div>
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
