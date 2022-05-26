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
    public Playlist createPlaylist(Playlist playlist);
    public Playlist getPlaylist(Long id);
    public Playlist setPlaylistName(String name, Long id);
    public Playlist addSong(Long songId, Long playlistId);
    public Playlist removeSong(Long songId, Long playlistId);
    public void deletePlaylist(Long id);
    
}
