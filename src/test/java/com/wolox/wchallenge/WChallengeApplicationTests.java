package com.wolox.wchallenge;

import com.wolox.wchallenge.controller.ExternalApiController;
import com.wolox.wchallenge.controller.SharedAlbumController;
import com.wolox.wchallenge.model.*;
import com.wolox.wchallenge.repository.SharedAlbumRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
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
	@Autowired
	private SharedAlbumController sharedAlbumController;
	@Autowired
	private SharedAlbumRepository sharedAlbumRepository;

	public void setUp() {
		sharedAlbumRepository.save(new SharedAlbum("1", "1", Permission.READ));
		sharedAlbumRepository.save(new SharedAlbum("2", "1", Permission.READ_AND_WRITE));
		sharedAlbumRepository.save(new SharedAlbum("3", "1", Permission.READ));
		sharedAlbumRepository.save(new SharedAlbum("1", "2", Permission.READ));
	}

	@Test
	void contextLoads() {
		assertThat(externalApiController).isNotNull();
		assertThat(sharedAlbumRepository).isNotNull();
	}

	@Test
	void getUsersTest() {
		setUp();

		ResponseEntity<List<User>> users = externalApiController.getUsers();
		assertThat(users).isNotNull();

		ResponseEntity<List<User>> userSharedAlbumRead = externalApiController.getUsers("1", Permission.READ.name());
		assertThat(userSharedAlbumRead).isNotNull();
		List<User> userList = userSharedAlbumRead.getBody();
		assertThat(userList.size() > 0).isEqualTo(true);
	}

	@Test
	void getAlbumsByUserTest() {
		ResponseEntity<Object> albumsByUser = externalApiController.getAlbumsByUser("1");
		assertThat(albumsByUser).isNotNull();
	}

	@Test
	void getPhotosByUserTest() {
		ResponseEntity<Object> objectResponseEntity = externalApiController.getPhotosByUser("1");
		assertThat(objectResponseEntity).isNotNull();
	}

	@Test
	void getPhotosTest() {
		ResponseEntity<List<Photo>> photos = externalApiController.getPhotos();
		assertThat(photos).isNotNull();
	}

	@Test
	void getAlbumsTest() {
		ResponseEntity<List<Album>> albums = externalApiController.getAlbums();
		assertThat(albums).isNotNull();
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

	@Test
	void getSharedAlbums() {
		ResponseEntity<List<SharedAlbum>> sharedAlbums = sharedAlbumController.getSharedAlbums();
		assertThat(sharedAlbums).isNotNull();
	}

	@Test
	void getPostSharedAlbums() {
		ResponseEntity<SharedAlbum> sharedAlbumResponseEntity = sharedAlbumController.postSharedAlbums(new SharedAlbum("2", "2", Permission.READ_AND_WRITE));
		assertThat(sharedAlbumResponseEntity.getBody()).isNotNull();
		assertThat(sharedAlbumResponseEntity.getBody().getId()).isNotNull();
	}

	@Test
	void getPutSharedAlbums() {
		ResponseEntity<SharedAlbum> sharedAlbumEntity = sharedAlbumController.postSharedAlbums(new SharedAlbum("3", "3", Permission.READ_AND_WRITE));
		SharedAlbum sharedAlbumEntityBody = sharedAlbumEntity.getBody();
		assertThat(sharedAlbumEntityBody).isNotNull();
		assertThat(sharedAlbumEntityBody.getPermission().equals(Permission.READ_AND_WRITE)).isEqualTo(true);

		ResponseEntity<SharedAlbum> sharedAlbumResponseEntity = sharedAlbumController.putSharedAlbums(new SharedAlbum("3", "3", Permission.READ));
		SharedAlbum sharedAlbumResponseEntityBody = sharedAlbumResponseEntity.getBody();
		assertThat(sharedAlbumResponseEntityBody.getPermission().equals(Permission.READ)).isEqualTo(true);
	}

}
