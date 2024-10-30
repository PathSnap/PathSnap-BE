package com.pathsnap.Backend;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void simpleMathTest() {
		assertEquals(2, 1 + 1, "1 + 1은 2여야 합니다.");
	}

}
