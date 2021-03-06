package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import model.Artist;
import model.Track;
import service.abstractions.AbstractFacade;

/**
 * @author ilja
 * 
 */
@Stateless
@LocalBean
@Named
public class TrackEJB extends AbstractFacade<Track> {

	public TrackEJB() {
		super(Track.class);
	}

	public Set<Track> getTracksByArtists(Collection<Artist> artists) {
		Set<Track> tracks = new HashSet<>();
		for (Artist artist : artists) {
			tracks.addAll(getTracksByArtist(artist));
		}
		return tracks;
	}

	private List<Track> getTracksByArtist(Artist artist) {
		Artist syncronizedArtist = getEm().find(Artist.class, artist.getId());
		return new ArrayList<>(syncronizedArtist.getTracks());
	}

}
