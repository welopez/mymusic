/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.resource;

import java.util.List;
import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.service.SongService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lenovo
 */
@Path("/songs")
public class SongResource {
    @Autowired
    private SongService songService;
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongs() {
        List<Song> songs = songService.getSongs();
        return Response.ok(songs).build();
    }
}
