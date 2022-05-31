/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.MyModelMapper;
import ar.edu.unnoba.pdyc.mymusic.dto.AuthenticationRequestDTO;
import ar.edu.unnoba.pdyc.mymusic.model.User;
import ar.edu.unnoba.pdyc.mymusic.service.UserService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lenovo
 */
@Path("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    private MyModelMapper modelMapper = new MyModelMapper();

    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response singUpUser(AuthenticationRequestDTO authenticationRequestDTO) {
        User user = modelMapper.map(authenticationRequestDTO, User.class);
        try {
            userService.create(user);
        } catch (Exception e) {
            return Response.status(Status.FORBIDDEN).build();
        }
        return Response.ok().build();
    }
    
}
