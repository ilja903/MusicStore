package testutil;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import service.AlbumEJB;
import service.ArtistEJB;
import service.ProductEJB;
import service.StoreEJB;
import service.TrackEJB;
import service.UserEJB;
import service.abstractions.AbstractFacade;
import service.authentication.AuthenticationEJB;
import testutil.fakeentity.FakeEntity;
import testutil.fakeentity.FakeEntityEJB;

public class IntegrationTestBase {
	protected final static AbstractFacade<FakeEntity> abstractFacade = spy(new FakeEntityEJB());
	protected final static AlbumEJB albumEJB = spy(new AlbumEJB());
	protected final static ArtistEJB artistEJB = spy(new ArtistEJB());
	protected final static ProductEJB productEJB = spy(new ProductEJB());
	protected final static StoreEJB storeEJB = spy(new StoreEJB());
	protected final static TrackEJB trackEJB = spy(new TrackEJB());
	protected final static UserEJB userEJB = spy(new UserEJB());
	protected final static AuthenticationEJB authenticationEJB = spy(new AuthenticationEJB());

	protected static final EntityManager fakeEm = Persistence
			.createEntityManagerFactory("TEST").createEntityManager();

	@BeforeClass
	public static void initOnceBeforeClass() {
		when(abstractFacade.getEm()).thenReturn(fakeEm);
		when(albumEJB.getEm()).thenReturn(fakeEm);
		when(artistEJB.getEm()).thenReturn(fakeEm);
		when(productEJB.getEm()).thenReturn(fakeEm);
		when(storeEJB.getEm()).thenReturn(fakeEm);
		when(trackEJB.getEm()).thenReturn(fakeEm);
		when(userEJB.getEm()).thenReturn(fakeEm);

		when(albumEJB.getProductEJB()).thenReturn(productEJB);
		when(authenticationEJB.getUserEJB()).thenReturn(userEJB);
	}

	@Before
	public void initBeforeEachTest() {
		fakeEm.getTransaction().begin();
	}

	@After
	public void initAfterEachTest() {
		fakeEm.getTransaction().rollback();
	}

}