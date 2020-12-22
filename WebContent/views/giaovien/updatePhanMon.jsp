<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
								<c:when test="${param.mess eq 'addsuccess' }">
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
									<i class="zmdi zmdi-account-calendar"></i>Phân môn
								</h3>
								<div class="card-body card-block">
									<form action="/QuanLyDiem/giaovien/updatePM?id_phanmon=${phanMon.id_phanmon }" method="post"
										class="form-horizontal">
										<div class="table-data__tool">
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Lớp</label>
												<select name="lop" id="select" class="form-control" required>
													<c:forEach items="${lopList}" var="lop">
														<option value="${lop.id_lop }"
															<c:if test="${phanMon.lop.id_lop eq lop.id_lop   }">selected</c:if>>${lop.tenlop }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Học
													Phần</label> <select name="mon" id="select" class="form-control"
													required>
													<c:forEach items="${monList}" var="mon">
														<option value="${mon.id_mon }"
															<c:if test="${phanMon.mon.id_mon eq mon.id_mon   }">selected</c:if>>${mon.mamon}-${mon.tenmon }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Giáo
													Viên</label> <select name="giaovien" id="select"
													class="form-control" required>
													<c:forEach items="${gvList}" var="gv">
														<option value="${gv.id_giaovien }"
															<c:if test="${phanMon.giaovien.id_giaovien eq gv.id_giaovien   }">selected</c:if>>${gv.hoten }</option>
													</c:forEach>
												</select>
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
							<!-- USER DATA-->
						</div>
						<!-- END USER DATA-->
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
