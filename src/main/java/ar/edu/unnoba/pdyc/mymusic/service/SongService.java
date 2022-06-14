/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Song;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Lenovo
 */
public interface SongService {

    public List<Song> getSongs(String author, String genre);

    public CompletableFuture<List<Song>> getSongsAsync(String author, String genre);
}
