/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic;

import ar.edu.unnoba.pdyc.mymusic.resource.SongResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lenovo
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig()
    {
        register(SongResource.class);
        //register(PlaylistResource.class);
        //register(UserResource.class);
    }
}
