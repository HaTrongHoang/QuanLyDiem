package com.hung.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.model.GiaoVien;

/**
 * Servlet Filter implementation class Login
 */
@WebFilter("/giaovien/*")
public class Login implements Filter {

	/**
	 * Default constructor.
	 */
	public Login() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		GiaoVien gv = (GiaoVien) session.getAttribute("loginGiaoVien");
		if (gv != null) {
			if (gv.getRole() == 0) {
				req.setAttribute("disabledAdmin", "disabled");
				chain.doFilter(req, resp);
				
			} else if (gv.getRole() == 1) {
				req.setAttribute("disabledMember", "disabled");
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/login-gv");
			}

		} else {
			resp.sendRedirect(req.getContextPath() + "/login-gv");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
