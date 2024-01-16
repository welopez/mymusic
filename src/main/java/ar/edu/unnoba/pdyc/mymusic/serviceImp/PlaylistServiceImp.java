/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.serviceImp;

import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.model.User;
import ar.edu.unnoba.pdyc.mymusic.repository.PlaylistRepository;
import ar.edu.unnoba.pdyc.mymusic.repository.SongRepository;
import ar.edu.unnoba.pdyc.mymusic.repository.UserRepository;
import ar.edu.unnoba.pdyc.mymusic.service.PlaylistService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class PlaylistServiceImp implements PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public List<Playlist> getPlaylists() {
        return playlistRepository.findAll();
    }
    
    @Override
    public CompletableFuture<List<Playlist>> getPlaylistsAsync() {
        Sort sort = Sort.by(Sort.Order.asc("name")); // Orden ascendente por el campo 'name'
        return CompletableFuture.completedFuture(playlistRepository.findAll(sort));
    }

    @Override
    public Playlist getPlaylist(Long id) {
        return playlistRepository.findById(id).get();
    }

    @Override
    public Playlist createPlaylist(Playlist playlist, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        playlist.setUser(user);
        return playlistRepository.save(playlist);
    }

    @Override
    public void updatePlaylist(Long id, Playlist playlist, String userEmail) throws Exception {

        User user = userRepository.findByEmail(userEmail);
        Playlist playlistDB = playlistRepository.findById(id).get();
        if (playlistDB != null) {
            if (playlistDB.getUser().equals(user)) {
                playlistDB.setName(playlist.getName());
                playlistRepository.save(playlistDB);
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void addSong(Long songId, Long playlistId, String userEmail) {

        User user = userRepository.findByEmail(userEmail);
        Playlist playlistDB = playlistRepository.findById(playlistId).get();
        Song songDB = songRepository.findById(songId).get();
        if (playlistDB != null && songDB != null) {
            // Si el usuario es el dueño del playlist y la playlist no contiene a la cancion, esta se agrega a la playlist.
            if (!playlistDB.getUser().equals(user)) {
                throw new ForbiddenException("Acceso denegado.");
            }else if(playlistDB.getSongs().contains(songDB)){
                throw new ForbiddenException("La cancion ya se encuentra en la playlist.");            
            } else {
                playlistDB.getSongs().add(songDB);
                playlistRepository.save(playlistDB);
            }
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void removeSong(Long songId, Long playlistId, String userEmail) {

        User user = userRepository.findByEmail(userEmail);
        Playlist playlistDB = playlistRepository.findById(playlistId).get();
        Song songDB = songRepository.findById(songId).get();
        if (playlistDB != null && songDB != null) {
            // Si el usuario es el dueño del playlist y la playlist contiene a la cancion, la elimino.
            if (playlistDB.getUser().equals(user) && playlistDB.getSongs().contains(songDB)) {
                playlistDB.getSongs().remove(songDB);
                playlistRepository.save(playlistDB);
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void deletePlaylist(Long id, String userEmail) {

        User user = userRepository.findByEmail(userEmail);
        Playlist playlistDB = playlistRepository.findById(id).get();
        if (playlistDB != null) {
            if (playlistDB.getUser().equals(user)) {
                playlistRepository.delete(playlistDB);
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new NotFoundException();
        }
    }

}
