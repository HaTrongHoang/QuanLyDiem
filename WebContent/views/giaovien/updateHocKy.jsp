<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Forms</title>

<jsp:include page="/views/giaovien/common/linkCss.jsp"></jsp:include>
</head>

<body>

	<!-- HEADER -->
	<jsp:include page="/views/giaovien/common/header.jsp"></jsp:include>
	<!-- MAIN CONTENT-->
	<div class="main-content">
		<div class="section__content section__content--p30">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-11">
						<div class="card">
							<c:choose>
								<c:when test="${param.mess eq 'exist' }">
									<div
										class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
										<span class="badge badge-pill badge-danger">error</span> Học kỳ
										ton tai
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
							</c:choose>
							<div class="card-header">
								<strong>Học kỳ</strong>
							</div>
							<div class="card-body card-block">
								<form
									action="/QuanLyDiem/giaovien/hocky/update?id_hocky=${hockyId.id_hocky }"
									method="post" class="form-horizontal">
									<div class="row form-group">
										<div class="col col-md-3">
											<label for="text-input" class=" form-control-label">Tên học kỳ</label>
										</div>
										<div class="col-12 col-md-9">
											<input type="text" id="text-input" name="tenhocky"
												placeholder="Tên học kỳ" class="form-control"
												value="${hockyId.tenhocky }" required> <small
												class="form-text text-muted">This is a help text</small>
										</div>
									</div>
									<div class="card-footer">
										<button type="submit" class="btn btn-primary btn-sm">
											<i class="fa fa-dot-circle-o"></i> Submit
										</button>
										<button type="reset" class="btn btn-danger btn-sm">
											<i class="fa fa-ban"></i> Reset
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
