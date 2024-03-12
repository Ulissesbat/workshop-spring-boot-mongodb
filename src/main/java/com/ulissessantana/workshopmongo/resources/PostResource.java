package com.ulissessantana.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ulissessantana.workshopmongo.domain.Post;
import com.ulissessantana.workshopmongo.resources.util.URL;
import com.ulissessantana.workshopmongo.services.PostServices;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostServices service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);    
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


    @RequestMapping(value="/titlesearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/fullsearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue="") String text,
            @RequestParam(value="minDate", defaultValue="") String minDateStr,
            @RequestParam(value="maxDate", defaultValue="") String maxDateStr) {
        text = URL.decodeParam(text);
        Date minDate = URL.convertDate(minDateStr, new Date(0L));
        Date maxDate = URL.convertDate(maxDateStr, new Date());
        List<Post> list = service.fullSearch(text, minDate, maxDate);
        return ResponseEntity.ok().body(list);
    }
}
