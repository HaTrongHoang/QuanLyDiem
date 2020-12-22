<nav aria-label="Page navigation example">
	<ul class="pagination">
		
		<li class="page-item"><a class="page-link"
			href="/HoaLanVN/admin/user?page=">Previous</a></li>
		<c:forEach items="${pageList}" var="page">
			<li class="page-item"><a class="page-link"
				href="/HoaLanVN/admin/user?page=${page}">${page}</a></li>
		</c:forEach>
		<li class="page-item"><a class="page-link"
			href="/HoaLanVN/admin/user?page=">Next</a></li>
	</ul>
</nav>