package com.ls.project.config.util;

import java.util.List;

import com.ls.project.model.Employee;
import com.ls.project.response.PageResponse;

public class PageResponseBuilder {

	public static PageResponse<Employee> buildPageResponse(List<Employee> employees, int totalRecords, int totalPages,
			int pageNo, int pageSize) {
		PageResponse<Employee> pageResponse = new PageResponse<>();
		pageResponse.setContent(employees);
		pageResponse.setTotalElements(totalRecords);
		pageResponse.setTotalPages(totalPages);
		pageResponse.setPageSize(pageSize);
		pageResponse.setPageNumber(pageNo);
		return pageResponse;
	}
}
