package controller.track;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import model.Album;
import model.Track;

import org.junit.Before;
import org.junit.Test;

import service.AlbumEJB;
import service.TrackEJB;
import testutil.FakeFacesContext;
import testutil.fem.FakeEntityManager;
import controller.util.MessagesHelper;

public class TrackControllerTest {

	static TrackController trackController;
	static AlbumEJB albumEJB;
	static TrackEJB trackEJB;
	static FakeEntityManager entityManager;

	@Before
	public void init() {
		trackController = spy(new TrackController());
		albumEJB = spy(new AlbumEJB());
		trackEJB = spy(new TrackEJB());
		entityManager = new FakeEntityManager();

		MessagesHelper.INSTANCE.setFacesContext(spy(new FakeFacesContext()));
		albumEJB.setEm(entityManager);
		trackEJB.setEm(entityManager);
		trackController.setTrackEJB(trackEJB);
		trackController.setAlbumEJB(albumEJB);
	}

	@Test
	public void testGetTracksByAlbumId() throws Exception {
		Album album = spy(new Album());
		when(trackController.getAlbumEJB().find(1)).thenReturn(album);
		trackController.getTracksByAlbumId(1);
		verify(trackController.getAlbumEJB().find(1)).getTracks();
	}

	@Test
	public void testDeleteTrackById() throws Exception {
		trackController.deleteTrackById(1);
		verify(trackController.getTrackEJB()).find(1);
		verify(trackController.getTrackEJB()).delete((Track) any());
	}

	@Test
	public void testGetAll() throws Exception {
		trackController.getAll();
		verify(trackController.getTrackEJB()).findAll();
	}

	@Test
	public void testGetTrackEJB() throws Exception {
		assertNotNull(trackController.getTrackEJB());
	}

	@Test
	public void testGetAlbumEJB() throws Exception {
		assertNotNull(trackController.getAlbumEJB());
	}

}
