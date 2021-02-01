package com.wolox.wchallenge;

import com.wolox.wchallenge.controller.ExternalApiController;
import com.wolox.wchallenge.model.Album;
import com.wolox.wchallenge.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class WChallengeApplicationTests {

	@Autowired
	private ExternalApiController externalApiController;

	@Test
	void contextLoads() {
		assertThat(externalApiController).isNotNull();
	}

	@Test
	void getAlbumsTest() {
		ResponseEntity<List<Album>> albums = externalApiController.getAlbums();
		assertThat(albums).isNotNull();
	}

	@Test
	void getAlbumsByUserTest() {
		ResponseEntity<Object> albumsByUser = externalApiController.getAlbumsByUser("1");
		assertThat(albumsByUser).isNotNull();
	}

	@Test
	void getCommentsTest() {
		ResponseEntity<List<Comment>> comments = externalApiController.getComments(null);
		assertThat(comments).isNotNull();

		ResponseEntity<List<Comment>> commentsWithName = externalApiController.getComments("a");
		assertThat(commentsWithName).isNotNull();

		ResponseEntity<List<Comment>> commentsNoResults = externalApiController.getComments("prueba de contenido que no figura");
		assertThat(commentsNoResults).isNotNull();

		List<Comment> resultsBody = commentsNoResults.getBody();
		assertThat(resultsBody.size()).isEqualTo(0);
	}

}
