/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic;

import ar.edu.unnoba.pdyc.mymusic.model.Song;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lenovo
 */
@Component
public class MyModelMapper extends ModelMapper {

    public MyModelMapper() {
        setGlobalConfiguration();
    }

    private void setGlobalConfiguration() {
        //aca adentro van los addMappings(name());
        //this.addMappings(DTO());
    }

    // Mapeo
    
}


