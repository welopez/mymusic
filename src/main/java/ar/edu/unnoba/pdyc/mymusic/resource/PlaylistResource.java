/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.MyModelMapper;
import ar.edu.unnoba.pdyc.mymusic.dto.CreatePlaylistRequestDTO;
import ar.edu.unnoba.pdyc.mymusic.dto.PlaylistResponseDTO;
import ar.edu.unnoba.pdyc.mymusic.dto.SongDTO;
import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import ar.edu.unnoba.pdyc.mymusic.service.PlaylistService;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Lenovo
 */
@Path("/playlists")
public class PlaylistResource {

    @Autowired
    private PlaylistService playlistService;

    private final MyModelMapper modelMapper = new MyModelMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists() {

        List<Playlist> playlists = playlistService.getPlaylists();
        Type listType = new TypeToken<List<PlaylistResponseDTO>>() {
        }.getType();
        List<PlaylistResponseDTO> listDTO = modelMapper.map(playlists, listType);
        return Response.ok(listDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlaylist(CreatePlaylistRequestDTO playlistDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) auth.getPrincipal();

        Playlist playlist = modelMapper.map(playlistDTO, Playlist.class);

        playlistService.createPlaylist(playlist, userEmail);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistById(@PathParam("id") Long id) {

        try {
            Playlist playlist = playlistService.getPlaylist(id);
            Type type = new TypeToken<PlaylistResponseDTO>() {
            }.getType();
            PlaylistResponseDTO playlistDTO = modelMapper.map(playlist, type);
            return Response.ok(playlistDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@PathParam("id") Long id, CreatePlaylistRequestDTO playlistDTO) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = (String) auth.getPrincipal();
            Playlist playlist = modelMapper.map(playlistDTO, Playlist.class);
            playlistService.updatePlaylist(id, playlist, userEmail);
            return Response.ok().build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/songs")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSong(@PathParam("id") Long playlistId, SongDTO song) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = (String) auth.getPrincipal();
            playlistService.addSong(song.getId(), playlistId, userEmail);
            return Response.ok().build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Path("/{id}/songs/{song_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeSong(@PathParam("id") Long playlistId, @PathParam("song_id") Long songId) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = (String) auth.getPrincipal();
            playlistService.removeSong(songId, playlistId, userEmail);
            return Response.ok().build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") Long playlistId) {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = (String) auth.getPrincipal();
            playlistService.deletePlaylist(playlistId, userEmail);
            return Response.ok().build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
