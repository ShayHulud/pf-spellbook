package ru.shayhulud.pfspellbook.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor for logging request execution time.
 */
@Slf4j
public class RequestExecutionTimeInterceptor extends HandlerInterceptorAdapter {

	private static final String EXECUTION_START_TIME_REQUEST_PARAM_NAME = "executionStartTime";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute(EXECUTION_START_TIME_REQUEST_PARAM_NAME, startTime);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
		long startTime = (Long) request.getAttribute(EXECUTION_START_TIME_REQUEST_PARAM_NAME);
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		log.info("{} {} <=> {} ms",
			request.getMethod(),
			request.getRequestURI(),
			executeTime
		);
	}
}
