package com.hung.library;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
	public static int totalPage(int totalUser, int limit) {
		int total = 0;
		if (totalUser % limit != 0) {
			total = totalUser / limit + 1;
		} else {
			total = totalUser / limit;
		}
		return total;

	}

	public static int offset(int page, int limit,int totalPage) {
		int offset=0;
		if(page<1) {
			offset =0;
		}
		else if(page > totalPage) {
			offset=((totalPage - 1) * limit);
		}
		else {
			offset = ((page - 1) * limit);
		}
		

		return offset;
	}

	public static List<Integer> listPage(int totalPage) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= totalPage; i++) {
			list.add(i);
		}
		return list;
	}

}
