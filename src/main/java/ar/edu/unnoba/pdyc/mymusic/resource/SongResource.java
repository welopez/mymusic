/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.MyModelMapper;
import ar.edu.unnoba.pdyc.mymusic.dto.SongDTO;
import java.util.List;
import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.service.SongService;
import java.lang.reflect.Type;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lenovo
 */
@Path("/songs")
public class SongResource {

    @Autowired
    private SongService songService;

    private final MyModelMapper modelMapper = new MyModelMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongs(@QueryParam("author") String author, @QueryParam("genre") String genre) {

        List<Song> songs = songService.getSongs(author, genre);
        Type listType = new TypeToken<List<SongDTO>>() {
        }.getType();
        List<SongDTO> listDTO = modelMapper.map(songs, listType);
        return Response.ok(listDTO).build();
    }
}
