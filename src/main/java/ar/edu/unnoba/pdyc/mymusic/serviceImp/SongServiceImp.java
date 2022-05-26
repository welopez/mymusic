/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.serviceImp;

import ar.edu.unnoba.pdyc.mymusic.model.Genre;
import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.repository.SongRepository;
import ar.edu.unnoba.pdyc.mymusic.service.SongService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class SongServiceImp implements SongService {
    @Autowired
    SongRepository songRepository;

    @Override
    public List<Song> getSongs() {
        return songRepository.findAll();
    }
    
    @Override
    public List<Song> getSongs(String author, String genre) {
        return songRepository.findByAuthorAndGenre(author, Genre.valueOf(genre));
    }
    
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

}
