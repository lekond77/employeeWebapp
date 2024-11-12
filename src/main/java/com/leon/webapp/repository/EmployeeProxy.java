package com.leon.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.leon.webapp.CustomPropreties;
import com.leon.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeProxy {

	@Autowired
	private CustomPropreties props;

	@Autowired
	RestTemplate restTemplate;

	public Iterable<Employee> getEmployees() {
		String baseApiUrl = props.getApiUrl();
		String getEmployeesUrl = baseApiUrl + "/employees";

		try {

			ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(getEmployeesUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<Iterable<Employee>>() {
					});

			return response.getBody();
		} catch (Exception e) {
			return null;

		}
	}

	public Employee createEmployee(Employee employee) {

		String baseApiUrl = props.getApiUrl();
		String createEmpoyeeUrl = baseApiUrl + "/employee";

		try {

			HttpEntity<Employee> request = new HttpEntity<Employee>(employee);

			ResponseEntity<Employee> response = restTemplate.exchange(createEmpoyeeUrl, HttpMethod.POST, request,
					Employee.class);

			return response.getBody();
		} catch (Exception e) {
			return null;
		}

	}

	public Employee getEmployee(final int id) {

		String baseApiUrl = props.getApiUrl();
		String getEmpoyeeUrl = baseApiUrl + "/employee/" + id;

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Employee> response = restTemplate.exchange(getEmpoyeeUrl, HttpMethod.GET, null, Employee.class);

		return response.getBody();
	}

	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		String baseApiUrl = props.getApiUrl();
		String deleteEmpoyeeUrl = baseApiUrl + "/employee/" + id;

		restTemplate.exchange(deleteEmpoyeeUrl, HttpMethod.DELETE, null, Employee.class);

	}

	public Employee updateEmployee(Employee employee) {
		String baseApiUrl = props.getApiUrl();
		String updateEmpoyeeUrl = baseApiUrl + "/employee/" + employee.getId();

		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);

		ResponseEntity<Employee> response = restTemplate.exchange(updateEmpoyeeUrl, HttpMethod.PUT, request,
				Employee.class);

		return response.getBody();
	}

}
