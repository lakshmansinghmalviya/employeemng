package com.ls.project.config.util;

import java.util.List;
import com.ls.project.model.Employee;
import com.ls.project.response.PageResponse;

public class PageResponseBuilder {

	public static PageResponse<Employee> buildPageResponse(List<Employee> employees, int totalElements, int totalPage,
			int pageNo, int pageSize, boolean lastPage) {
		PageResponse<Employee> pageResponse = new PageResponse<>();
		pageResponse.setContent(employees);
		pageResponse.setTotalElements(totalElements);
		pageResponse.setTotalPages(totalPage);
		pageResponse.setPageSize(pageSize);
		pageResponse.setPageNumber(pageNo);
		pageResponse.setLastPage(lastPage);
		return pageResponse;
	}
}
