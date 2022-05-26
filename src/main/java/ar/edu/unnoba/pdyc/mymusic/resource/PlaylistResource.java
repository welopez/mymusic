/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.MyModelMapper;
import ar.edu.unnoba.pdyc.mymusic.dto.PlaylistDTO;
import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import ar.edu.unnoba.pdyc.mymusic.service.PlaylistService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lenovo
 */
@Path("/playlists")
public class PlaylistResource {

    @Autowired
    private PlaylistService playlistService;

    private MyModelMapper modelMapper = new MyModelMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists() {

        List<Playlist> playlists = playlistService.getPlaylists();
        return Response.ok(playlists).build();
    }

    @POST
    public Response createPlaylist(PlaylistDTO playlistDTO) {

        Playlist playlist = modelMapper.map(playlistDTO, Playlist.class);
        try {
            playlistService.createPlaylist(playlist);
        } catch (Exception e) {
            return Response.status(Status.FORBIDDEN).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistById(@PathParam("id") Long id) {
        //Agregar Try
        Playlist playlist = playlistService.getPlaylist(id);
        return Response.ok(playlist).build();
    }
}
