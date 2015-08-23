package com.example.http.client

import org.springframework.web.client.RestTemplate

object Main {

	def main(args: Array[String]) :Unit = {
		val rest = new RestTemplate()
		val res = rest.getForObject("http://gturnquist-quoters.cfapps.io/api/random", classOf[String])
		println(res)

		val res2 = rest.postForObject("http://gturnquist-quoters.cfapps.io/api/random", res, classOf[String])
		println(res2)
//		val requestHeaders=new HttpHeaders()
//		requestHeaders.setAccept(util.Arrays.asList(MediaType.APPLICATION_JSON))
//		requestHeaders.setContentType(MediaType.APPLICATION_JSON)
//		val requestEntity = new HttpEntity[AnyVal](requestHeaders)
//		val uri = "http://localhost:8080/springrestexample/employees.xml"
//		val result = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
//

	}

}