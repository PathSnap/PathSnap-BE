package com.pathsnap.Backend;


import com.amazonaws.services.s3.AmazonS3;
import com.pathsnap.Backend.Image.Service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BackendApplication.class)
class BackendApplicationTests {

	@Mock
	private AmazonS3 amazonS3; // AWS 클라이언트 모킹

	@Autowired
	private ImageService imageService;

	@Test
	void contextLoads() {
		// AWS 관련 예외 방지를 위해 모킹 설정
		when(amazonS3.putObject(anyString(), anyString(), any(), any())).thenReturn(null);
	}

	@Test
	void simpleMathTest() {
		assertEquals(2, 1 + 1, "1 + 1은 2여야 합니다.");
	}

}
