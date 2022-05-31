/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface PlaylistService {

    public List<Playlist> getPlaylists();

    public void createPlaylist(Playlist playlist, String userEmail);

    public Playlist getPlaylist(Long id) throws Exception;

    public void updatePlaylist(Long id, Playlist playlist, String userEmail) throws Exception;

    public void addSong(Long songId, Long playlistId, String userEmail) throws Exception;

    public void removeSong(Long songId, Long playlistId, String userEmail) throws Exception;

    public void deletePlaylist(Long id, String userEmail) throws Exception;

}
